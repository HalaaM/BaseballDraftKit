/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static wdk.WDK_StartupConstants.PATH_IMAGES;
import wdk.data.Contract;
import wdk.data.DraftDataManager;
import wdk.data.MLBTeams;
import wdk.data.Players;
import wdk.data.Positions;
import static wdk.gui.WDK_GUI.CLASS_HEADING_LABEL;
import static wdk.gui.WDK_GUI.PRIMARY_STYLE_SHEET;

/**
 *
 * @author halaamenasy
 */
public class EditPlayerDialog extends Stage{
     Players baseballplayer;
     DraftDataManager ddm;
    
     // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label fantasyTeamLabel;
    ComboBox fantasyTeamComboBox;
    Label positionLabel;
    ComboBox positionComboBox;
    Label contractLabel;
    ComboBox contractComboBox;
    Label salaryLabel;
    TextField salaryTextField;
    
    Label playerNameLabel;
    Label positionsEligibleLabel;
    ImageView playerImage;
    ImageView playerFlag;
    
    Button completeButton;
    Button cancelButton;
    VBox playerPane;
    
    Label position;
    Label nameOfPlayer;
    // CONSTANTS FOR OUR UI
    public static final String EDIT_PLAYER_TITLE = "Edit Player";
    public static final String EDIT_PLAYER_HEADING = "Player Details";
    public static final String FANTASY_TEAM_PROMPT = "Fantasy Team: ";
    public static final String POSITION_PROMPT = "Position: ";
    public static final String CONTRACT_PROMPT = "Contract: ";
    public static final String SALARY_PROMPT = "Salary($): ";
    public static final String CLASS_PROMPT_LABEL = "prompt_label";
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    
     public EditPlayerDialog(Stage primaryStage, Players player,  MessageDialog messageDialog) {       
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20,20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        
//        //For the picutre
        String playerName= player.getLastName()+player.getFirstName()+".jpg";
        nameOfPlayer= new Label(player.getFirstName()+" "+player.getLastName());
        position= new Label(player.getPositions());
        
        String flagName= player.getNationality()+".png";
        String imagePath1= "file:" + PATH_IMAGES+"flags/"+flagName;
        Image flagPic= new Image(imagePath1);
        
        
        String imagePath = "file:" + PATH_IMAGES +"players/"+playerName;
        Image playerPic= new Image(imagePath);
        
        if (playerPic==null){
            playerPic=  new Image("file:" + PATH_IMAGES +"players/"+"AAA_PhotoMissing.jpg");        
        }
        
        ImageView playerPicture = new ImageView(playerPic);
        ImageView flagPicture= new ImageView(flagPic);
        // for the player label
      //  nameOfPlayer= new Label(playerName);
//        position= new Label(baseballplayer.getPositions());
        
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(EDIT_PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
    
        // NOW THE FANTASY TEAM
        fantasyTeamLabel = new Label(FANTASY_TEAM_PROMPT);
        fantasyTeamLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        fantasyTeamComboBox = new ComboBox();
//        fantasyTeamComboBox.addListener((observable, oldValue, newValue) -> {
//       //     player.setFirstName(newValue);
//        });
//        
         // AND THE POSITIONS
        positionLabel = new Label(POSITION_PROMPT);
        positionLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
       
        
        ObservableList<String> positionChoices = FXCollections.observableArrayList();
        for (Positions s : Positions.values()) {
            positionChoices.add(s.toString());
        }
        
        positionComboBox = new ComboBox();
        positionComboBox.setItems(positionChoices);
       

        //AND THE CONTRACTS
        contractLabel = new Label(CONTRACT_PROMPT);
        positionLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        
         ObservableList<String> contractChoices = FXCollections.observableArrayList();
        for (Contract s : Contract.values()) {
            contractChoices.add(s.toString());
        }
        contractComboBox = new ComboBox(contractChoices);
        
        //AND THE SALARY
        salaryLabel= new Label (SALARY_PROMPT);
        salaryLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        salaryTextField= new TextField();
        
     
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
         //   EditPlayerDialog.this.selection = sourceButton.getText();
            EditPlayerDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
        
        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 3, 1);
        gridPane.add(playerPicture,0,1,1,3);
        gridPane.add(flagPicture, 1,1,2,1);
        gridPane.add(nameOfPlayer,1,2,3,1);
        gridPane.add(position, 1,3,1,1);
        
        gridPane.add(fantasyTeamLabel, 0,4,1,1);
        gridPane.add(fantasyTeamComboBox, 1,4,3,1);
        gridPane.add(positionLabel, 0,5,1,1);
        gridPane.add(positionComboBox,1,5,3,1);
        gridPane.add(contractLabel,0,6,1,1);
        gridPane.add(contractComboBox,1,6,3,1);
        gridPane.add(salaryLabel, 0, 7,1,1);
        gridPane.add(salaryTextField,1,7,3,1);
        gridPane.add(completeButton, 1,8,1,1);
        gridPane.add(cancelButton, 2,8,1,1);
        
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
    
    
      public void showEditPlayerDialog(Players player) {
        // SET THE DIALOG TITLE
        setTitle(EDIT_PLAYER_TITLE);
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
         baseballplayer= new Players();
        
//        
        
//        // LOAD THE UI STUFF
   
//        
        // AND OPEN IT UP
        this.showAndWait();
   
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
    
    
}
