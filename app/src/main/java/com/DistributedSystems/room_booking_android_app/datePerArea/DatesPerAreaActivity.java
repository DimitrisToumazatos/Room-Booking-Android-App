package com.DistributedSystems.room_booking_android_app.datePerArea;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.reservationsPerArea.ReservationsPerAreaActivity;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatesPerAreaActivity extends AppCompatActivity implements DatesPerAreaView {
    EditText roomStDate, roomDepDate;
    String stDate, depDate;
    Button search;
    Boolean searchButtonEnabled;


    TextWatcher inputFieldsWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            stDate = ViewUtils.getTextFromEditTextElement(roomStDate);
            depDate = ViewUtils.getTextFromEditTextElement(roomDepDate);
            boolean roomStDatePassed = true, roomDepDatePassed = true;


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startingDate = LocalDate.now(), departureDate;

            try{
                startingDate = LocalDate.parse(stDate, formatter);
                if(startingDate.isBefore(LocalDate.now())){
                    roomStDatePassed = false;
                }
            }catch(Exception e){
                roomStDatePassed = false;
            }

            try{
                departureDate = LocalDate.parse(depDate, formatter);
                if(departureDate.isBefore(LocalDate.now()) || departureDate.isBefore(startingDate)){
                    roomDepDatePassed = false;
                }
            }catch(Exception e){
                roomDepDatePassed = false;
            }

            if(!(stDate.isEmpty() || depDate.isEmpty()) && (roomStDatePassed && roomDepDatePassed)){
                search.setAlpha(1.0f);
                searchButtonEnabled = true;
            } else {
                search.setAlpha(0.5f);
                searchButtonEnabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_per_area);

        roomStDate = findViewById(R.id.startingDateEditText);
        roomDepDate = findViewById(R.id.departureDateEditText);

        search = findViewById(R.id.searchReservarionsButton);
        search.setAlpha(0.5f);
        searchButtonEnabled = false;

        roomStDate.addTextChangedListener(inputFieldsWatcher);
        roomDepDate.addTextChangedListener(inputFieldsWatcher);

        final DatesPerAreaPresenter presenter = new DatesPerAreaPresenter(this);

        findViewById(R.id.searchReservarionsButton).setOnClickListener(v -> presenter.onSearchReservations(stDate, depDate, searchButtonEnabled));

    }

    public void searchReservations() {
        Intent intent = new Intent(DatesPerAreaActivity.this, ReservationsPerAreaActivity.class);
        intent.putExtra("startDate", stDate.toString());
        intent.putExtra("depDate", depDate.toString());
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
