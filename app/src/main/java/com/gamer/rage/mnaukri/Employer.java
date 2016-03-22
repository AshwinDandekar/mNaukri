package com.gamer.rage.mnaukri;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by asd29 on 3/8/2016.
 */
public class Employer implements Serializable {

    private String name;
    private String phno;
    private String jobtitle;
    private String orgname;
    private String location;
    private String duration;
    private String gender;
    private String age;
    private String specneeds;
    private String jobinfo;
    private String vacancy;

    Employer(String name,String phno,String orgname,String jobtitle,String location,String duration,String gender,String age,String specneeds,String jobinfo,String vacancy) {
        this.name = name;
        this.phno = phno;
        this.jobtitle = jobtitle;
        this.orgname=orgname;
        this.location=location;
        this.gender=gender;
        this.duration=duration;
        this.age= age;
        this.specneeds= specneeds;
        this.jobinfo= jobinfo;
        this.vacancy= vacancy;
    }
    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSpecneeds() {
        return specneeds;
    }

    public void setSpecneeds(String specneeds) {
        this.specneeds = specneeds;
    }

    public String getJobinfo() {
        return jobinfo;
    }

    public void setJobinfo(String jobinfo) {
        this.jobinfo = jobinfo;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
