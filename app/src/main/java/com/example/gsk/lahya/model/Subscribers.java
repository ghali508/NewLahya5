package com.example.gsk.lahya.model;

import java.util.HashMap;

import io.realm.RealmList;

public class Subscribers extends HashMap<String ,Subscriber> {

    private static Subscribers subscribersList;

    private Subscribers() {


    }


    public static Subscribers getInstance() {

        if (subscribersList == null) {
            subscribersList = new Subscribers();
        }
        return subscribersList;
    }

}
