package com.DistributedSystems.room_booking_android_app.insertion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsertRoomPresenter {
    private final InsertRoomView view;

    public InsertRoomPresenter(InsertRoomView view) {
        this.view = view;
    }

    void onInsertRoom(String roomName, String roomArea, String roomImage, String roomPrice, String roomCapacity, String roomStDate, String roomDepDate, Boolean insertButtonEnabled) {
        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        String ERROR_INVALID_CAPACITY_MSG = "Please give a positive number of persons!";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";

        if (!insertButtonEnabled) {
            view.showToast(ERROR_EMPTY_FIELD_MSG);
            return;
        }

        int localRoomPrice = Integer.parseInt(roomPrice);
        int localRoomCapacity = Integer.parseInt(roomCapacity);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localRoomStDate = LocalDate.parse(roomStDate, dateFormatter);
        LocalDate localRoomDepDate = LocalDate.parse(roomDepDate, dateFormatter);

        if (localRoomPrice <= 0) {
            view.showToast(ERROR_INVALID_PRICE_MSG);
        }
        else if (localRoomCapacity<=0){
            view.showToast(ERROR_INVALID_CAPACITY_MSG);
        }
        else if (LocalDate.now().isAfter(localRoomStDate) || LocalDate.now().isAfter(localRoomDepDate)){
            view.showToast(ERROR_DATE_PASSED_MSG);
        }
        else if (localRoomStDate.isAfter(localRoomDepDate)){
            view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
        }
        else {
            view.successfulInsertion();
        }
    }
}
