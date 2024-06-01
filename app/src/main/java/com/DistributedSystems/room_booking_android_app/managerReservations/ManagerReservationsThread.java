package com.DistributedSystems.room_booking_android_app.managerReservations;


import android.os.Handler;
import android.os.Message;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.util.List;

public class ManagerReservationsThread extends Thread{

    Handler handler;
    List<String> reservationStrings;
    public ManagerReservationsThread(Handler handler, List<String> reservationStrings) {
        this.handler = handler;
        this.reservationStrings = reservationStrings;
    }

    public void run() {

        try {
            List<String> rooms = (List<String>) Dao.getIn().readObject();

            reservationStrings.addAll(rooms);

            Message msg = new Message();
            handler.sendMessage(msg);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
