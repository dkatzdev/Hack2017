package com.dkatzdev.hackbu2017;

public class Item {
     String message = null;
     String phoneNumber = null;

    Item() {
    }

    public void setMessage(String message) {
        this.message = message ;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
