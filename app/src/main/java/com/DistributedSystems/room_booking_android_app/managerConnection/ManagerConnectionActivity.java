package com.DistributedSystems.room_booking_android_app.managerConnection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.managerReservations.ManagerReservationsActivity;
import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.addDates.AddDatesActivity;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;
import com.DistributedSystems.room_booking_android_app.insertion.InsertRoomActivity;
import com.DistributedSystems.room_booking_android_app.reservationsPerArea.ReservationsPerAreaActivity;

public class ManagerConnectionActivity extends AppCompatActivity implements ManagerConnectionView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        final ManagerConnectionPresenter presenter = new ManagerConnectionPresenter(this);

        findViewById(R.id.insertion_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onInsertRoom();
            }
        });

        findViewById(R.id.exitMaster).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onExit();
            }
        });

        findViewById(R.id.dates).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onAddDates();
            }
        });

        findViewById(R.id.reservations_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onGetManagersReservations();
            }
        });

        findViewById(R.id.areaReservations).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onGetReservationsPerArea();
            }
        });

    }

    public void insertRoom() {
        Intent intent = new Intent(ManagerConnectionActivity.this, InsertRoomActivity.class);
        startActivity(intent);
    }

    public void exit() {
        Intent intent = new Intent(ManagerConnectionActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    public void addDates(){
        Intent intent = new Intent(ManagerConnectionActivity.this, AddDatesActivity.class);
        startActivity(intent);
    }

    public void getManagersReservations(){
        Intent intent = new Intent(ManagerConnectionActivity.this, ManagerReservationsActivity.class);
        startActivity(intent);
    }

    public void getReservationsPerArea(){
        Intent intent = new Intent(ManagerConnectionActivity.this, ReservationsPerAreaActivity.class);
        startActivity(intent);
    }
}
