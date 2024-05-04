package com.DistributedSystems.room_booking_android_app.managerConnection;

public class ManagerConnectionPresenter {

    private final ManagerConnectionView view;


    public ManagerConnectionPresenter(ManagerConnectionView view) {
        this.view = view;
    }

    void onInsertRoom() {view.insertRoom();}

    void onExit() {view.exit();}

    void onAddDates() {view.addDates();}

    void onGetManagersReservations() {view.getManagersReservations();}

}
