package com.gamer.rage.mnaukri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainSelectionActivity extends AppCompatActivity {

    static final String user = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selection);

    }

    public void onAllJobsClicked(View view) {
    Intent intent = new Intent(getApplicationContext(),FindJobsActivity.class);
        String location = getIntent().getExtras().getString(LoginActivity.loginuserlocation);
        intent.putExtra(user,location);
        startActivity(intent);
    }

    public void onAddSeekerClicked(View view) {
        Intent intent = new Intent(getApplicationContext(),JobSeekerEntryActivity.class);
        startActivity(intent);
    }

    public void onTrainingClicked(View view) {
        Toast.makeText(this,"Training is currently under development",Toast.LENGTH_LONG).show();
    }
}
