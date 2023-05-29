package com.cab.booking.cab.dto;

public class Driver {
    private String drivername;
    private String password;
    private String bookId;
    private String rideStatus;
    private String pickup;
    private  String drop;
    private String pickLat;
    private String pickLong;
    private String dropLat;
    private String dropLong;
    private  int count;

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getPickLat() {
        return pickLat;
    }

    public void setPickLat(String pickLat) {
        this.pickLat = pickLat;
    }

    public String getPickLong() {
        return pickLong;
    }

    public void setPickLong(String pickLong) {
        this.pickLong = pickLong;
    }

    public String getDropLat() {
        return dropLat;
    }

    public void setDropLat(String dropLat) {
        this.dropLat = dropLat;
    }

    public String getDropLong() {
        return dropLong;
    }

    public void setDropLong(String dropLong) {
        this.dropLong = dropLong;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
