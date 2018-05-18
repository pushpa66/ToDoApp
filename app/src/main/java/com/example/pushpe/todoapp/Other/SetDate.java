package com.example.pushpe.todoapp.Other;

/**
 * Created by pushpe on 5/18/18.
 */

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class SetDate implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView textView;
    private Calendar myCalendar;
    private Context ctx;

    public SetDate(TextView textView, Context ctx){
        this.textView = textView;
        this.textView.setOnClickListener(this);
        this.myCalendar = Calendar.getInstance();
        this.ctx = ctx;
    }

    @Override
    public void onClick (View v) {
        int year = myCalendar.get(Calendar.YEAR);
        int month = myCalendar.get(Calendar.MONTH);
        int day = myCalendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(ctx, this, year, month, day).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String strMonth;
        String strDay;

        if (month < 10){
            strMonth = "0" + month;
        } else {
            strMonth = "" + month;
        }

        if (day < 10){
            strDay = "0" + day;
        } else {
            strDay = "" + day;
        }
        this.textView.setText( year + "/" + strMonth + "/" + strDay );
    }
}