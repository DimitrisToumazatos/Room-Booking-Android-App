package com.DistributedSystems.room_booking_android_app.managerConnection;

public class ManagerConnectionPresenter {

    private final ManagerConnectionView view;


    public ManagerConnectionPresenter(ManagerConnectionView view) {
        this.view = view;
    }

    void onInsertRoom() {view.insertRoom();}


    void onAddDates() {view.addDates();}

    void onGetManagersReservations() {view.getManagersReservations();}

    void onGetReservationsPerArea () {view.getReservationsPerArea();}

    void onExit() {
        view.showToast("Exit");
        view.onExitButton();
    }
}
