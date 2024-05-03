package com.DistributedSystems.room_booking_android_app.insertion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;

public class InsertRoomActivity extends AppCompatActivity implements InsertRoomView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);

        final InsertRoomPresenter presenter = new InsertRoomPresenter(this);
    }
}
