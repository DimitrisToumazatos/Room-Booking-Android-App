package com.DistributedSystems.room_booking_android_app.homepage;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class InitializeConnectionThread extends Thread {
    @Override
    public void run() {
        try {
            Dao.initializeConnections();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
