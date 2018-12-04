package com.example.gsk.lahya;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gsk.lahya.model.Subscriber;
import com.example.gsk.lahya.fragments.*;
import java.util.List;

/**
 * Created by SGK on 3/4/2017.
 */

public class MyListAdapter extends BaseAdapter {
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    List<Subscriber> subscriber;
    int itemLayout;
    Context context;
    FragmentManager fragmentManager ;
    public MyListAdapter(Context context, List<Subscriber> subscriber, int itemLayout, FragmentManager fragmentManager){
        this.context = context;
        this.subscriber = subscriber;
        this.itemLayout = itemLayout;
        this.fragmentManager = fragmentManager;
    }
    @Override
    public int getCount() {
        return subscriber.size();
    }
    @Override
    public Object getItem(int position) {
        return subscriber.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

         final Subscriber subscriber = this.subscriber.get(position);


        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(itemLayout, null);

            ViewHolder vh = new ViewHolder();
            vh.subNo = (TextView) convertView.findViewById(R.id.sub_no);
            vh.subName  = (TextView) convertView.findViewById(R.id.sub_name);
            vh.buildingNo = (TextView) convertView.findViewById(R.id.building_no);
            vh.locationNo = (TextView) convertView.findViewById(R.id.location_no);
            vh.streetNo  = (TextView) convertView.findViewById(R.id.street_no);
            vh.status = (TextView) convertView.findViewById(R.id.status);
            vh.preReading = (TextView) convertView.findViewById(R.id.pre_reading);
            vh.preReadingDate = (TextView) convertView.findViewById(R.id.pre_reading_date);
            vh.currentReading = (TextView) convertView.findViewById(R.id.current_reading);
            vh.currentReadingDate = (TextView) convertView.findViewById(R.id.current_reading_date);

            convertView.setTag(vh);
        }
        ViewHolder vh = (ViewHolder)convertView.getTag();
        vh.subNo.setText(subscriber.getSubNo());
        vh.subName.setText(subscriber.getSubName());
        vh.buildingNo.setText(subscriber.getBuildingNo());

        vh.locationNo.setText(subscriber.getLocationNo());
        vh.streetNo.setText(subscriber.getStreetNo());
        vh.status.setText(subscriber.getStatus());

        vh.preReading.setText(subscriber.getPreReading());
        vh.preReadingDate.setText(subscriber.getPreReadingDate());
        vh.currentReading.setText(subscriber.getCurrentReading());
        vh.currentReadingDate.setText(subscriber.getCurrentReadingDate());

        convertView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogFragment(subscriber);
            }
        } );

        if(!subscriber.getCurrentReading().equals("" )){
            convertView.setSelected( true );
        }
        return  convertView;
    }


    private void openDialogFragment(Subscriber subscriber){
        Bundle bundle = new Bundle();
        bundle.putSerializable( "subscriber", subscriber );

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments( bundle );
        detailsFragment.show( fragmentManager ,"jjj");
    }

    public static class ViewHolder{

        TextView subNo;
        TextView subName;
        TextView buildingNo;
        TextView locationNo;
        TextView streetNo;
        TextView status;
        TextView preReading;
        TextView preReadingDate;
        TextView currentReading;
        TextView currentReadingDate;
    }
}