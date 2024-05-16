package com.DistributedSystems.room_booking_android_app.homepage;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

public class SendManagerNameThread extends Thread {
    String managerName;
    SendManagerNameThread(String managerName) {
        this.managerName = managerName;
    }
    @Override
    public void run() {
        try {
            Dao.getOut().writeObject("manager");
            Dao.getOut().flush();
            Dao.getOut().writeObject(managerName);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
