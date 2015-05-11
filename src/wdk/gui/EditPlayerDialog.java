/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import wdk.data.Team;
import static wdk.gui.WDK_GUI.CLASS_HEADING_LABEL;
import static wdk.gui.WDK_GUI.PRIMARY_STYLE_SHEET;

/**
 *
 * @author halaamenasy
 */
public class EditPlayerDialog extends Stage {

    Players baseballplayer;
    DraftDataManager ddm;
  

    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label fantasyTeamLabel;
    ComboBox<Team> fantasyTeamComboBox;
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

    public EditPlayerDialog(Stage primaryStage, Players player, MessageDialog messageDialog) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        ddm = WDK_GUI.getGUI().getDataManager();
      
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

//        //For the picutre
        String playerName = player.getLastName() + player.getFirstName() + ".jpg";
        nameOfPlayer = new Label(player.getFirstName() + " " + player.getLastName());
        position = new Label(player.getPositionsEligible());

        String flagName = player.getNationality() + ".png";
        String imagePath1 = "file:" + PATH_IMAGES + "flags/" + flagName;
        Image flagPic = new Image(imagePath1);

        String imagePath = "file:" + PATH_IMAGES + "players/" + playerName;
        Image playerPic = new Image(imagePath);

        File f = new File(imagePath);
        if (playerPic.getWidth() == 0) {
            playerPic = new Image("file:" + PATH_IMAGES + "players/" + "AAA_PhotoMissing.jpg");
        }

        ImageView playerPicture = new ImageView(playerPic);
        ImageView flagPicture = new ImageView(flagPic);


        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(EDIT_PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);

        // NOW THE FANTASY TEAM
        fantasyTeamLabel = new Label(FANTASY_TEAM_PROMPT);
        fantasyTeamLabel.getStyleClass().add(CLASS_PROMPT_LABEL);

        fantasyTeamComboBox = new ComboBox();
        ObservableList<Team> teamChoices = FXCollections.observableArrayList();

        teamChoices.addAll(WDK_GUI.getGUI().getDataManager().getDraft().getTeam());

        fantasyTeamComboBox.setItems(teamChoices);

        fantasyTeamComboBox.getItems().add(new Team("Free Agent", ""));
        
        

        // AND THE POSITIONS
        positionLabel = new Label(POSITION_PROMPT);
        positionLabel.getStyleClass().add(CLASS_PROMPT_LABEL);

