package com.DistributedSystems.room_booking_android_app.insertion;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

public class InsertRoomActivity extends AppCompatActivity implements InsertRoomView {

    EditText roomNameText, roomPriceText, roomStDateText, roomDepDateText, roomAreaText,roomCapacityText, roomImageText;
    String roomName, roomPrice, roomStDate, roomDepDate, roomArea, roomCapacity, roomImage;
    Button insertButton;
    Boolean insertButtonEnabled;
    TextWatcher inputFieldsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            roomName = ViewUtils.getTextFromEditTextElement(roomNameText);
            roomPrice = ViewUtils.getTextFromEditTextElement(roomPriceText);
            roomArea = ViewUtils.getTextFromEditTextElement(roomAreaText);
            roomCapacity = ViewUtils.getTextFromEditTextElement(roomCapacityText);
            roomImage = ViewUtils.getTextFromEditTextElement(roomImageText);
            roomStDate = ViewUtils.getTextFromEditTextElement(roomStDateText);
            roomDepDate = ViewUtils.getTextFromEditTextElement(roomDepDateText);

            if (roomName.isEmpty() || roomArea.isEmpty() || roomImage.isEmpty() || roomPrice.isEmpty() || roomCapacity.isEmpty() || roomStDate.isEmpty() || roomDepDate.isEmpty()) {
                insertButton.setAlpha(0.5f);
                insertButtonEnabled = false;
            } else {
                insertButton.setAlpha(1.0f);
                insertButtonEnabled = true;
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
            public void onClick(View v) {
                presenter.onInsertRoom(roomName, roomArea, roomImage, roomPrice, roomCapacity, roomStDate, roomDepDate, insertButtonEnabled);
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
