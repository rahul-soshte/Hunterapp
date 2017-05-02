package com.example.hunter.planmap123.logger;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hunter on 30/4/17.
 */

public class LogView extends TextView implements LogNode{
    public LogView(Context context)
    {
        super(context);
    }
    public LogView(Context context, AttributeSet attrs)
    {
        super(context,attrs);

    }
    public LogView(Context context,AttributeSet attrs,int defStyle)
    {
        super(context,attrs,defStyle);

    }

    @Override
    public void println(int priority,String tag,String msg,Throwable tr)
    {
        String priorityStr=null;
        switch(priority)
        {
            case android.util.Log.VERBOSE:
                priorityStr="VERBOSE";
            case android.util.Log.DEBUG:
                priorityStr="DEBUG";
            case android.util.Log.INFO:
                priorityStr="INFO";
            case android.util.Log.WARN:
                priorityStr="WARN";
            case android.util.Log.ERROR:
                priorityStr="ERROR";
            case android.util.Log.ASSERT:
                priorityStr="ASSERT";
                default:break;

        }
        String exceptionStr=null;
        if(tr!=null)
        {
            exceptionStr=android.util.Log.getStackTraceString(tr);

        }
        final StringBuilder outputBuilder=new StringBuilder();
        String delimiter="\t";
        appendIfNotNull(outputBuilder,priorityStr,delimiter);
        appendIfNotNull(outputBuilder,tag,delimiter);
        appendIfNotNull(outputBuilder,msg,delimiter);
        appendIfNotNull(outputBuilder,exceptionStr,delimiter);

        ((Activity)getContext()).runOnUiThread((new Thread(new Runnable(){
            @Override
            public void run()
            {
                appendToLog(outputBuilder.toString());


            }
        })));
        if(mNext!=null)
        {
            mNext.println(priority,tag,msg,tr);

        }
    }
    public LogNode getNext()
    {
        return mNext;

    }
    public void setNext(LogNode node)
    {
        mNext=node;

    }
    private StringBuilder appendIfNotNull(StringBuilder source,String addStr,String delimiter)
    {
        if(addStr!=null)
        {
            if(addStr.length()==0)
            {
                delimiter="";
            }
            return source.append(addStr).append(delimiter);

        }
        return source;

    }
    LogNode mNext;
    public void appendToLog(String s)
    {
        append("\n"+s);

    }
}


