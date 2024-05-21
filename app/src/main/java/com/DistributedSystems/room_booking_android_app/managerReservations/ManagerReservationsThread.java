package com.DistributedSystems.room_booking_android_app.managerReservations;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.DistributedSystems.room_booking_android_app.utils.Room;
import com.DistributedSystems.room_booking_android_app.utils.Dao;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerReservationsThread extends Thread{

    Handler handler;
    ArrayList<String> reservationStrings;
    public ManagerReservationsThread(Handler handler, ArrayList<String> reservationStrings) {
        this.handler = handler;
        this.reservationStrings = reservationStrings;
    }

    public void run() {

        try {
            Dao.getOut().writeInt(33);
            Dao.getOut().flush();

            List<String> rooms = (List<String>) Dao.getIn().readObject();
            ArrayList<Room> reservationObjects = new ArrayList<>();
            for (String room : rooms){
                reservationObjects.add(new Room(room));
            }
            reservationStrings.addAll(rooms);

            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Reservations", reservationObjects);
            msg.setData(bundle);
            handler.sendMessage(msg);

        } catch (IOException | ClassNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
