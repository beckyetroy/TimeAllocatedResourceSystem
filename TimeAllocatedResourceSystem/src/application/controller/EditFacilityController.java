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

public class EditFacilityController {

    @FXML
    private TextField facilityid;
    @FXML
    private TextField facilityname;
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
        facilityid.setText(null);
        facilityname.setText(null);
        txtAreaFeedback.setText(null);
    }

    public void HandleIdSelectBtn(ActionEvent e) throws Exception {
        facilityname.setText("");
        ArrayList<Facility> facilities= null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("facilities.xml"));
        facilities = (ArrayList<Facility>) is.readObject();
        is.close();

        for (int i = 0; i < facilities.size(); i++) {
            Facility facility = facilities.get(i);
            if (facility.getFacilityId() == parseInt(facilityid.getText())) {
                facilityname.setText(facility.getFacilityName());
                setIndex(i);
            }
        }
    }

    public void handleSaveChangesBtn(ActionEvent e) throws Exception {
        if(facilityname == null){
            txtAreaFeedback.setText("Please fill in all fields");
        }

        else if (index == null){
            txtAreaFeedback.setText("Please enter a valid Facility ID");
        }
        else if (update(index, facilityname.getText()))
        {
            System.out.println("Edit successful");
            Main.set_pane(3);
        }
    }

    private boolean update(int index, String facilityname) {
        ArrayList<Facility> facilities= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("facilities.xml"));
            facilities = (ArrayList<Facility>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            facilities = new ArrayList<Facility>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            Facility f = facilities.get(index);
            f.setFacilityName(facilityname);

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
