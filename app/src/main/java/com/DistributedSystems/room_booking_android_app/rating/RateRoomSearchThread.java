package com.DistributedSystems.room_booking_android_app.rating;

import android.os.Bundle;
import android.os.Message;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class RateRoomSearchThread extends Thread {

    Handler handler;
    ArrayList<String> roomStrings;
    public RateRoomSearchThread(Handler handler, ArrayList<String> roomStrings) {
        this.handler = handler;
        this.roomStrings = roomStrings;
    }

    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(11);
            Dao.getOut().flush();
            Dao.getOut().writeObject("default search");
            Dao.getOut().flush();

            List<String> rooms = (List<String>) Dao.getIn().readObject();

            roomStrings.addAll(rooms);

            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("rooms", roomStrings);
            msg.setData(bundle);
            handler.sendMessage(msg);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
