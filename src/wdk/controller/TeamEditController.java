/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wdk.WDK_PropertyType.REMOVE_ITEM_MESSAGE;
import wdk.data.Draft;
import wdk.data.Team;
import wdk.data.DraftDataManager;
import wdk.gui.WDK_GUI;
import wdk.gui.MessageDialog;
import wdk.gui.AddNewFantasyTeamDialog;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author halaamenasy
 */
public class TeamEditController {
    AddNewFantasyTeamDialog ad;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    public TeamEditController(Stage initPrimaryStage, Team team, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        ad = new AddNewFantasyTeamDialog(initPrimaryStage, team, initMessageDialog);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    // THESE ARE FOR ASSIGNMENT ITEMS
    
    public void handleAddTeamRequest(WDK_GUI gui) {
        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();
        ad.showAddTeamDialog();
//        
//        // DID THE USER CONFIRM?
//        if (ad.wasCompleteSelected()) {
//            // GET THE SCHEDULE ITEM
//            Team a = ad.getTeam();
//              
//            
//            // AND ADD IT AS A ROW TO THE TABLE
//            course.addAssignment(a);
        }
//        else {
//            // THE USER MUST HAVE PRESSED CANCEL, SO
//            // WE DO NOTHING
//        }

    
    public void handleEditTeamRequest(WDK_GUI gui, Team teamToEdit) {
//        DraftDataManager cdm = gui.getDataManager();
//        Draft course = cdm.getDraft();
//        ad.showEditTeamDialog(teamToEdit);
//        
//        // DID THE USER CONFIRM?
//        if (ad.wasCompleteSelected()) {
//            // UPDATE THE SCHEDULE ITEM
//            Assignment a = ad.getAssignment();
//            assignmentToEdit.setName(a.getName());
//            assignmentToEdit.setDate(a.getDate());
//            assignmentToEdit.setTopics(a.getTopics());
//        }
//        else {
//            // THE USER MUST HAVE PRESSED CANCEL, SO
//            // WE DO NOTHING
//        }        
    }
    
    public void handleRemoveTeamRequest(WDK_GUI gui, Team teamToRemove) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) { 
            gui.getDataManager().getDraft().removeTeam(teamToRemove);
        }
    }
    
}
