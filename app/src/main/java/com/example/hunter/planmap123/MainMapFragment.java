package com.example.hunter.planmap123;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;


    public MainMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
 View rootView=inflater.inflate(R.layout.fragment_main_map2, container, false);
        // Inflate the layout for this fragment
        mMapView=(MapView)rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();//Needed to get the map to display immediately
        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap=mMap;

                //For showing a move to my location button
                try {
                    googleMap.setMyLocationEnabled(true);
                }
                catch(SecurityException e)
                {

                }
                    //For dropping a marker
                LatLng sydney=new LatLng(-34,151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
                //For zooming automatically to the location of marker
                CameraPosition cameraPosition=new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            }
        });
return rootView;

    }

    @Override
    public void onResume()
    {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
