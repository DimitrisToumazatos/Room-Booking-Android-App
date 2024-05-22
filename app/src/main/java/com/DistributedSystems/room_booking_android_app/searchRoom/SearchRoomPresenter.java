package com.DistributedSystems.room_booking_android_app.searchRoom;

import android.os.Build;

import androidx.annotation.RequiresApi;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    void onSearchRoom(String stars, String startPrice, String endPrice, String startingDate, String departureDate, String area, String persons) throws JSONException {
        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        String ERROR_INVALID_STARS_MSG = "The number of stars you gave is not between 1-5.";
        String ERROR_INVALID_CAPACITY_MSG = "Please give a positive number of persons!";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";
        String ERROR_BOTH_DATES_MSG = "Please enter both dates!";

        if (stars.isEmpty() && startPrice.isEmpty() && endPrice.isEmpty() && startingDate.isEmpty() && departureDate.isEmpty() && area.isEmpty() && persons.isEmpty()) {
            search = "default search";
        } else {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


            JSONObject filtersJson = new JSONObject();
            if (area.isEmpty()){
                filtersJson.put("area", "-");
            }else{
                filtersJson.put("area", area);
            }

            if ((startingDate.isEmpty() && !departureDate.isEmpty()) || !startingDate.isEmpty() && departureDate.isEmpty()) {
                view.showToast(ERROR_BOTH_DATES_MSG);
                return;
            }

            LocalDate localStDate = null;
            if(startingDate.isEmpty()){
                filtersJson.put("Starting Date", null);
            }else{
                localStDate = LocalDate.parse(startingDate, dateFormatter);

                if (LocalDate.now().isAfter(localStDate)) {
                    view.showToast(ERROR_DATE_PASSED_MSG);
                    return;
                }

                filtersJson.put("Starting Date", localStDate);
            }

            LocalDate localDepDate = null;
            if (departureDate.isEmpty()){
                filtersJson.put("Departure Date", null);
            }else{
                localDepDate = LocalDate.parse(departureDate, dateFormatter);

                if (LocalDate.now().isAfter(localDepDate)) {
                    view.showToast(ERROR_DATE_PASSED_MSG);
                    return;
                }

                filtersJson.put("Departure Date", localDepDate);
            }

            if (localDepDate != null && localStDate != null) {

                if (localStDate.isAfter(localDepDate)) {
                    view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
                    return;
                }
            }


            if(persons.isEmpty()){
                filtersJson.put("persons", -1);
            }else{
                int localPersons = Integer.parseInt(persons);

                if (localPersons <= 0) {
                    view.showToast(ERROR_INVALID_CAPACITY_MSG);
                    return;
                }

                filtersJson.put("persons", localPersons);
            }

            if (startPrice.isEmpty()){
                filtersJson.put("Starting price", -1);
            }else{
                int localStartPrice = Integer.parseInt(startPrice);

                if (localStartPrice <= 0) {
                    view.showToast(ERROR_INVALID_PRICE_MSG);
                    return;
                }

                filtersJson.put("Starting price", localStartPrice);
            }

            if(endPrice.isEmpty()){
                filtersJson.put("Final price", -1);
            }else{
                int localEndPrice = Integer.parseInt(endPrice);

                if (localEndPrice <= 0) {
                    view.showToast(ERROR_INVALID_PRICE_MSG);
                    return;
                }

                filtersJson.put("Final Price", localEndPrice);
            }

            if(stars.isEmpty()){
                filtersJson.put("stars", -1);
            }else{
                int localStars = Integer.parseInt(stars);

                if (localStars <= 0 || localStars >= 6) {
                    view.showToast(ERROR_INVALID_STARS_MSG);
                    return;
                }

                filtersJson.put("stars", localStars);
            }
            search = filtersJson.toString();
        }
        view.searchRoom(search);
    }
}
