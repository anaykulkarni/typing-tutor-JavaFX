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
import java.util.Timer;
import java.util.TimerTask;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javafx.util.Duration.millis;

/**
 * FXML Controller class
 *
 * @author Anay
 */
public class TutorialController implements Initializable{

    /**
     * Initializes the controller class.
     */
    @FXML
    private ChoiceBox<String> lessonChoiceBox;

    @FXML
    private CheckBox soundCheckBox;

    @FXML
    private Label ETLabel;

    @FXML
    private Label displayArea;

    @FXML
    private JFXTextArea textInputArea;
    
    @FXML
    private Button timerButton;
                        
    @FXML
    private JFXButton keybefore1;

    @FXML
    private JFXButton key1;

    @FXML
    private JFXButton key2;

    @FXML
    private JFXButton key3;

    @FXML
    private JFXButton key4;

    @FXML
    private JFXButton key5;

    @FXML
    private JFXButton key6;

    @FXML
    private JFXButton key7;

    @FXML
    private JFXButton key8;

    @FXML
    private JFXButton key9;

    @FXML
    private JFXButton key0;

    @FXML
    private JFXButton keyminus;

    @FXML
    private JFXButton keyplus;

    @FXML
    private JFXButton backspace;

    @FXML
    private JFXButton tab;

    @FXML
    private JFXButton q;

    @FXML
    private JFXButton w;

    @FXML
    private JFXButton e;

    @FXML
    private JFXButton r;

    @FXML
    private JFXButton t;

    @FXML
    private JFXButton y;

    @FXML
    private JFXButton u;

    @FXML
    private JFXButton i;

    @FXML
    private JFXButton o;

    @FXML
    private JFXButton p;

    @FXML
    private JFXButton keyBoxBracketL;

    @FXML
    private JFXButton keyBoxBracketR;

    @FXML
    private JFXButton keyBackslash;

    @FXML
    private JFXButton capslock;

    @FXML
    private JFXButton a;

    @FXML
    private JFXButton s;

    @FXML
    private JFXButton d;

    @FXML
    private JFXButton f;

    @FXML
    private JFXButton g;

    @FXML
    private JFXButton h;

    @FXML
    private JFXButton j;

    @FXML
    private JFXButton k;

    @FXML
    private JFXButton l;

    @FXML
    private JFXButton keySemicolon;

    @FXML
    private JFXButton keyAphostrophe;

    @FXML
    private JFXButton enter;

    @FXML
    private JFXButton shiftl;

    @FXML
    private JFXButton z;

    @FXML
    private JFXButton x;

    @FXML
    private JFXButton c;

    @FXML
    private JFXButton v;

    @FXML
    private JFXButton b;

    @FXML
    private JFXButton n;

    @FXML
    private JFXButton m;

    @FXML
    private JFXButton keyComma;

    @FXML
    private JFXButton keyDot;

    @FXML
    private JFXButton keyForwardslash;

    @FXML
    private JFXButton shiftr;

    @FXML
    private JFXButton ctrll;

    @FXML
    private JFXButton altl;

    @FXML
    private JFXButton space;

    @FXML
    private JFXButton altr;

    @FXML
    private JFXButton ctrlr;

    @FXML
    private JFXButton HomeButton;
    
    @FXML
    private JFXButton goButton;
    
    @FXML
    private Button goToResultButton;
    
    //For Sounds
    private static final AudioClip ALERT_AUDIOCLIP = new AudioClip(TutorialController.class.getResource("/Audio/alert.mp3").toString());
    
    private static final AudioClip TYPING_AUDIOCLIP = new AudioClip(TutorialController.class.getResource("/Audio/typing.wav").toString());
    
    //progress tracking variables
        
    private int errorCountWithoutBackspace;
    
    private int errorCountWithBackspace;
    
    private int totalChar;
    
    private char expectedKey;
    
    String timeToComplete;
    
    private char typedKey; 
    
    int indexOfLine=0;
    
    String line;
    
    int wordCount=0;
    
    private int[] problemKeyArray = new int[26];
    
    private char[] problemCharArray = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    
    //Timer variables
    Timeline timeline;
    
    int mins = 0, secs = 0, millis = 0;
        
