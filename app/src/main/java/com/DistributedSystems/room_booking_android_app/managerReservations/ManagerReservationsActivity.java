package com.DistributedSystems.room_booking_android_app.managerReservations;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ReservationAdapter;


import java.util.ArrayList;
import java.util.List;

public class ManagerReservationsActivity extends AppCompatActivity implements ManagerReservationsView {

    ReservationAdapter adapter;
    Button exitButton;
    ListView reservationListView;
    List<String> reservationStrings = new ArrayList<>();

    public Handler myHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            adapter.notifyDataSetChanged();
            return false;
        }
    });

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        exitButton = findViewById(R.id.reservationListExitButton);
        reservationListView = findViewById(R.id.reservationList);

        final ManagerReservationsPresenter presenter = new ManagerReservationsPresenter(this);

        adapter = new ReservationAdapter(getLayoutInflater(), reservationStrings);
        reservationListView.setAdapter(adapter);

        new ManagerReservationsThread(myHandler, reservationStrings).start();

        exitButton.setOnClickListener(v -> presenter.onExit());
    }

    public void exit() {
        Intent intent = new Intent(ManagerReservationsActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }
}
