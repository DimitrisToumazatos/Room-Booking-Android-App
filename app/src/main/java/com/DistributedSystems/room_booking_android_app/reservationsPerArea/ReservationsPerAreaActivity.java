package com.DistributedSystems.room_booking_android_app.reservationsPerArea;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;

public class ReservationsPerAreaActivity extends AppCompatActivity implements ReservationsPerAreaView {

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationsperarea);

        final ReservationsPerAreaPresenter presenter = new ReservationsPerAreaPresenter(this);

        findViewById(R.id.reservationPerAreaExitButton).setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                    presenter.onExit();
                }
        });

    }

    public void exit() {
        Intent intent = new Intent(com.DistributedSystems.room_booking_android_app.reservationsPerArea.ReservationsPerAreaActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }
}

