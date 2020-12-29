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

public class EditUserController {

    @FXML
    private TextField userId;
    @FXML
    private TextField firstname;
    @FXML
    private TextField surname;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeat_password;
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
        userId.setText(null);
        firstname.setText(null);
        surname.setText(null);
        phonenumber.setText(null);
        email.setText(null);
        dob.setValue(null);
        password.setText(null);
        repeat_password.setText(null);
    }

    public void HandleUserIdSelectBtn(ActionEvent e) throws Exception {
        firstname.setText(null);
        surname.setText(null);
        dob.setValue(null);
        email.setText(null);
        phonenumber.setText(null);
        password.setText(null);

        ArrayList<User> users= null;

        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
        users = (ArrayList<User>) is.readObject();
        is.close();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUserId() == parseInt(userId.getText())) {
                firstname.setText(user.getForeName());
                surname.setText(user.getSurName());
                dob.setValue(user.getDob());
                email.setText(user.getEmail());
                phonenumber.setText(user.getPhoneNumber());
                password.setText(user.getPassword());
                setIndex(i);
            }
        }
    }

    public void handleSaveChangesBtn(ActionEvent e) throws Exception {
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
        else if (index == null){
            txtAreaFeedback.setText("Please enter a valid user ID.");
        }
        else if (update(index, firstname.getText(), surname.getText(), phonenumber.getText(), dob.getValue(), email.getText(),
                password.getText()))
        {
            System.out.println("Edit successful");
            Main.set_pane(3);
        }
    }

    private boolean update(int index, String firstname, String surname, String phonenumber, LocalDate dob, String email, String password) {
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

        try {
            User u = users.get(index);
            u.setForeName(firstname);
            u.setSurName(surname);
            u.setPhoneNumber(phonenumber);
            u.setDob(dob);
            u.setEmail(email);
            u.setPassword(password);

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
        Main.set_pane(3);
    }
}

