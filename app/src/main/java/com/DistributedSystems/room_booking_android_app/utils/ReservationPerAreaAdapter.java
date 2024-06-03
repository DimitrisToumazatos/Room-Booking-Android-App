package com.DistributedSystems.room_booking_android_app.utils;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.DistributedSystems.room_booking_android_app.R;

import java.util.HashMap;
import java.util.Map;

public class ReservationPerAreaAdapter extends BaseAdapter {

    HashMap<String, Integer> reservationsPerArea;
    LayoutInflater inflater;

    public ReservationPerAreaAdapter(LayoutInflater inflater, HashMap<String, Integer> reservationsPerArea){
        this.inflater = inflater;
        this.reservationsPerArea = reservationsPerArea;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_per_area_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.regionEditText);
        TextView areaCountTextView = convertView.findViewById(R.id.reservationsEditText);

        Map.Entry<String, Integer> entry = searchMap(position);
        nameTextView.setText(entry.getKey());
        areaCountTextView.setText(entry.getValue().toString());
        return convertView;
    }

    public Map.Entry<String, Integer> searchMap(int position){

        int count=0;
        for(Map.Entry<String, Integer> entry: reservationsPerArea.entrySet()){
            if (count==position-1){
                return entry;
            }
            count++;
        }
        return null;
    }
}
