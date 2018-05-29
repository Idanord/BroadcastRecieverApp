package com.example.will.broadcasereciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;

/**
 * Created by Will on 5/28/2018.
 */

public class NumberReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //local variables
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        //write to database
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            dbHelper.saveNumber(number, database);
            dbHelper.close();
        }

        Intent intent1 = new Intent(DBContract.UPDATE_UI_FILTER);

        context.sendBroadcast(intent1);

    }
}
