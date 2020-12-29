package application.controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import application.controller.Main;
import application.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static java.lang.Integer.parseInt;

public class RemoveReservationController {

    @FXML
    private TextField reservationId;
    @FXML
    private TextArea reservationDetails;

    protected SystemModel system;

    Integer index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void initialize() {
        reservationId.setText(null);
        reservationDetails.setText(null);
    }

    public void handleReservationIdSelectBtn(ActionEvent e) throws Exception {
        reservationDetails.setText(null);
        ArrayList<Reservation> reservations= null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
        reservations = (ArrayList<Reservation>) is.readObject();
        is.close();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getReservationId() == parseInt(reservationId.getText())) {
                setIndex(i);
                reservationDetails.setText(reservation.toString());
            }
        }
    }

    public void handleRemoveReservationBtn(ActionEvent e) throws Exception {
        if (index == null) {
            System.out.println("Sorry, that ID is invalid");
        }
        else if (remove(index))
        {
            System.out.println("Reservation deleted successfully");
            Main.set_pane(3);
        }
    }

    private boolean remove(int index) {
        ArrayList<Reservation> reservations= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
            reservations = (ArrayList<Reservation>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            reservations =  new ArrayList<Reservation>();
            System.out.println("New Password File");
        }
        catch (Exception e) {
            System.out.println("Error accessing Password File");
            return false;
        }

        try {
            reservations.remove(index);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("reservations.xml"));
            out.writeObject(reservations);
            out.close();
        }
        catch (Exception e) {
            reservationDetails.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }
}
