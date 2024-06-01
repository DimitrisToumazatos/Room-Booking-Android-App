package com.DistributedSystems.room_booking_android_app.customerConnection;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class RatingCodeSentThread extends Thread{
    @Override
    public void run(){
        try {
            Dao.getOut().writeInt(22);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}