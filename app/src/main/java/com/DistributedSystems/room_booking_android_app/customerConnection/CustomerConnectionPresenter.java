package com.DistributedSystems.room_booking_android_app.customerConnection;

public class CustomerConnectionPresenter {
    private final CustomerConnectionView view;

    public CustomerConnectionPresenter(CustomerConnectionView view) {
        this.view = view;
    }

    public void onRatingButton() {view.ratingButton();}

    public void onSearchButton() {view.searchButton();}

    public void onExitButton() {
        view.showToast("Exit");
        view.exitButton();
    }
}
