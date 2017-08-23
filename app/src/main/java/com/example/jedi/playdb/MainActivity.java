package com.example.jedi.playdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jedi.playdb.data.WaitlistContract;
import com.example.jedi.playdb.data.WaitlistDbHelper;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private DataEntered mDataEntered;
    private SQLiteDatabase mDb;
    private TextView mNoData;
    private  Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.enter_name);
        mButton = (Button) findViewById(R.id.button_submit);
        mNoData = (TextView) findViewById(R.id.no_data);
        WaitlistDbHelper waitlistDbHelper = new WaitlistDbHelper(this);
        mDb = waitlistDbHelper.getWritableDatabase();
        waitlistDbHelper.onCreate(mDb);
         mCursor = getAllNames();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        if (mCursor.getCount() > 0) {
//            String[] passToAdapter = setDbData(cursor);
            mDataEntered = new DataEntered(this, mCursor);
            // passToAdapter =
            mRecyclerView.setAdapter(mDataEntered);

        } else {
            // TODO: Display a no data in table
            mNoData.setVisibility(View.VISIBLE);
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeName(id);
                mDataEntered.swapCursor(getAllNames());

            }
        }).attachToRecyclerView(mRecyclerView);

    }

    public Cursor getAllNames(){
        String query = "SELECT * FROM " + WaitlistContract.WaitlistEntry.TABLE_NAME;
        Cursor cursor =  mDb.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
//        return mDb.query(WaitlistContract.WaitlistEntry.TABLE_NAME,
//                new String[]{WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME},
//                null,
//                null,
//                null,
//                null,
//                WaitlistContract.WaitlistEntry._ID

//        );
    }

    public void addToDb(View view){
        if( mEditText.length() == 0){
            Log.i("in the if statement", "heheehe");
          return ;
        }
//        mEditText = (EditText)findViewById(R.id.enter_name);
        String userInput = mEditText.getText().toString();
        Log.i("Hey look here", "addToDb: " + userInput);
        insertIntoDb(userInput);
        mCursor = getAllNames();
        if(mDataEntered.getItemCount() <= 1) {
            mDataEntered = new DataEntered(this, mCursor);
            mRecyclerView.setAdapter(mDataEntered);
        }
        else {
            mDataEntered.swapCursor(mCursor);
        }
//        mRecyclerView.setAdapter(mDataEntered);
//        mNoData.setVisibility(View.INVISIBLE);
    }
    public long insertIntoDb(String userInput){
        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, userInput);
        return mDb.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, cv);
    }

    public String[] setDbData(Cursor nameEntered){
        String[] namesEntered = null;
        if(nameEntered != null && nameEntered.moveToFirst()){
            namesEntered = new String[nameEntered.getCount()];

            for (int i= 0; i>= nameEntered.getCount(); i++ ){
                String thisColumnValue = nameEntered.getString(nameEntered.getColumnIndexOrThrow("Guestlist"));
                Log.i(" papa ", "setDbData: " + thisColumnValue);
                namesEntered[i] = thisColumnValue;

            }
        }
       // mDataEntered.dumbMethod();
        //mNamesEntered = nameEntered;

       // notifyDataSetChanged();
        return namesEntered;
    }

    public boolean removeName(long id){
        // THIS WILL RETURN THE RESULT OF THIS ACTION SO IF WE REMOVE ONE ROW WHICH IS LARGER THAN 0 IT WILL RETURN TRUE
       return mDb.delete(WaitlistContract.WaitlistEntry.TABLE_NAME,
                WaitlistContract.WaitlistEntry._ID + "=" +id, null) > 0;
    }

}
