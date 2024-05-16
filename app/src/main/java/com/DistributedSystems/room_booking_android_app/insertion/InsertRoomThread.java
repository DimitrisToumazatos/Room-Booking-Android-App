package com.DistributedSystems.room_booking_android_app.insertion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InsertRoomThread extends Thread {
    Socket actionsForClientSocket;
    ObjectOutputStream actionsForClientOutputStream;
    String json, owner;

    public InsertRoomThread(String json, String owner) {
        this.json = json;
        this.owner = owner;
    }
    @Override
    public void run() {
        try {
            actionsForClientSocket = new Socket("192.168.1.10", 8000);
            actionsForClientOutputStream = new ObjectOutputStream(actionsForClientSocket.getOutputStream());

            actionsForClientOutputStream.writeObject("manager");
            actionsForClientOutputStream.flush();
            actionsForClientOutputStream.writeObject(owner);
            actionsForClientOutputStream.flush();
            actionsForClientOutputStream.writeInt(1);
            actionsForClientOutputStream.flush();

            actionsForClientOutputStream.writeObject(this.json);
            actionsForClientOutputStream.flush();

            actionsForClientOutputStream.writeInt(0);
            actionsForClientOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                actionsForClientOutputStream.close();
                actionsForClientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
