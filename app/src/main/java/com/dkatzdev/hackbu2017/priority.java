package com.dkatzdev.hackbu2017;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Vector;
import java.lang.String;

public class priority extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent message_deets = getIntent();
        Message current = new Message(message_deets.getStringExtra("message"), message_deets.getStringExtra("sender"));


        String text = current.getText();
        Vector<String> words = new Vector<String>(1);
        words = getWords(text);
        int num_words = text.trim().split("\\s+").length;
        int urgent, important, neutral, unimportant, junk, other, i;
        urgent = important = neutral = unimportant = junk = other = 0;
        Vector<String> urgent_v, important_v, neutral_v, unimportant_v, junk_v, other_v;


        PriorityStrings priorities  = new PriorityStrings();
        urgent_v = priorities.getUrgent();
        neutral_v = priorities.getNeutral();
        important_v = priorities.getImportant();
        unimportant_v = priorities.getUnimportant();
        junk_v = priorities.getJunk();
        other_v = priorities.getOther();

        String temp;

        for(i = 0; i < num_words; i++){
            temp = "";
            for(int j = i; j < num_words; j++){
                temp = temp + words.elementAt(j);
                if(urgent_v.contains(temp)){
                    urgent++;
                }
                else if(important_v.contains(temp)){
                    important++;
                }
                else if(neutral_v.contains(temp)){
                    neutral++;
                }
                else if(unimportant_v.contains(temp)){
                    unimportant++;
                }
                else if(junk_v.contains(temp)){
                    junk++;
                }
                else if(other_v.contains(temp)){
                    other++;
                }
                else{
                    if(j == i){
                        other++;
                    }
                }
            }
        }

        int[] nums = {urgent, important, neutral, unimportant, junk, other};
        int highest = 0;

        for(i = 1; i < 6; i++){
            if(nums[highest] < nums[i]){
                highest = i;
            }
        }

        int category = 5;

        //int sent, recieved;
        //sent = current.getSent();
        //recieved  = current.getRecieved();
        //double div = recieved/sent;

       // if((div > 1) && (highest != 0) && (highest < 3)){
       //     highest++;
       // }

        if(highest == 0){
            category = 0;
        }
        else if(highest == 1){
            category = 1;
        }
        else if(highest == 2){
            category = 2;
        }
        else if(highest == 3){
            category = 3;
        }
        else if(highest == 4){
            category = 4;
        }
        else if(highest == 5){
            category = 5;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("Address", current.getAddress());
        resultIntent.putExtra ("Text", current.getText());
        resultIntent.putExtra("Category", category);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }




    public static Vector<String> getWords(String text) {
        String temp = "";
        Vector<String> words = new Vector<String>(1);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                temp = temp + text.charAt(i);
            } else if (temp.length() != 0) {
                words.addElement(temp);
                temp = "";
            }
        }
        words.addElement(temp);
        return words;
    }



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_priority);
//
//    // Example of a call to a native method
//    TextView tv = (TextView) findViewById(R.id.sample_text);
//    tv.setText(stringFromJNI());
//    }
//
//    /**
//     * A native method that is implemented by the 'native-lib' native library,
//     * which is packaged with this application.
//     */
//    public native String stringFromJNI();
//
//    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }
}
