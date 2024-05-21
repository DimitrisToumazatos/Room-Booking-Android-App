package com.DistributedSystems.room_booking_android_app.domain;

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
    public String toJSON(){
        return json.toString();
    }

    public String toString(){
        return "Id: " + get("id") + "\nName: " + get("roomName") + "\nPrice per night: " + get("price") + "\nNumber of people: " + get("noOfPersons") +
                "\nArea: " + get("area") + "\nStars: " + get("stars") + "\nAvailable Dates: " + get("availableDates") + "\n";
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Initializes the new reservation object and adds it in the reservations list of the room object.
     * Also splits the date period that conflicts with reservation to 2 new date periods, which are valid.
     * @param nameOfCustomer the name of the customer
     * @param startDate starting date of the reservation
     * @param endDate final date of the reservation
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addReservation(String nameOfCustomer, LocalDate startDate, LocalDate endDate) {
        reservations.add(new Reservation( nameOfCustomer, getId().intValue(), startDate, endDate));

        List<List<String>> availableDates = (List<List<String>>) get("availableDates");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<List<String>> result = new ArrayList<>();
        //splits the conflicting date period to 2 new valid ones after the reservation is made
        for (List<String> date : availableDates) {
            List<String> temp = new ArrayList<>();
            if (startDate.compareTo(LocalDate.parse(date.get(0), formatter)) >= 0 && endDate.compareTo(LocalDate.parse(date.get(1), formatter)) <= 0) {
                String start1 = date.get(0);
                String end1 = startDate.toString();
                if (!start1.equals(end1)) {
                    temp.add(start1);
                    temp.add(startDate.minusDays(1).toString());
                    result.add(temp);
                }

                temp = new ArrayList<>();
                String start2 = endDate.toString();
                String end2 = date.get(1);

                if (!start2.equals(end2)) {
                    temp.add(endDate.plusDays(1).toString());
                    temp.add(end2);
                    result.add(temp);
                }
            } else {
                temp = date;
                result.add(temp);
            }
        }

        put("availableDates", result);
    }

    public void put(String field, Object value) {
        json.put(field, value);
    }
}
