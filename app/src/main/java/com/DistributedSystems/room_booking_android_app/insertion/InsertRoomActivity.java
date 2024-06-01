package com.DistributedSystems.room_booking_android_app.insertion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    EditText roomNameText, roomPriceText, roomStDateText, roomDepDateText, roomAreaText,roomCapacityText;
    String roomName, roomPrice, roomStDate, roomDepDate, roomArea, roomCapacity;
    Button insertButton,roomButtonImage;
    ImageView image;
    Boolean insertButtonEnabled;
    Bitmap bitmap;
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
            roomStDate = ViewUtils.getTextFromEditTextElement(roomStDateText);
            roomDepDate = ViewUtils.getTextFromEditTextElement(roomDepDateText);
            boolean roomPricePassed = true, roomCapacityPassed = true, roomStDatePassed = true, roomDepDatePassed = true, roomNamePassed;
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

            if (!(roomName.isEmpty() || roomArea.isEmpty() || roomPrice.isEmpty() || roomCapacity.isEmpty() || roomStDate.isEmpty() || roomDepDate.isEmpty()) && (roomPricePassed && roomDepDatePassed && roomStDatePassed && roomCapacityPassed && roomNamePassed)) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);

        final InsertRoomPresenter presenter = new InsertRoomPresenter(this);

        roomNameText = findViewById(R.id.roomNameText);
        roomPriceText = findViewById(R.id.priceText);
        roomAreaText = findViewById(R.id.areaText);
        roomCapacityText = findViewById(R.id.personsText);
        roomButtonImage = findViewById(R.id.roomButtonImage);
        roomStDateText = findViewById(R.id.startingDateText);
        roomDepDateText = findViewById(R.id.departureDateText);
        image = findViewById(R.id.roomImage);
        image.setAlpha(0.0f);

        insertButton = findViewById(R.id.insertButton);
        insertButton.setAlpha(0.5f);
        insertButtonEnabled = false;

        roomNameText.addTextChangedListener(inputFieldsWatcher);
        roomPriceText.addTextChangedListener(inputFieldsWatcher);
        roomAreaText.addTextChangedListener(inputFieldsWatcher);
        roomCapacityText.addTextChangedListener(inputFieldsWatcher);
        roomStDateText.addTextChangedListener(inputFieldsWatcher);
        roomDepDateText.addTextChangedListener(inputFieldsWatcher);

        roomButtonImage.setOnClickListener(v -> {
            Intent iGallery =  new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, 1000);
        });
        insertButton.setOnClickListener(v -> {
            try {
                presenter.onInsertRoom(roomName, roomArea, roomPrice, roomCapacity, roomStDate, roomDepDate, insertButtonEnabled, bitmap);
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==1000){
            image.setImageURI(data.getData());
            image.getLayoutParams().height = 250;
            image.getLayoutParams().width = 400;
            image.setAlpha(1.0f);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
            bitmap = bitmapDrawable.getBitmap();
        }
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
