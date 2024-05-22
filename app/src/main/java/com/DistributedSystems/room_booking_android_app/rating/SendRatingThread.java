package com.DistributedSystems.room_booking_android_app.rating;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class SendRatingThread extends Thread{
    int roomId, rating;
    public SendRatingThread(String roomId, String rating) {
        this.roomId = Integer.parseInt(roomId);
        this.rating = Integer.parseInt(rating);
    }

    public void run(){
        try {
            Dao.getOut().writeInt(roomId);
            Dao.getOut().flush();
            Dao.getOut().writeInt(rating);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
