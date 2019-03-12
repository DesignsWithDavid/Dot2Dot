/*
 * Course: CS2852 - 021
 * Spring 2019
 * Lab 1 - Dot2Dot
 * Name: David Gonzalez-Salzwedel
 * Created: 03/05/2019
 */
package gonzalez_salzwedelda;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

/**
 * This class controls the Dot2Dot FXML file
 */
public class Dot2DotController implements Initializable {

    private Picture picture = new Picture();

    @FXML
    private Canvas canvas;

    @FXML
    private Menu editMenu;

    @FXML
    private MenuItem dotNumbersMenuItem;

    @FXML
    private Label numDotsLabel;

    @FXML
    /**
     * This method loads in the desired file from the user, limiting the choices to only
     * ".DOT" files while handing the errors that could be thrown.
     */
    private void load(){
        //open the file chooser's open UI
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File Selection");
        fileChooser.getExtensionFilters().addAll(//limit the file types that the user can select from
                new FileChooser.ExtensionFilter("Dot Files", "*.DOT"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        try {
            //if the user inputs a file, open it
            if (selectedFile != null) {
                picture.load(selectedFile.toPath());
                editMenu.setVisible(true);
            }
        } catch(IllegalArgumentException e){ //if the user inputs an improper file extension
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Unsupported File Type");
            alert.setContentText("The file extension you entered is not supported");
            alert.showAndWait();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unexpected Error");
            alert.setContentText("An error occurred while reading the file.");
            alert.showAndWait();
        } catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Corrupted File");
            alert.setHeaderText("Corrupted File");
            alert.setContentText("The input file is either corrupted or not in the proper format.");
            alert.showAndWait();
        }

    }

    @FXML
    /**
     * This method closes the program
     */
    private void close(){
        Platform.exit();
    }

    /**
     * This method calls refresh so that it only draws lines
     */
    @FXML
    private void drawLines(){
        picture.refresh(false, true);
    }

    /**
     * This method calls refresh so that it only draws dots
     */
    @FXML
    private void drawDots(){
        picture.refresh(true, false);
    }

    /**
     * This method calls refresh so that it draws both lines and dots
     */
    @FXML
    private void drawBoth(){
        picture.refresh(true, true);
    }

    /**
     * This method turns on and off the numbers corresponding to each dot
     * while changing the menu options text.
     */
    @FXML
    private void toggleDotNumbers(){
        if (dotNumbersMenuItem.getText().equals("Turn on Dot Numbers")){
            dotNumbersMenuItem.setText("Turn off Dot Numbers");
            picture.setNumbers(true);
        } else{
            dotNumbersMenuItem.setText("Turn on Dot Numbers");
            picture.setNumbers(false);
        }
        picture.refresh();
    }

    /**
     * This method is required to implement the Initializable Interface.
     * In this case it also initializes the button list, deactivates all
     * buttons other than OPEN, and initializes the ImageIO object.
     * @param location URL
     * @param resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editMenu.setVisible(false);
        picture.setCanvas(canvas);
        picture.setNumDotsLabel(numDotsLabel);
    }
}
