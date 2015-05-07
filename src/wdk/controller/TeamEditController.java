/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wdk.WDK_PropertyType.REMOVE_ITEM_MESSAGE;
import wdk.data.Draft;
import wdk.data.Team;
import wdk.data.DraftDataManager;
import wdk.gui.WDK_GUI;
import wdk.gui.MessageDialog;
import wdk.gui.AddNewFantasyTeamDialog;
import wdk.gui.EditTeamDialog;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author halaamenasy
 */
public class TeamEditController {
    AddNewFantasyTeamDialog ad;
    EditTeamDialog ed;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    Stage primaryStage;
    Draft draft;
    
    public TeamEditController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
      
        primaryStage=initPrimaryStage;
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    // THESE ARE FOR ASSIGNMENT ITEMS
    
    public void handleAddTeamRequest(WDK_GUI gui) {
        Team team= new Team();
        DraftDataManager cdm = gui.getDataManager();
        ObservableList <Team> teamList = cdm.getDraft().getTeam();
        ad = new AddNewFantasyTeamDialog(primaryStage,team,messageDialog);
        
        ad.showAddTeamDialog();
        
//        // DID THE USER CONFIRM?
        if (ad.wasCompleteSelected()) {
            //get the player
          Team teamToAdd=ad.getTeam();
//            
//            // AND ADD IT AS A ROW TO THE TABLE
          draft=cdm.getDraft();
      
          teamList.add(teamToAdd);
          gui.fantasyTab.fantasyTeamsComboBox.getItems().add(teamToAdd);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
//            // WE DO NOTHING
        }
    }
    public void handleEditTeamRequest(WDK_GUI gui, Team teamToEdit) {
        DraftDataManager cdm = gui.getDataManager();
        Draft course = cdm.getDraft();
        ed = new EditTeamDialog(primaryStage,teamToEdit,messageDialog);
        ed.showEditTeamDialog(teamToEdit);
   
//        // DID THE USER CONFIRM?
        if (ad.wasCompleteSelected()) {
            gui.fantasyTab.fantasyTeamsComboBox.getItems().remove(teamToEdit);
            teamToEdit=ed.getTeam();   
            gui.fantasyTab.fantasyTeamsComboBox.getItems().add(teamToEdit);
        }            
//        }
//        else {
//            // THE USER MUST HAVE PRESSED CANCEL, SO
//            // WE DO NOTHING
        }        
    
    
    public void handleRemoveTeamRequest(WDK_GUI gui, Team teamToRemove) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) { 
             gui.getDataManager().getDraft().getTeam().remove(teamToRemove);
             gui.fantasyTab.fantasyTeamsComboBox.getItems().remove(teamToRemove);
        }
    }
    
}
