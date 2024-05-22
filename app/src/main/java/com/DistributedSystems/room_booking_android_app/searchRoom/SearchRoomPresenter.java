package com.DistributedSystems.room_booking_android_app.searchRoom;

import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionView;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchRoomPresenter {

    private final SearchRoomView view;
    String search;


    public SearchRoomPresenter(SearchRoomView view) {
        this.view = view;
    }

    void onSearchRoom(String stars, String startPrice, String endPrice, String startingDate, String departureDate, String area, String persons, Boolean defaultSearch) throws JSONException {
        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        String ERROR_INVALID_STARS_MSG = "The number of stars you gave is not between 1-5.";
        String ERROR_INVALID_CAPACITY_MSG = "Please give a positive number of persons!";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";

        if (defaultSearch) {
            search = "default search";
        }else {
            int localStars = Integer.parseInt(stars);
            int localStartPrice = Integer.parseInt(startPrice);
            int localEndPrice = Integer.parseInt(endPrice);
            int localPersons = Integer.parseInt(persons);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate localStDate = LocalDate.parse(startingDate, dateFormatter);
            LocalDate localDepDate = LocalDate.parse(departureDate, dateFormatter);

            if (localStars <= 0 || localStars >= 6) {
                view.showToast(ERROR_INVALID_STARS_MSG);
                return;
            } else if (localStartPrice <= 0 || localEndPrice <= 0) {
                view.showToast(ERROR_INVALID_PRICE_MSG);
                return;
            } else if (localPersons <= 0) {
                view.showToast(ERROR_INVALID_CAPACITY_MSG);
                return;
            } else if (LocalDate.now().isAfter(localStDate) || LocalDate.now().isAfter(localDepDate)) {
                view.showToast(ERROR_DATE_PASSED_MSG);
                return;
            } else if (localStDate.isAfter(localDepDate)) {
                view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
                return;
            }
            JSONObject filtersJson = new JSONObject();
            if(area.isEmpty()){
                filtersJson.put("area", "-");
            }else{
                filtersJson.put("area",area);
            }

            if(startingDate.isEmpty()){
                filtersJson.put("Starting Date", null);
            }else{
                filtersJson.put("Starting Date", localStDate);
            }

            if(departureDate.isEmpty()){
                filtersJson.put("Departure Date", null);
            }else{
                filtersJson.put("Departure Date", localDepDate);
            }

            if(persons.isEmpty()){
                filtersJson.put("persons", -1);
            }else{
                filtersJson.put("persons", localPersons);
            }

            if(startPrice.isEmpty()){
                filtersJson.put("Starting price", -1);
            }else{
                filtersJson.put("Starting price", localStartPrice);
            }

            if(endPrice.isEmpty()){
                filtersJson.put("Final price", -1);
            }else{
                filtersJson.put("Final Price", localEndPrice);
            }

            if(stars.isEmpty()){
                filtersJson.put("stars", -1);
            }else{
                filtersJson.put("area", localStars);
            }
            search = filtersJson.toString();
        }
        view.searchRoom(search);
    }
}