    boolean sos = true;
        
    boolean timerStarted = false;
            
    //Method declarations.
    
    /**
     * goHome method is called on pressing HomeButton.
     * aborts the tutorial and opens landing page.
     */
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

    
    /**
     * onPressGo method is called when the GO button is clicked.
     * This method opens the lesson file.
     * Initializes the variables to track user input errors.
     * registers an event handler with the textInputArea to handle keyboard events.
     * Displays the first line in the lesson on the displayArea.
     */
    @FXML
    void onPressGo(ActionEvent event){
        
        try{
            BufferedReader reader = new BufferedReader( new FileReader( new File( "files\\" + lessonChoiceBox.getSelectionModel().getSelectedItem().toString() + ".txt" )));
            
            errorCountWithBackspace =0; errorCountWithoutBackspace=0; totalChar =0; indexOfLine=0; 
            displayArea.setText("");//clears the display area.
            textInputArea.setText(""); //clears the inputTextArea.
            textInputArea.requestFocus();//brings focus on the textInputArea.
            
            TutorialController.ALERT_AUDIOCLIP.setRate(2.0);
            TutorialController.TYPING_AUDIOCLIP.setVolume(1.0); 
            for(int i=0; i<26; i++){
                problemKeyArray[i]=0;
            }
            
            textInputArea.setOnKeyTyped( new EventHandler<KeyEvent>(){
                @Override
                public void handle(KeyEvent event) {
                    textInputArea.setId("normal");
                    expectedKey = line.charAt(indexOfLine);
                    typedKey = event.getCharacter().charAt(0);
                    
  //DEBUG STATEMENT System.out.println("expected : " + expectedKey);
                    
                    //check if timer started and start if it is not.
                    if(!timerStarted){
                        timerStarted = true;
                        timerButton.fire();
                    }
                    
                    //if the last letter of the shown line is typed, read the next line of the lesson
                    if(indexOfLine == line.length() - 1){
    //DEBUG STATEMENT   System.out.println("typed : " + typedKey);
    
                        //check the last character
                        if(typedKey != expectedKey){
                            
                            if(soundCheckBox.isSelected())
                                TutorialController.ALERT_AUDIOCLIP.play();
                            textInputArea.setId("warn");//warns the user by setting background red.
                            incrementProblemKeyCount(Character.toString(expectedKey));
                            errorCountWithBackspace++;
                            errorCountWithoutBackspace++;
    //DEBUG STATEMENT       System.out.println("false");
                        }
                        else{
                            if(soundCheckBox.isSelected())
                                TutorialController.TYPING_AUDIOCLIP.play();
                        }
                        try {
                            //if file is not completely read
                            if( (line = reader.readLine() ) != null){
                                displayArea.setText(line);
                                totalChar += line.length();
                                wordCount += countSpaces(line);
                                textInputArea.setText("");
                            }
                            //end of file reached
                            else{ 
                                timerButton.fire(); //timer is paused.
                                timeToComplete = ETLabel.getText(); // final time reading is taken and the end of the lesson.
                                //displayArea.setText(Integer.toString(totalChar));
                                //close the lesson file.
                                reader.close();
                                //lesson finished - switch the scene to the result page.
                                goToResultButton.fire();
                            }
                            //In any of the above cases, set the index of line to zero - for the next line or the next lesson.
                            indexOfLine = 0;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }  
                    }
                    //if not last character and if key pressed is not backspace.
                    else if(!(event.getCharacter().equals("\u0008"))){
//DEBUG STATEMENT       System.out.println("typed : " + typedKey);
                        if(typedKey != expectedKey){
                            
                            if(soundCheckBox.isSelected())
                                TutorialController.ALERT_AUDIOCLIP.play();
                            textInputArea.setId("warn");
                            incrementProblemKeyCount(Character.toString(expectedKey));

                            errorCountWithBackspace++;
                            errorCountWithoutBackspace++;
//DEBUG STATEMENT           System.out.println("false");
                        }
                        else{
                            if(soundCheckBox.isSelected())
                                TutorialController.TYPING_AUDIOCLIP.play();
                        }
//DEBUG STATEMENT       System.out.println("true");
                        indexOfLine++;
                    }
                    // if backspace pressed, decrements the indexOfLine and only the errorCountWithBackspace.
                    else if(event.getCharacter().equals("\u0008") && textInputArea.getText() != null){
                        indexOfLine--;
                        if(errorCountWithBackspace > 0)
                            errorCountWithBackspace--;
                    }
                    //call the showKeyPressed method to show key pressed in GUI Keyboard.
                    showKeyPressed( event.getCharacter() );
                }
            });
            
            //First line is displayed.
            if((line = reader.readLine()) != null){
                displayArea.setText(line);    
                totalChar += line.length();
                wordCount += countSpaces(line);
//DEBUG STATEMENT System.out.println(line);
            }
	} catch (IOException e) {
            e.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }    
    }
    
