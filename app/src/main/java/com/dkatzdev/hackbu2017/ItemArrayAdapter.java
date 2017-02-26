package com.dkatzdev.hackbu2017;

import android.content.Context;

public class ItemArrayAdapter extends TwoLineArrayAdapter<Item> {
    public ItemArrayAdapter(Context context, Item[] items) {
        super(context, items);
    }

    @Override
    public String lineOneText(Item e) {
        return e.message;
    }

    @Override
    public String lineTwoText(Item e) {
        return e.phoneNumber;
    }
}
