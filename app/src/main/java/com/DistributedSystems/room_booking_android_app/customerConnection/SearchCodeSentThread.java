package com.DistributedSystems.room_booking_android_app.customerConnection;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class SearchCodeSentThread extends Thread{
    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(11);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
