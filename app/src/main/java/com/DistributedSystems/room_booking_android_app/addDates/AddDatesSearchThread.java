package com.DistributedSystems.room_booking_android_app.addDates;

import android.os.Handler;
import android.os.Message;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")

public class AddDatesSearchThread extends Thread {
    Handler handler;
    ArrayList<String> roomStrings;
    List<byte[]> roomImages;
    public AddDatesSearchThread(Handler handler, ArrayList<String> roomStrings, List<byte[]> roomImages ){
        this.handler = handler;
        this.roomStrings = roomStrings;
        this.roomImages = roomImages;
    }

    @Override
    public void run(){
        try {
            Dao.getOut().writeObject("default search");
            Dao.getOut().flush();

            roomStrings.addAll((List<String>) Dao.getIn().readObject());
            roomImages.addAll((List<byte[]>) Dao.getIn().readObject());

            Message msg = new Message();
            handler.sendMessage(msg);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
