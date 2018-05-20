package com.example.pushpe.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.pushpe.todoapp.Database.DatabaseHelper;
import com.example.pushpe.todoapp.Database.ToDo;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<ToDo> toDoArrayList;
    DatabaseHelper dbHelper;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listViewToDo);

        Button btnAddToDo = findViewById(R.id.btnAddToDo);

        btnAddToDo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToAddToDo(v);
            }
        });

        dbHelper = new DatabaseHelper(MainActivity.this);
        toDoArrayList = (ArrayList<ToDo>)dbHelper.getAllToDoes();

        // Set some data to array list
//        ArrayList<String> mArrData = new ArrayList<>(Arrays.asList("144,155,166".split(",")));

        // Initialize adapter and set adapter to list view
        CustomItemAdapter mAdapter = new CustomItemAdapter(MainActivity.this, toDoArrayList);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();
        CustomItemAdapter mAdapter = new CustomItemAdapter(MainActivity.this, toDoArrayList);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    private void goToAddToDo(View view)
    {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
