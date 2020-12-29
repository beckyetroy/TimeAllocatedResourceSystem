package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;


import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class creates three ArrayLists and contains methods to access and edit the objects
 * (facilities, users, and reservations) in the ArrayList
 *
 * @author Becky Troy
 */


public class SystemModel {

    /**
     * Default Constructor for class SystemModel
     */
    public SystemModel() {
    }

    /**
     * Declares and initiates a private ArrayList of type Facility, called facilities
     */
    private ArrayList<Facility> facilities = new ArrayList<>();

    /**
     * Declares and initiates a private ArrayList of type User, called users
     */
    private ArrayList<User> users = new ArrayList<>();

    /**
     * Declares and initiates a private ArrayList of type Reservation, called reservations
     */
    private ArrayList<Reservation> reservations = new ArrayList<>();

    /**
     * Method to add a Facility object to the ArrayList facilities
     */
    public void addFacility(String facilityName) {
        int facilityId = 1001000+facilities.size();
        for (int i =0; i <= facilities.size(); i++) {
            Facility f = facilities.get(i);
            if (facilityId == f.getFacilityId()) {
                facilityId++;
            } else {
            }
        }
        Facility facility = new Facility(facilityId, facilityName);
    }

    /**
     * Method to add a User object to the ArrayList users
     */
    public void addUser(String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password) {
        int userId = 2020000+users.size();
        for (int i =0; i <= users.size(); i++) {
            User u = users.get(i);
            if (userId == u.getUserId()) {
                userId++;
            }
            else {
            }
        }
        User user = new User(userId, foreName, surName, phoneNumber, dob, email, password);
    }

    /**
     * Method to add a Reservation object to the ArrayList reservations
     */
    public void addReservation(int userId, int facilityId, LocalDate date, double duration) {
        int reservationId = 3000010+reservations.size();
        Reservation reservation = new Reservation(reservationId, userId, facilityId, date, duration);
    }

    /**
     * Method to remove a Facility object from the Arraylist
     */
    public boolean removeFacility(int index) {
        //Validation statement - ensures index entered is not empty in the Arraylist
        if ((index < (facilities.size()) && (index >= 0))) {
            facilities.remove(index);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to remove a User object from the Arraylist
     */
    public boolean removeUser(int index) {
        //Validation statement - ensures index entered is not empty in the Arraylist
        if ((index < (users.size()) && (index >= 0))) {
            users.remove(index);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to remove a Reservation object from the Arraylist
     */
    public boolean removeReservation(int index) {
        //Validation statement - ensures index entered is not empty in the Arraylist
        if ((index < (reservations.size()) && (index >= 0))) {
            reservations.remove(index);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to update a User object in the ArrayList
     */
    public void updateUser(int index, String foreName, String surName, String phoneNumber, LocalDate dob, String email, String password) {
        User u = users.get(index);
        u.setForeName(foreName);
        u.setSurName(surName);
        u.setPhoneNumber(phoneNumber);
        u.setDob(dob);
        u.setEmail(email);
        u.setPassword(password);
    }

    /**
     * Method to build and return a String with all the Facility objects in the ArrayList
     */
    public String listOfFacilities() {
        //If the Arraylist is empty
        if (facilities.size() == 0) {
            return "There are no facilities at the moment.";
        }
        else {
            String listOfFacilities = "";
            for (int i = 0; i < facilities.size(); i++) {
                listOfFacilities = listOfFacilities + i + ": " + facilities.get(i).toString() + "\n";
            }
            return listOfFacilities;
        }
    }

    /**
     * Method to build and return a String with all the User objects in the ArrayList
     */
    public String listOfUsers() {
        //If the Arraylist is empty
        if (users.size() == 0) {
            return "There are no users at the moment.";
        }
        else {
            String listOfUsers = "";
            for (int i = 0; i < users.size(); i++) {
                listOfUsers = listOfUsers + i + ": " + users.get(i).toString() + "\n";
            }
            return listOfUsers;
        }
    }

    /**
     * Method to build and return a String with all the Reservation objects in the ArrayList
     */
    public String listOfReservations() {
        //If the Arraylist is empty
        if (reservations.size() == 0) {
            return "There are no reservations at the moment.";
        }
        else {
            String listofReservations = "";
            for (int i = 0; i < reservations.size(); i++) {
                listofReservations = listofReservations + i + ": " + reservations.get(i).toString() + "\n";
            }
            return listofReservations;
        }
    }

    /**

     * save() - This method saves the contents of the Facility ArrayList and bargains ArrayList to

     * an XML file called facilities.xml

     * */

    public void saveFacilities() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("facilities.xml"));

        out.writeObject(facilities);

        out.close();
    }

    /**

     * save() - This method saves the contents of the User ArrayList and bargains ArrayList to

     * an XML file called users.xml

     * */

    public void saveUsers() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));

        out.writeObject(users);

        out.close();
    }

    /**

     * save() - This method saves the contents of the Reservations ArrayList and bargains ArrayList to

     * an XML file called reservations.xml

     * */

    public void saveReservations() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("reservations.xml"));

        out.writeObject(reservations);

        out.close();
    }


    /**
     * load() - This method reads the contents of the XML file called
     * facilities.xml stored in the project directory.  The file should contain two components -

     *    an ArrayList of Facility, and

     *    an ArrayList of BargainBundle.

     * The contents are casted as an ArrayList of Facility and stored in the facilities variable.

     * The remaining contents are casted as an ArrayList of BargainBundle and stored in the bargains variable.

     * */

    public void loadFacilities() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("facilities.xml"));

        facilities = (ArrayList<Facility>) is.readObject();

        is.close();
    }

    /**
     * load() - This method reads the contents of the XML file called
     * users.xml stored in the project directory.  The file should contain two components -

     *    an ArrayList of User, and

     *    an ArrayList of BargainBundle.

     * The contents are casted as an ArrayList of User and stored in the users variable.

     * The remaining contents are casted as an ArrayList of BargainBundle and stored in the bargains variable.

     * */

    public void loadUsers() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));

        users = (ArrayList<User>) is.readObject();

        is.close();
    }

    /**
     * load() - This method reads the contents of the XML file called
     * reservations.xml stored in the project directory.  The file should contain two components -

     *    an ArrayList of Reservation, and

     *    an ArrayList of BargainBundle.

     * The contents are casted as an ArrayList of Reservation and stored in the reservations variable.

     * The remaining contents are casted as an ArrayList of BargainBundle and stored in the bargains variable.

     * */

    public void loadReservations() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));

        reservations = (ArrayList<Reservation>) is.readObject();

        is.close();
    }

}
