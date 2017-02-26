package com.dkatzdev.hackbu2017;

import java.util.Vector;
import java.lang.String;

public class PriorityStrings{
    private Vector<String> urgent = new Vector<String>(1);
    private Vector<String> important = new Vector<String>(1);
    private Vector<String> neutral = new Vector<String>(1);
    private Vector<String> unimportant = new Vector<String>(1);
    private Vector<String> junk = new Vector<String>(1);
    private Vector<String> other = new Vector<String>(1);

    public PriorityStrings(){
        createUrgent();
        createImportant();
        createNeutral();
        createUnimportant();
        createJunk();
        createOther();
    }

    public void createUrgent(){
        urgent.add("call me now");
        urgent.add("right now");
        urgent.add("emergency");
        urgent.add("help");
        urgent.add("my parents aren't home");
        urgent.add("911");
        urgent.add("It's yours");
        urgent.add("Police on the way");
        urgent.add("Oh shit get the car");
    }

    public Vector<String> getUrgent(){
        return this.urgent;
    }

    private void createImportant(){
        important.add("call me");
        important.add("Text me back");
        important.add("need");
        important.add("delivered");
        important.add("arriving");
        important.add("verification");
        important.add("activation");
        important.add("do you");
        important.add("Happy Birthday");
        important.add("Merry Christmas");
        important.add("Happy Thanksgiving");
        important.add("Happy Holidays");
        important.add("getting food");
        important.add("going on date");
        important.add("are you free");
        important.add("Where");
        important.add("Who are");
        important.add("Wanna");
        important.add("Starbucks");
        important.add("Does this look normal");
        important.add("Dog");
    }

    public Vector<String> getImportant(){
        return this.important;
    }

    private void createNeutral(){
        neutral.add("want");
        neutral.add("here");
        neutral.add("payment posted");
        neutral.add("Hey");
        neutral.add("Thank You");
        neutral.add("Bored");
        neutral.add("I think");
        neutral.add("Thanks");
        neutral.add("Hello");
        neutral.add("oh");
    }

    public Vector<String> getNeutral(){
        return this.neutral;
    }

    private void createUnimportant(){
        unimportant.add("Yup");
        unimportant.add("Lol");
        unimportant.add("k");
        unimportant.add("okay");
        unimportant.add("Yeah");
        unimportant.add("yah");
        unimportant.add("lmao");
        unimportant.add("word");
        unimportant.add("no doubt");
        unimportant.add("cool");
        unimportant.add("ight");
        unimportant.add("aight");
        unimportant.add("ok");
        unimportant.add("Gotcha");
    }

    public Vector<String> getUnimportant(){
        return this.unimportant;
    }

    private void createJunk(){
        junk.add("your message could not be delivered");
        junk.add("Like and");
        junk.add("Share this video");
        junk.add("Dennis Foreman");
        junk.add("clean your room");
    }

    public Vector<String> getJunk(){
        return this.junk;
    }

    private void createOther(){
        other.add("STRAIGHT TALK");
    }

    public Vector<String> getOther(){
        return this.other;
    }
}
