package com.gamer.rage.mnaukri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd29 on 4/19/2016.
 */
public class ContentAdapterJobSeeker extends ArrayAdapter {
    List list = new ArrayList<>();

    public ContentAdapterJobSeeker(Context context, int resource) {
        super(context, resource);
    }

public void clear() {
    if(!list.isEmpty()) {
        list.clear();
    }
}
    public void add(JobSeeker object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        v = convertView;
        ContactHolder holder;
        if(v == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.customjobseeker,parent,false);
            holder = new ContactHolder();
            holder.name = (TextView)v.findViewById(R.id.listname);
            holder.phno = (TextView)v.findViewById(R.id.listphno);
            holder.location = (TextView)v.findViewById(R.id.listlocation);
            holder.gender = (TextView)v.findViewById(R.id.listgender);
            v.setTag(holder);
        }
        else {
            holder = (ContactHolder) v.getTag();
        }
        JobSeeker jobseeker = (JobSeeker)this.getItem(position);
        holder.name.setText(jobseeker.getName());
        holder.location.setText(jobseeker.getLocation());
        holder.gender.setText(jobseeker.getGender());
        holder.phno.setText(jobseeker.getPhno());
        return v;
    }

    static class ContactHolder {
        TextView name,location,phno,gender;
    }
}
