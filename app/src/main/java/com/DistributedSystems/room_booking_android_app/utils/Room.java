package com.DistributedSystems.room_booking_android_app.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable{
    private final JSONObject json;
    private final List<Reservation> reservations = new ArrayList<>();
    private static int currentId = 0;
    static JSONParser parser = new JSONParser();

    public Room(JSONObject json){
        this.json = json;
        this.json.put("id", currentId);
        currentId++;
    }

    public Room(String json) throws ParseException{
        this.json = (JSONObject) parser.parse(json);
    }

    public Object get(String field){
        return json.get(field);
    }

    public Number getId(){
        return (Number) json.get("id");
    }
    public String jsonToString(){
        return json.toString();
    }

    public String toString(){
        return "Id: " + get("id") + "\nName: " + get("roomName") + "\nPrice per night: " + get("price") + "\nNumber of people: " + get("noOfPersons") +
                "\nArea: " + get("area") + "\nStars: " + get("stars") + "\nAvailable Dates: " + get("availableDates") + "\n";
    }

    public void put(String field, Object value) {
        json.put(field, value);
    }
}
