package com.DistributedSystems.room_booking_android_app.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DistributedSystems.room_booking_android_app.R;
import com.DistributedSystems.room_booking_android_app.customerConnection.CustomerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.managerConnection.ManagerConnectionActivity;
import com.DistributedSystems.room_booking_android_app.utils.ViewUtils;

public class HomepageActivity extends AppCompatActivity implements HomepageView {
    String managerName;
    EditText managerNameText;
    Button managerButton, clientButton;
    boolean managerButtonEnabled;

    TextWatcher inputFieldsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            managerName = ViewUtils.getTextFromEditTextElement(managerNameText);
            boolean managerNamePassed = isAlpha(managerName);

            if (managerName.isEmpty() || !managerNamePassed) {
                managerButton.setAlpha(0.5f);
                managerButtonEnabled = false;
            } else {
                managerButton.setAlpha(1.0f);
                managerButtonEnabled = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        managerNameText = findViewById(R.id.managerNameText);
        clientButton = findViewById(R.id.home_customer_button);

        managerButton = findViewById(R.id.home_manager_button);
        managerButtonEnabled = false;
        managerButton.setAlpha(0.5f);

        final HomepagePresenter presenter = new HomepagePresenter(this);

        new InitializeConnectionThread().start();

        managerNameText.addTextChangedListener(inputFieldsWatcher);

        managerButton.setOnClickListener(v -> presenter.onManagerConnection(managerName, managerButtonEnabled));

        clientButton.setOnClickListener(v -> presenter.onCustomerConnection());
    }

    @Override
    public void managerConnection() {
        Intent intent = new Intent(HomepageActivity.this, ManagerConnectionActivity.class);
        startActivity(intent);
    }

    @Override
    public void customerConnection() {
        Intent intent = new Intent(HomepageActivity.this, CustomerConnectionActivity.class);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
