package com.example.hunter.planmap123.Events;

import com.example.hunter.planmap123.ChatsFn.Chats;
import com.example.hunter.planmap123.User.UserOne;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

/**
 * Created by hunter on 4/5/17.
 */

public class EventsOne {

    public ArrayList<UserOne> UsersList;
    public GoogleMap map;
    public Chats chat_instance;
    public String event_name;


    public EventsOne(String event_name)
    {

        this.event_name=event_name;

    }

    public static final EventsOne[] event = {
            new EventsOne("Event 1"),
            new EventsOne("Event 2"),
            new EventsOne("Event 3"),
            new EventsOne("Event 4"),
            new EventsOne("Event 5")

    };

    public String toString()
    {
        return this.event_name;
    }

    private String getEvent_name()
    {
        return event_name;

    }

    public void addUser(UserOne u1)
    {
        UsersList.add(u1);
    }
    public void removeUser(UserOne u1)
    {
        UsersList.remove(u1);

    }

}
