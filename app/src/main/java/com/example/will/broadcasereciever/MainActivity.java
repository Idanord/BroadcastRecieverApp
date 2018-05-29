package com.example.will.broadcasereciever;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private TextView mTextView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<IncomingNumber> mArrayList = new ArrayList<>();
    private RecyclerAdapter mAdapter;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTextView = (TextView) findViewById(R.id.emptyTxt);
        mLayoutManager = new LinearLayoutManager(this);

        //set layout manager to recyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerAdapter(mArrayList);

        //set the adapter
        mRecyclerView.setAdapter(mAdapter);

        //call methods
        readFromDb();

        //initialize broadcastReciver
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readFromDb();
            }
        };
    }

    //read the data and display
    private void readFromDb(){

        //clear the array list
        mArrayList.clear();

        //variables
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = dbHelper.readNumber(database);

        if(cursor.getCount() > 0) {

            while (cursor.moveToNext()){
                String number;
                int id;
                number = cursor.getString(cursor.getColumnIndex(DBContract.INCOMING_NUMBER));
                id = cursor.getInt(cursor.getColumnIndex("id"));
                mArrayList.add(new IncomingNumber(id, number));
            }

            //close the cursor and dbHelper
            cursor.close();
            dbHelper.close();

            mAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, new IntentFilter(DBContract.UPDATE_UI_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }
}
