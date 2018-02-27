package com.aust.austpc.austpcbeta6.database;

/**
 * Created by USER on 2/3/2018.
 */

public class MemberDesc {
    private String name, roll, phone, year, sem,email;

    public MemberDesc(String name, String roll, String phone, String year, String sem,String email) {
        this.name = name;
        this.roll = roll;
        this.phone = phone;
        this.year = year;
        this.sem = sem;
        this.email=email;
    }
    public MemberDesc()
    {

    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public String getPhone() {
        return phone;
    }

    public String getYear() {
        return year;
    }

    public String getSem() {
        return sem;
    }
    public String getEmail() {
        return email;
    }

}
