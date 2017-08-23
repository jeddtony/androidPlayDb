package com.example.jedi.playdb.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jedi on 7/25/2017.
 */

public class WaitlistDbHelper extends SQLiteOpenHelper {

    //THIS IS THE DATABASE FILE NAME
    private  static final String DATABASE_NAME = "waitlist.db";

    //THIS IS THE DATABASE NUMBER BEST FOR UPDATING THE APP
    private static final int DATABASE_VERSION = 1;

    public WaitlistDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE IF NOT EXISTS " + WaitlistContract.WaitlistEntry.TABLE_NAME +
                " ( " + WaitlistContract.WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                WaitlistContract.WaitlistEntry.COLUMN_PRIORITY + " INT NOT NULL" +
                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " + ");";
        db.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // TODO: Work on this later for the updates
    }

}