        ObservableList<String> positionChoices = FXCollections.observableArrayList();
        positionChoices.addAll(player.getPositionsEligible().split("_"));

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
        salaryLabel = new Label(SALARY_PROMPT);
        salaryLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        salaryTextField = new TextField();

        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);

        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            try {
                Button sourceButton = (Button) ae.getSource();
                if (sourceButton.getText().equals(CANCEL)) {
                    EditPlayerDialog.this.hide();
                }
                else{completeTeam();
                EditPlayerDialog.this.hide();
                }
            } catch (NumberFormatException e) {

            }

        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 3, 1);
        gridPane.add(playerPicture, 0, 1, 1, 3);
        gridPane.add(flagPicture, 1, 1, 2, 1);
        gridPane.add(nameOfPlayer, 1, 2, 3, 1);
        gridPane.add(position, 1, 3, 1, 1);

        gridPane.add(fantasyTeamLabel, 0, 4, 1, 1);
        gridPane.add(fantasyTeamComboBox, 1, 4, 3, 1);
        gridPane.add(positionLabel, 0, 5, 1, 1);
        gridPane.add(positionComboBox, 1, 5, 3, 1);
        gridPane.add(contractLabel, 0, 6, 1, 1);
        gridPane.add(contractComboBox, 1, 6, 3, 1);
        gridPane.add(salaryLabel, 0, 7, 1, 1);
        gridPane.add(salaryTextField, 1, 7, 3, 1);
        gridPane.add(completeButton, 1, 8, 1, 1);
        gridPane.add(cancelButton, 2, 8, 1, 1);

        // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
    }

    /**
     * Accessor method for getting the selection the user made.
     *
     * @return Either YES, NO, or CANCEL, depending on which button the user
     * selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }

    public void showEditPlayerDialog(Players player) {
        // SET THE DIALOG TITLE
        setTitle(EDIT_PLAYER_TITLE);
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        baseballplayer = player;

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

    public void completeTeam() throws NumberFormatException {

        if (!baseballplayer.getFantasyTeam().equals("")) {
            ddm.getDraft().findTeam(baseballplayer.getFantasyTeam()).removePlayer(baseballplayer);
            
        }
        if (fantasyTeamComboBox.getValue().toString().equalsIgnoreCase("Free Agent")) {
            baseballplayer.setFantasyTeam("");
            baseballplayer.setContract("");
            baseballplayer.setSalary(0);
            ddm.getPlayers().add(baseballplayer);
            WDK_GUI.getGUI().draftSummaryTab.getDraftList().remove(baseballplayer);
            
        } else {
            baseballplayer.setSalary(Integer.parseInt(salaryTextField.getText()));
            baseballplayer.setFantasyTeam(fantasyTeamComboBox.getValue().toString());
            baseballplayer.setPositionOnTeam(positionComboBox.getValue().toString());
            baseballplayer.setContract(contractComboBox.getValue().toString());

            try {
                ddm.getDraft().findTeam(fantasyTeamComboBox.getValue().toString()).addPlayer(baseballplayer);
                
                //*******************************************************************
               
                if(baseballplayer.getContract().equalsIgnoreCase("S2")){
                WDK_GUI.getGUI().draftSummaryTab.getDraftList().add(baseballplayer);
                }
                 // **********************************************************************
                Collections.sort(fantasyTeamComboBox.getValue().getPlayers(), new Comparator<Players>() {
                    @Override
                    public int compare(Players p1, Players p2) {
                        int a;
                        int b;

                        if (p1.getPositionOnTeam().equals("C")) {
                            a = 10;
                        } else if (p1.getPositionOnTeam().equals("1B")) {
                            a = 9;
                        } else if (p1.getPositionOnTeam().equals("CI")) {
                            a = 8;
                        } else if (p1.getPositionOnTeam().equals("3B")) {
                            a = 7;
                        } else if (p1.getPositionOnTeam().equals("2B")) {
                            a = 6;
                        } else if (p1.getPositionOnTeam().equals("MI")) {
                            a = 5;
                        } else if (p1.getPositionOnTeam().equals("SS")) {
                            a = 4;
                        } else if (p1.getPositionOnTeam().equals("OF")) {
                            a = 3;
                        } else if (p1.getPositionOnTeam().equals("U")) {
                            a = 2;
                        } else {
                            a = 1;
                        }

                        if (p2.getPositionOnTeam().equals("C")) {
                            b = 10;
                        } else if (p2.getPositionOnTeam().equals("1B")) {
                            b = 9;
                        } else if (p2.getPositionOnTeam().equals("CI")) {
                            b = 8;
                        } else if (p2.getPositionOnTeam().equals("3B")) {
                            b = 7;
                        } else if (p2.getPositionOnTeam().equals("2B")) {
                            b = 6;
                        } else if (p2.getPositionOnTeam().equals("MI")) {
                            b = 5;
                        } else if (p2.getPositionOnTeam().equals("SS")) {
                            b = 4;
                        } else if (p2.getPositionOnTeam().equals("OF")) {
                            b = 3;
                        } else if (p2.getPositionOnTeam().equals("U")) {
                            b = 2;
                        } else {
                            b = 1;
                        }
                        return (a > b) ? -1 : 1;
                    }

                });
                ddm.getPlayers().remove(baseballplayer);
            } catch (Exception E) {

            }

        }

    }

}
