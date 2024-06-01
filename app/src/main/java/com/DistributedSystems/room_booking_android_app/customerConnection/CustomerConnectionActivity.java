package com.DistributedSystems.room_booking_android_app.customerConnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;
import com.DistributedSystems.room_booking_android_app.rating.RateARoomActivity;
import com.DistributedSystems.room_booking_android_app.searchRoom.SearchRoomActivity;

public class CustomerConnectionActivity extends AppCompatActivity implements CustomerConnectionView {
    Button rateButton, searchButton, exitButton;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        rateButton = findViewById(R.id.rate);
        searchButton = findViewById(R.id.search);
        exitButton = findViewById(R.id.exitButton);

        final CustomerConnectionPresenter presenter = new CustomerConnectionPresenter(this);

        rateButton.setOnClickListener(v -> presenter.onRatingButton());

        searchButton.setOnClickListener(v -> presenter.onSearchButton());

        exitButton.setOnClickListener(v -> presenter.onExitButton());
    }

    public void ratingButton() {
        RatingCodeSentThread t2 = new RatingCodeSentThread();
        t2.start();
        Intent intent = new Intent(CustomerConnectionActivity.this, RateARoomActivity.class);
        startActivity(intent);
    }

    public void searchButton() {
        SearchCodeSentThread t1 = new SearchCodeSentThread();
        t1.start();
        Intent intent = new Intent(CustomerConnectionActivity.this, SearchRoomActivity.class);
        startActivity(intent);
    }

    @Override
    public void exitButton() {
        ExitCodeSentThread t3 = new ExitCodeSentThread();
        t3.start();
        Intent intent = new Intent(CustomerConnectionActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
