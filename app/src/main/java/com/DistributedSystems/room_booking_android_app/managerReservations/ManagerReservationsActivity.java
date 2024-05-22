package com.DistributedSystems.room_booking_android_app.managerReservations;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.utils.Reservation;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ReservationAdapter;


import java.util.ArrayList;

public class ManagerReservationsActivity extends AppCompatActivity implements ManagerReservationsView {

    ReservationAdapter adapter;
    ListView reservationListView;
    ArrayList<String> reservationStrings = new ArrayList<>();
    ArrayList<Reservation> reservations;


    public Handler myHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            reservations = (ArrayList<Reservation>) message.getData().getSerializable("Reservations");
            adapter.notifyDataSetChanged();
            return false;
        }
    });

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        final ManagerReservationsPresenter presenter = new ManagerReservationsPresenter(this);

        reservationListView = findViewById(R.id.reservationList);
        adapter = new ReservationAdapter(getLayoutInflater(), reservationStrings);
        reservationListView.setAdapter(adapter);

        ManagerReservationsThread t1 = new ManagerReservationsThread(myHandler, reservationStrings);
        t1.start();

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
