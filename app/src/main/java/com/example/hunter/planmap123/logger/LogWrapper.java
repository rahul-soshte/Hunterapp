package com.example.hunter.planmap123.logger;

import android.util.Log;

/**
 * Created by hunter on 30/4/17.
 */


public class LogWrapper implements LogNode {
    private LogNode mNext;

    public LogNode getNext()
    {
        return mNext;

    }
    public void setNext(LogNode node)
    {
        mNext=node;

    }
    public void println(int priority,String tag,String msg,Throwable tr)
    {
        String useMsg=msg;
        if(useMsg==null)
        {
            useMsg="";

        }
        if(tr!=null)
        {
            msg+="\n"+ Log.getStackTraceString(tr);

        }
        Log.println(priority, tag, msg);
        if(mNext!=null)
        {
            mNext.println(priority,tag,msg,tr);

        }
    }
}
