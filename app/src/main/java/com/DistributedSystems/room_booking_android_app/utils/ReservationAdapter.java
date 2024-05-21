package com.DistributedSystems.room_booking_android_app.utils;

import static com.DistributedSystems.room_booking_android_app.utils.RoomAdapter.parser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.DistributedSystems.room_booking_android_app.R;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends BaseAdapter {

    ArrayList<String> reservationStrings;
    LayoutInflater inflater;
    static JSONParser parser = new JSONParser();


    public ReservationAdapter(LayoutInflater inflater, ArrayList<String> reservationStrings) {
        this.inflater = inflater;
        this.reservationStrings = reservationStrings;
    }

    @Override
    public int getCount() {
        return reservationStrings.size();
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
        TextView startDateTextView = (TextView) convertView.findViewById(R.id.startDate);
        TextView departureDateTextView = (TextView) convertView.findViewById(R.id.depDate);
        TextView idTextView = (TextView) convertView.findViewById(R.id.roomId);

        try {
            JSONObject json = (JSONObject) parser.parse(reservationStrings.get(position));
            nameTextView.setText((String) json.get("customerName"));
            startDateTextView.setText((String) json.get("startingDate"));
            departureDateTextView.setText((String) json.get("departureDate"));
            idTextView.setText((Long.toString((Long) json.get("id"))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return convertView;
    }
}

