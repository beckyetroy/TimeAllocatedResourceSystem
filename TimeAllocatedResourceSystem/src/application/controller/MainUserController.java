package application.controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import application.controller.Main;
import application.model.ManagerUser;
import application.model.Reservation;
import application.model.SystemModel;
import application.model.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static java.lang.Integer.parseInt;

public class MainUserController {

    @FXML
    private TextArea reservationList;
    @FXML
    private Label greetingText;

    protected SystemModel system;

    public void initialise() {
        reservationList.setText(null);
    }

    public void handleViewBtn(ActionEvent e) throws Exception {
        ArrayList<Reservation> reservations= null;
        ArrayList<Reservation> customerReservations = new ArrayList<>();

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
        reservations = (ArrayList<Reservation>) is.readObject();
        is.close();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getUserId() == LogInController.customerUserId) {
                customerReservations.add(reservation);
            }
        }
        if (customerReservations.isEmpty()) {
            reservationList.setText("You have no reservations at the moment.");
        }
        else {
            reservationList.setText(customerReservations.toString());
        }
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

}
