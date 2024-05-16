package com.DistributedSystems.room_booking_android_app.rating;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.DistributedSystems.room_booking_android_app.domain.Room;
import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;


public class RateRoomSearchThread extends Thread {

    Handler handler;
    public RateRoomSearchThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(3);
            Dao.getOut().flush();
            Dao.getOut().writeObject("default search");
            Dao.getOut().flush();

            List<Room> rooms = (List<Room>) Dao.getIn().readObject();

            ArrayList<String> roomNames = new ArrayList<>();
            ArrayList<String> roomIds = new ArrayList<>();

            for (Room room:rooms){
                roomNames.add((String) room.get("roomName"));
                roomIds.add(room.getId().toString());
            }

            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("roomNames", roomNames);
            bundle.putStringArrayList("roomIds", roomIds);
            msg.setData(bundle);
            handler.sendMessage(msg);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
