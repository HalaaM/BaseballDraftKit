/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.data.MLBTeams;
import wdk.data.Players;
import static wdk.gui.WDK_GUI.CLASS_HEADING_LABEL;
import static wdk.gui.WDK_GUI.PRIMARY_STYLE_SHEET;

/**
 *
 * @author halaamenasy
 */
public class AddNewPlayerDialog extends Stage{
        // THIS IS THE OBJECT DATA BEHIND THIS UI
    Players player;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label firstNameLabel;
    TextField firstNameTextField;
    Label lastNameLabel;
    TextField lastNameTextField;
    Label proTeamLabel;
    ComboBox<String> mlbTeamsComboBox;
    
    Button completeButton;
    Button cancelButton;
    
    CheckBox C;
    CheckBox One_B;
    CheckBox three_B;
    CheckBox two_B;
    CheckBox SS;
    CheckBox OF;
    CheckBox P;
    
    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    
    public static final String ADD_NEW_PLAYER_TITLE = "Add New Player";
    public static final String PLAYER_HEADING = "Player Details";
    public static final String FIRST_NAME_PROMPT = "First Name: ";
    public static final String LAST_NAME_PROMPT = "Last Name: ";
    public static final String PRO_TEAM_PROMPT= "Pro Team: ";
    public static final String CLASS_PROMPT_LABEL = "prompt_label";
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    
   
    /**
     * Initializes this dialog so that it can be used for either adding
     * new assignments or editing existing ones.
     * 
     * @param primaryStage The owner of this modal dialog.
     */
    public AddNewPlayerDialog(Stage primaryStage, Players player,  MessageDialog messageDialog) {       
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20,20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
    
        // NOW THE NAME
        firstNameLabel = new Label(FIRST_NAME_PROMPT);
        firstNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        firstNameTextField = new TextField();
        
        
         // AND THE TOPIC
        lastNameLabel = new Label(LAST_NAME_PROMPT);
        lastNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        lastNameTextField = new TextField();
       

           ObservableList<String> teamChoices = FXCollections.observableArrayList();
        for (MLBTeams s : MLBTeams.values()) {
            teamChoices.add(s.toString());
        }
        
        mlbTeamsComboBox= new ComboBox();
        mlbTeamsComboBox.setItems(teamChoices);
        
        C= new CheckBox("C");
        One_B= new CheckBox("1_B");
        three_B= new CheckBox("3_B");
        two_B= new CheckBox("2_B");
        SS= new CheckBox("SS");
        OF= new CheckBox("OF");
        P= new CheckBox("P");
        
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            completePlayer();
            AddNewPlayerDialog.this.selection = sourceButton.getText();
            AddNewPlayerDialog.this.hide();
            
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);

        proTeamLabel= new Label(PRO_TEAM_PROMPT);
        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(firstNameLabel, 0, 1, 1, 1);
        gridPane.add(firstNameTextField, 1, 1, 1, 1);
        gridPane.add(lastNameLabel, 0, 2, 1, 1);
        gridPane.add(lastNameTextField, 1, 2, 1, 1);
        gridPane.add(proTeamLabel, 0, 3, 1, 1);
        gridPane.add(mlbTeamsComboBox, 1, 3, 1, 1);
        gridPane.add(C,0,4,1,1);
        gridPane.add(One_B,1,4,1,1);
        gridPane.add(three_B, 2,4,1,1);
        gridPane.add(two_B, 3,4,1,1);
        gridPane.add(SS, 4,4,1,1);
        gridPane.add(OF, 5, 4,1,1);
        gridPane.add(P,6,4,1,1);
        gridPane.add(completeButton, 0, 5, 1, 1);
        gridPane.add(cancelButton, 1, 5, 1, 1);
        
        // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
    }
    
    /**
     * Accessor method for getting the selection the user made.
     * 
     * @return Either YES, NO, or CANCEL, depending on which
     * button the user selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }
    
    public Players getPlayer() { 
        return player;
    }
    
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     * 
     * @param message Message to appear inside the dialog.
     */
    public Players showAddPlayerDialog() {
        // SET THE DIALOG TITLE
        setTitle(ADD_NEW_PLAYER_TITLE);
        
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        player = new Players();
//        
        
//        // LOAD THE UI STUFF
//        firstNameTextField.setText("enter first name");
//        lastNameTextField.setText("enter last name");
//        datePicker.setValue(initDate);
//        
        // AND OPEN IT UP
        this.showAndWait();
        
        return player;
    }
    
    public void loadGUIData() {
        // LOAD THE UI STUFF
//        nameTextField.setText(assignment.getName());
//        topicsTextField.setText(assignment.getTopics());  
//        datePicker.setValue(assignment.getDate());
            
    }
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    //if the complete button was pressed. used to initialize all the data
    public void completePlayer(){
      
    player.setFirstName(firstNameTextField.getText());
    player.setLastName(lastNameTextField.getText());
    player.setTeam(mlbTeamsComboBox.getValue());
             
       
        if (C.isSelected()){
            player.setPositions(player.getPositions()+"C");
        }
      
         if (One_B.isSelected()){
            player.setPositions(player.getPositions()+"1_B");
        }
        
         if (three_B.isSelected()){
            player.setPositions(player.getPositions()+"3_B");
        }
        
         if (two_B.isSelected()){
            player.setPositions(player.getPositions()+"2_B");
        }
        
         if (SS.isSelected()){
            player.setPositions(player.getPositions()+"SS");
        }
    
         if (OF.isSelected()){
            player.setPositions(player.getPositions()+"OF");
        }
     
         if (P.isSelected()){
            player.setPositions("P");
            
        }
    }
}
