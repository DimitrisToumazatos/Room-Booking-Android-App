package com.DistributedSystems.room_booking_android_app.managerReservations;

public class ManagerReservationsPresenter {

    private final ManagerReservationsView view;


    public ManagerReservationsPresenter(ManagerReservationsView view) {
        this.view = view;
    }

    void onExit() {
        view.exit();
    }
}
