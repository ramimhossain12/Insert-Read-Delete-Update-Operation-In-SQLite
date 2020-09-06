package com.shoedazzle.m.listviewdemodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Create Table;
    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final int VERSION_NUMBER = 1;
    //Context Variable;
    Context context;

    //Create Database;
    private  static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(25));";

    //Drop Table;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Oncreate is called",Toast.LENGTH_LONG).show();

        }catch (Exception e){

            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"OnUpgate is called",Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }
    }

    //Save Data;
    public long saveData(String Id,String Name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,Id);
        contentValues.put(NAME,Name);
        long rawNumber = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rawNumber;

    }

    //Show Data;
    public Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    //Update data;
    public boolean updateData(String id, String Name){
        //Return Data;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //All Data Store;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,Name);

        sqLiteDatabase.update(TABLE_NAME,contentValues ,ID+" = ?",new String[]{id});
        return true;
    }

    //Delete Data;
    public int deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }

}
