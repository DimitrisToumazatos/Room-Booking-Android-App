package com.DistributedSystems.room_booking_android_app.managerConnection;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.homepage.HomepagePresenter;

public class ManagerConnectionActivity extends AppCompatActivity implements ManagerConnectionView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        final ManagerConnectionPresenter presenter = new ManagerConnectionPresenter(this);
    }
}
