package application.controller;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.controller.Main;
import application.model.ManagerUser;
import application.model.SystemModel;
import application.model.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainManagerController {

    @FXML
    private TextArea userList;
    @FXML
    private TextArea facilityList;
    @FXML
    private TextArea reservationList;
    @FXML
    private Label greetingText;

    protected SystemModel system;

    public void initialize() {

        system = new SystemModel();

        try {
            reservationList.setText(null);
            userList.setText(null);
            facilityList.setText(null);

        } catch (Exception e) {

        }
    }

    public void handleListUsers(ActionEvent e) throws Exception {
        system.loadUsers();
        userList.setText(system.listOfUsers());
    }

    public void handleCreateUserBtn(ActionEvent e) throws Exception {
        Main.set_pane(5);
    }

    public void handleEditUserBtn(ActionEvent e) throws Exception {
        Main.set_pane(6);
    }

    public void handleRemoveUserBtn(ActionEvent e) throws Exception {
        Main.set_pane(7);
    }

    public void handleListFacilities(ActionEvent e) throws Exception {
        system.loadFacilities();
        facilityList.setText(system.listOfFacilities());
    }

    public void handleCreateFacilityBtn(ActionEvent e) throws Exception {
        Main.set_pane(8);
    }

    public void handleEditFacilityBtn(ActionEvent e) throws Exception {
        Main.set_pane(9);
    }

    public void handleRemoveFacilityBtn(ActionEvent e) throws Exception {
        Main.set_pane(10);
    }

    public void handleListReservations(ActionEvent e) throws Exception {
        system.loadReservations();
        reservationList.setText(system.listOfReservations());
    }

    public void handleCreateReservationBtn(ActionEvent e) throws Exception {
        Main.set_pane(11);
    }

    public void handleEditReservationBtn(ActionEvent e) throws Exception {
        Main.set_pane(12);
    }

    public void handleRemoveReservationBtn(ActionEvent e) throws Exception {
        Main.set_pane(13);
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

    public void handleSaveBtn(ActionEvent e) {
        try {
            system.saveUsers();
            System.out.println("Users saved");
        }
        catch (Exception exception) {
            System.out.println("Error saving Users");
        }
        try {
            system.saveFacilities();
            System.out.println("Facilities saved");
        }
        catch (Exception exception){
            System.out.println("Error saving Facilities");
        }

        try {
            system.saveReservations();
            System.out.println("Reservations Saved");
        }
        catch (Exception exception) {
            System.out.println("Error saving Reservations");
        }
    }

    public void handleLoadBtn(ActionEvent e) {
        try {
            system.loadFacilities();
            System.out.println("Facilities loaded");
        } catch (Exception exception) {
            System.out.println("Error loading Facilities");
        }

        try {
            system.loadUsers();
            System.out.println("Users loaded");
        } catch (Exception exception) {
            System.out.println("Error loading Users");
        }

        try {
            system.loadReservations();
            System.out.println("Reservations loaded");
        } catch (Exception exception) {
            System.out.println("Error loading Reservations");
        }
    }
}
