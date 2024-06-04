package com.DistributedSystems.room_booking_android_app.utils;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.DistributedSystems.room_booking_android_app.R;

import java.util.List;

public class ReservationPerAreaAdapter extends BaseAdapter {

    List<String> reservationsPerArea;
    LayoutInflater inflater;

    public ReservationPerAreaAdapter(LayoutInflater inflater, List<String> reservationsPerArea){
        this.inflater = inflater;
        this.reservationsPerArea = reservationsPerArea;
    }
    @Override
    public int getCount() {
        return reservationsPerArea.size();
    }

    @Override
    public Object getItem(int position) {
        return reservationsPerArea.get(position);
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

        TextView reservationTextView = convertView.findViewById(R.id.reservationEditText);
        reservationTextView.setText(reservationsPerArea.get(position));

        return convertView;
    }
}
