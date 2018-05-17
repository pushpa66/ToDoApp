package com.example.pushpe.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListview = findViewById(R.id.listViewToDo);

        // Set some data to array list
        ArrayList<String> mArrData = new ArrayList<>(Arrays.asList("111,222,333,444,555,666,123,144,155,166".split(",")));

        // Initialize adapter and set adapter to list view
        CustomItemAdapter mAdapter = new CustomItemAdapter(MainActivity.this, mArrData);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
