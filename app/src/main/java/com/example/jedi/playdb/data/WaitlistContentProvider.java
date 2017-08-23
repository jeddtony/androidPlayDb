package com.example.jedi.playdb.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.jedi.playdb.data.WaitlistContract.WaitlistEntry.TABLE_NAME;

/**
 * Created by jedi on 8/22/2017.
 */

public class WaitlistContentProvider extends ContentProvider{
    private  WaitlistDbHelper mWaitlistDbHelper;

    public static final int TASKS = 100;
    public static final int TASK_WITH_ID = 101;
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher(){
        // First declare an empty uriMatcher
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(WaitlistContract.AUTHORITY, WaitlistContract.PATH_TASKS, TASKS);
        uriMatcher.addURI(WaitlistContract.AUTHORITY, WaitlistContract.PATH_TASKS + "/#", TASK_WITH_ID);
        return uriMatcher;
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        mWaitlistDbHelper = new WaitlistDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mWaitlistDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match){
            case TASKS:
                long id = db.insert(TABLE_NAME, null, values);
                if(id > 0){
                    //SUCCESS
                    returnUri = ContentUris.withAppendedId(WaitlistContract.WaitlistEntry.CONTENT_URI, id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
//        throw new UnsupportedOperationException("not yet implemented");
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
