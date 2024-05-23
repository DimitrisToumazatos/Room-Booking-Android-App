package com.DistributedSystems.room_booking_android_app.addDates;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.Room;
import com.DistributedSystems.room_booking_android_app.utils.RoomAdapter;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddDatesActivity extends AppCompatActivity implements AddDatesView {
    EditText roomIdText, startingDateText, departureDateText;
    String roomId, startingDate, departureDate;
    ListView roomListView;
    Button addDatesButton;
    Boolean addDatesButtonEnabled;
    RoomAdapter roomAdapter;
    ArrayList<Room> rooms;
    ArrayList<String> roomStrings = new ArrayList<>();

    public Handler myHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            rooms = (ArrayList<Room>) message.getData().getSerializable("Rooms");
            roomAdapter.notifyDataSetChanged();
            return false;
        }
    });

    TextWatcher inputFieldsWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            roomId = ViewUtils.getTextFromEditTextElement(roomIdText);
            startingDate = ViewUtils.getTextFromEditTextElement(startingDateText);
            departureDate = ViewUtils.getTextFromEditTextElement(departureDateText);
            boolean roomIdPassed = true, startingDatePassed = true, departureDatePassed = true;
            try{
                Integer.parseInt(roomId);
            }catch(Exception e){
                roomIdPassed = false;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate stDate = LocalDate.now(), depDate;
            try{
                stDate = LocalDate.parse(startingDate, formatter);
                if(stDate.isBefore(LocalDate.now())){
                    startingDatePassed = false;
                }

            }catch(Exception e){

                startingDatePassed = false;
            }
            try{
                depDate = LocalDate.parse(departureDate, formatter);
                if(depDate.isBefore(LocalDate.now()) || depDate.isBefore(stDate)){
                    departureDatePassed = false;
                }
            }catch(Exception e){
                departureDatePassed = false;
            }


            if(!(roomId.isEmpty() || startingDate.isEmpty() || departureDate.isEmpty()) && (startingDatePassed && roomIdPassed && departureDatePassed)){
                addDatesButton.setAlpha(1.0f);
                addDatesButtonEnabled = true;
            } else {
                addDatesButton.setAlpha(0.5f);
                addDatesButtonEnabled = false;
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

        roomListView = findViewById(R.id.addDatesListView);
        roomAdapter = new RoomAdapter(getLayoutInflater(), roomStrings);
        roomListView.setAdapter(roomAdapter);

        AddDatesSearchThread t1 = new AddDatesSearchThread(myHandler, roomStrings);
        t1.start();

        roomIdText = findViewById(R.id.roomIdText);
        startingDateText = findViewById(R.id.startingDateText);
        departureDateText = findViewById(R.id.departureDateText);

        addDatesButton = findViewById(R.id.add_dates_button);
        addDatesButton.setAlpha(0.5f);
        addDatesButtonEnabled = false;

        roomIdText.addTextChangedListener(inputFieldsWatcher);
        startingDateText.addTextChangedListener(inputFieldsWatcher);
        departureDateText.addTextChangedListener(inputFieldsWatcher);

        addDatesButton.setOnClickListener(v -> presenter.onAddDates(roomId, startingDate, departureDate, addDatesButtonEnabled));
    }
    public void addDates() {
        AddDatesThread t2 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            t2 = new AddDatesThread(roomId, startingDate, departureDate);
            t2.start();
        }
        Intent intent = new Intent(AddDatesActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

