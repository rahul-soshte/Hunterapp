package com.example.hunter.planmap123;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        ListView listChats = getListView();
        ArrayAdapter<Chats> listAdapter = new ArrayAdapter<Chats>(this, android.R.layout.simple_list_item_1, Chats.chat);
        listChats.setAdapter(listAdapter);
    }
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (position == 0) {
            Intent intent =new Intent(MainActivity.this,MapsActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, MyActivity.class);
            startActivity(intent);
        }
    }
}

