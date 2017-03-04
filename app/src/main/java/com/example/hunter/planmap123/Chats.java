package com.example.hunter.planmap123;

public class Chats {
    private String name;

    public static final Chats[] chat = {
            new Chats("Event 1"),
            new Chats("Event 2"),
            new Chats("Event 3")

    };

    private Chats(String name)
    {
        this.name=name;

    }
public String getName()
{
    return name;


}
    public String toString()
    {
        return this.name;
    }

}
