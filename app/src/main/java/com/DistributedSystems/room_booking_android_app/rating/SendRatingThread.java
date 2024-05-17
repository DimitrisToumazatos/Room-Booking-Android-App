package com.DistributedSystems.room_booking_android_app.rating;

import android.os.Handler;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.util.ArrayList;

public class SendRatingThread extends Thread{

    Handler handler;

    int roomId, rating;
    public SendRatingThread(Handler handler, String roomId, String rating) {
        this.handler = handler;
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
