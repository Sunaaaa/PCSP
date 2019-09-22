package com.example.pcsp;

public class ReservationVO {
    int carReservationNum;
    String carNum;
    int carUser;
    String carRentStartTime;
    String carRentEndTime;
    String reservationStatus;

    public int getCarReservationNum() {
        return carReservationNum;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public int getCarUser() {
        return carUser;
    }

    public void setCarUser(int carUser) {
        this.carUser = carUser;
    }

    public String getCarRentStartTime() {
        return carRentStartTime;
    }

    public void setCarRentStartTime(String carRentStartTime) {
        this.carRentStartTime = carRentStartTime;
    }

    public String getCarRentEndTime() {
        return carRentEndTime;
    }

    public void setCarRentEndTime(String carRentEndTime) {
        this.carRentEndTime = carRentEndTime;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
