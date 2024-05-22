package com.DistributedSystems.room_booking_android_app.filteredRooms;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class ExitReservationThread extends Thread {
    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(1);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
