/*
@develper note:
This code was written by Ashwin
 */






package com.gamer.rage.mnaukri;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuItem;
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

public class ScrollingActivity extends AppCompatActivity {

    public static ArrayList<Employer> employerList;
    static HttpURLConnection httpcon2;
    public static String Listname = "Employers";
    static String Json_response;
    static String adminlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateView();
    }

    public void updateView() {
        adminlocation = getIntent().getExtras().getString(MainSelectionActivity.user);
        employerList = new ArrayList<Employer>();
        Toast.makeText(this,"Loading available jobs",Toast.LENGTH_LONG).show();
        try {
            new JSONfetcher().execute().get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(Listname,employerList);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }


    static class JSONfetcher extends AsyncTask<Void,Void,Void> {


        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://www.mnaukri.co.nf/viewall.php";
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {


                URL url = new URL(json_url);
                httpcon2 = (HttpURLConnection) url.openConnection();
                httpcon2.setRequestMethod("POST");
                httpcon2.setDoOutput(true);
                httpcon2.setDoInput(true);
                String joblocation = URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(adminlocation,"UTF-8");
                OutputStreamWriter wr = new OutputStreamWriter(httpcon2.getOutputStream());
                wr.write(joblocation);
                wr.flush();
                InputStreamReader inputStream = new InputStreamReader(httpcon2.getInputStream());
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
                    String name = object.getString("name");
                    String phno = object.getString("phno");
                    String orgname = object.getString("orgname");
                    String jobtitle = object.getString("jobtitle");
                    String location = object.getString("location");
                    String duration = object.getString("duration");
                    String gender = object.getString("gender");
                    String age = object.getString("age");
                    String specneeds = object.getString("specneeds");
                    String jobinfo = object.getString("jobinfo");
                    String vacancy = object.getString("vacancy");
                    Employer emp= new Employer(name,phno,orgname,jobtitle,location,duration,gender,age,specneeds,jobinfo,vacancy);
                    employerList.add(emp);
                }
            }
            catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e2) {
                e2.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                httpcon2.disconnect();
            }
            return null;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
