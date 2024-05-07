package com.DistributedSystems.room_booking_android_app.insertion;


import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;

import java.time.LocalDate;

public class InsertRoomPresenter {
    private final InsertRoomView view;

    public InsertRoomPresenter(InsertRoomView view) {
        this.view = view;
    }

    void onInsertRoom(
            String roomName,
            String roomArea,
            String roomImage,
            int roomPrice,
            int roomCapacity,
            LocalDate roomStDate,
            LocalDate roomDepDate
    ) {
        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        if (roomPrice <= 0) {
            view.showToast(ERROR_INVALID_PRICE_MSG);
        }
        view.successfulInsertion();
    }
}
