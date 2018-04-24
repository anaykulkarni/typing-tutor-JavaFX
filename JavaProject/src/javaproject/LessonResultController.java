/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anay
 */
public class LessonResultController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton goBackButton;

    @FXML
    private JFXButton redoButton;

    @FXML
    private JFXButton nextButton;

    @FXML
    private Label resultHead;

    @FXML
    private Label resultBody;

    @FXML
    private Label speedWPM;

    @FXML
    private Label speedKPM;

    @FXML
    private Label trueAccuracy;

    @FXML
    private Label timeSpent;

    @FXML
    private Label troubleKeys;

    @FXML
    private Label accuracy;
    
    private int currentLessonChoice;

    @FXML
    void goHome(ActionEvent event) {
        try{
            Stage theStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
            Scene scene = new Scene(root);
            theStage.setScene(scene);
            theStage.show();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void nextLesson(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Tutorial.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage theStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();
            
            TutorialController controller = loader.getController();
            controller.initializeLessonChoiceAndBegin( ++currentLessonChoice );
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void redoLesson(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Tutorial.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage theStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();
            
            TutorialController controller = loader.getController();
            controller.initializeLessonChoiceAndBegin( currentLessonChoice );               
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void initializeMyData( int totalChar, int errorCountWithBackspace, int errorCountWithoutBackspace, String timeToComplete, int wordCount, String troubleKeyString, int currentLessonChoice){
        try{
        Double timeInMin = (Double.parseDouble(timeToComplete.substring(0, 2)) + (Double.parseDouble(timeToComplete.substring(3, 5))/60.0)) ;
        double tacc = (double) (100 - (errorCountWithoutBackspace * 100)/totalChar);
        double acc = (double) (100 - (errorCountWithBackspace * 100)/totalChar);
        speedKPM.setText( String.format("%.0f", (totalChar/timeInMin)));
        speedWPM.setText( String.format("%.0f", (wordCount/timeInMin)));
        trueAccuracy.setText(String.format("%.1f", tacc));
        accuracy.setText(String.format("%.1f", acc) + "%");
        timeSpent.setText(timeToComplete);
        troubleKeys.setText(troubleKeyString);
        
        this.currentLessonChoice = currentLessonChoice;
        }catch(RuntimeException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
