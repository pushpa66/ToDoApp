package com.example.pushpe.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pushpe.todoapp.Database.DatabaseHelper;
import com.example.pushpe.todoapp.Database.ToDo;
import com.example.pushpe.todoapp.Other.SetDateFromDB;
import com.example.pushpe.todoapp.Other.SetTimeFromDB;

public class EditActivity extends AppCompatActivity {

    private TextView date, time;
    private EditText description;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        String strDescription = intent.getStringExtra("DESCRIPTION");
        String strDateTime = intent.getStringExtra("DATE_TIME");
        long id = intent.getLongExtra("ID", 0);

        int year = 2018;
        int month = 1;
        int day = 1;

        int hour = 1;
        int minute = 1;

        date = findViewById(R.id.txtViewDate);
        time = findViewById(R.id.txtViewTime);
        description = findViewById(R.id.editTextDescription);
        description.setText(strDateTime);

        Button addToDo = findViewById(R.id.btnAddToDo);

        SetTimeFromDB fromTime = new SetTimeFromDB(time, this, hour, minute);
        SetDateFromDB fromDate = new SetDateFromDB(date, this, year, month, day);

        addToDo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String timestamp = date.getText().toString() + "-" + time.getText().toString();
                String strDescription = description.getText().toString();
                ToDo toDo = new ToDo(strDescription,timestamp);

                dbHelper = new DatabaseHelper(EditActivity.this);
                long id = dbHelper.insertToDo(toDo);
            }
        });
    }
}
