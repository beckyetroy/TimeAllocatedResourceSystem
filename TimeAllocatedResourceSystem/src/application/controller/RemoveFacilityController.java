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

public class RemoveFacilityController {

    @FXML
    private TextField facilityId;
    @FXML
    private TextArea facilityDetails;

    protected SystemModel system;

    Integer index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void initialize() {
        facilityId.setText(null);
        facilityDetails.setText(null);
    }

    public void handleFacilityIdSelectBtn(ActionEvent e) throws Exception {
        facilityDetails.setText(null);
        ArrayList<Facility> facilities= null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("facilities.xml"));
        facilities = (ArrayList<Facility>) is.readObject();
        is.close();

        for (int i = 0; i < facilities.size(); i++) {
            Facility facility = facilities.get(i);
            if (facility.getFacilityId() == parseInt(facilityId.getText())) {
                setIndex(i);
                facilityDetails.setText(facility.toString());
            }
        }
    }

    public void handleRemoveFacilityBtn(ActionEvent e) throws Exception {
        if (index == null) {
            facilityDetails.setText("Sorry that ID is invalid.");
        }
        else if (remove(index))
        {
            System.out.println("Facility deleted successfully");
            Main.set_pane(3);
        }
    }

    private boolean remove(int index) {
        ArrayList<Facility> facilities= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("facilities.xml"));
            facilities = (ArrayList<Facility>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            facilities =  new ArrayList<Facility>();
            System.out.println("New Password File");
        }
        catch (Exception e) {
            System.out.println("Error accessing Password File");
            return false;
        }

        try {
            facilities.remove(index);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("facilities.xml"));
            out.writeObject(facilities);
            out.close();
        }
        catch (Exception e) {
            facilityDetails.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }
}
