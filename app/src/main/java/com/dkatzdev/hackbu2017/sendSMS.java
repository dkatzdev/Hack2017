package com.dkatzdev.hackbu2017;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class sendSMS extends AppCompatActivity {

    Intent intent2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendsms);
        EditText phone_number = (EditText) findViewById(R.id.phoneNumber);
        TextView given = (TextView) findViewById(R.id.given);
        phone_number.setText(intent.getStringExtra("number"));
        given.setText(intent.getStringExtra("message"));


/*
Creates the click listener for the application and what should happen if the user clicks the send button
 */
        FloatingActionButton send_button = (FloatingActionButton) findViewById(R.id.send_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Defines and retrieves the phone number and the maessage entered by the user and
                checks to see if the message is greater
                 */
                EditText phone_number = (EditText) findViewById(R.id.phoneNumber);
                EditText message = (EditText) findViewById(R.id.message);
                System.out.println(intent.getStringExtra(Intent.EXTRA_TEXT));
                String phoneNumber = phone_number.getText().toString();
                String Textmessage = message.getText().toString();

                if (phoneNumber.length() > 0 && Textmessage.length() > 0) {
                    sendSMS_method(phoneNumber, Textmessage);
                }
                /*
                IF not then we create a Toast object which pop up display that displays an error message
                 */
                else{
                    Toast.makeText(getBaseContext(),
                                    "Please enter a message and phone number",
                                    Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    private void sendSMS_method(String phoneNumber, String Textmessage ){
        /*
        This identifies a pending intent object that identifies the object's request and in turn
        starts up the smsManager and sends the text message
         */
        //PendingIntent pi = PendingIntent.getActivity(this, 0,
        //        new Intent(this, MainActivity.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, Textmessage, null, null);
        System.out.println("Send pressed");
        this.finish();
    }
}
