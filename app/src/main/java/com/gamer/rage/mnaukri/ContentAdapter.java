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
 * Created by asd29 on 3/8/2016.
 */
public class ContentAdapter extends ArrayAdapter {

    List list = new ArrayList<>();

    public ContentAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Employer object) {
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
            v = layoutInflater.inflate(R.layout.customitem,parent,false);
            holder = new ContactHolder();
            holder.title = (TextView)v.findViewById(R.id.jobtitle);
            holder.location = (TextView)v.findViewById(R.id.location);
            v.setTag(holder);
        }
        else {
            holder = (ContactHolder) v.getTag();
        }
        Employer employer = (Employer)this.getItem(position);
        holder.title.setText(employer.getJobtitle());
        holder.location.setText(employer.getLocation());
        return v;
    }

    static class ContactHolder {
        TextView title,location;
    }
}
