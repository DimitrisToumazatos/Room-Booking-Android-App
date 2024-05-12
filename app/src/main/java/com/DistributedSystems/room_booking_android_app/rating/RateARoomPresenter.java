package com.DistributedSystems.room_booking_android_app.rating;


public class RateARoomPresenter {

    private final RateARoomView view;


    public RateARoomPresenter(RateARoomView view) {
        this.view = view;
    }

    void onRateRoom(String roomId, String rating, Boolean rateRoomButtonEnabled){
        String ERROR_INVALID_RATING_MSG ="Please give a rating between 1 and 5!";
        String ERROR_EMPTY_FIELD_MSG = "Please fill all the given fields";

        if (!rateRoomButtonEnabled) {
            view.showToast(ERROR_EMPTY_FIELD_MSG);
            return;
        }

        int localRating = Integer.parseInt(rating);

        if (localRating <=0 || localRating >=6){
            view.showToast(ERROR_INVALID_RATING_MSG);
        }
        else{
            view.rateRoom();
        }
    }
}
