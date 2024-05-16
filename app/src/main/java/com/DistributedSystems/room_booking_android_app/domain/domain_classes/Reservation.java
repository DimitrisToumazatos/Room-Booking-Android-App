package com.DistributedSystems.room_booking_android_app.domain.domain_classes;
import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private final String nameOfCustomer;
    LocalDate startDate;
    LocalDate endDate;
    int roodId;

    public Reservation (String nameOfCustomer, int roodId, LocalDate startDate, LocalDate endDate) {
        this.nameOfCustomer = nameOfCustomer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roodId = roodId;
    }

    public String toString() {
        return "Room Id: " + roodId + "\nName Of Customer: " + nameOfCustomer + "\nArrival: " + startDate + "\nDeparture: " + endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
