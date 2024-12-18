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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
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
    ArrayList<String> roomStrings = new ArrayList<>();
    ArrayList<byte []> roomImages = new ArrayList<>();

    public Handler myHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        //we set a rooms list for our tests and notify the adapter that a change has been made
        @Override
        public boolean handleMessage(@NonNull Message message) {
            roomAdapter.notifyDataSetChanged();
            return false;
        }
    });

    TextWatcher inputFieldsWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        //we check all the text inputs from the user so as to enable the add dates button when they are all correct
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datesaddition);

        roomIdText = findViewById(R.id.roomIdText);
        startingDateText = findViewById(R.id.startingDateText);
        departureDateText = findViewById(R.id.departureDateText);
        roomListView = findViewById(R.id.addDatesListView);
        addDatesButton = findViewById(R.id.add_dates_button);

        final AddDatesPresenter presenter = new AddDatesPresenter(this);

        roomAdapter = new RoomAdapter(getLayoutInflater(), roomStrings, roomImages);
        roomListView.setAdapter(roomAdapter);

        AddDatesSearchThread t1 = new AddDatesSearchThread(myHandler, roomStrings, roomImages);
        t1.start();

        addDatesButton.setAlpha(0.5f);
        addDatesButtonEnabled = false;

        roomIdText.addTextChangedListener(inputFieldsWatcher);
        startingDateText.addTextChangedListener(inputFieldsWatcher);
        departureDateText.addTextChangedListener(inputFieldsWatcher);

        addDatesButton.setOnClickListener(v -> presenter.onAddDates( startingDate, departureDate, addDatesButtonEnabled));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addDates() {
        AddDatesThread t2 = new AddDatesThread(roomId, startingDate, departureDate);
        t2.start();
        Intent intent = new Intent(AddDatesActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
