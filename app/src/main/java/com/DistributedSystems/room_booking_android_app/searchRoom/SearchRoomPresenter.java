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
        String ERROR_WRONG_DATES_GIVEN = "The departure date you gave is after the starting date";
        String ERROR_WRONG_START_DATE_INPUT = "The start date you gave does not comply to a valid date input";
        String ERROR_WRONG_DEP_DATE_INPUT = "The departure date you gave does not comply to a valid date input";
        String ERROR_PERSONS_NOT_NUMERICAL = "The persons input you gave is not numerical";
        String ERROR_START_PRICE_NOT_NUMERICAL = "The starting price input you gave is not numerical";
        String ERROR_END_PRICE_NOT_NUMERICAL = "The end price input you gave is not numerical";
        String ERROR_LOCAL_STARS_NOT_NUMERICAL = "The stars input you gave is not numerical";

        if (stars.isEmpty() && startPrice.isEmpty() && endPrice.isEmpty() && startingDate.isEmpty() && departureDate.isEmpty() && area.isEmpty() && persons.isEmpty()) {
            search = "default search";
        } else {

            JSONObject filtersJson = new JSONObject();

            if (area.isEmpty()){
                filtersJson.put("area", "-");
            }else{
                    filtersJson.put("area", area);
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            if ((startingDate.isEmpty() && !departureDate.isEmpty()) || (!startingDate.isEmpty() && departureDate.isEmpty())) {
                view.showToast(ERROR_BOTH_DATES_MSG);
                return;
            }

            LocalDate localStDate, localDepDate;
            if(startingDate.isEmpty() && departureDate.isEmpty()){
                filtersJson.put("Starting Date", null);
                filtersJson.put("Departure Date", null);

            }else{
                try {
                    localStDate = LocalDate.parse(startingDate, dateFormatter);
                }catch(Exception e){
                    view.showToast(ERROR_WRONG_START_DATE_INPUT);
                    return;
                }

                if (LocalDate.now().isAfter(localStDate)) {
                    view.showToast(ERROR_DATE_PASSED_MSG);
                    return;
                }

                filtersJson.put("Starting Date", localStDate);

                try {
                    localDepDate = LocalDate.parse(departureDate, dateFormatter);
                }catch(Exception e){
                    view.showToast(ERROR_WRONG_DEP_DATE_INPUT);
                    return;
                }
                if (LocalDate.now().isAfter(localDepDate)) {
                    view.showToast(ERROR_DATE_PASSED_MSG);
                    return;
                }else if(localDepDate.isBefore(localStDate)){
                    view.showToast(ERROR_WRONG_DATES_GIVEN);
                    return;
                }

                filtersJson.put("Departure Date", localDepDate);

                if (localStDate.isAfter(localDepDate)) {
                    view.showToast(ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG);
                    return;
                }
            }

            int localPersons;
            if(persons.isEmpty()){
                filtersJson.put("persons", -1);
            }else{
                try {
                    localPersons = Integer.parseInt(persons);
                }catch(Exception e){
                    view.showToast(ERROR_PERSONS_NOT_NUMERICAL);
                    return;
                }

                if (localPersons <= 0) {
                    view.showToast(ERROR_INVALID_CAPACITY_MSG);
                    return;
                }

                filtersJson.put("persons", localPersons);
            }

            Long localStartPrice, localEndPrice;
            if (startPrice.isEmpty() && endPrice.isEmpty()){
                filtersJson.put("Starting price", -1);
                filtersJson.put("Final price", -1);
            }else{
                try {
                    localStartPrice = Long.valueOf(startPrice);
                }catch(Exception e){
                    view.showToast(ERROR_START_PRICE_NOT_NUMERICAL);
                    return;
                }

                if (localStartPrice <= 0) {
                    view.showToast(ERROR_INVALID_PRICE_MSG);
                    return;
                }

                filtersJson.put("Starting price", localStartPrice);

                try{
                    localEndPrice = Long.valueOf(endPrice);
                }catch(Exception e){
                    view.showToast(ERROR_END_PRICE_NOT_NUMERICAL);
                    return;
                }

                if (localEndPrice <= 0) {
                    view.showToast(ERROR_INVALID_PRICE_MSG);
                    return;
                }

                filtersJson.put("Final price", localEndPrice);
            }

            int localStars;
            if(stars.isEmpty()){
                filtersJson.put("stars", -1);
            }else{
                try {
                    localStars = Integer.parseInt(stars);
                }catch(Exception e){
                    view.showToast(ERROR_LOCAL_STARS_NOT_NUMERICAL);
                    return;
                }

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
