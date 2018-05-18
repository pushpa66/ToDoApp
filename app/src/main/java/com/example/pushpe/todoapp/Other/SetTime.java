package com.example.pushpe.todoapp.Other;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class SetTime implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private TextView textView;
    private Calendar myCalendar;
    private Context ctx;

    public SetTime(TextView textView, Context ctx){
        this.textView = textView;
        this.textView.setOnClickListener(this);
        this.myCalendar = Calendar.getInstance();
        this.ctx = ctx;

    }


    @Override
    public void onClick (View v) {
        int hour = myCalendar.get(Calendar.HOUR);
//            int hour24 = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
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