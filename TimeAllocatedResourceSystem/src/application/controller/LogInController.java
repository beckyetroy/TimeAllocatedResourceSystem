package application.controller;

import java.io.*;
import java.util.ArrayList;

import application.controller.Main;
import application.model.ManagerUser;
import application.model.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import static java.lang.Integer.parseInt;

public class LogInController {
    @FXML
    private TextField userid;
    @FXML
    private PasswordField password;
    @FXML
    private TextArea txtAreaFeedback;

    public static int customerUserId;

    public void initialize() {
        userid.setText(null);
        password.setText(null);
        txtAreaFeedback.setText(null);
    }

    public void handleLoginBtn(ActionEvent e) throws Exception {
        if (loginManager(userid.getText(), password.getText())) {
            System.out.println("Successful Login");
            Main.set_pane(3);
        }
        else if (loginUser(userid.getText(), password.getText())) {
            System.out.println("Successful Login");
            Main.set_pane(4);
        }
        else {
            txtAreaFeedback.setText("Unsuccessful Login. Please try again.");
            password.clear();
        }
    }

    private boolean loginManager(String userid, String password) {
        ArrayList<User> users = null;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        } catch (FileNotFoundException e) {
            users = new ArrayList<User>();
            txtAreaFeedback.setText("No users found");
            return false;

        } catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            for (User user : users) {
                if (user.getUserId() == (parseInt(userid)) && user.getPassword().equals(password) && user.toString().contains("ManagerUser{")) {
                    Main.setUser(user);
                    return true;
                } else {
                    txtAreaFeedback.setText("User not logged in");
                }
            }
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Sorry, please try again.");
            return false;
        }
        return false;
    }

    private boolean loginUser(String userid, String password) {
        ArrayList<User> users = null;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        } catch (FileNotFoundException e) {
            users = new ArrayList<User>();
            txtAreaFeedback.setText("No users found");
            return false;

        } catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        for (User user: users) {
            if (user.getUserId()==(parseInt(userid)) && user.getPassword().equals(password) && user.toString().contains("CustomerUser{")) {
                customerUserId = parseInt(userid);
                Main.setUser(user);
                return true;
            }
            else {
                txtAreaFeedback.setText("Sorry, please try again.");
            }
        }
        return false;
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }
}
