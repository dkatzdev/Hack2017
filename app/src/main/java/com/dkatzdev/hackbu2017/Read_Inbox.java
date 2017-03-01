package com.dkatzdev.hackbu2017;

import java.util.Iterator;
import java.util.Vector;

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

//TODO Implement MMS support
//TODO Fix Action Bar not displaying in the different inboxes
//TODO Replace the various inbox activities with one dynamic one
    // This dynamic inbox will take an intent from the main activity which specifies the "inbox"
    // chosen, and uses that to select the priority level to display. This will make it easier to
    // modify the categorization algorithm without redundant code
public class Read_Inbox extends Activity{

    private static final String INBOX_URI = "content://sms/inbox";
    private static Read_Inbox activity;
    private TwoLineArrayAdapter<String> smsList;
    private ListView mListView;
    Vector<Item> items = new Vector<Item>();
    Item[] itemArray;
    private ArrayAdapter<String> adapter;
    public static Read_Inbox instance() {
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_list);
        readSMS();
        mListView = (ListView) findViewById(R.id.recent_list);
        mListView.setAdapter(new ItemArrayAdapter(this,itemArray ));
       // mListView.setAdapter(smsList);
        mListView.setOnItemClickListener(MyItemClickListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        activity = this;
    }
    public void readSMS() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse(INBOX_URI), null, null, null, null);
        int senderIndex = smsInboxCursor.getColumnIndex("address");
        int messageIndex = smsInboxCursor.getColumnIndex("body");
        if (messageIndex < 0 || !smsInboxCursor.moveToFirst()) return;
        //adapter.clear();
        do {
            String sender = smsInboxCursor.getString(senderIndex);
            String message = smsInboxCursor.getString(messageIndex);
            Item item = new Item();
            item.setPhoneNumber(sender);
            item.setMessage(message);
            items.add(item);
            String formattedText = String.format(getResources().getString(R.string.sms_message), sender, message);
            //adapter.add(Html.fromHtml(formattedText).toString());
        } while (smsInboxCursor.moveToNext());
        itemArray = new Item[items.size()];
        Iterator it = items.iterator();
        for(int counter = 0; counter < items.size(); counter++){
            itemArray[counter] = (Item) it.next();
        }
    }
    public void updateList(final String newSms) {
        adapter.insert(newSms, 0);
        adapter.notifyDataSetChanged();
    }
    private OnItemClickListener MyItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            try {
                Intent myIntent = new Intent(Read_Inbox.this, sendSMS.class);
                myIntent.putExtra("number", itemArray[pos].getPhoneNumber());
                myIntent.putExtra("message", itemArray[pos].getMessage());
                Read_Inbox.this.startActivity(myIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
