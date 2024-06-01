package com.DistributedSystems.room_booking_android_app.managerConnection;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class InsertCodeSentThread extends Thread{
    @Override
    public void run(){
        try {
            Dao.getOut().writeInt(1);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
