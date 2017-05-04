package com.example.hunter.planmap123.Suggestion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hunter.planmap123.Places;
import com.example.hunter.planmap123.R;
import com.example.hunter.planmap123.jason.HttpHandler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SuggestionActivity extends FragmentActivity implements OnMapReadyCallback{
private ArrayList<Places> contactList;
public String spin1string;
    public String spin2string;
    public String editstring;
    private GoogleMap mMap;
Spinner spin1;
    Spinner spin2;
    EditText edit1;
    private String TAG=SuggestionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_suggestion);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    spin1=(Spinner)findViewById(R.id.place_type);
        spin2=(Spinner)findViewById(R.id.inornear);
        edit1=(EditText)findViewById(R.id.place);
contactList=new ArrayList<>();

    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
    public void OnSuggest(View v)
    {
         contactList.clear();
         mMap.clear();
         spin1string=String.valueOf(spin1.getSelectedItem());
         spin2string=String.valueOf(spin2.getSelectedItem());
         editstring=edit1.getText().toString();

new GetContacts().execute();

    }

    private class GetContacts extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Toast.makeText(SuggestionActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }
        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpHandler sh=new HttpHandler();
            //Making a request to url and getting response
            String url="https://maps.googleapis.com/maps/api/place/textsearch/json?query="+spin1string+spin2string+editstring+"&key=AIzaSyCineFailFgYFaUrUGfMxrrxsUKYSSFvIU";
            String jsonStr=sh.makeServiceCall(url);
            Log.e(TAG,"Response from url: "+jsonStr);
            if(jsonStr!=null)
            {
                try{
                    JSONObject jsonObj=new JSONObject(jsonStr);
                    //Getting JSON Array node
                    JSONArray results=jsonObj.getJSONArray("results");

                    for(int i=0;i<results.length();i++)
                    {
                        JSONObject c=results.getJSONObject(i);
                        //  String id=c.getString("id");
                        String name=c.getString("name");
                        String place_id=c.getString("place_id");

                        // String address=c.getString("address");
                        // String gender=c.getString("gender");
                        //pHone node is JSON object
                        //JSONObject phone=c.getJSONObject("phone");
                        JSONObject geometry=c.getJSONObject("geometry");
                        JSONObject location=geometry.getJSONObject("location");
                        double lat=location.getDouble("lat");
                        double longi=location.getDouble("lng");

                        //String mobile=phone.getString("mobile");
                        //String home=phone.getString("home");
                        //String office=phone.getString("office");
                        //HashMap<String,String> place=new HashMap<>();
                        Places place=new Places(name,lat,longi);
                        //contact.put("id",id);
                    /*
                    place.put("name",name);
                    place.put("place_id",place_id);
                    place.put("long",longi);
                    place.put("lat",lat);
                    */
                        //contact.put("mobile",mobile);
                        contactList.add(place);

                    }

                }catch(final JSONException e)
                {
                    Log.e(TAG,"Json Parsing error: "+e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(getApplicationContext(),"Json parsing error:"+e.getMessage(),Toast.LENGTH_LONG).show();


                        }
                    });
                }
            }else{
                Log.e(TAG,"Couldnt get Json from Server");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Couldnt get json from server.Check Logcat for possible errors",Toast.LENGTH_LONG).show();

                    }
                });

            }
            return null;

        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
//        ListAdapter adapter=new SimpleAdapter(JasonActivity.this,contactList,R.layout.list_item,new String[]{"name","lat","long"},new int[]{R.id.name,R.id.lat,R.id.longi});
//        ArrayAdapter<Places> listadapter=new ArrayAdapter<Places>(JasonActivity.this,R.layout.list_item,);
        plotthem();
        }
    }
    public void plotthem()
    {
        int i;

        for(i=0;i<contactList.size();i++) {

            LatLng latLng = new LatLng(contactList.get(i).getLatitude(), contactList.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(contactList.get(i).getPlaceName()));
            if (i == 0) {
                CameraPosition cameraPosition=new CameraPosition.Builder().target(latLng).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }

    }


}
