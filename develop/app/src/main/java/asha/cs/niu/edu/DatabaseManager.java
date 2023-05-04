package asha.cs.niu.edu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TaskDB",
            TABLE_NAME = "todoTable",
            ITEM_ID = "id",
            ITEM_NAME = "name",
            IS_SELECTED = "selected";

    private static final int DATABASE_VERSION = 1;

    //constructor
    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//end constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_NAME + "( " +
                ITEM_ID + " integer primary key autoincrement, " +
                ITEM_NAME + " text, " +
                IS_SELECTED + " text )";

        db.execSQL(sqlCreate);
    }//end onCreate method

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDrop = "drop table if exists " + TABLE_NAME;

        db.execSQL(sqlDrop);

        onCreate(db);
    }//end onUpgrade method

    //method that inserts a value (Product) into the database
    public void insertTodo(TaskModel todo) {
        String sqlInsert = "insert into " + TABLE_NAME +
                " values( null, '" + todo.getName() +
                "','" + todo.getSelected() + "' )";

        //retrieve a database and insert the product
        SQLiteDatabase database = getWritableDatabase();

        database.execSQL(sqlInsert);

        //close the database
        database.close();
    }//end insertProduct method

    //method to retrieve all the database entries
    public ArrayList<TaskModel> getAllTasks() {
        String sqlSelect = "select * from " + TABLE_NAME;

        //get the database
        SQLiteDatabase database = getWritableDatabase();

        //retrieve the info from the database - it will be stored in a Cursor object
        Cursor cursor = database.rawQuery(sqlSelect, null);

        //create the ArrayList to hold the info
        ArrayList<TaskModel> products = new ArrayList<>();

        //loop through the cursor object and transfer values to the arraylist
        while (cursor.moveToNext()) {
            //get the info from the cursor
            int currentID = cursor.getInt(0);
            String currentName = cursor.getString(1);
            String currentIsSelected = cursor.getString(2);

            //create a product object
            TaskModel product = new TaskModel(currentID, currentName, currentIsSelected);

            //add the product to the array list
            products.add(product);
        }//end while

        //close the database
        database.close();

        //return the arraylist
        return products;
    }//end selectAll

    //method to delete a single product based on an id number
    public void deleteById(int id) {
        String sqlDelete = "delete from " + TABLE_NAME + " where " + ITEM_ID + " = " + id;

        //get the database and apply the SQL command
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlDelete);

        //close the database
        database.close();
    }//end deleteByID


    //method to update a product in the database based on id number
    public void updateByID(int id, String name, String selected) {
        String sqlUpdate = "update " + TABLE_NAME + " set " +
                ITEM_NAME + " = '" + name + "', " +
                IS_SELECTED + " = '" + selected + "'" +
                " where " + ITEM_ID + " = " + id;

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlUpdate);
        database.close();
    }//end updateByID


    //method to delete all info in a table, but keep the table in the database
    public void deleteAll() {
        String sqlDeleteAll = "delete from " + TABLE_NAME;

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlDeleteAll);
        database.close();
    }//end deleteAll
}//end DatabaseManager class
