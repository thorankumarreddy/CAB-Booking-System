package com.cab.booking.cab.dto;

public class Booking {
    private String id;
    private String pickup;
    private String dropoff;
    private String date;
    private String time;
    private String cabType;
    private String DriverName;
    private String paymentMethod;
    private String cardNo;
    private String cvv;
    private String expiryDate;
    private String fullName;

    private String username;
    private String rating;
    private String payment;
    private String feedback;
    private String amount;
    private boolean check=true;


    public Booking() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
// Getters and setters for all fields


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPickup() {
        return pickup;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCabType() {
        return cabType;
    }
    public void setCabType(String cabType) {
        this.cabType = cabType;
    }
    public String getDriverName() {
        return DriverName;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getRating(String bookingId) {
        return 0;
    }
// You can also add validation annotations if necessary
}
