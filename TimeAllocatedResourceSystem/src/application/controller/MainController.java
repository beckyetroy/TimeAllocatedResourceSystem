package application.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import java.nio.channels.FileChannel;
import java.util.ResourceBundle;


import application.controller.Main;
import application.model.SystemModel;
import application.model.Facility;
import application.model.User;
import application.model.Reservation;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

public class MainController implements Initializable {

    protected SystemModel system;

    @FXML private Button registerManagerBtn;
    @FXML private Button logInManagerBtn;
    @FXML private Button registerUserBtn;
    @FXML private Button logInUserBtn;

    public void handleRegisterManagerBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(1);
    }

    public void handleLogInManagerBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(2);
    }

    public void handleLogInUserBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(2);
    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        system = new SystemModel();

        try {
            system.loadFacilities();
        }
        catch (Exception e) {
            System.out.println("Error loading Facilities");
        }

        try{
            system.loadUsers();
        }
        catch (Exception e){
            System.out.println("Error loading Users");
        }

        try{
            system.loadReservations();
        }
        catch (Exception e){
            System.out.println("Error loading Reservations");
        }
    }
}