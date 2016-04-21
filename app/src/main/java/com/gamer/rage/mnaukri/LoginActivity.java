package com.gamer.rage.mnaukri;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private String enteredusername;
    private String enteredpassword;
    private HttpURLConnection httpcon;
    String location;
    public static final String loginuserlocation = "loggedinuser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }

    class loginfetcher extends AsyncTask<Void,Void,ArrayList<String>> {

        ArrayList<String> userinfo = new ArrayList<String>();
        String userinfourl;
        String Json_response;

        @Override
        protected void onPreExecute() {
            userinfourl = "http://www.mnaukri.co.nf/trylogin.php";
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                String posteddata = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(enteredusername,"UTF-8")+"&";
                posteddata+= URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(enteredpassword,"UTF-8");
                URL url = new URL(userinfourl);
                httpcon = (HttpURLConnection)url.openConnection();
                httpcon.setRequestMethod("POST");
               httpcon.setDoOutput(true);
               httpcon.setDoInput(true);
                OutputStreamWriter wr = new OutputStreamWriter(httpcon.getOutputStream());
                wr.write(posteddata);
                wr.flush();
                InputStreamReader inputStream = new InputStreamReader(httpcon.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                StringBuilder stringBuilder = new StringBuilder();
                while((Json_response = bufferedReader.readLine())!=null) {
                    stringBuilder.append(Json_response+"\n");
                }
                Json_response = stringBuilder.toString().trim();
                JSONObject obj = new JSONObject(Json_response);
                JSONArray jsonArray = obj.getJSONArray("db_response");
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String username = object.getString("username");
                    String password = object.getString("password");
                    location = object.getString("location");
                    userinfo.add(username);
                    userinfo.add(password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userinfo;
        }
    }

    public void onLoginClick(View view) {

        enteredpassword = password.getText().toString();
        enteredusername = username.getText().toString();

        try {
            ArrayList<String> userinfo = new loginfetcher().execute().get(10, TimeUnit.SECONDS);
            if(userinfo == null || userinfo.size()==0) {
                Toast.makeText(this,"Invalid Username or password",Toast.LENGTH_LONG).show();
            }
          else if(userinfo.get(0).equals(username.getText().toString()) && userinfo.get(1).equals(password.getText().toString())) {
                Toast.makeText(this,"Welcome "+username.getText().toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MainSelectionActivity.class);
              intent.putExtra(loginuserlocation,location);
              startActivity(intent);
          } else {
                Toast.makeText(this,"Invalid username or password! try again",Toast.LENGTH_LONG).show();
          }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
