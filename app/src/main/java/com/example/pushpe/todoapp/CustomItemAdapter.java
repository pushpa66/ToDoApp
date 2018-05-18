package com.example.pushpe.todoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.pushpe.todoapp.Database.ToDo;

import java.util.ArrayList;

public class CustomItemAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ToDo> mArrItemData;

    static class ViewHolder {
        TextView txtDescription;
        TextView txtDateTime;
        Button btnDelete;
        Button btnEdit;
        long id;
        int position;
    }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null){

            // inflate the layout
            LayoutInflater inflater = ((MainActivity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.activity_item, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.txtDescription = convertView.findViewById(R.id.textViewDescription);
            viewHolder.txtDescription.setText(mArrItemData.get(position).getTodo());

            viewHolder.txtDateTime = convertView.findViewById(R.id.textViewDateTime);
            viewHolder.txtDateTime.setText(mArrItemData.get(position).getTimestamp());

            viewHolder.id = mArrItemData.get(position).getId();
            viewHolder.btnDelete = convertView.findViewById(R.id.buttonDelete);
            viewHolder.btnEdit = convertView.findViewById(R.id.buttonEdit);
            // store the holder with the view.
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.position = position;

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditActivity.class);
//                intent.putExtra("DESCRIPTION", );
                mContext.startActivity(intent);
            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("DESCRIPTION", viewHolder.txtDescription.getText().toString());
                intent.putExtra("DATE_TIME", viewHolder.txtDateTime.getText().toString());
                intent.putExtra("ID", viewHolder.id);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }}