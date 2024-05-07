package com.DistributedSystems.room_booking_android_app.insertion;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

import java.time.LocalDate;

public class InsertRoomActivity extends AppCompatActivity implements InsertRoomView {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);

        EditText roomNameText = null;
        EditText roomPriceText = null;
        EditText roomStDateText = null;
        EditText roomDepDateText = null;
        EditText roomAreaText = null;
        EditText roomCapacityText = null;
        EditText roomImageText = null;

        String roomName;
        String roomArea;
        String roomImage;
        int roomPrice;
        int roomCapacity;

        LocalDate roomStDate = null;
        LocalDate roomDepDate = null;

        Button insert_button;

        roomName = ViewUtils.getTextFromEditTextElement(roomNameText);
        roomPrice = parseInt(ViewUtils.getTextFromEditTextElement(roomPriceText));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            roomStDate = LocalDate.parse(ViewUtils.getTextFromEditTextElement(roomStDateText));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            roomDepDate = LocalDate.parse(ViewUtils.getTextFromEditTextElement(roomDepDateText));
        }
        roomArea = ViewUtils.getTextFromEditTextElement(roomAreaText);
        roomCapacity = parseInt(ViewUtils.getTextFromEditTextElement(roomCapacityText));
        roomImage = ViewUtils.getTextFromEditTextElement(roomImageText);

        insert_button = findViewById(R.id.insert_button);
        roomNameText = findViewById(R.id.roomNameText);
        roomPriceText = findViewById(R.id.priceText);
        roomStDateText = findViewById(R.id.startingDateText);
        roomDepDateText = findViewById(R.id.departureDateText);
        roomAreaText = findViewById(R.id.areaText);
        roomCapacityText = findViewById(R.id.personsText);
        roomImageText = findViewById(R.id.imageText);

        final InsertRoomPresenter presenter = new InsertRoomPresenter(this);

        LocalDate finalRoomStDate = roomStDate;
        LocalDate finalRoomDepDate = roomDepDate;
        insert_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onInsertRoom(roomName, roomArea, roomImage, roomPrice, roomCapacity, finalRoomStDate, finalRoomDepDate);
            }
        });
    }

    public void successfulInsertion() {
        Intent intent = new Intent(InsertRoomActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
