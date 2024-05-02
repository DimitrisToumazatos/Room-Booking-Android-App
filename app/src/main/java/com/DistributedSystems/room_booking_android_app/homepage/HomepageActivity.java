package com.DistributedSystems.room_booking_android_app.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;

public class HomepageActivity extends AppCompatActivity implements HomepageView {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        final HomepagePresenter presenter = new HomepagePresenter(this);

        findViewById(R.id.home_manager_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManagerConnection();
            }
        });

        findViewById(R.id.home_customer_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onCustomerConnection();
            }
        });
    }

    @Override
    public void managerConnection() {
        Intent intent = new Intent(HomepageActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }

    @Override
    public void customerConnection() {
        Intent intent = new Intent(HomepageActivity.this, CustomerConnectionActivity.class);
        startActivity(intent);
    }
}
