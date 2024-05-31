package com.DistributedSystems.room_booking_android_app.reservationsPerArea;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ReservationAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReservationsPerAreaActivity extends AppCompatActivity implements ReservationsPerAreaView {
    String managerName;
    EditText managerText;

    ReservationAdapter adapter;
    ListView reservationListView;
    String stDate;
    String depDate;
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
        setContentView(R.layout.activity_reservationsperarea);

        final ReservationsPerAreaPresenter presenter = new ReservationsPerAreaPresenter(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            stDate = intent.getStringExtra("startDate");
            depDate = intent.getStringExtra("depDate");
        }

        reservationListView = findViewById(R.id.reservationPerAreaList);
        adapter = new ReservationAdapter(getLayoutInflater(), reservationStrings);
        reservationListView.setAdapter(adapter);

        ReservationsPerAreaThread t1 = new ReservationsPerAreaThread(myHandler, reservationStrings, stDate, depDate);
        t1.start();

        findViewById(R.id.reservationPerAreaExitButton).setOnClickListener(v -> presenter.onExit());

    }

    public void exit() {
        Intent intent = new Intent(com.DistributedSystems.room_booking_android_app.reservationsPerArea.ReservationsPerAreaActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }
}

