package com.example.pushpe.todoapp.Database;

/**
 * Created by pushpe on 5/18/18.
 */

public class ToDo {
    public static final String TABLE_NAME = "todo";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "todo";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String todo;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_TIMESTAMP + " TEXT"
                    + ")";

    public ToDo() {
    }

    public ToDo(int id, String note, String timestamp) {
        this.id = id;
        this.todo = note;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
