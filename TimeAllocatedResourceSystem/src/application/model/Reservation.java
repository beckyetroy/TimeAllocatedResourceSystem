package application.model;

import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private int userId;
    private int facilityId;
    private LocalDate date;
    private double duration;

    public Reservation(int reservationId, int userId, int facilityId, LocalDate date, double duration) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.facilityId = facilityId;
        this.date = date;
        this.duration = duration;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Reservation{" + "Reservation ID: " + reservationId +
                ", User ID: " + userId +
                ", Facility ID: " + facilityId +
                ", Date Reserved for: " + date +
                ", Length Reserved for: " + duration + " hours}";
    }
}
