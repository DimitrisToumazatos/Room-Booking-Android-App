package com.DistributedSystems.room_booking_android_app.searchRoom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.filteredRooms.FilteredRoomsActivity;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

import org.json.JSONException;

public class SearchRoomActivity extends AppCompatActivity implements SearchRoomView {

    EditText starsText, startPriceText, endPriceText, startingDateText, departureDateText,areaText, personsText;
    String stars, startPrice, endPrice, startingDate, departureDate, area, persons;
    Button searchButton;

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

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                try {
                    stars = ViewUtils.getTextFromEditTextElement(starsText);
                    startPrice = ViewUtils.getTextFromEditTextElement(startPriceText);
                    endPrice = ViewUtils.getTextFromEditTextElement(endPriceText);
                    startingDate = ViewUtils.getTextFromEditTextElement(startingDateText);
                    departureDate = ViewUtils.getTextFromEditTextElement(departureDateText);
                    area = ViewUtils.getTextFromEditTextElement(areaText);
                    persons = ViewUtils.getTextFromEditTextElement(personsText);

                    presenter.onSearchRoom(stars, startPrice, endPrice, startingDate, departureDate, area, persons);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void searchRoom(String search){
        Intent intent = new Intent(SearchRoomActivity.this, FilteredRoomsActivity.class);
        intent.putExtra("searchOption", search);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
