package com.gamer.rage.mnaukri;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

public class JobSeekerEntryActivity extends AppCompatActivity {

    private String seekerLocation;
    private String seekerName;
    private Integer seekerPhno;
    private String seekerSkills;
    private String seekerGender;
    private Integer seekerAge;
    private String seekerPref;
    private Integer seekerDisability;
    private HttpURLConnection httpURLConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_entry);
    }

    public void checkForm(View view) {

        EditText textName;
        EditText textAge;
        EditText textPhno;
        EditText textLocation;
        EditText textSkills;
        textName = (EditText)findViewById(R.id.EnteredName);
        textAge = (EditText)findViewById(R.id.EnteredAge);
        textPhno = (EditText)findViewById(R.id.EnteredPhno);
        textLocation = (EditText)findViewById(R.id.EnteredLocation);
        textSkills = (EditText)findViewById(R.id.EnteredSkills);

        if((((RadioGroup)findViewById(R.id.gendergroup)).getCheckedRadioButtonId() == -1 )||
                ((RadioGroup)findViewById(R.id.disabilitygroup)).getCheckedRadioButtonId()== -1 ||
                ((RadioGroup)findViewById(R.id.smartphonegroup)).getCheckedRadioButtonId()== -1) {
            Toast.makeText(this,"Please fill out all the information",Toast.LENGTH_LONG).show();
            return;
        } else if(textName.getText().toString().equals("") || textAge.getText().toString().equals("") || textPhno.getText().toString().equals("") ||
                textLocation.getText().toString().equals("") || textSkills.getText().toString().equals("")) {
            Toast.makeText(this,"Please fill out all the information",Toast.LENGTH_LONG).show();
            return;
        } else if(!((CheckBox)findViewById(R.id.checkBoxLT)).isChecked() &&
                !((CheckBox)findViewById(R.id.checkBoxST)).isChecked() &&
                !((CheckBox)findViewById(R.id.checkBoxNoPref)).isChecked()) {
            Toast.makeText(this,"Please fill out all the information",Toast.LENGTH_LONG).show();
            return;
        } else {
            addData();
        }
    }

    public void addData() {
        seekerName = ((EditText)findViewById(R.id.EnteredName)).getText().toString();
        seekerLocation = ((EditText)findViewById(R.id.EnteredLocation)).getText().toString();
        seekerSkills =  ((EditText)findViewById(R.id.EnteredSkills)).getText().toString();
        seekerAge = Integer.parseInt(((EditText) findViewById(R.id.EnteredAge)).getText().toString());
        seekerPhno = Integer.parseInt(((EditText) findViewById(R.id.EnteredPhno)).getText().toString());

        if(((RadioButton)findViewById(R.id.radioButtonDisabilityN)).isChecked()){
            seekerDisability = 0;
        } else if (((RadioButton)findViewById(R.id.radioButtonDisabilityY)).isChecked()) {
            seekerDisability = 1;
        }

        if(((CheckBox)findViewById(R.id.checkBoxLT)).isChecked() && !((CheckBox)findViewById(R.id.checkBoxST)).isChecked()) {
            seekerPref = "L";
        } else if(!((CheckBox)findViewById(R.id.checkBoxLT)).isChecked() && ((CheckBox)findViewById(R.id.checkBoxST)).isChecked()) {
            seekerPref = "S";
        }
        else {
            seekerPref = "N";
        }

        if(((RadioButton)findViewById(R.id.radioButtonM)).isChecked()) {
            seekerGender = "M";
        } else if (((RadioButton)findViewById(R.id.radioButtonF)).isChecked()) {
            seekerGender = "F";
        } else if(((RadioButton)findViewById(R.id.radioButtonNA)).isChecked()) {
            seekerGender = "N/A";
        }
        try {
            new addDatatoDB().execute().get(5, TimeUnit.SECONDS);
        }
        catch(Exception e) {

        }
    }

    public class addDatatoDB extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String urltoDB = "http://www.mnaukri.co.nf/insertdata.php";
            try {
                URL url = new URL(urltoDB);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                String postdata = URLEncoder.encode("seekerName","UTF-8")+"="+URLEncoder.encode(seekerName,"UTF-8")+"&";
                postdata += URLEncoder.encode("seekerAge","UTF-8")+"="+URLEncoder.encode(seekerAge.toString(),"UTF-8")+"&";
                postdata += URLEncoder.encode("seekerPref","UTF-8")+"="+URLEncoder.encode(seekerPref,"UTF-8")+"&";
                postdata += URLEncoder.encode("seekerLocation","UTF-8")+"="+URLEncoder.encode(seekerLocation,"UTF-8")+"&";
                postdata += URLEncoder.encode("seekerDisability","UTF-8")+"="+URLEncoder.encode(seekerDisability.toString(),"UTF-8")+"&";
                postdata += URLEncoder.encode("seekerphno","UTF-8")+"="+URLEncoder.encode(seekerPhno.toString(),"UTF-8")+"&";
                postdata += URLEncoder.encode("seekerSkills","UTF-8")+"="+URLEncoder.encode(seekerSkills,"UTF-8")+"&";
                postdata += URLEncoder.encode("seekerGender","UTF-8")+"="+URLEncoder.encode(seekerGender,"UTF-8");

                OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
                wr.write(postdata);
                wr.flush();
                InputStreamReader inputStream = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                String json = bufferedReader.readLine();
                if(json.trim().equals("success")) {
                    Toast.makeText(getApplicationContext(),"Successfully entered job seeker in the database",Toast.LENGTH_LONG).show();
                    //  Intent intent = new Intent(getApplicationContext(),SuccessPage.class);
                    //  startActivity(intent);
                } else if(json.trim().equals("Error")) {
                    Toast.makeText(getApplicationContext(),"There was an ERROR while inserting seeker data",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