    //This method selects & shows the key that is pressed in the GUI Keyboard.
    public void showKeyPressed( String key ){
        if(Character.isLetter(key.charAt(0)))
            key = key.toLowerCase();
        switch(key){
               case "q": q.arm(); q.disarm(); break;
               case "w": w.arm(); w.disarm(); break;
               case "e": e.arm(); e.disarm(); break;
               case "r": r.arm(); r.disarm(); break;
               case "t": t.arm(); t.disarm(); break;
               case "y": y.arm(); y.disarm(); break;
               case "u": u.arm(); u.disarm(); break;
               case "i": i.arm(); i.disarm(); break;
               case "o": o.arm(); o.disarm(); break;
               case "p": p.arm(); p.disarm(); break;
               case "a": a.arm(); a.disarm(); break;
               case "s": s.arm(); s.disarm(); break;
               case "d": d.arm(); d.disarm(); break;
               case "f": f.arm(); f.disarm(); break;
               case "g": g.arm(); g.disarm(); break;
               case "h": h.arm(); h.disarm(); break;
               case "j": j.arm(); j.disarm(); break;
               case "k": k.arm(); k.disarm(); break;
               case "l": l.arm(); l.disarm(); break;
               case "z": z.arm(); z.disarm(); break;
               case "x": x.arm(); x.disarm(); break;
               case "c": c.arm(); c.disarm(); break;
               case "v": v.arm(); v.disarm(); break;
               case "b": b.arm(); b.disarm(); break;
               case "n": n.arm(); n.disarm(); break;
               case "m": m.arm(); m.disarm(); break;
               case "1":
               case "!": key1.arm(); key1.disarm(); break;
               case "2":
               case "@": key2.arm(); key2.disarm(); break;
               case "3":
               case "#": key3.arm(); key3.disarm(); break;
               case "4":
               case "$": key4.arm(); key4.disarm(); break;
               case "5":
               case "%": key5.arm(); key5.disarm(); break;
               case "6":
               case "^": key6.arm(); key6.disarm(); break;
               case "7":
               case "&": key7.arm(); key7.disarm(); break;
               case "8":
               case "*": key8.arm(); key8.disarm(); break;
               case "9":
               case "(": key9.arm(); key9.disarm(); break;
               case "0":
               case ")": key0.arm(); key0.disarm(); break;
               case "-":
               case "_": keyminus.arm(); keyminus.disarm(); break;
               case "+":
               case "=": keyplus.arm(); keyplus.disarm(); break;
               case "{":
               case "[": keyBoxBracketL.arm(); keyBoxBracketL.disarm(); break;
               case "}":
               case "]": keyBoxBracketR.arm(); keyBoxBracketR.disarm(); break;
               case "|":
               case "\\": keyBackslash.arm(); keyBackslash.disarm(); break;
               case ";":
               case ":": keySemicolon.arm(); keySemicolon.disarm(); break;
               case "\"":
               case "'": keyAphostrophe.arm(); keyAphostrophe.disarm(); break;
               case ",":
               case "<": keyComma.arm(); keyComma.disarm(); break;
               case ">":
               case ".": keyDot.arm(); keyDot.disarm(); break;
               case "?":
               case "/": keyForwardslash.arm(); keyForwardslash.disarm(); break;
               case "\u21E7": shiftl.arm();shiftr.arm();shiftl.disarm();shiftr.disarm();break;
               case " ": space.arm();space.disarm();break;
               case "\u0008": backspace.arm();backspace.disarm(); break;
        }
        
    }
    
