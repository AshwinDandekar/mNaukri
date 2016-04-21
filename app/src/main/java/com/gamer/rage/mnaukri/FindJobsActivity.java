package com.gamer.rage.mnaukri;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FindJobsActivity extends AppCompatActivity {

    ListView listView;
    ContentAdapterJobSeeker contentAdapterJobSeeker;
    ContentAdapter contentAdapter;
    static ArrayList<Employer> employers;
    static ArrayList<JobSeeker> jobSeekers;
    String location;
    HttpURLConnection httpURLConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_jobs);
        jobSeekers = new ArrayList<JobSeeker>();
        listView = (ListView)findViewById(R.id.listviewjobseeker);
        contentAdapterJobSeeker = new ContentAdapterJobSeeker(this,R.id.customjobseeker);
        contentAdapter = new ContentAdapter(this,R.id.customitem);
        listView.setAdapter(contentAdapterJobSeeker);
        location = getIntent().getExtras().getString(MainSelectionActivity.user);
    }

    public void populateFragmentWithJobs(View view) {
        try {
            ScrollingActivity.employerList = new ArrayList<Employer>();
            employers=ScrollingActivity.employerList;
            employers.clear();
            contentAdapter.clear();
            ScrollingActivity.adminlocation = getIntent().getExtras().getString(MainSelectionActivity.user);
            new ScrollingActivity.JSONfetcher().execute().get(20, TimeUnit.SECONDS);
            for(int i=0;i<employers.size();i++){
                contentAdapter.add(employers.get(i));
            }
            listView.setAdapter(contentAdapter);
            contentAdapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Employer employer = (Employer) listView.getItemAtPosition(position);
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void populateFragmentWithSeekers(View view) {
        try {
            jobSeekers.clear();
            contentAdapterJobSeeker.clear();
            new getseekers().execute().get(20, TimeUnit.SECONDS);
            for(int i=0;i<jobSeekers.size();i++) {
                contentAdapterJobSeeker.add(jobSeekers.get(i));
            }
            listView.setAdapter(contentAdapterJobSeeker);
           // contentAdapterJobSeeker.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    JobSeeker jobSeeker = (JobSeeker) listView.getItemAtPosition(position);
                }
            });
        }
        catch (Exception e) {

        }
    }

    public class getseekers extends AsyncTask<Void,Void,Void> {

        String jsonresp;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String seekerphp = "http://www.mnaukri.co.nf/viewseekers.php";
                URL url = new URL(seekerphp);
                String postData = URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
                wr.write(postData);
                wr.flush();
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                while((jsonresp = reader.readLine())!= null) {
                   builder.append(jsonresp+"/n");
                }
                jsonresp = builder.toString().trim();
                JSONObject jsonObject = new JSONObject(jsonresp);
                JSONArray jsonArray = jsonObject.getJSONArray("db_response");
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String name = obj.getString("name");
                    String age = obj.getString("age");
                    String pref = obj.getString("preference");
                    String location = obj.getString("location");
                    String disability = obj.getString("disability");
                    String phno = obj.getString("phno");
                    String skills = obj.getString("skills");
                    String gender = obj.getString("gender");
                    JobSeeker seeker = new JobSeeker(name,location,phno,age,gender,skills,pref,disability);
                    jobSeekers.add(seeker);
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                httpURLConnection.disconnect();
            }
            return null;
        }
    }
}
