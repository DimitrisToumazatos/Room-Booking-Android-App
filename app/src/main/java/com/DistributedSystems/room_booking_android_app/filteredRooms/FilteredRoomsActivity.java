package com.DistributedSystems.room_booking_android_app.filteredRooms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;


public class FilteredRoomsActivity extends AppCompatActivity implements FilteredRoomsView {

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_rooms);

        final FilteredRoomsPresenter presenter = new FilteredRoomsPresenter(this);

        findViewById(R.id.reserveButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onReserveButton();
            }
        });
    }

    public void reserveButton() {
        Intent intent = new Intent(FilteredRoomsActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}
