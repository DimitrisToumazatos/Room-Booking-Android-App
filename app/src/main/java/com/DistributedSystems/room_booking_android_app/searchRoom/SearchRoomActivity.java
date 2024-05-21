package com.DistributedSystems.room_booking_android_app.searchRoom;

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
import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionPresenter;
import com.DistributedSystems.room_booking_android_app.filteredRooms.FilteredRoomsActivity;
import com.DistributedSystems.room_booking_android_app.homepage.HomepageActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

public class SearchRoomActivity extends AppCompatActivity implements SearchRoomView {

    EditText starsText, startPriceText, endPriceText, startingDateText, departureDateText,areaText, personsText;
    String stars, startPrice, endPrice, startingDate, departureDate, area, persons;
    Button searchButton;
    Boolean searchButtonEnabled;

    TextWatcher inputFieldsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            stars = ViewUtils.getTextFromEditTextElement(starsText);
            startPrice = ViewUtils.getTextFromEditTextElement(startPriceText);
            endPrice = ViewUtils.getTextFromEditTextElement(endPriceText);
            startingDate = ViewUtils.getTextFromEditTextElement(startingDateText);
            departureDate = ViewUtils.getTextFromEditTextElement(departureDateText);
            area = ViewUtils.getTextFromEditTextElement(areaText);
            persons = ViewUtils.getTextFromEditTextElement(personsText);

            if (stars.isEmpty() || startPrice.isEmpty() || endPrice.isEmpty() || startingDate.isEmpty() || departureDate.isEmpty() || area.isEmpty() || persons.isEmpty()) {
                searchButton.setAlpha(0.5f);
                searchButtonEnabled = false;
            } else {
                searchButton.setAlpha(1.0f);
                searchButtonEnabled = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);

        final SearchRoomPresenter presenter = new SearchRoomPresenter(this);

        starsText = findViewById(R.id.starsText);
        startPriceText = findViewById(R.id.startPriceText);
        endPriceText = findViewById(R.id.endPriceText);
        startingDateText = findViewById(R.id.startingDateText);
        departureDateText = findViewById(R.id.departureDateText);
        areaText = findViewById(R.id.areaText);
        personsText = findViewById(R.id.personsText);

        searchButton = findViewById(R.id.search_button);
        searchButton.setAlpha(0.5f);
        searchButtonEnabled = false;

        starsText.addTextChangedListener(inputFieldsWatcher);
        startPriceText.addTextChangedListener(inputFieldsWatcher);
        endPriceText.addTextChangedListener(inputFieldsWatcher);
        startingDateText.addTextChangedListener(inputFieldsWatcher);
        departureDateText.addTextChangedListener(inputFieldsWatcher);
        areaText.addTextChangedListener(inputFieldsWatcher);
        personsText.addTextChangedListener(inputFieldsWatcher);

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSearchRoom(stars, startPrice, endPrice, startingDate, departureDate, area, persons, searchButtonEnabled);
            }
        });

    }

    public void searchRoom(){
        Intent intent = new Intent(SearchRoomActivity.this, FilteredRoomsActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
