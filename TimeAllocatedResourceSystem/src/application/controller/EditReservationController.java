package application.controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import application.controller.Main;
import application.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static java.lang.Integer.parseInt;

public class EditReservationController {

    @FXML
    private TextField reservationid;
    @FXML
    private TextField userid;
    @FXML
    private TextField userdetails;
    @FXML
    private TextField facilityid;
    @FXML
    private TextField facilitydetails;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Double> durationComboCategory;
    @FXML
    private TextArea txtAreaFeedback;

    protected SystemModel system;

    Integer index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void initialize() {

        reservationid.setText(null);
        userid.setText(null);
        userdetails.setText(null);
        facilityid.setText(null);
        facilitydetails.setText(null);
        date.setValue(null);
        durationComboCategory.setValue(null);

        ObservableList<Double> durationComboCategories = FXCollections.observableArrayList(0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0);

        durationComboCategory.setItems(durationComboCategories);
    }

    public void handleSelectReservationIdBtn(ActionEvent e) throws Exception {
        ArrayList<Reservation> reservations= null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
        reservations = (ArrayList<Reservation>) is.readObject();
        is.close();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getReservationId() == parseInt(reservationid.getText())) {
                userid.setText(String.valueOf(reservation.getUserId()));
                facilityid.setText(String.valueOf(reservation.getFacilityId()));
                date.setValue(reservation.getDate());
                durationComboCategory.setValue(reservation.getDuration());
                setIndex(i);
            }
        }
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
            }
        }
    }

    public void handleSaveChangesBtn(ActionEvent e) throws Exception {
        if(userid.getText() == null || facilityid.getText() == null || reservationid.getText() == null
                || date.getValue() == null || durationComboCategory.getValue() == null){
            txtAreaFeedback.setText("Please fill in all fields");
        }

        else if(facilitydetails.getText() == null || userdetails.getText() == null) {
            txtAreaFeedback.setText("Please confirm the facility and user IDs by searching.");
        }

        else if (index == null){
            txtAreaFeedback.setText("Please enter a valid Reservation ID");
        }
        else if (update(index, parseInt(userid.getText()), parseInt(facilityid.getText()), date.getValue(), durationComboCategory.getValue()))
        {
            System.out.println("Edit successful");
            Main.set_pane(3);
        }
    }

    private boolean update(int index, int userid, int facilityid, LocalDate date, double duration) {
        ArrayList<Reservation> reservations= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
            reservations = (ArrayList<Reservation>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            reservations = new ArrayList<Reservation>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            if (reservations.size() > 0) {
                for (int i = 0; i < reservations.size(); i++) {
                    Reservation r = reservations.get(i);
                    if (facilityid == r.getFacilityId() && date.getDayOfMonth() == r.getDate().getDayOfMonth()
                            && date.getMonthValue() == r.getDate().getMonthValue() && date.getYear() == r.getDate().getYear()) {
                        txtAreaFeedback.setText("Sorry, this facility is already being utilised on this date.");
                        return false;
                    }
                }
            }
            Reservation r = reservations.get(index);
            r.setUserId(userid);
            r.setFacilityId(facilityid);
            r.setDate(date);
            r.setDuration(duration);

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
