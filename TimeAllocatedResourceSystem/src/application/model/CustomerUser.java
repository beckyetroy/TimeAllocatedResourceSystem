package application.model;

import java.time.LocalDate;

public class CustomerUser extends User {

    private char userType;

    public CustomerUser(int id, String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password, char userType) {
        super(id, foreName, surName, phoneNumber, dob, email, password);
        this.userType = userType;
    }

    public CustomerUser(int id, String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password) {
        super(id, foreName, surName, phoneNumber, dob, email, password);
        userType = 'C';
    }

    public char getUserType() {
        return userType;
    }

    public void setUserType(char userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "CustomerUser{" + super.toString() +
                ", User Type (Admin/Customer): " + userType +
                '}' + '\n';
    }
}
