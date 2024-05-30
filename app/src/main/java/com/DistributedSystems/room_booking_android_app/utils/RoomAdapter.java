package com.DistributedSystems.room_booking_android_app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.DistributedSystems.room_booking_android_app.R;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends BaseAdapter {
    List<String> roomStrings;
    List<byte []> roomImages;
    LayoutInflater inflater;
    static JSONParser parser = new JSONParser();
    public RoomAdapter(LayoutInflater inflater, List<String> roomStrings, List<byte[]> roomImages) {
        this.inflater = inflater;
        this.roomStrings = roomStrings;
        this.roomImages = roomImages;
    }

    @Override
    public int getCount() {
        return roomStrings.size();
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
        TextView priceTextView = (TextView) convertView.findViewById(R.id.price);
        TextView areaTextView = (TextView) convertView.findViewById(R.id.area);
        TextView capacityTextView = (TextView) convertView.findViewById(R.id.capacity);
        TextView starsTextView = (TextView) convertView.findViewById(R.id.stars);
        TextView noOfReviewsTextView = (TextView) convertView.findViewById(R.id.noOfReviews);
        TextView fromTextView = (TextView) convertView.findViewById(R.id.from);
        TextView toTextView = (TextView) convertView.findViewById(R.id.to);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.roomImage);

        ArrayList<ArrayList<LocalDate>> availableDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            JSONObject json = (JSONObject) parser.parse(roomStrings.get(position));

            nameTextView.setText((String) json.get("roomName"));
            idTextView.setText((Long.toString((Long) json.get("id"))));
            priceTextView.setText((Long.toString((Long) json.get("price"))));
            areaTextView.setText((String) json.get("area"));
            capacityTextView.setText((Long.toString((Long) json.get("noOfPersons"))));
            starsTextView.setText((Long.toString((Long) json.get("stars"))));
            noOfReviewsTextView.setText((Long.toString((Long) json.get("noOfReviews"))));
            availableDates = (ArrayList<ArrayList<LocalDate>>) json.get("availableDates");
            fromTextView.setText((availableDates.get(0).get(0)).format(formatter));
            toTextView.setText((availableDates.get(0).get(1)).format(formatter));

            Bitmap bmp = BitmapFactory.decodeByteArray(roomImages.get(position), 0, roomImages.get(position).length);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 130, 130, false));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return convertView;
    }
}
