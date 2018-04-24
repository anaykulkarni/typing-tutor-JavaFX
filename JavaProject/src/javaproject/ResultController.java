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
public class ResultController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label resultHead;

    @FXML
    private Label resultBody;

    @FXML
    private Label speed;

    @FXML
    private Label accuracy;

    @FXML
    private Label timeSpent;

    @FXML
    private JFXButton homeButton;
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
    public void initializeMyData( int wordCount, int errorCount, int totalChar ){
       
        double acc = (double) (100 - (errorCount * 100)/totalChar);
        
        speed.setText( String.format("%d", wordCount));
        accuracy.setText(String.format("%.1f", acc));

        timeSpent.setText("1:00");

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
