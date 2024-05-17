package com.DistributedSystems.room_booking_android_app.rating;


import com.DistributedSystems.room_booking_android_app.domain.Room;

import java.util.ArrayList;

public class RateARoomPresenter {

    private final RateARoomView view;


    public RateARoomPresenter(RateARoomView view) {
        this.view = view;
    }

    void onRateRoom(String roomId, String rating, Boolean rateRoomButtonEnabled, ArrayList<Room> roomObjects){
        String ERROR_INVALID_RATING_MSG ="Please give a rating between 1 and 5!";
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";
        String ERROR_INVALID_ROOM_ID = "Please give a room id among the existing ones";
        String ERROR_BOTH_TEXTS_WRONG = "Please give a correct room id and rating";

        if (!rateRoomButtonEnabled) {
            view.showToast(ERROR_EMPTY_FIELD_MSG);
            return;
        }

        int localRating = Integer.parseInt(rating);

        int roomIdInteger = Integer.parseInt(roomId);

        boolean found = false;
        for (Room room : roomObjects){
            if(roomIdInteger == ((Number)room.getId()).intValue()){
                found = true;
            }
        }

        if (!found && (localRating <=0 || localRating >=6)) {
            view.showToast(ERROR_BOTH_TEXTS_WRONG);
        }else if (!found){
            view.showToast(ERROR_INVALID_ROOM_ID);
        }
        else if (localRating <=0 || localRating >=6){
            view.showToast(ERROR_INVALID_RATING_MSG);
        }
        else{
            view.rateRoom();
        }
    }
}
