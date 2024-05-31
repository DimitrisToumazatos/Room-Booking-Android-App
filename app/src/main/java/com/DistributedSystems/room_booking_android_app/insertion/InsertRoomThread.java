package com.DistributedSystems.room_booking_android_app.insertion;

import android.graphics.Bitmap;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InsertRoomThread extends Thread {
    String json;
    Bitmap bitmap;

    public InsertRoomThread(String json, Bitmap bitmap) {
        this.json = json;
        this.bitmap = bitmap;
    }
    @Override
    public void run() {
        try {
            Dao.getOut().writeInt(1);
            Dao.getOut().flush();

            Dao.getOut().writeObject(this.json);
            Dao.getOut().flush();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Dao.getOut().writeInt(byteArray.length);
            Dao.getOut().flush();

            Dao.getOut().write(byteArray);
            Dao.getOut().flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
