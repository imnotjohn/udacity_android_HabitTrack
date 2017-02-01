package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by fuguBook on 13/7/16.
 */
public class DBHelperClass extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContractClass.innerClass.TABLE_NAME + " (" +
                    ContractClass.innerClass._ID + " INTEGER PRIMARY KEY," +
                    ContractClass.innerClass.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    ContractClass.innerClass.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContractClass.innerClass.TABLE_NAME;

    public DBHelperClass (Context context) {
        super(context, ContractClass.innerClass.DATABASE_NAME, null, ContractClass.innerClass.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertHabit  (String habit, Boolean irritating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("habit", habit);
        contentValues.put("irritating", irritating);
        db.insert("ContractClass.innerClass.TABLE_NAME", null, contentValues);
        return true;
    }

    public Cursor readData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from habits where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numOfRows = (int) DatabaseUtils.queryNumEntries(db, ContractClass.innerClass.TABLE_NAME);
        return numOfRows;
    }

    public boolean updateHabit (Integer id, String habit, Boolean irritating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("habit", habit);
        contentValues.put("irritating", irritating);
        db.update(ContractClass.innerClass.TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteDatabase (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("habit",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public void deleteAll () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete * from * " + ContractClass.innerClass.TABLE_NAME);
        db.close();
    }

    public ArrayList<String> getAllHabits()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from habits", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(ContractClass.innerClass.COLUMN_NAME_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
}