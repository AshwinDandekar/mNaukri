package com.gamer.rage.mnaukri;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DisplayItemActivity extends AppCompatActivity {

    TextView name,phno,orgname,vacancy,location,duration,age,specneeds,jobinfo,jobtitle,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView)findViewById(R.id.name);
        phno = (TextView)findViewById(R.id.phno);
        orgname = (TextView)findViewById(R.id.orgname);
        vacancy = (TextView)findViewById(R.id.vacancy);
        location = (TextView)findViewById(R.id.location);
        duration = (TextView)findViewById(R.id. duration);
        age = (TextView)findViewById(R.id.age);
        specneeds = (TextView)findViewById(R.id.specneeds);
       jobinfo = (TextView)findViewById(R.id.jobinfo);
        jobtitle = (TextView)findViewById(R.id.jobtitle);
        gender = (TextView)findViewById(R.id.gender);

        Employer employer = (Employer) getIntent().getExtras().get(MainActivity.selectedObj);
        if(employer.getName().equals("null")) {
            name.setText("N/A");
        }
        else {
            name.setText(employer.getName());
        }
        phno.setText(employer.getPhno());
        orgname.setText(employer.getOrgname());
        vacancy.setText(employer.getVacancy());
        location.setText(employer.getLocation());
        duration.setText(employer.getDuration());
        jobtitle.setText(employer.getJobtitle());
        jobinfo.setText(employer.getJobinfo());
        age.setText(employer.getAge());
        specneeds.setText(employer.getSpecneeds());
        if(employer.getGender().equals("F")) {
            gender.setText("Women Only");
        }
        else if(employer.getGender().equals("M")) {
            gender.setText("Men Only");
        }
        else if(employer.getGender().equals("F/M")){
            gender.setText("No restriction");
        }
    }

}
