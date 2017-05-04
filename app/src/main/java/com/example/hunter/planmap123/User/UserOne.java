package com.example.hunter.planmap123.User;

/**
 * Created by hunter on 4/5/17.
 */

public class UserOne {

    public String username;
    public double GpsLat;
    public double GpsLong;
    public int contactno;
    public String email_id;

    public UserOne(String username,int contactno,String email_id)
    {
        this.username=username;
        this.contactno=contactno;
        this.email_id=email_id;
    }

    public void setGpsLatLong(double latitude,double longitude)
    {
        this.GpsLat=latitude;

        this.GpsLong=longitude;

    }

    public double getGpsLat()
    {
        return GpsLat;
    }

    public double getGpsLong()
    {
        return GpsLong;
    }

    public String getUsername()
    {
        return username;

    }
    public int getContactNo()
    {
    return contactno;
    }
    public String getemailid()
    {
        return email_id;

    }

}
