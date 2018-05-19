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
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        String strDescription = intent.getStringExtra("DESCRIPTION");
        String strDateTime = intent.getStringExtra("DATE_TIME");
        this.id = intent.getLongExtra("ID", 0);

        String[] parts = strDateTime.split("-");
        String strDate = parts[0];
        String strTime = parts[1];

        parts = strDateTime.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        strDateTime = parts[2];
        parts = strDateTime.split("-");
        int day = Integer.parseInt(parts[0]);

        strDateTime = parts[1];
        parts = strDateTime.split(":");

        int hour = Integer.parseInt(parts[0]);

        strDateTime = parts[1];

        parts = strDateTime.split(" ");

        int minute = Integer.parseInt(parts[0]);
        String AmPm = parts[1];

        if (AmPm.equals("PM")){
            hour += 12;
        }

        date = findViewById(R.id.txtViewDate);
        time = findViewById(R.id.txtViewTime);
        description = findViewById(R.id.editTextDescription);
        description.setText(strDescription);

        date.setText(strDate);
        time.setText(strTime);

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
                ToDo toDo = new ToDo(id, strDescription,timestamp);

                dbHelper = new DatabaseHelper(EditActivity.this);
                dbHelper.updateToDo(toDo);
                Intent intent = new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
