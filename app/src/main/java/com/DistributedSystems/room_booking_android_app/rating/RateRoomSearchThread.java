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

import org.json.simple.JSONObject;


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
            Dao.getOut().writeInt(3);
            Dao.getOut().flush();
            Dao.getOut().writeObject("default search");
            Dao.getOut().flush();

            List<Room> rooms = (List<Room>) Dao.getIn().readObject();

//            ArrayList<String> roomNames = new ArrayList<>();
//            ArrayList<String> roomIds = new ArrayList<>();

//            for (Room room:rooms){
//                roomNames.add((String) room.get("roomName"));
//                roomIds.add(room.getId().toString());
//
            roomStrings.clear();
            for (Room room : rooms) {
                roomStrings.add(room.toJSONString());
            }

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
