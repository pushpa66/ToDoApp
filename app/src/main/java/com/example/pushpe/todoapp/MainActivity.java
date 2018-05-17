package com.example.pushpe.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pushpe.todoapp.Database.DatabaseHelper;
import com.example.pushpe.todoapp.Database.ToDo;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    ArrayList<ToDo> toDoArrayList;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListview = findViewById(R.id.listViewToDo);

        dbHelper = new DatabaseHelper(MainActivity.this);
        toDoArrayList = (ArrayList<ToDo>)dbHelper.getAllToDoes();

        // Set some data to array list
        ArrayList<String> mArrData = new ArrayList<>(Arrays.asList("144,155,166".split(",")));

        // Initialize adapter and set adapter to list view
        CustomItemAdapter mAdapter = new CustomItemAdapter(MainActivity.this, mArrData);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
