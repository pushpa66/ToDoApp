package com.example.pushpe.todoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.pushpe.todoapp.Database.DatabaseHelper;
import com.example.pushpe.todoapp.Database.ToDo;

import java.util.ArrayList;
import java.util.Calendar;

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

            this.setBackground(convertView, mArrItemData.get(position).getTimestamp());

            convertView.setTag(viewHolder);

        }else{
            this.setBackground(convertView, mArrItemData.get(position).getTimestamp());
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.position = position;


        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clickeda
                                DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                                ToDo toDo = new ToDo(viewHolder.id, viewHolder.txtDescription.getText().toString(), viewHolder.txtDateTime.getText().toString());
                                dbHelper.deleteToDo(toDo);
                                dialog.dismiss();
                                Intent intent = new Intent(mContext, MainActivity.class);
                                mContext.startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                dialog.dismiss();
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
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
    }

    private void setBackground(View convertView, String strDateTime){
        String[] parts;
        parts = strDateTime.split("-");
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

        Calendar myCalendar = Calendar.getInstance();
        long time = Calendar.getInstance().getTimeInMillis();
        myCalendar.set(year, month - 1, day, hour, minute);
        long time2 = myCalendar.getTimeInMillis();

        if(time>time2){
            convertView.setBackgroundColor(Color.parseColor("#ff4646"));
        }
    }
}