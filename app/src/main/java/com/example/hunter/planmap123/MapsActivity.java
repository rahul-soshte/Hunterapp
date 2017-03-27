package com.example.hunter.planmap123;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapLongClickListener,OnMapReadyCallback {

    private ArrayList<Places> LatSort=new ArrayList<Places>();
  private ArrayList<Places> LongSort=new ArrayList<Places>();
private List <Draggable> mCircles=new ArrayList<Draggable>();

   private LatLngBounds bound;
private LatLng Southwest;
    private LatLng NorthEast;
    double MaxLat;
    double MinLat;
    double MaxLong;
    double MinLong;

public class Draggable

{
    private final Circle circle;
    private Marker centerMarker;
    private double radius;

    public Draggable(LatLng center, LatLng radiusLatLng) {
        this.radius = toRadiusMeters(center, radiusLatLng);

        circle = mMap.addCircle(new CircleOptions().center(center).radius(radius).strokeColor(Color .BLACK).fillColor(5));


    }

}
    private static double toRadiusMeters(LatLng center,LatLng radius)
    {
        float[] result =new float[1];
        Location.distanceBetween(center.latitude,center.longitude,radius.latitude,radius.longitude,result);
        return result[0];

    }

    private GoogleMap mMap;
    private ArrayList<Places> drenched=new ArrayList<Places>();
Places p1;
Iterator itr,itr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();

        List <Address> addressList = null;
        if ( location!= null || !location.equals("")) {
            Geocoder geocoder=new Geocoder(this);
            try{
                addressList=geocoder.getFromLocationName(location, 1);

            }catch(IOException e){
                e.printStackTrace();
            }
            Address address=addressList.get(0);
            LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            p1=new Places(location,address.getLongitude(), address.getLatitude());
            drenched.add(p1);
        }


    }
    public void onFindCenter(View v)
    {
        if(drenched.size()==1)
        {
            //Do nothing
        }
        else if(drenched.size()!=1)
        {

        }
//Conditions for Southwest
        //MinLat and MinLong
    }
    public void ClearMarkers(View v)
    {
        mMap.clear();
        mCircles.clear();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng point)
    {
        //We know the center let's place the outline at a point 3/4
        View view=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getView();
        LatLng radiusLatLng=mMap.getProjection().fromScreenLocation(new Point(view.getHeight()*3/4,view.getWidth()*3/4));
        Draggable circle1 =new Draggable(point,radiusLatLng);
mCircles.add(circle1);

    }

}