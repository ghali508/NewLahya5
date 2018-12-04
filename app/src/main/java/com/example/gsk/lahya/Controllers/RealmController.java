package com.example.gsk.lahya.Controllers;

import com.example.gsk.lahya.model.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    public static Realm realm ;



    public static List<Subscriber> getAllSubscribers() {
        realm = Realm.getDefaultInstance();
        ListIterator<Subscriber> listIterator = realm.where( Subscriber.class ).findAll().listIterator();

        List<Subscriber> subscribers = new ArrayList<>();
        while (listIterator.hasNext()) {
            subscribers.add( listIterator.next() );
        }
        return subscribers;
    }


}
