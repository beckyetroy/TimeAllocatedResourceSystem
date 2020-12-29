package application.controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import application.controller.Main;
import application.model.ManagerUser;
import application.model.SystemModel;
import application.model.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterManagerController {

    @FXML private TextField firstname;
    @FXML private TextField surname;
    @FXML private TextField phonenumber;
    @FXML private TextField email;
    @FXML private DatePicker dob;
    @FXML private PasswordField password;
    @FXML private PasswordField repeat_password;
    @FXML private TextArea txtAreaFeedback;

    protected SystemModel system;

    public void initialize() {
        firstname.setText(null);
        surname.setText(null);
        phonenumber.setText(null);
        email.setText(null);
        dob.setValue(null);
        password.setText(null);
        repeat_password.setText(null);
        txtAreaFeedback.setText(null);
    }

    public void handleRegisterBtn(ActionEvent e) throws Exception {
        if(firstname.getText() == null || surname.getText() == null || phonenumber.getText() == null
                || email.getText() == null || dob.getValue() == null || password.getText() == null ||
                repeat_password.getText() == null){
            txtAreaFeedback.setText("Please fill in all fields");
        }
        else if(password.getText().length()<8 ){
            txtAreaFeedback.setText("Password must be at least 8 characters");
        }
        else if(!password.getText().equals(repeat_password.getText())){
            txtAreaFeedback.setText("Password and Verified Password must match");
        }
        else if(!email.getText().contains("@") || !email.getText().contains(".")){
            txtAreaFeedback.setText("Invalid email format");
        }

        else if (register(firstname.getText(), surname.getText(), phonenumber.getText(), dob.getValue(), email.getText(),
                    password.getText()))
        {
            System.out.println("Registration successful");
            Main.set_pane(0);
        }
    }

    private boolean register(String firstname, String surname, String phonenumber, LocalDate dob, String email, String password) {
        ArrayList<User> users= null;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            users =  new ArrayList<User>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        int id = 2020000+users.size();
        if(users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                if (id == users.get(i).getUserId()) {
                    id++;
                } else {
                }
            }
        }

        try {
            User user = new ManagerUser(id, firstname, surname, phonenumber, dob, email, password);

            users.add(user);
            System.out.println("Your user ID is " + user.getUserId());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
            out.writeObject(users);
            out.close();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleCancelBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }
}
