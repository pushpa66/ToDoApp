package com.example.pushpe.todoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todo_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(ToDo.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ToDo.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertToDo(ToDo todo) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ToDo.COLUMN_DESCRIPTION, todo.getTodo());
        values.put(ToDo.COLUMN_TIMESTAMP, todo.getTimestamp());

        // insert row
        long id = db.insert(ToDo.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public ToDo getToDo(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ToDo.TABLE_NAME,
                new String[]{ToDo.COLUMN_ID, ToDo.COLUMN_DESCRIPTION, ToDo.COLUMN_TIMESTAMP},
                ToDo.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare to-do object
        ToDo toDo = new ToDo(
                cursor.getInt(cursor.getColumnIndex(ToDo.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return toDo;
    }

    public List<ToDo> getAllToDoes() {
        List<ToDo> toDoes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + ToDo.TABLE_NAME + " ORDER BY " +
                ToDo.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDo toDo = new ToDo();
                toDo.setId(cursor.getInt(cursor.getColumnIndex(ToDo.COLUMN_ID)));
                toDo.setTodo(cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_DESCRIPTION)));
                toDo.setTimestamp(cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TIMESTAMP)));

                toDoes.add(toDo);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return to-does list
        return toDoes;
    }

    public int getToDoesCount() {
        String countQuery = "SELECT  * FROM " + ToDo.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public int updateToDo(ToDo toDo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ToDo.COLUMN_DESCRIPTION, toDo.getTodo());
        values.put(ToDo.COLUMN_TIMESTAMP, toDo.getTimestamp());

        // updating row
        return db.update(ToDo.TABLE_NAME, values, ToDo.COLUMN_ID + " = ?",
                new String[]{String.valueOf(toDo.getId())});
    }

    public void deleteToDo(ToDo toDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ToDo.TABLE_NAME, ToDo.COLUMN_ID + " = ?",
                new String[]{String.valueOf(toDo.getId())});
        db.close();
    }
}