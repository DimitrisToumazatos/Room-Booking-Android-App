package com.DistributedSystems.room_booking_android_app.managerReservations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;

public class ManagerReservationsActivity extends AppCompatActivity implements ManagerReservationsView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        final ManagerReservationsPresenter presenter = new ManagerReservationsPresenter(this);

        findViewById(R.id.reservationListExitButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onExit();
            }
        });
    }

    public void exit() {
        Intent intent = new Intent(ManagerReservationsActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }
}
