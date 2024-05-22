package com.DistributedSystems.room_booking_android_app.customerConnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.rating.RateARoomActivity;
import com.DistributedSystems.room_booking_android_app.searchRoom.SearchRoomActivity;
import com.DistributedSystems.room_booking_android_app.utils.TerminateAppThread;

public class CustomerConnectionActivity extends AppCompatActivity implements CustomerConnectionView {

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        final CustomerConnectionPresenter presenter = new CustomerConnectionPresenter(this);

        findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onRatingButton();
            }
        });

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSearchButton();
            }
        });

        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onExitButton();
            }
        });
    }

    public void ratingButton() {
        Intent intent = new Intent(CustomerConnectionActivity.this, RateARoomActivity.class);
        startActivity(intent);
    }

    public void searchButton() {
        Intent intent = new Intent(CustomerConnectionActivity.this, SearchRoomActivity.class);
        startActivity(intent);
    }

    @Override
    public void exitButton() {
        new TerminateAppThread().start();
    }
}
