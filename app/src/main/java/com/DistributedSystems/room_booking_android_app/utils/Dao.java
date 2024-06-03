package com.DistributedSystems.room_booking_android_app.utils;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Dao {
    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;
    static String managerName;

    public static ObjectInputStream getIn() {
        return in;
    }


    public static String getManagerName() {
        return managerName;
    }

    public static void setManagerName(String managerName) {
        Dao.managerName = managerName;
    }

    public static ObjectOutputStream getOut() {
        return out;
    }

    public static void initializeConnections() throws IOException {
        socket = new Socket("192.168.1.6", 8000);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public static void terminateConnections() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
