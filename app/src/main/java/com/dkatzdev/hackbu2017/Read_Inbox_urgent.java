package com.dkatzdev.hackbu2017;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.Vector;

public class Read_Inbox_urgent extends Activity{

    private static final String INBOX_URI = "content://sms/inbox";
    private TwoLineArrayAdapter<String> smsList;
    private ListView mListView;
    int category;
    Vector<Item> items = new Vector<Item>();
    Item[] itemArray;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_list);
        int loops = 0;
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse(INBOX_URI), null, null, null, null);
        int senderIndex = smsInboxCursor.getColumnIndex("address");
        int messageIndex = smsInboxCursor.getColumnIndex("body");
        if (messageIndex < 0 || !smsInboxCursor.moveToFirst()) return;
        //adapter.clear();
        do {
            String sender = smsInboxCursor.getString(senderIndex);
            String message = smsInboxCursor.getString(messageIndex);
            Message current = new Message(message, sender);


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
            if(category == 0) {
                Item item = new Item();
                item.setPhoneNumber(sender);
                item.setMessage(message);
                items.add(item);
            }
            /*
            //adapter.add(Html.fromHtml(formattedText).toString());*/
            loops++;
        } while (smsInboxCursor.moveToNext() && loops<500);
        itemArray = new Item[items.size()];
        Iterator it = items.iterator();
        for(int counter = 0; counter < items.size(); counter++){
            itemArray[counter] = (Item) it.next();
        }
        mListView = (ListView) findViewById(R.id.recent_list);
        mListView.setAdapter(new ItemArrayAdapter(this,itemArray ));
       // mListView.setAdapter(smsList);
        mListView.setOnItemClickListener(MyItemClickListener);
    }
    public void readSMS() {

    }

    private OnItemClickListener MyItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            try {
                Intent myIntent = new Intent(Read_Inbox_urgent.this, sendSMS.class);
                myIntent.putExtra(Intent.EXTRA_TEXT, itemArray[pos].getPhoneNumber());
                Read_Inbox_urgent.this.startActivity(myIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String sender = "";
        String message = "";
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (0): {
                if (resultCode == Activity.RESULT_OK) {
                    category = data.getIntExtra("Category", 0);
                    System.out.println(category);
                    // sender = data.getStringExtra("Address");
                    //message = data.getStringExtra("Text");
                }
                break;
            }
        }
    }*/
    public void add_to_list(String sender, String message){
        Item item = new Item();
        item.setPhoneNumber(sender);
        item.setMessage(message);
        items.add(item);
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
}
