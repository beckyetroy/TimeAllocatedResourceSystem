package application.controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import application.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static java.lang.Integer.parseInt;

public class CreateReservationController {

    @FXML private TextField userid;
    @FXML private TextField userdetails;
    @FXML private TextField facilityid;
    @FXML private TextField facilitydetails;
    @FXML private DatePicker date;
    @FXML private ComboBox<Double> durationComboCategory;
    @FXML private TextArea txtAreaFeedback;

    protected SystemModel system;

    public void initialize() {

        userid.setText(null);
        userdetails.setText(null);
        facilityid.setText(null);
        facilitydetails.setText(null);
        date.setValue(null);
        durationComboCategory.setValue(null);
        txtAreaFeedback.setText(null);

        ObservableList<Double> durationComboCategories = FXCollections.observableArrayList(0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0);

        durationComboCategory.setItems(durationComboCategories);
    }

    Integer userIndex;

    public int getUserIndex() {
        return userIndex;
    }

    public void setUserIndex(int userIndex) {
        this.userIndex = userIndex;
    }

    Integer facilityIndex;

    public int getFacilityIndex() {
        return facilityIndex;
    }

    public void setFacilityIndex(int facilityIndex) {
        this.facilityIndex = facilityIndex;
    }

    public void handleSelectUserIdBtn(ActionEvent e) throws Exception {
        userdetails.setText(null);
        ArrayList<User> users = null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
        users = (ArrayList<User>) is.readObject();
        is.close();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUserId() == parseInt(userid.getText())) {
                userdetails.setText(user.toString());
                setUserIndex(i);
            }
            else {
            }
        }
    }

    public void handleSelectFacilityIdBtn(ActionEvent e) throws Exception {
        facilitydetails.setText(null);
        ArrayList<Facility> facilities = null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("facilities.xml"));
        facilities = (ArrayList<Facility>) is.readObject();
        is.close();

        for (int i = 0; i < facilities.size(); i++) {
            Facility facility = facilities.get(i);
            if (facility.getFacilityId() == parseInt(facilityid.getText())) {
                facilitydetails.setText(facility.toString());
                setFacilityIndex(i);
            }
        }
    }

    public void handleCreateBtn(ActionEvent e) throws Exception {

        if(userid.getText() == null || facilityid.getText() == null || date.getValue() == null ||
                durationComboCategory.getValue() == null) {
            txtAreaFeedback.setText("Please fill in all fields.");
        }

        else if(facilitydetails.getText() == null || userdetails.getText() == null) {
            txtAreaFeedback.setText("Please enter a valid user ID and facility ID.");
        }

        else if (create(parseInt(userid.getText()), parseInt(facilityid.getText()), date.getValue(), durationComboCategory.getValue()))
        {
            System.out.println("Reservation created successfully");
            Main.set_pane(3);
        }
        else {
            System.out.println("Sorry, there was a problem completing your request.");
        }
    }

    private boolean create(int userid, int facilityid, LocalDate date, double duration) {
        ArrayList<Reservation> reservations= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
            reservations = (ArrayList<Reservation>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            reservations =  new ArrayList<Reservation>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            int id = 3000010+reservations.size();
            if (reservations.size() > 0) {
                for (int i = 0; i < reservations.size(); i++) {
                    Reservation r = reservations.get(i);
                    if (id == r.getReservationId()) {
                        id++;
                    }
                    if (facilityid == r.getFacilityId() && date.getDayOfMonth() == r.getDate().getDayOfMonth()
                    && date.getMonthValue() == r.getDate().getMonthValue() && date.getYear() == r.getDate().getYear()) {
                        txtAreaFeedback.setText("Sorry, this facility is already being utilised on this date.");
                        return false;
                    }
                }
            }
            Reservation reservation = new Reservation(id, userid, facilityid, date, duration);

            reservations.add(reservation);
            System.out.println("Reservation ID: " + reservation.getReservationId());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("reservations.xml"));
            out.writeObject(reservations);
            out.close();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }
}
