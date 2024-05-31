package com.DistributedSystems.room_booking_android_app.datePerArea;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatesPerAreaPresenter {
    private final DatesPerAreaView view;

    public DatesPerAreaPresenter(DatesPerAreaView view){this.view = view; }

    void onSearchReservations(String roomStDate, String roomDepDate, Boolean searchButtonEnabled) {
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";

        if (!searchButtonEnabled) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate localRoomStDate = LocalDate.parse(roomStDate, dateFormatter);
            LocalDate localRoomDepDate = LocalDate.parse(roomDepDate, dateFormatter);

            if (LocalDate.now().isAfter(localRoomStDate) || LocalDate.now().isAfter(localRoomDepDate)){
                view.showToast(ERROR_DATE_PASSED_MSG);
            } else if (localRoomStDate.isAfter(localRoomDepDate)){
                view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
            }else {
                view.showToast(ERROR_EMPTY_FIELD_MSG);
            }
            return;
        }
        view.searchReservations();

    }
}
