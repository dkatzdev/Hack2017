package com.dkatzdev.hackbu2017;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Toast;

public class ReceiverOfSMS extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        //Receive the message
        Bundle extras = intent.getExtras();

        SmsMessage[] smgs;

        String infoSMS = "";

        if (extras != null) {
            Object[] pdus = (Object[]) extras.get("pdus");

            smgs = new SmsMessage[pdus.length];

            for (int counter = 0; counter < smgs.length; counter++) {
                smgs[counter] = SmsMessage.createFromPdu((byte[]) pdus[counter]);

                infoSMS += smgs[counter].getMessageBody();
                infoSMS += "\n";
            }
            Toast.makeText(context, infoSMS, Toast.LENGTH_SHORT).show();
        }
    }

}
