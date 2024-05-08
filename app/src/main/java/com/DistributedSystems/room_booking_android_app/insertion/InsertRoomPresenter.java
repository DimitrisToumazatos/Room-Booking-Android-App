package com.DistributedSystems.room_booking_android_app.insertion;

import android.os.Build;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class InsertRoomPresenter {
    private final InsertRoomView view;

    public InsertRoomPresenter(InsertRoomView view) {
        this.view = view;
    }

    void onInsertRoom(String roomName, String roomArea, String roomImage, String roomPrice, String roomCapacity, String roomStDate, String roomDepDate) {
        int localRoomPrice = Integer.parseInt(roomPrice);
        int localRoomCapacity = Integer.parseInt(roomCapacity);
        LocalDate localRoomStDate = LocalDate.parse(roomStDate);
        LocalDate localRoomDepDate = LocalDate.parse(roomDepDate);

        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!\nPlease give right dates.";
        String ERROR_INVALID_CAPACITY_MSG = "Please give a positive number of persons!";
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";
        if (localRoomPrice <= 0) {
            view.showToast(ERROR_INVALID_PRICE_MSG);
        }
        else if (localRoomCapacity<=0){
            view.showToast(ERROR_INVALID_CAPACITY_MSG);
        }
        else if (LocalDate.now().isAfter(localRoomStDate)){
            view.showToast(ERROR_DATE_PASSED_MSG);
        }
        else if (LocalDate.now().isAfter(localRoomDepDate)){
            view.showToast(ERROR_DATE_PASSED_MSG);
        }
        else if (localRoomStDate.isAfter(localRoomDepDate)){
            view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
        }
        else if(roomName.isEmpty() || roomArea.isEmpty() || roomImage.isEmpty() || roomPrice.isEmpty() || roomCapacity.isEmpty() || roomStDate.isEmpty() || roomDepDate.isEmpty()){
            view.showToast(ERROR_EMPTY_FIELD_MSG);
        }
        else {
            view.successfulInsertion();
        }
    }
}



