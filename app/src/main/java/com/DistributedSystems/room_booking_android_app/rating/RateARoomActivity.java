package com.DistributedSystems.room_booking_android_app.rating;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.Room;
import com.DistributedSystems.room_booking_android_app.utils.RoomAdapter;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

import java.util.ArrayList;

@SuppressWarnings("unchecked")

public class RateARoomActivity extends AppCompatActivity implements RateARoomView {

    EditText roomIdText, ratingText;
    String roomId, rating;
    Button rateRoomButton;
    Boolean rateRoomButtonEnabled;
    ListView roomListView;
    RoomAdapter adapter;
    ArrayList<String> roomStrings = new ArrayList<>();
    ArrayList<Room> rooms;
    ArrayList<byte[]> roomImages = new ArrayList<>();
    TextWatcher inputFieldsWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            roomId = ViewUtils.getTextFromEditTextElement(roomIdText);
            rating = ViewUtils.getTextFromEditTextElement(ratingText);
            boolean roomIdPassed = true, ratingPassed = true;
            try{
                int roomIdTemp = Integer.parseInt(roomId);
                if(roomIdTemp >= (rooms.size() - 1) || roomIdTemp < 0){
                    roomIdPassed = false;
                }
            }catch(Exception e){
                roomIdPassed = false;
            }

            try{
                int ratingTemp = Integer.parseInt(rating);
                if(ratingTemp > 5 || ratingTemp < 0){
                    ratingPassed = false;
                }
            }catch(Exception e){
                ratingPassed = false;
            }

            if(!(roomId.isEmpty() || rating.isEmpty()) && (ratingPassed && roomIdPassed)){
                rateRoomButton.setAlpha(1.0f);
                rateRoomButtonEnabled = true;
            } else {
                rateRoomButton.setAlpha(0.5f);
                rateRoomButtonEnabled = false;
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
        setContentView(R.layout.activity_rating);

        roomIdText = findViewById(R.id.roomIdText);
        ratingText = findViewById(R.id.ratingText);
        rateRoomButton = findViewById(R.id.rating_button);
        roomListView = findViewById(R.id.rateListView);

        final RateARoomPresenter presenter = new RateARoomPresenter(this);

        adapter = new RoomAdapter(getLayoutInflater(), roomStrings, roomImages);
        roomListView.setAdapter(adapter);

        RateRoomSearchThread t1 = new RateRoomSearchThread(myHandler, roomStrings, roomImages);
        t1.start();


        rateRoomButton.setAlpha(0.5f);
        rateRoomButtonEnabled = false;

        roomIdText.addTextChangedListener(inputFieldsWatcher);
        ratingText.addTextChangedListener(inputFieldsWatcher);

        rateRoomButton.setOnClickListener(v -> presenter.onRateRoom(roomId, rating, rateRoomButtonEnabled, rooms));
    }

    public void rateRoom() {
        new SendRatingThread(roomId, rating).start();
        Intent intent = new Intent(RateARoomActivity.this, CustomerConnectionActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
