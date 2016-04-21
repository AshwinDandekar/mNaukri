package com.gamer.rage.mnaukri;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String selectedObj = "EmployerObj";
    ContentAdapter contentAdapter;
    ListView listView;
    ArrayList<Employer> employerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        contentAdapter = new ContentAdapter(this,R.id.customitem);
        employerList = (ArrayList<Employer>) getIntent().getExtras().getSerializable(ScrollingActivity.Listname);
        for(int i=0;i<employerList.size();i++) {
            contentAdapter.add(employerList.get(i));
        }
        listView.setAdapter(contentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employer employer = (Employer) listView.getItemAtPosition(position);
               Intent intent = new Intent(getApplicationContext(),DisplayItemActivity.class);
                intent.putExtra(selectedObj,employer);
                startActivity(intent);
            }
        });
    }
}
