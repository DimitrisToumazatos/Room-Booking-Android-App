package com.DistributedSystems.room_booking_android_app.reservationsPerArea;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;

public class ReservationsPerAreaActivity extends AppCompatActivity implements ReservationsPerAreaView {
    String managerName;
    EditText managerText;
    ImageView image;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationsperarea);

        final ReservationsPerAreaPresenter presenter = new ReservationsPerAreaPresenter(this);

        image = findViewById(R.id.imageViewEx);
        Thread t1 = new GetServerImage(image);
        t1.start();
        findViewById(R.id.reservationPerAreaExitButton).setOnClickListener(v -> presenter.onExit());

    }

    public void exit() {
        Intent intent = new Intent(com.DistributedSystems.room_booking_android_app.reservationsPerArea.ReservationsPerAreaActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }
}

