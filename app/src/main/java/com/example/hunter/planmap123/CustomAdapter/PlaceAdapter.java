package com.example.hunter.planmap123.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hunter.planmap123.Places;
import com.example.hunter.planmap123.R;

import java.util.ArrayList;

/**
 * Created by hunter on 3/5/17.
 */

public class PlaceAdapter extends ArrayAdapter{

    private ArrayList<Places> places;

public PlaceAdapter(Context context,int textViewResourceId,ArrayList<Places> places)
{
    super(context,textViewResourceId,places);
    this.places=places;
}
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v=convertView;
        if(v==null)
        {
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.list_item,null);
        }
        Places i=places.get(position);
  if(i!=null)
  {
      TextView tn=(TextView)v.findViewById(R.id.name);
      TextView ttlat=(TextView)v.findViewById(R.id.lat);
      TextView ttlong=(TextView)v.findViewById(R.id.longi);

      if(tn!=null)
      {
          tn.setText(i.getPlaceName());

      }
      if(ttlat!=null)
      {
          ttlat.setText(String.valueOf(i.getLatitude()));


      }
      if(ttlong!=null)
      {
          ttlong.setText(String.valueOf(i.getLongitude()));
      }
  }
        return v;
    }
}
