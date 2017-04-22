package com.example.hunter.planmap123;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyActivity extends Activity {
    private DrawerLayout drawerLayout;
    private String[] titles;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        titles = getResources().getStringArray(R.array.titles);
        drawerList = (ListView) findViewById(R.id.drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (savedInstanceState == null) {
            selectItem(0);
        }
        //Create the ActionBarDrawerLayout
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {

            //Called when a drawer has settled in a completely closed state

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                //Code to runn when drawer is open
            }
            //Called when a drawer has settled in a completely open state
        };

        drawerLayout.setDrawerListener(drawerToggle);
    getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

@Override
public boolean onOptionsItemSelected(MenuItem item)
{
    if(drawerToggle.onOptionsItemSelected(item))
    {
        return true;
    }
    return super.onOptionsItemSelected(item);


}
    private class DrawerItemClickListener implements ListView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        //Code to run when the item gets clicked
        selectItem(position);
setActionBarTitle(position);
    }
};
    private void setActionBarTitle(int position){
    String title;
        title=getResources().getString(R.string.app_name);
        getActionBar().setTitle(title);

        drawerLayout.closeDrawer(drawerList);
    }

    private void selectItem(int position)
    {
        Fragment fragment;

        switch(position)
        {


            case 1:
                fragment=new MainMapFragment();

                break;
            case 2:
                fragment=new MainMapFragment();
                break;
            case 3:
                fragment=new MainMapFragment();
                break;
            case 4:
                fragment=new MainMapFragment();
                break;
            default:fragment=new MainMapFragment();

        }
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

    
};

