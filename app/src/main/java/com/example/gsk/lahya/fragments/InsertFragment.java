package com.example.gsk.lahya.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gsk.lahya.MyListAdapter;
import com.example.gsk.lahya.R;
import com.example.gsk.lahya.model.Subscriber;
import com.example.gsk.lahya.model.Subscribers;

import java.util.ArrayList;

public class InsertFragment extends Fragment {

    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_insert, container, false );

        // View header = inflater.inflate( R.layout.header_list,null );

        final Subscribers subscribersMap = Subscribers.getInstance();

        for (int i = 625486; i < 626190; i++) {
            Subscriber subscriber = new Subscriber( i + "", "غالي شعبان غالي خضر", "25", "33", "15/222", "بحالة جيدة", "38434", "10/10/2018", "", "" );
            subscribersMap.put( subscriber.getSubNo(), subscriber );
        }

        fragmentManager = getChildFragmentManager();
        MyListAdapter adapter = new MyListAdapter( getContext(), new ArrayList( Subscribers.getInstance().values() ), R.layout.my_list_item_layout, fragmentManager );

        final ListView listView = (ListView) view.findViewById( R.id.list1 );

        //  listView.addHeaderView( header );
        listView.setAdapter( adapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subscriber s = subscribersMap.get( position );
                view.setSelected( true );
                if (!s.getCurrentReading().equals( "" )) {
//                    view.setSelected( true );
                    view.setBackgroundColor( Color.parseColor( "#000fff" ) );

                }
//                view.setBackgroundColor( Color.parseColor( "#000fff" ) );

            }
        } );
        return view;
    }

}
