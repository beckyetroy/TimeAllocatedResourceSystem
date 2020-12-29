package application.controller;

import application.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static AnchorPane root;
    static List<AnchorPane> anchor = new ArrayList<AnchorPane>();
    private static int sceneIndex = 0;

    private static User user = null;



    @Override
    public void start(Stage primaryStage) throws Exception{
        root = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Anchor.fxml"));

        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"))); //index 0
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/RegisterManager.fxml"))); //index 1
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/LogIn.fxml"))); //index 2
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/MainManager.fxml"))); //index 3
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/MainUser.fxml"))); //index 4
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/RegisterUser.fxml"))); //index 5
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/EditUser.fxml"))); //index 6
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/RemoveUser.fxml"))); //index 7
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/CreateFacility.fxml"))); //index 8
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/EditFacility.fxml"))); //index 9
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/RemoveFacility.fxml"))); //index 10
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/CreateReservation.fxml"))); //index 11
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/EditReservation.fxml"))); //index 12
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("../view/RemoveReservation.fxml"))); //index 13
        root.getChildren().add(anchor.get(0)); // initially set to MainScreen

        primaryStage.setTitle("Becky's Time Allocated Resource System");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    private void init_app(){
        for(int i=0; i<anchor.size();i++){
            //can be used to initiate some details about each pane
        }
    }

    public static AnchorPane get_pane(int index){
        return anchor.get(index);
    }

    public static void set_pane(int index){
        if (index >= 0 && index < anchor.size()) {
            root.getChildren().remove(anchor.get(sceneIndex));  //remove the existing pane from the root
            root.getChildren().add(anchor.get(index));          //add the selected pane to the root
            sceneIndex=index;                                   //update the stored sceneIndex
        }
        else {
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
        System.out.println("User: " + user.toString());
    }
}
