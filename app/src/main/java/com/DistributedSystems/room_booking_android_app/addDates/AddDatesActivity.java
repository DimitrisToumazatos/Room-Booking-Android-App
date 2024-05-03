package com.DistributedSystems.room_booking_android_app.addDates;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionPresenter;

public class AddDatesActivity extends AppCompatActivity implements AddDatesView {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datesaddition);

        final AddDatesPresenter presenter = new AddDatesPresenter(this);
    }
}
