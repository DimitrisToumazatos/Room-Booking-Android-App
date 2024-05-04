package com.DistributedSystems.room_booking_android_app.customerConnection;

public class CustomerConnectionPresenter {
    private final CustomerConnectionView view;


    public CustomerConnectionPresenter(CustomerConnectionView view) {
        this.view = view;
    }

    public void onExit() {view.exit();}

    public void onRatingButton() {view.ratingButton();}
}
