package com.DistributedSystems.room_booking_android_app.homepage;

public class HomepagePresenter {

    private final HomepageView view;


    public HomepagePresenter(HomepageView view) {
        this.view = view;
    }


    void onManagerConnection() {
        view.managerConnection();
    }

    void onCustomerConnection() {
        view.customerConnection();
    }
}
