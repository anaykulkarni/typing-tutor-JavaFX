/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Anay
 */
public class QuickTestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton abortButton;

    @FXML
    private Label timeLabel;

    @FXML
    private ImageView refreshButton;

    @FXML
    private JFXTextArea textDisplay;
    
    @FXML
    private Button resultButton;

    @FXML
    private Button timerButton;
    
    //For Sounds
    private static final AudioClip ALERT_AUDIOCLIP = new AudioClip(TutorialController.class.getResource("/Audio/alert.mp3").toString());
    
    private static final AudioClip TYPING_AUDIOCLIP = new AudioClip(TutorialController.class.getResource("/Audio/typing.wav").toString());
    
    //progress tracking variables
       
    private int errorCount;
    
    private int totalChar;
    
    private char expectedKey;
    
    private char typedKey; 
    
    int indexOfLine=0;
    
    String arr="";
    
    int wordCount=0;
    
    //Timer variables
    Timeline timeline;
    
    int mins = 0, secs = 0, millis = 0;
        
    boolean sos = true;
        
    boolean timerStarted = false;
   
   
    @FXML
    void goToMainPage(ActionEvent event) {
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
    void switchSceneToResult(ActionEvent event) {
        try{ 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Result.fxml"));
            Parent root = loader.load();
        
            Scene scene = new Scene(root);
            ResultController controller = loader.getController();
        
                        
            controller.initializeMyData( ++wordCount,  errorCount,  totalChar );
        
            Stage theStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    void restartTest(MouseEvent event){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("QuickTest.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage theStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();
            
            QuickTestController controller = loader.getController();
            controller.loadTest();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
            
    

    void loadTest(){
        try{
            BufferedReader reader = new BufferedReader( new FileReader( new File("files\\Test.txt") ) ); 
            String line;
            while((line = reader.readLine()) != null){
                textDisplay.setText( textDisplay.getText() + line);
                arr += line;
            }
            reader.close();
            textDisplay.requestFocus();
            errorCount =0; indexOfLine=0; wordCount = 0; totalChar = arr.length();
            
            QuickTestController.ALERT_AUDIOCLIP.setRate(2.0);
            QuickTestController.TYPING_AUDIOCLIP.setVolume(1.0); 
            
            textDisplay.setStyle("-fx-highlight-fill: #bbdefb; -fx-highlight-text-fill: #2196f3;");
            textDisplay.selectRange(indexOfLine, indexOfLine+1);
            
            textDisplay.setOnKeyTyped( new EventHandler<KeyEvent>(){
                @Override
                public void handle(KeyEvent event) {
                    if(!timerStarted){
                        timerStarted = true;
                        timerButton.fire();
                    }
                    
                    expectedKey = arr.charAt(indexOfLine);
                    //System.out.println(expectedKey);
                    typedKey = event.getCharacter().charAt(0);
                    if(indexOfLine < arr.length()){
                        if(typedKey != expectedKey){
                            QuickTestController.ALERT_AUDIOCLIP.play();
                            errorCount++;
                            indexOfLine++;
                            textDisplay.setStyle("-fx-background-color: #ffcdd2;-fx-highlight-fill: #bbdefb; -fx-highlight-text-fill: #2196f3;");
                            textDisplay.selectRange(indexOfLine, indexOfLine+1);
                        }
                        else{
                            if(typedKey == ' ')
                                wordCount++;
                            QuickTestController.TYPING_AUDIOCLIP.play();
                            indexOfLine++;
                            textDisplay.setStyle("-fx-background-color: white;-fx-highlight-fill: #bbdefb; -fx-highlight-text-fill: #2196f3;");
                            textDisplay.selectRange(indexOfLine, indexOfLine+1);
                        }
                        
                    }
                    if(indexOfLine == arr.length()){
                        timerButton.fire();
                        resultButton.fire();
                    }
                }
            });
            
        }catch( IOException ex){
            ex.printStackTrace();
        }
    }
    //method to change the text of the Elapsed Time label.
    void change(){
		if(millis == 1000) {
			secs++;
			millis = 0;
		}
		if(secs == 60) {
			mins++;
			secs = 0;
		}
		timeLabel.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
		 + (((secs/10) == 0) ? "0" : "") + secs);
                millis++;
                if(timeLabel.getText().equals("01:00")){
                        timerButton.fire();
                        resultButton.fire();
                }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Initially Elapsed time is set to 0.
        timeLabel.setText("00:00");
        
        timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
	@Override
            public void handle(ActionEvent event) {
                change();
            }
	}));
        
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.setAutoReverse(false);
        
        //Action to be performed by timer button on firing.
	timerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(sos) {
            		timeline.play();
            		sos = false;
            		timerButton.setText("Stop");
            	} else {
            		timeline.pause();
            		sos = true;
            		timerButton.setText("Start");
            	}
            }
        });
        
    }    
    
}
