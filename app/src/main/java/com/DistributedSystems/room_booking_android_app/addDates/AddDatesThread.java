package com.DistributedSystems.room_booking_android_app.addDates;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AddDatesThread extends Thread{
    int roomId;
    String startDate, endDate;


    public AddDatesThread(String roomId, String startDate, String endDate){
        this.roomId = Integer.parseInt(roomId);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void run(){
        try {
            Dao.getOut().writeInt(roomId);
            Dao.getOut().flush();
            Dao.getOut().writeObject(startDate);
            Dao.getOut().flush();
            Dao.getOut().writeObject(endDate);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
