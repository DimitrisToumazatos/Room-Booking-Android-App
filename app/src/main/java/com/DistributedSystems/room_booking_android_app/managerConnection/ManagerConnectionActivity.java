package com.DistributedSystems.room_booking_android_app.managerConnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.datePerArea.DatesPerAreaActivity;
import com.DistributedSystems.room_booking_android_app.managerReservations.ManagerReservationsActivity;
import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.addDates.AddDatesActivity;
import com.DistributedSystems.room_booking_android_app.insertion.InsertRoomActivity;
import com.DistributedSystems.room_booking_android_app.reservationsPerArea.ReservationsPerAreaActivity;
import com.DistributedSystems.room_booking_android_app.utils.TerminateAppThread;

public class ManagerConnectionActivity extends AppCompatActivity implements ManagerConnectionView {

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        final ManagerConnectionPresenter presenter = new ManagerConnectionPresenter(this);

        findViewById(R.id.insertion_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onInsertRoom();
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

        findViewById(R.id.exitButtonManager).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onExit();
            }
        });
    }

    public void insertRoom() {
        Intent intent = new Intent(ManagerConnectionActivity.this, InsertRoomActivity.class);
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
        Intent intent = new Intent(ManagerConnectionActivity.this, DatesPerAreaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onExitButton() {
        new TerminateAppThread().start();
    }
}
