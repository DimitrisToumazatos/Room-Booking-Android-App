package com.DistributedSystems.room_booking_android_app.addDates;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.insertion.InsertRoomActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionPresenter;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

public class AddDatesActivity extends AppCompatActivity implements AddDatesView {
    EditText roomIdText, startingDateText, departureDateText;
    String roomId, startingDate, departureDate;
    Button addDatesButton;
    Boolean addDatesButtonEnabled;

    TextWatcher inputFieldsWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            roomId = ViewUtils.getTextFromEditTextElement(roomIdText);
            startingDate = ViewUtils.getTextFromEditTextElement(startingDateText);
            departureDate = ViewUtils.getTextFromEditTextElement(departureDateText);

            if(roomId.isEmpty() || startingDate.isEmpty() || departureDate.isEmpty()){
                addDatesButton.setAlpha(0.5f);
                addDatesButtonEnabled = false;
            } else {
                addDatesButton.setAlpha(1.0f);
                addDatesButtonEnabled = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datesaddition);

        final AddDatesPresenter presenter = new AddDatesPresenter(this);

        roomIdText = findViewById(R.id.roomIdText);
        startingDateText = findViewById(R.id.startingDateText);
        departureDateText = findViewById(R.id.departureDateText);

        addDatesButton = findViewById(R.id.add_dates_button);
        addDatesButton.setAlpha(0.5f);
        addDatesButtonEnabled = false;

        roomIdText.addTextChangedListener(inputFieldsWatcher);
        startingDateText.addTextChangedListener(inputFieldsWatcher);
        departureDateText.addTextChangedListener(inputFieldsWatcher);

        addDatesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onAddDates(roomId, startingDate, departureDate, addDatesButtonEnabled);
            }
        });
    }
    public void addDates() {
        Intent intent = new Intent(AddDatesActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

