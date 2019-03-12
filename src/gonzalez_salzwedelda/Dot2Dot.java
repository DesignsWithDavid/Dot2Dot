/*
 * Course: CS2852 - 021
 * Spring 2019
 * Lab 1 - Dot2Dot
 * Name: David Gonzalez-Salzwedel
 * Created: 03/05/2019
 */
package gonzalez_salzwedelda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This class initializes the FXML file and its attributes
 */
public class Dot2Dot extends Application {
    /**
     * This method creates a stage that contains the FXML file for the UI
     *
     * @param mainStage the stage that will host the GUI
     * @throws Exception any exception that occurs is propagated
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        //create main stage
        FXMLLoader mainLoader = new FXMLLoader();
        Parent root = mainLoader.load(getClass().getResource("Dot2Dot.fxml").openStream());
        mainStage.setTitle("Dot to Dot");
        mainStage.setScene(new Scene(root));
        mainStage.show();

    }

    /**
     * This method initializes the program
     * @param args Any launched arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
