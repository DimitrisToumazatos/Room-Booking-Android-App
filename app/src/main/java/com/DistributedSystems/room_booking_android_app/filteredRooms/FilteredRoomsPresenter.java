package com.DistributedSystems.room_booking_android_app.filteredRooms;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.DistributedSystems.room_booking_android_app.utils.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FilteredRoomsPresenter {

    private final FilteredRoomsView view;

    public FilteredRoomsPresenter(FilteredRoomsView view) {
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void onReserveButton(String roomId, String stDate, String depDate, boolean reservationButtonEnabled, ArrayList<Room> roomObjects) {
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";
        String ERROR_INVALID_ROOM_ID = "Please give a room id among the existing ones";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";

        if (!reservationButtonEnabled) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startingDate = LocalDate.parse(stDate,formatter), departureDate = LocalDate.parse(depDate,formatter);

            int roomIdInteger = Integer.parseInt(roomId);
            boolean found = false;
            for (Room room : roomObjects){
                if(roomIdInteger == ((Number)room.getId()).intValue()){
                    found = true;
                }
            }
            if(!found){
                view.showToast(ERROR_INVALID_ROOM_ID);
            }else if (LocalDate.now().isAfter(startingDate) || LocalDate.now().isAfter(departureDate)){
                view.showToast(ERROR_DATE_PASSED_MSG);
            } else if (startingDate.isAfter(departureDate)) {
                view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
            }else {
                view.showToast(ERROR_EMPTY_FIELD_MSG);
            }
            return;
        }
        view.reserveButton();
    }

    void onExit() {
        view.showToast("Exit");
        view.exitButton();
    }
}
