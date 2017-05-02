package com.example.hunter.planmap123.jason;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.hunter.planmap123.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
//AIzaSyCineFailFgYFaUrUGfMxrrxsUKYSSFvIU
//https://maps.googleapis.com/maps/api/place/textsearch/xml?query=restaurants+in+Sydney&key= AIzaSyCineFailFgYFaUrUGfMxrrxsUKYSSFvIU
public class JasonActivity extends Activity {

    private String TAG=JasonActivity.class.getSimpleName();
    private ListView lv;
    ArrayList<HashMap<String,String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jason);
    contactList=new ArrayList<>();
        lv=(ListView)findViewById(R.id.list);
        new GetContacts().execute();
    }
private class GetContacts extends AsyncTask<Void,Void,Void>
{
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        Toast.makeText(JasonActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

    }
    @Override
    protected Void doInBackground(Void... arg0)
    {
        HttpHandler sh=new HttpHandler();
    //Making a request to url and getting response
        String url="https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Worli&key=AIzaSyCineFailFgYFaUrUGfMxrrxsUKYSSFvIU";
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
                    //String mobile=phone.getString("mobile");
                    //String home=phone.getString("home");
                    //String office=phone.getString("office");
                    HashMap<String,String> place=new HashMap<>();
                    //contact.put("id",id);
                    place.put("name",name);
                    place.put("place_id",place_id);
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
        ListAdapter adapter=new SimpleAdapter(JasonActivity.this,contactList,R.layout.list_item,new String[]{"name"},new int[]{R.id.name});
        lv.setAdapter(adapter);
    }
}
}
