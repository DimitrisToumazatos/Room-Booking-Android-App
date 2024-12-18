package com.DistributedSystems.room_booking_android_app.filteredRooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.DistributedSystems.room_booking_android_app.utils.Dao;
import com.DistributedSystems.room_booking_android_app.utils.Room;

import org.json.simple.parser.ParseException;

@SuppressWarnings("unchecked")

public class FilteredSearchThread extends Thread {
    Handler handler;
    ArrayList<String> roomStrings;
    ArrayList<byte []> roomImages;
    String searchOption;
    public FilteredSearchThread(Handler handler, ArrayList<String> roomStrings, String searchOption, ArrayList<byte []> roomImages) {
        this.handler = handler;
        this.roomStrings = roomStrings;
        this.searchOption = searchOption;
        this.roomImages = roomImages;
    }

    @Override
    public void run() {
        try {
            Dao.getOut().writeObject(searchOption);
            Dao.getOut().flush();

            roomStrings.addAll((List<String>) Dao.getIn().readObject());
            roomImages.addAll((List<byte[]>) Dao.getIn().readObject());
            ArrayList<Room> roomObjects = new ArrayList<>();

            for (String room : roomStrings){
                roomObjects.add(new Room(room));
            }

            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Rooms", roomObjects);
            msg.setData(bundle);
            handler.sendMessage(msg);
        } catch (IOException | ParseException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
