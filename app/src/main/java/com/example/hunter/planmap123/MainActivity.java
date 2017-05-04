package com.example.hunter.planmap123;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hunter.planmap123.Events.EventsOne;
import com.example.hunter.planmap123.Suggestion.SuggestionActivity;
import com.example.hunter.planmap123.jason.JasonActivity;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        ListView listChats = getListView();
        ArrayAdapter<EventsOne> listAdapter = new ArrayAdapter<EventsOne>(this, android.R.layout.simple_list_item_1, EventsOne.event);
        listChats.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        } else if (position == 1) {
            Intent intent = new Intent(MainActivity.this, MyActivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(MainActivity.this, AutoCompleteMainActivity.class);
            startActivity(intent);
        }
        else if(position==3)
        {
            Intent intent = new Intent(MainActivity.this, JasonActivity.class);
            startActivity(intent);

        }
        else if(position==4)
        {
            Intent intent=new Intent(MainActivity.this, SuggestionActivity.class);
            startActivity(intent);

        }
    }
}

