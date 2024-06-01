package com.DistributedSystems.room_booking_android_app.insertion;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InsertRoomPresenter {
    private final InsertRoomView view;
    public InsertRoomPresenter(InsertRoomView view) {
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void onInsertRoom(String roomName, String roomArea, String roomPrice, String roomCapacity, String roomStDate, String roomDepDate, Boolean insertButtonEnabled, Bitmap bitmap) throws IOException, JSONException {
        String ERROR_INVALID_PRICE_MSG = "The price you gave is equal or below zero.";
        String ERROR_INVALID_CAPACITY_MSG = "Please give a positive number of persons!";
        String ERROR_DATE_PASSED_MSG = "The date you chose has passed!";
        String ERROR_STARTING_DATE_AFTER_DEPARTURE_MSG = "Your End Date is before the starting date!";
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";

        int localRoomPrice = Integer.parseInt(roomPrice);
        int localRoomCapacity = Integer.parseInt(roomCapacity);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localRoomStDate = LocalDate.parse(roomStDate, dateFormatter);
        LocalDate localRoomDepDate = LocalDate.parse(roomDepDate, dateFormatter);

        if (!insertButtonEnabled) {

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
            }else {
                view.showToast(ERROR_EMPTY_FIELD_MSG);
            }
            return;
        }
        List<List<String>> datesList = new ArrayList<>();
        List<String> tempDatesList = new ArrayList<>();

        tempDatesList.add(localRoomStDate.toString());
        tempDatesList.add(localRoomDepDate.toString());

        datesList.add(tempDatesList);

        JSONObject json = new JSONObject();
        json.put("owner", Dao.getManagerName());
        json.put("price", roomPrice);
        json.put("availableDates", datesList);
        json.put("roomName", roomName);
        json.put("noOfPersons", localRoomCapacity);
        json.put("area", roomArea);
        json.put("stars", 0);
        json.put("noOfReviews", 0);


        new InsertRoomThread(json.toString(), bitmap).start();

        view.successfulInsertion();

    }
}
