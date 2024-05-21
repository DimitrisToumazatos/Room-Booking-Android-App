package com.DistributedSystems.room_booking_android_app.reservationsPerArea;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.DistributedSystems.room_booking_android_app.utils.Dao;
import java.io.IOException;

public class GetServerImage extends Thread {

    ImageView image;

    GetServerImage(ImageView img) {
        image = img;
    }

    @Override
    public void run() {
        int length;
        try {
            Dao.getOut().writeInt(5);
            Dao.getOut().flush();

            length = Dao.getIn().readInt();



            byte[] imageData = new byte[length];
            Dao.getIn().readFully(imageData, 0, imageData.length);

            Bitmap bmp = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            image.setImageBitmap(bmp);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
