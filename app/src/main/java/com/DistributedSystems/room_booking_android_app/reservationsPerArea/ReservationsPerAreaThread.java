package com.DistributedSystems.room_booking_android_app.reservationsPerArea;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class ReservationsPerAreaThread extends Thread{

    Handler handler;
    List<String> reservationStrings;
    String stDate;
    String depDate;

    public ReservationsPerAreaThread(Handler handler, List<String> reservationStrings, String stDate, String depDate) {
        this.handler = handler;
        this.reservationStrings = reservationStrings;
        this.stDate = stDate;
        this.depDate = depDate;
    }

    public void run() {

        try {
            Dao.getOut().writeInt(44);
            Dao.getOut().flush();
            Dao.getOut().writeObject(stDate);
            Dao.getOut().flush();
            Dao.getOut().writeObject(depDate);
            Dao.getOut().flush();

            HashMap<String, Integer> reservationsPerArea = (HashMap<String, Integer>) Dao.getIn().readObject();

            Message msg = new Message();
            Bundle bundle = new Bundle();
            msg.setData(bundle);
            handler.sendMessage(msg);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
