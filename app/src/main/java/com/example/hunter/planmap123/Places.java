package com.example.hunter.planmap123;

import java.util.Comparator;

/**
 * Created by hunter on 21/2/17.
 */

public class Places {
     String name;
     double longitude;
     double latitude;
Places(String name,double latitude,double longitude)
{
    this.name=name;
    this.longitude=longitude;
    this.latitude=latitude;

}
public String getPlaceName() {
    return name;

}
public void setLongitude(double longitude)
{this.longitude=longitude;
}
    public void setLatitude(double latitude)
    {
        this.latitude=latitude;
    }
public double getLatitude()
{
    return latitude;

}
    public double getLongitude()
    {
        return longitude;

    }
    public static Comparator<Places> StuLatitudeComparator=new Comparator<Places>() {
        @Override
        public int compare(Places t1, Places t2) {
            int latitude1=(int)t1.getLatitude();
            int latitude2=(int)t2.getLongitude();
            return latitude1-latitude2;
        }
    };
    public static Comparator<Places> StuLongitudeComparator=new Comparator<Places>() {
        @Override
        public int compare(Places t1, Places t2) {
            int longitude1=(int)t1.getLatitude();
            int longitude2=(int)t2.getLongitude();
            return longitude1-longitude2;
        }
    };




}
