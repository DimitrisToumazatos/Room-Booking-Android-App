package com.DistributedSystems.room_booking_android_app.filteredRooms;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReservationRequestThread extends Thread {
    int roomId;
    String customerName;
    LocalDate stDate, depDate;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public ReservationRequestThread(String roomId, String stDate, String depDate, String customerName) {
        this.roomId = Integer.parseInt(roomId);
        this.stDate = LocalDate.parse(stDate, formatter);
        this.depDate = LocalDate.parse(depDate, formatter);
        this.customerName = customerName;
    }
    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(0);
            Dao.getOut().flush();
            Dao.getOut().writeInt(roomId);
            Dao.getOut().flush();
            Dao.getOut().writeObject(stDate);
            Dao.getOut().flush();
            Dao.getOut().writeObject(depDate);
            Dao.getOut().flush();
            Dao.getOut().writeObject(customerName);
            Dao.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
