package com.DistributedSystems.room_booking_android_app.addDates;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddDatesPresenter {

    private final AddDatesView view;


    public AddDatesPresenter(AddDatesView view) {
        this.view = view;
    }

    void onAddDates(String roomId, String startingDate, String departureDate, Boolean addDatesButtonEnabled){
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";

        if (!addDatesButtonEnabled) {
            view.showToast(ERROR_EMPTY_FIELD_MSG);
            return;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localRoomStDate = LocalDate.parse(startingDate, dateFormatter);
        LocalDate localRoomDepDate = LocalDate.parse(departureDate, dateFormatter);

        if (LocalDate.now().isAfter(localRoomStDate) || LocalDate.now().isAfter(localRoomDepDate)){
            view.showToast(ERROR_DATE_PASSED_MSG);
        }
        else if (localRoomStDate.isAfter(localRoomDepDate)){
            view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
        }
        else {
            view.addDates();
        }
    }
}
