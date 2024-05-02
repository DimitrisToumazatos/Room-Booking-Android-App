package com.DistributedSystems.room_booking_android_app.customerConnection;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionPresenter;

public class CustomerConnectionActivity extends AppCompatActivity implements CustomerConnectionView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        final CustomerConnectionPresenter presenter = new CustomerConnectionPresenter(this);
    }
}
