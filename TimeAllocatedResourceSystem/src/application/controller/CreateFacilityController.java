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

public class CreateFacilityController {

    @FXML private TextField facilityname;
    @FXML private TextArea txtAreaFeedback;

    protected SystemModel system;

    public void initialize() {
        facilityname.setText(null);
        txtAreaFeedback.setText(null);
    }

    public void handleCreateBtn(ActionEvent e) throws Exception {
        if(facilityname.getText() == null){
            txtAreaFeedback.setText("Please fill in facility name");
        }

        else if (create(facilityname.getText()))
        {
            System.out.println("Facility created successfully");
            Main.set_pane(3);
        }
    }

    private boolean create(String facilityname) {
        ArrayList<Facility> facilities= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("facilities.xml"));
            facilities = (ArrayList<Facility>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            facilities =  new ArrayList<Facility>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        int id = 1001000+facilities.size();
        if (facilities.size() > 0) {
            for (int i = 0; i < facilities.size(); i++) {
                Facility f = facilities.get(i);
                if (id == f.getFacilityId()) {
                    id++;
                } else {
                }
            }
        }

        try {
            Facility facility = new Facility(id, facilityname);

            facilities.add(facility);
            System.out.println("Facility ID: " + facility.getFacilityId());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("facilities.xml"));
            out.writeObject(facilities);
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