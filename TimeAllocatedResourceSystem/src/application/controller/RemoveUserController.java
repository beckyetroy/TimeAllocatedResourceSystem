package application.controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import application.controller.Main;
import application.model.CustomerUser;
import application.model.ManagerUser;
import application.model.SystemModel;
import application.model.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static java.lang.Integer.parseInt;

public class RemoveUserController {

    @FXML
    private TextField userId;
    @FXML
    private TextArea userDetails;

    protected SystemModel system;

    Integer index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void initialize() {
        userId.setText(null);
        userDetails.setText(null);
    }

    public void handleUserIdSelectBtn(ActionEvent e) throws Exception {
        userDetails.setText(null);
        ArrayList<User> users= null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
        users = (ArrayList<User>) is.readObject();
        is.close();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUserId() == parseInt(userId.getText())) {
                setIndex(i);
                userDetails.setText(user.toString());
            }
        }
    }

    public void handleRemoveUserBtn(ActionEvent e) throws Exception {
        if (index == null) {
            userDetails.setText("Sorry, that ID is invalid.");
        }
        else if (remove(index))
        {
            System.out.println("User deleted successfully");
            Main.set_pane(3);
        }
    }

    private boolean remove(int index) {
        ArrayList<User> users= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            users =  new ArrayList<User>();
            System.out.println("New Password File");
        }
        catch (Exception e) {
            System.out.println("Error accessing Password File");
            return false;
        }

        try {
            users.remove(index);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
            out.writeObject(users);
            out.close();
        }
        catch (Exception e) {
            userDetails.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }
}

