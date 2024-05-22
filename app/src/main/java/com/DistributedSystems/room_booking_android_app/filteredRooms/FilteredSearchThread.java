package com.DistributedSystems.room_booking_android_app.filteredRooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.DistributedSystems.room_booking_android_app.utils.Dao;
import com.DistributedSystems.room_booking_android_app.utils.Room;

import org.json.simple.parser.ParseException;

public class FilteredSearchThread extends Thread {
    Handler handler;
    ArrayList<String> roomStrings;
    String searchOption;
    public FilteredSearchThread(Handler handler, ArrayList<String> roomStrings, String searchOption) {
        this.handler = handler;
        this.roomStrings = roomStrings;
        this.searchOption = searchOption;
    }

    @Override
    public void run() {
        try {
            Log.d("poutsa","3");
            Dao.getOut().writeInt(11);
            Dao.getOut().flush();
            Dao.getOut().writeObject(searchOption);
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
        } catch (IOException | ParseException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
