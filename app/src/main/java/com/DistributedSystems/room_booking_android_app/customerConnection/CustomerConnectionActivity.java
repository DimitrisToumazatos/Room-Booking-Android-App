package com.DistributedSystems.room_booking_android_app.customerConnection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionPresenter;

public class CustomerConnectionActivity extends AppCompatActivity implements CustomerConnectionView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        final CustomerConnectionPresenter presenter = new CustomerConnectionPresenter(this);

        findViewById(R.id.exitCustomer).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onExit();
            }
        });
    }

    public void exit() {
        Intent intent = new Intent(CustomerConnectionActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}
