package com.DistributedSystems.room_booking_android_app.managerConnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.datePerArea.DatesPerAreaActivity;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;
import com.DistributedSystems.room_booking_android_app.managerReservations.ManagerReservationsActivity;
import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.addDates.AddDatesActivity;
import com.DistributedSystems.room_booking_android_app.insertion.InsertRoomActivity;

public class ManagerConnectionActivity extends AppCompatActivity implements ManagerConnectionView {
    Button insertionButton, addDatesButton, reservationsButton, areaReservationsButton, exitButton;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        insertionButton = findViewById(R.id.insertion_button);
        addDatesButton = findViewById(R.id.dates);
        reservationsButton = findViewById(R.id.reservations_button);
        areaReservationsButton = findViewById(R.id.areaReservations);
        exitButton = findViewById(R.id.exitButtonManager);

        final ManagerConnectionPresenter presenter = new ManagerConnectionPresenter(this);

        insertionButton.setOnClickListener(v -> presenter.onInsertRoom());

        addDatesButton.setOnClickListener(v -> presenter.onAddDates());

        reservationsButton.setOnClickListener(v -> presenter.onGetManagersReservations());

        areaReservationsButton.setOnClickListener(v -> presenter.onGetReservationsPerArea());

        exitButton.setOnClickListener(v -> presenter.onExit());
    }

    public void insertRoom() {
        InsertCodeSentThread t1 = new InsertCodeSentThread();
        t1.start();
        Intent intent = new Intent(ManagerConnectionActivity.this, InsertRoomActivity.class);
        startActivity(intent);
    }

    public void addDates(){
        AddDatesCodeSentThread t2 = new AddDatesCodeSentThread();
        t2.start();
        Intent intent = new Intent(ManagerConnectionActivity.this, AddDatesActivity.class);
        startActivity(intent);
    }

    public void getManagersReservations(){
        ReservationsCodeSentThread t3 = new ReservationsCodeSentThread();
        t3.start();
        Intent intent = new Intent(ManagerConnectionActivity.this, ManagerReservationsActivity.class);
        startActivity(intent);
    }

    public void getReservationsPerArea(){
        ReservationsPerAreaCodeSentThread t4 = new ReservationsPerAreaCodeSentThread();
        t4.start();
        Intent intent = new Intent(ManagerConnectionActivity.this, DatesPerAreaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onExitButton() {
        ExitManagerCodeSentThread t5 = new ExitManagerCodeSentThread();
        t5.start();
        Intent intent = new Intent(ManagerConnectionActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
