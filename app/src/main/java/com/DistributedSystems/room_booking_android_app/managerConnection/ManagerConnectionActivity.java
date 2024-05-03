package com.DistributedSystems.room_booking_android_app.managerConnection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.addDates.AddDatesActivity;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;
import com.DistributedSystems.room_booking_android_app.insertion.InsertRoomActivity;

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
}
