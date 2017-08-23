package com.example.jedi.playdb;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jedi.playdb.data.WaitlistContract;

public class DataEntered extends RecyclerView.Adapter<DataEntered.DataEnteredAdapterViewHolder> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Cursor mCursor;
    private int mCount;
    private Context mContext;

    // TODO: Rename and change types of parameters
    private String[] mNamesEntered;


    public DataEntered(Context context,Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
//        mNamesEntered = namesEntered;
       // mCount = count;
        // Required empty public constructor
    }


    @Override
    public void onBindViewHolder(DataEnteredAdapterViewHolder holder, int position) {
//        String dataEntered = mNamesEntered[position];
//        Log.i(" mama ", "onBindViewHolder: this is a logoooo " + dataEntered);
//        holder.mDataEnteredTextView.setText(dataEntered);
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        holder.mDataEnteredTextView.setText(name);
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
//        if(null == mNamesEntered){
//            return mCount;
//        }
//        return mNamesEntered.length;
    }
    @Override
    public DataEnteredAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.fragment_data_entered;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentsImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentsImmediately);
        return new DataEnteredAdapterViewHolder(view);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataEntered.
     */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in his fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public class DataEnteredAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView mDataEnteredTextView;

        public DataEnteredAdapterViewHolder(View itemView) {
            super(itemView);
            mDataEnteredTextView = (TextView)itemView.findViewById(R.id.data_entered);
        }
    }

//    public String[] setDbData(Cursor nameEntered){
//        String[] namesEntered = null;
//        if(nameEntered != null && nameEntered.moveToFirst()){
//           namesEntered = new String[nameEntered.getCount()];
//
//            for (int i= 0; i>= nameEntered.getCount(); i++ ){
//                String thisColumnValue = nameEntered.getString(nameEntered.getColumnIndexOrThrow("Guestlist"));
//                Log.i(" papa ", "setDbData: " + thisColumnValue);
//                namesEntered[i] = thisColumnValue;
//
//            }
//        }
//
//        //mNamesEntered = nameEntered;
//        notifyDataSetChanged();
//        return namesEntered;
//    }

    public void dumbMethod(){
        notifyDataSetChanged();
    }



public void swapCursor(Cursor cursor){
    this.mCursor = cursor;
    notifyDataSetChanged();
}
}