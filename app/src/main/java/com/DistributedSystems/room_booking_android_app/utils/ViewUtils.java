package com.DistributedSystems.room_booking_android_app.utils;

import android.widget.EditText;

public class ViewUtils {

    public static String getTextFromEditTextElement(EditText element) {
        return element.getText().toString().trim();
    }
}
