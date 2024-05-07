package com.DistributedSystems.room_booking_android_app.insertion;

public class InsertRoomPresenter {
    private final InsertRoomView view;

    public InsertRoomPresenter(InsertRoomView view) {
        this.view = view;
    }

    void onInsertRoom(String roomName, String roomArea, String roomImage, String roomPrice, String roomCapacity, String roomStDate, String roomDepDate) {
        int localRoomPrice = Integer.parseInt(roomPrice);
        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        if (localRoomPrice <= 0) {
            view.showToast(ERROR_INVALID_PRICE_MSG);
        } else {
            view.successfulInsertion();
        }
    }
}
