package com.example.pushpe.todoapp.Other;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class SetDateFromDB implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView textView;
    private Context ctx;
    private int year, month, day;

    public SetDateFromDB(TextView textView, Context ctx, int year, int month, int day){
        this.textView = textView;
        this.textView.setOnClickListener(this);
        this.ctx = ctx;
        this.year = year;
        this.month = month;
        this.day = day;

    }

    @Override
    public void onClick (View v) {
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