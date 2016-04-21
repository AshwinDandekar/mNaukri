package com.gamer.rage.mnaukri;

/**
 * Created by asd29 on 4/19/2016.
 */
public class JobSeeker {
    private String name;

    private String location;
    private String phno;
    private String Age;
    private String gender;
    private String skills;
    private String preference;
    private String disability;


    public JobSeeker(String name, String location, String phno, String age, String gender, String skills, String preference, String disability) {
        this.name = name;
        this.location = location;
        this.phno = phno;
        Age = age;
        this.gender = gender;
        this.skills = skills;
        this.preference = preference;
        this.disability = disability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

}
