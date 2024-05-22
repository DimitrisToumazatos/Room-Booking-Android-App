package com.DistributedSystems.room_booking_android_app.rating;

import android.os.Bundle;
import android.os.Message;

import com.DistributedSystems.room_booking_android_app.utils.Room;
import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import org.json.simple.parser.ParseException;

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
            Dao.getOut().writeInt(22);
            Dao.getOut().flush();
            Dao.getOut().writeObject("default search");
            Dao.getOut().flush();

            List<String> rooms = (List<String>) Dao.getIn().readObject();
            ArrayList<Room> roomObjects = new ArrayList<>();

            for (String room : rooms){
                roomObjects.add(new Room(room));
            }

            roomStrings.addAll(rooms);

            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Rooms", roomObjects);
            msg.setData(bundle);
            handler.sendMessage(msg);

        } catch (IOException | ClassNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
