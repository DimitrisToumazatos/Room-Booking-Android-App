package com.DistributedSystems.room_booking_android_app.insertion;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextWatcher;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

import org.json.JSONException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsertRoomActivity extends AppCompatActivity implements InsertRoomView {

    EditText roomNameText, roomPriceText, roomStDateText, roomDepDateText, roomAreaText,roomCapacityText, roomImageText;
    String roomName, roomPrice, roomStDate, roomDepDate, roomArea, roomCapacity, roomImage;
    Button insertButton;
    Boolean insertButtonEnabled;
    TextWatcher inputFieldsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            roomName = ViewUtils.getTextFromEditTextElement(roomNameText);
            roomPrice = ViewUtils.getTextFromEditTextElement(roomPriceText);
            roomArea = ViewUtils.getTextFromEditTextElement(roomAreaText);
            roomCapacity = ViewUtils.getTextFromEditTextElement(roomCapacityText);
            roomImage = ViewUtils.getTextFromEditTextElement(roomImageText);
            roomStDate = ViewUtils.getTextFromEditTextElement(roomStDateText);
            roomDepDate = ViewUtils.getTextFromEditTextElement(roomDepDateText);
            boolean roomPricePassed = true, roomCapacityPassed = true, roomStDatePassed = true, roomDepDatePassed = true, roomNamePassed = true;
            try{
                Integer.parseInt(roomPrice);
            }catch (Exception e){
                roomPricePassed = false;
            }

            try{
                Integer.parseInt(roomCapacity);
            }catch(Exception e){
                roomCapacityPassed = false;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate stDate = LocalDate.now(), depDate;

            try{
                stDate = LocalDate.parse(roomStDate, formatter);
                if(stDate.isBefore(LocalDate.now())){
                    roomStDatePassed = false;
                }
            }catch(Exception e){
                roomStDatePassed = false;
            }

            try{
                depDate = LocalDate.parse(roomDepDate, formatter);
                if(depDate.isBefore(LocalDate.now()) || depDate.isBefore(stDate)){
                    roomDepDatePassed = false;
                }
            }catch(Exception e){
                roomDepDatePassed = false;
            }

            roomNamePassed = isAlpha(roomName);

            if (!(roomName.isEmpty() || roomArea.isEmpty() || roomImage.isEmpty() || roomPrice.isEmpty() || roomCapacity.isEmpty() || roomStDate.isEmpty() || roomDepDate.isEmpty()) && (roomPricePassed && roomDepDatePassed && roomStDatePassed && roomCapacityPassed && roomNamePassed)) {
                insertButton.setAlpha(1.0f);
                insertButtonEnabled = true;
            } else {
                insertButton.setAlpha(0.5f);
                insertButtonEnabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);

        final InsertRoomPresenter presenter = new InsertRoomPresenter(this);

        roomNameText = findViewById(R.id.roomNameText);
        roomPriceText = findViewById(R.id.priceText);
        roomAreaText = findViewById(R.id.areaText);
        roomCapacityText = findViewById(R.id.personsText);
        roomImageText = findViewById(R.id.imageText);
        roomStDateText = findViewById(R.id.startingDateText);
        roomDepDateText = findViewById(R.id.departureDateText);

        insertButton = findViewById(R.id.insert_button);
        insertButton.setAlpha(0.5f);
        insertButtonEnabled = false;

        roomNameText.addTextChangedListener(inputFieldsWatcher);
        roomPriceText.addTextChangedListener(inputFieldsWatcher);
        roomAreaText.addTextChangedListener(inputFieldsWatcher);
        roomCapacityText.addTextChangedListener(inputFieldsWatcher);
        roomImageText.addTextChangedListener(inputFieldsWatcher);
        roomStDateText.addTextChangedListener(inputFieldsWatcher);
        roomDepDateText.addTextChangedListener(inputFieldsWatcher);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                try {
                    presenter.onInsertRoom(roomName, roomArea, roomImage, roomPrice, roomCapacity, roomStDate, roomDepDate, insertButtonEnabled);
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }
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

    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

}
