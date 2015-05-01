/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wdk.data.Team;
import static wdk.gui.WDK_GUI.CLASS_HEADING_LABEL;
import static wdk.gui.WDK_GUI.PRIMARY_STYLE_SHEET;

/**
 *
 * @author halaamenasy
 */
public class EditTeamDialog extends Stage {
     Team team;
  
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    
    //Labels
    Label nameLabel;
    Label ownerLabel;
    
    //Textfield
    TextField nameTextField;
    TextField ownerTextField;
    
    //Buttons
    Button completeButton;
    Button cancelButton;
    
     // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    
    // CONSTANTS FOR OUR UI
    public static final String ADD_FANTASY_TEAM_TITLE = "Add New Fantasy Team";
    public static final String TEAM_HEADING = "Fantasy Team Details";
    public static final String NAME_PROMPT = "Name: ";
    public static final String OWNER_PROMPT = "Owner: ";
    public static final String CLASS_PROMPT_LABEL = "prompt_label";
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    
    public EditTeamDialog(Stage primaryStage, Team team, MessageDialog messageDialog){
          // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(TEAM_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
    
         // NOW THE NAME
        nameLabel = new Label(NAME_PROMPT);
        nameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        nameTextField = new TextField();
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            //.setName(newValue);
        });
        
         // AND THE TOPIC
        ownerLabel = new Label(OWNER_PROMPT);
        ownerLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        ownerTextField = new TextField();
        ownerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
         //   assignment.setTopics(newValue);
        });
        
          completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            EditTeamDialog.this.selection = sourceButton.getText();
          //  AddNewFantasyTeamDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
        

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(nameLabel, 0, 1, 1, 1);
        gridPane.add(nameTextField, 1, 1, 1, 1);
        gridPane.add(ownerLabel, 0, 2, 1, 1);
        gridPane.add(ownerTextField, 1, 2, 1, 1);
        gridPane.add(completeButton, 0, 3,1,1);
        gridPane.add(cancelButton, 1,3,1,1);
        
          // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
        
    }
    
    //corner infielder= 1st and 3rd basemen
    //middle infielder=2nd basema, IF, or short stop
    //Utility=all hitters
    
    
    public Team showAddTeamDialog() {
        // SET THE DIALOG TITLE
        setTitle( ADD_FANTASY_TEAM_TITLE);
        
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        team = new Team();
        
        // LOAD THE UI STUFF
        nameTextField.setText(team.getTeamName());
        ownerTextField.setText(team.getTeamOwnerName());
       
        
        // AND OPEN IT UP
        this.showAndWait();
        
        return team;
    }
  public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    public Team getTeam() { 
        return team;
    }
    
}
