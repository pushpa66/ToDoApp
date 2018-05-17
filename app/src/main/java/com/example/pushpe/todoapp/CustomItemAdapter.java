package com.example.pushpe.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomItemAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mArrItemData;

    public CustomItemAdapter(Context context, ArrayList arrItemData) {
        super();
        mContext = context;
        mArrItemData = arrItemData;
    }

    public int getCount() {
        // return the number of records
        return mArrItemData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_item, parent, false);


        // get the reference of textView and button
        TextView txtDescription = view.findViewById(R.id.textViewDescription);
        Button btnDelete = view.findViewById(R.id.buttonDelete);
        Button btnEdit = view.findViewById(R.id.buttonEdit);

        txtDescription.setText(mArrItemData.get(position));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic goes here
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic goes here
            }
        });

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }}