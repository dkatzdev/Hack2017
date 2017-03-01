package com.dkatzdev.hackbu2017;

class Item {
    String phoneNumber = null;

    String message = null;

    Item() {
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

}
