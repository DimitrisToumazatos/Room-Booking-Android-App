package com.DistributedSystems.room_booking_android_app.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.DistributedSystems.room_booking_android_app.R;

import java.util.List;

public class RoomAdapter extends BaseAdapter {
    Context context;
    List<String> roomNames, roomIds;
    LayoutInflater inflater;
    public RoomAdapter(LayoutInflater inflater, List<String> roomNames, List<String> roomIds) {
        this.inflater = inflater;
        this.roomIds = roomIds;
        this.roomNames = roomNames;
    }

    public void setData(List<String> roomNames, List<String> roomIds) {
        this.roomNames = roomNames;
        this.roomIds = roomIds;
    }
    @Override
    public int getCount() {
        return roomIds.size();
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
            convertView = inflater.inflate(R.layout.activity_item_list, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.roomName);
        TextView idTextView = (TextView) convertView.findViewById(R.id.roomId);
        nameTextView.setText(roomNames.get(position));
        idTextView.setText(roomIds.get(position));
        return convertView;
    }
}
