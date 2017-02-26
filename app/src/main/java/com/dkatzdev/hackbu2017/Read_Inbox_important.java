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

public class Read_Inbox_important extends Activity{
    int category = 0;

    private static final String INBOX_URI = "content://sms/inbox";
    private static Read_Inbox_important activity;
    private TwoLineArrayAdapter<String> smsList;
    private ListView mListView;
    Vector<Item> items = new Vector<Item>();
    Item[] itemArray;
    private ArrayAdapter<String> adapter;
    public static Read_Inbox_important instance() {
        return activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_input);
        readSMS();
        mListView = (ListView) findViewById(R.id.list);
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
            Intent message_deets = new Intent(Read_Inbox_important.this, priority.class);
            message_deets.putExtra("sender", sender);
            message_deets.putExtra("message", message);
            startActivityForResult (message_deets, 0);

            /*Item item = new Item();
            item.setPhoneNumber(sender);
            item.setMessage(message);
            items.add(item);
            //adapter.add(Html.fromHtml(formattedText).toString());*/
            loops++;
        } while (smsInboxCursor.moveToNext() && loops<100);
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
                Intent myIntent = new Intent(Read_Inbox_important.this, sendSMS.class);
                myIntent.putExtra(Intent.EXTRA_TEXT, itemArray[pos].getPhoneNumber());
                Read_Inbox_important.this.startActivity(myIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String sender = "";
        String message = "";
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (0): {
                if (resultCode == Activity.RESULT_OK) {
                    category = data.getIntExtra("Category", 0);
                    sender = data.getStringExtra("Address");
                    message = data.getStringExtra("Text");
                }
                break;
            }
        }
        if(category == 1) {
            add_to_list(sender, message);
        }

    }
    public void add_to_list(String sender, String message){
        Item item = new Item();
        item.setPhoneNumber(sender);
        item.setMessage(message);
        items.add(item);
    }
}
