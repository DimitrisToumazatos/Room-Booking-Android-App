package com.DistributedSystems.room_booking_android_app.filteredRooms;


public class FilteredRoomsPresenter {

    private final FilteredRoomsView view;

    public FilteredRoomsPresenter(FilteredRoomsView view) {
        this.view = view;
    }

    void onReserveButton(){ view.reserveButton();}
}
