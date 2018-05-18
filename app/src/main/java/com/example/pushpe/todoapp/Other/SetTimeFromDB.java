package com.example.pushpe.todoapp.Other;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


/**
 * Created by pushpe on 5/19/18.
 */

public class SetTimeFromDB implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private TextView textView;
    private Context ctx;
    private int hour, minute;

    public SetTimeFromDB(TextView textView, Context ctx, int hour, int minute){
        this.textView = textView;
        this.textView.setOnClickListener(this);
        this.ctx = ctx;
        this.hour = hour;
        this.minute = minute;
    }


    @Override
    public void onClick (View v) {
        new TimePickerDialog(ctx, this, hour, minute, false).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // TODO Auto-generated method stub
        String suffix = "AM";
        String strHour;
        String strMinute;

        if (hourOfDay >= 12 ){
            hourOfDay = hourOfDay - 12;
            suffix = "PM";
        }

        if (hourOfDay < 10){
            strHour = "0" + hourOfDay;
        } else {
            strHour = "" + hourOfDay;
        }

        if (minute < 10){
            strMinute = "0" + minute;
        } else {
            strMinute = "" + minute;
        }
        this.textView.setText( strHour + ":" + strMinute + " " + suffix);
    }
}