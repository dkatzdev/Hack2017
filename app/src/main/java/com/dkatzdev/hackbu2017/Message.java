package com.dkatzdev.hackbu2017;

import java.lang.String;

public class Message{
    private String text;
    private String address;
    //private int recieved;
    //private int sent;
    private int category;


    public Message(String text, String address){
        this.text = text;
        this.address = address;
       // this.recieved  = recieved;
       // this.sent = sent;
    }

    public String getText(){
        return this.text;
    }

    public String getAddress(){
        return this.address;
    }

    public void setCategory(int category){
        this.category = category;
    }

    public int getCategory(){
        return this.category;
    }

    //public int getSent(){
    //    return this.sent;
    //}

   // public int getRecieved(){
   //     return this.recieved;
    //}

}
