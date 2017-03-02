package com.dkatzdev.hackbu2017;

import android.content.Context;

class ItemArrayAdapter extends TwoLineArrayAdapter<Item> {
    ItemArrayAdapter(Context context, Item[] items) {
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
