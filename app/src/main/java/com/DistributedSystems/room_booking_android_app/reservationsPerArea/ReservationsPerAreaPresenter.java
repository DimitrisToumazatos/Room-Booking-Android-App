package com.DistributedSystems.room_booking_android_app.reservationsPerArea;


public class ReservationsPerAreaPresenter {

    private final ReservationsPerAreaView view;


    public ReservationsPerAreaPresenter(ReservationsPerAreaView view) {
        this.view = view;
    }

    void onExit() {
        view.exit();
    }

}
