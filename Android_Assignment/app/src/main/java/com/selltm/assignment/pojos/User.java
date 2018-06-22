package com.selltm.assignment.pojos;

import com.selltm.assignment.cabs.pojos.Cab;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Asim on 21/06/18.
 */

public class User {
    private static User user;
    private String userEmail;
    private String pickupCity, dropCity;
    private Calendar pickupDateAndTime;
    private boolean oneWayTrip = false;
    private String tripType;
    private Cab cabSelected;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userEmail.substring(0, userEmail.indexOf("@"));
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getDropCity() {
        return dropCity;
    }

    public void setDropCity(String dropCity) {
        this.dropCity = dropCity;
    }

    public Calendar getPickupDateAndTime() {
        return pickupDateAndTime;
    }

    public String getPickupDateAndTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM - hh:mm a");
        return sdf.format(pickupDateAndTime.getTime());
    }

    public void setPickupDateAndTime(Calendar pickupDateAndTime) {
        this.pickupDateAndTime = pickupDateAndTime;
    }

    public boolean isOneWayTrip() {
        return oneWayTrip;
    }

    public void setOneWayTrip(boolean oneWayTrip) {
        this.oneWayTrip = oneWayTrip;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public Cab getCabSelected() {
        return cabSelected;
    }

    public void setCabSelected(Cab cabSelected) {
        this.cabSelected = cabSelected;
    }

    private User() {}
    public static User getInstance() {
        if(null == user) {
            user = new User();
        }
        return user;
    }
}
