package com.DistributedSystems.room_booking_android_app.filteredRooms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.Room;
import com.DistributedSystems.room_booking_android_app.utils.RoomAdapter;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FilteredRoomsActivity extends AppCompatActivity implements FilteredRoomsView {
    EditText roomIdText, stDateText, depDateText, customerNameText;
    ListView roomListView;
    String roomId, stDate, depDate, customerName;
    Button reserveButton;
    boolean reserveButtonEnabled;
    ArrayList<Room> rooms;
    ArrayList<String> roomStrings = new ArrayList<>();
    RoomAdapter adapter;
    String searchOption = "default search";

    TextWatcher inputFieldsWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            roomId = ViewUtils.getTextFromEditTextElement(roomIdText);
            stDate = ViewUtils.getTextFromEditTextElement(stDateText);
            depDate = ViewUtils.getTextFromEditTextElement(depDateText);
            customerName = ViewUtils.getTextFromEditTextElement(customerNameText);
            boolean roomIdPassed = true, stDatePassed = true, depDatePassed = true, customerNamePassed = true;

            try{
                Integer.parseInt(roomId);
            }catch(Exception e){
                roomIdPassed = false;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate startingDate = LocalDate.now(), departureDate;
            try{
                startingDate = LocalDate.parse(stDate, formatter);
                if(startingDate.isBefore(LocalDate.now())){
                    stDatePassed = false;
                }

            }catch(Exception e){
                stDatePassed = false;
            }

            try{
                departureDate = LocalDate.parse(depDate, formatter);
                if(departureDate.isBefore(LocalDate.now()) || departureDate.isBefore(startingDate)){
                    depDatePassed = false;
                }
            }catch(Exception e){
                depDatePassed = false;
            }

            customerNamePassed = isAlpha(customerName);

            if((roomId.isEmpty() || stDate.isEmpty() || depDate.isEmpty() || customerName.isEmpty()) && (roomIdPassed && stDatePassed && depDatePassed && customerNamePassed)){
                reserveButton.setAlpha(1.0f);
                reserveButtonEnabled = true;
            } else {
                reserveButton.setAlpha(0.5f);
                reserveButtonEnabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public Handler myHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            rooms = (ArrayList<Room>) message.getData().getSerializable("Rooms");
            adapter.notifyDataSetChanged();
            return false;
        }
    });

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_rooms);
        Log.d("message","3");

        final FilteredRoomsPresenter presenter = new FilteredRoomsPresenter(this);

        roomListView = findViewById(R.id.filteredRooms);
        adapter = new RoomAdapter(getLayoutInflater(), roomStrings);
        roomListView.setAdapter(adapter);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            searchOption = intent.getStringExtra("searchOption");
        }
        FilteredSearchThread t1 = new FilteredSearchThread(myHandler, roomStrings, searchOption);
        t1.start();

        roomIdText = findViewById(R.id.idEditText);
        customerNameText = findViewById(R.id.nameEditText);
        stDateText = findViewById(R.id.stDateEditText);
        depDateText = findViewById(R.id.depDateEditText);

        reserveButton = findViewById(R.id.reserveButton);
        reserveButton.setAlpha(0.5f);
        reserveButtonEnabled = false;

        roomIdText.addTextChangedListener(inputFieldsWatcher);
        customerNameText.addTextChangedListener(inputFieldsWatcher);
        stDateText.addTextChangedListener(inputFieldsWatcher);
        depDateText.addTextChangedListener(inputFieldsWatcher);

        findViewById(R.id.reserveButton).setOnClickListener(v -> presenter.onReserveButton(roomId, stDate, depDate, customerName, reserveButtonEnabled, rooms));

        findViewById(R.id.exitReservation).setOnClickListener(v -> presenter.onExit());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void reserveButton() {
        new ReservationRequestThread(roomId, stDate, depDate, customerName).start();
        Intent intent = new Intent(FilteredRoomsActivity.this, CustomerConnectionActivity.class);
        startActivity(intent);
    }

    public void exitButton() {
        new ExitReservationThread().start();
        Intent intent = new Intent(FilteredRoomsActivity.this, CustomerConnectionActivity.class);
        startActivity(intent);
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

    @Override
    public void showToast(String msg) {

    }
}
