package com.DistributedSystems.room_booking_android_app.filteredRooms;


import com.DistributedSystems.room_booking_android_app.utils.Room;

import java.util.ArrayList;

public class FilteredRoomsPresenter {

    private final FilteredRoomsView view;

    public FilteredRoomsPresenter(FilteredRoomsView view) {
        this.view = view;
    }

    void onReserveButton(String roomId, String stDate, String depDate, String customerName, boolean reservationButtonEnabled, ArrayList<Room> roomObjects) {
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";
        String ERROR_INVALID_ROOM_ID = "Please give a room id among the existing ones";

        if (!reservationButtonEnabled) {
            view.showToast(ERROR_EMPTY_FIELD_MSG);
            return;
        }


        view.reserveButton();
    }

    void onExit() {
        view.exitButton();
    }
}
