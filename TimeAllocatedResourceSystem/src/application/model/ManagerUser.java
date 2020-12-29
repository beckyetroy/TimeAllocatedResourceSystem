package application.model;

import java.time.LocalDate;

public class ManagerUser extends User {

    private char userType;

    public ManagerUser(int id, String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password, char userType) {
        super(id, foreName, surName, phoneNumber, dob, email, password);
        this.userType = userType;
    }

    public ManagerUser(int id, String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password) {
        super(id, foreName, surName, phoneNumber, dob, email, password);
        userType = 'A';
    }

    public char getUserType() {
        return userType;
    }

    public void setUserType(char userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "ManagerUser{" + super.toString() +
                ", User Type (Admin/Customer): " + userType +
                '}' + '\n';
    }
}