    void incrementProblemKeyCount( String key ){
        if(Character.isLetter(key.charAt(0)))
            key = key.toLowerCase();
        switch(key){
               case "q": problemKeyArray[0]++; break;
               case "w": problemKeyArray[1]++; break;
               case "e": problemKeyArray[2]++; break;
               case "r": problemKeyArray[3]++; break;
               case "t": problemKeyArray[4]++; break;
               case "y": problemKeyArray[5]++; break;
               case "u": problemKeyArray[6]++; break;
               case "i": problemKeyArray[7]++; break;
               case "o": problemKeyArray[8]++; break;
               case "p": problemKeyArray[9]++; break;
               case "a": problemKeyArray[10]++; break;
               case "s": problemKeyArray[11]++; break;
               case "d": problemKeyArray[12]++; break;
               case "f": problemKeyArray[13]++; break;
               case "g": problemKeyArray[14]++; break;
               case "h": problemKeyArray[15]++; break;
               case "j": problemKeyArray[16]++; break;
               case "k": problemKeyArray[17]++; break;
               case "l": problemKeyArray[18]++; break;
               case "z": problemKeyArray[19]++; break;
               case "x": problemKeyArray[20]++; break;
               case "c": problemKeyArray[21]++; break;
               case "v": problemKeyArray[22]++; break;
               case "b": problemKeyArray[23]++; break;
               case "n": problemKeyArray[24]++; break;
               case "m": problemKeyArray[25]++; break;     
        }
    }
    String generateProblemKeyString(){
        String string = "-";
        int first, second, third;
        first = second = third = 0;
        for (int i = 0; i < 26 ; i ++)
        {
            /* If current element is smaller than first*/
            if (problemKeyArray[i] > first)
            {
                third = second;
                second = first;
                first = i;
            }
  
            /* If arr[i] is in between first and second then update second  */
            else if (problemKeyArray[i] > second)
            {
                third = second;
                second = i;
            }
  
            else if (problemKeyArray[i] > third)
                third = i;
        }
        if(problemKeyArray[first] != 0)
            string = problemCharArray[first] + " ";
        if(problemKeyArray[second] != 0)
            string += problemCharArray[second] + " ";
        if(problemKeyArray[third] != 0)
            string += problemCharArray[third] + "";
        
        string = string.toUpperCase();
        return string;
    }
    
    
    int countSpaces(String myLine){
        int counter = 0;
        for( int i=0; i<myLine.length(); i++ ) {
            if( myLine.charAt(i) == ' ' ) {
                counter++;
            }        
        }
        return counter;
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
		ETLabel.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
		 + (((secs/10) == 0) ? "0" : "") + secs);
                millis++;
    }
    
    @FXML
    void switchSceneToResultPage(ActionEvent event){
        try{ 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LessonResult.fxml"));
            Parent root = loader.load();
        
            Scene scene = new Scene(root);
            LessonResultController controller = loader.getController();
        
            String problemKeyString = generateProblemKeyString();
            int currentLessonChoice = lessonChoiceBox.getSelectionModel().getSelectedIndex();
            
            controller.initializeMyData( totalChar, errorCountWithBackspace, errorCountWithoutBackspace, timeToComplete, wordCount, problemKeyString, currentLessonChoice );
        
            Stage theStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    
    }
    
    //loads the same or next lesson.
    void initializeLessonChoiceAndBegin( int choice ){
        if(choice <= 16 && 0 <= choice)
            lessonChoiceBox.getSelectionModel().select(choice);
        else if(choice == 17)
            lessonChoiceBox.getSelectionModel().select(16);
        goButton.fire();
    }

    //Runs when the scene is loaded.
    public void initialize(URL url, ResourceBundle rb) {

        //Initializes the choice box with lesson options.
        lessonChoiceBox.getItems().addAll( "Demo", "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4","Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Common Words");
        lessonChoiceBox.getSelectionModel().select("Demo");
        
        //Initially Elapsed time is set to 0.
        ETLabel.setText("00:00");
        
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
        
    }//Initialize method ends.   
    
}//Controller class ends.
