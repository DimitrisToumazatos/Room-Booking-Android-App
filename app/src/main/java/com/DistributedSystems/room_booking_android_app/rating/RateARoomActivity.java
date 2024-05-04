package com.DistributedSystems.room_booking_android_app.rating;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;

public class RateARoomActivity extends AppCompatActivity implements RateARoomView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        final RateARoomPresenter presenter = new RateARoomPresenter(this);
    }
}
