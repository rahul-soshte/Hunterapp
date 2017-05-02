package com.example.hunter.planmap123.logger;

/**
 * Created by hunter on 30/4/17.
 */

public interface LogNode {
    public void println(int priority,String tag,String msg,Throwable tr);
}
