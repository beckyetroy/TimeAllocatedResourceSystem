package application.model;

import java.time.LocalDate;

public class User {

    private int userId;
    private String foreName;
    private String surName;
    private String phoneNumber;
    private LocalDate dob;
    private String email;
    private String password;

    public User(int userId, String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password) {
        this.userId = userId;
        this.foreName = foreName;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public User(String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password) {
        this.foreName = foreName;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "User ID: " + userId +
                ", Full name: " + foreName + ' ' + surName +
                ", Phone Number: " + phoneNumber + '\'' +
                ", Date of Birth (yyyy-mm-dd): " + dob +
                ", E-mail Address: " + email +
                ", Password: " + password;
    }
}
