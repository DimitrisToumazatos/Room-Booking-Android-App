package com.DistributedSystems.room_booking_android_app.rating;

import android.util.Log;


import com.DistributedSystems.room_booking_android_app.domain.domain_classes.Room;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;



public class RateRoomSearchThread extends Thread {

    List<String> roomNames, roomIds;
    Socket actionsForClientSocket;
    ObjectOutputStream actionsForClientOutputStream;
    ObjectInputStream actionsForClientInputStream;
    public RateRoomSearchThread(List<String> roomNames, List<String> roomIds) {
        this.roomNames = roomNames;
        this.roomIds = roomIds;
    }

    @Override
    public void run() {
        try {
            actionsForClientSocket = new Socket("192.168.1.5", 8000);

            actionsForClientOutputStream = new ObjectOutputStream(actionsForClientSocket.getOutputStream());
            actionsForClientInputStream = new ObjectInputStream(actionsForClientSocket.getInputStream());

            actionsForClientOutputStream.writeObject("client");
            actionsForClientOutputStream.flush();
            actionsForClientOutputStream.writeInt(2);
            actionsForClientOutputStream.flush();
            actionsForClientOutputStream.writeObject("default search");
            actionsForClientOutputStream.flush();

            List<Room> rooms = (List<Room>) actionsForClientInputStream.readObject();

            Log.i("room", rooms.get(0).toString());

            actionsForClientOutputStream.writeInt(0);
            actionsForClientOutputStream.flush();

            for (Room room:rooms){
                roomNames.add((String) room.get("roomName"));
                roomIds.add(room.getId().toString());
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                actionsForClientOutputStream.close();
                actionsForClientInputStream.close();
                actionsForClientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
