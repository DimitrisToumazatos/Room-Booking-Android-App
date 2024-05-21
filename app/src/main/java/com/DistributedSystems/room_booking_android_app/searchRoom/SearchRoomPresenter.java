package com.DistributedSystems.room_booking_android_app.searchRoom;

import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchRoomPresenter {

    private final SearchRoomView view;


    public SearchRoomPresenter(SearchRoomView view) {
        this.view = view;
    }

    void onSearchRoom(String stars, String startPrice, String endPrice, String startingDate, String departureDate, String area, String persons, Boolean searchButtonEnabled) {
        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        String ERROR_INVALID_STARS_MSG = "The number of stars you gave is not between 1-5.";
        String ERROR_INVALID_CAPACITY_MSG = "Please give a positive number of persons!";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";

        if (!searchButtonEnabled) {
            view.showToast(ERROR_EMPTY_FIELD_MSG);
            return;
        }

        int localStars = Integer.parseInt(stars);
        int localStartPrice = Integer.parseInt(startPrice);
        int localEndPrice = Integer.parseInt(endPrice);
        int localPersons = Integer.parseInt(persons);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localStDate = LocalDate.parse(startingDate, dateFormatter);
        LocalDate localDepDate = LocalDate.parse(departureDate, dateFormatter);

        if (localStars <= 0 || localStars >= 0) {
            view.showToast(ERROR_INVALID_STARS_MSG);
        } else if (localStartPrice <= 0 || localEndPrice <= 0) {
            view.showToast(ERROR_INVALID_PRICE_MSG);
        } else if (localPersons <= 0) {
            view.showToast(ERROR_INVALID_CAPACITY_MSG);
        } else if (LocalDate.now().isAfter(localStDate) || LocalDate.now().isAfter(localDepDate)) {
            view.showToast(ERROR_DATE_PASSED_MSG);
        } else if (localStDate.isAfter(localDepDate)) {
            view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
        } else {
            view.searchRoom();
        }
    }
}
