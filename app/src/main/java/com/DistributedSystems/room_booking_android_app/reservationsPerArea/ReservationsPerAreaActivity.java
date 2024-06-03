package com.DistributedSystems.room_booking_android_app.reservationsPerArea;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ReservationAdapter;
import com.DistributedSystems.room_booking_android_app.utils.ReservationPerAreaAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationsPerAreaActivity extends AppCompatActivity implements ReservationsPerAreaView {
    Button exitButton;
    ReservationPerAreaAdapter adapter;
    ListView reservationListView;
    String stDate;
    String depDate;
    HashMap<String, Integer> reservationStrings = new HashMap<>();

    public Handler myHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            reservationStrings.putAll((HashMap<String, Integer>) message.getData().getSerializable("reservationStrings"));

            adapter.notifyDataSetChanged();
            return false;
        }
    });

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationsperarea);

        reservationListView = findViewById(R.id.reservationPerAreaList);
        exitButton = findViewById(R.id.reservationPerAreaExitButton);

        final ReservationsPerAreaPresenter presenter = new ReservationsPerAreaPresenter(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            stDate = intent.getStringExtra("startDate");
            depDate = intent.getStringExtra("depDate");
        }

        adapter = new ReservationPerAreaAdapter(getLayoutInflater(), reservationStrings);
        reservationListView.setAdapter(adapter);

        ReservationsPerAreaThread t1 = new ReservationsPerAreaThread(myHandler, reservationStrings, stDate, depDate);
        t1.start();

        exitButton.setOnClickListener(v -> presenter.onExit());

    }

    public void exit() {
        Intent intent = new Intent(com.DistributedSystems.room_booking_android_app.reservationsPerArea.ReservationsPerAreaActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }
}

