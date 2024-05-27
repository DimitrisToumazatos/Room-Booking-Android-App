package com.DistributedSystems.room_booking_android_app.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.DistributedSystems.room_booking_android_app.R;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class ReservationPerAreaAdapter extends BaseAdapter {

    HashMap<String, Integer> reservationsPerArea;
    LayoutInflater inflater;
    JSONParser parser;

    public ReservationPerAreaAdapter(LayoutInflater inflater, HashMap<String, Integer> reservationsPerArea){
        this.inflater = inflater;
        this.reservationsPerArea = reservationsPerArea;
    }
    @Override
    public int getCount() {
        return reservationsPerArea.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_res_list, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.customerName);
        TextView areaCountTextView = (TextView) convertView.findViewById(R.id.startDate);

        try {
            JSONObject json = (JSONObject) parser.parse(String.valueOf(reservationsPerArea.get(position)));

            nameTextView.setText((String) json.get("areaName"));
            areaCountTextView.setText((String) json.get("count"));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return convertView;
    }
    }
}
