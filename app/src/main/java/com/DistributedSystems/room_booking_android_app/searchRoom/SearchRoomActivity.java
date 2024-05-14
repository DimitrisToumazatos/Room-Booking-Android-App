package com.DistributedSystems.room_booking_android_app.searchRoom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionPresenter;

public class SearchRoomActivity extends AppCompatActivity implements SearchRoomView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);

        final SearchRoomPresenter presenter = new SearchRoomPresenter(this);

    }

}
