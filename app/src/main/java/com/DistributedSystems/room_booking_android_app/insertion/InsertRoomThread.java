package com.DistributedSystems.room_booking_android_app.insertion;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class InsertRoomThread extends Thread {
    String json;

    public InsertRoomThread(String json) {
        this.json = json;
    }
    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(1);
            Dao.getOut().flush();

            Dao.getOut().writeObject(this.json);
            Dao.getOut().flush();

//            Dao.getOut().writeInt(0);
//            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
