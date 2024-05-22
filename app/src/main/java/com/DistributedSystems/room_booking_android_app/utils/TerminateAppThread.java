package com.DistributedSystems.room_booking_android_app.utils;

import java.io.IOException;

public class TerminateAppThread extends Thread {
    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(0);
            Dao.getOut().flush();
            Dao.terminateConnections();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
