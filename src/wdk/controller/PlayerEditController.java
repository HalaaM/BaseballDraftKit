/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wdk.WDK_PropertyType.REMOVE_ITEM_MESSAGE;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.Players;
import wdk.data.Team;
import wdk.gui.AddNewPlayerDialog;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author halaamenasy
 */
public class PlayerEditController {
        AddNewPlayerDialog ad;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    /**
     *
     * @param initPrimaryStage
     * @param player
     * @param initMessageDialog
     * @param initYesNoCancelDialog
     */
    public PlayerEditController(Stage initPrimaryStage, Tab player, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        ad = new AddNewPlayerDialog(initPrimaryStage, player, initMessageDialog);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    // THESE ARE FOR ASSIGNMENT ITEMS
    
    public void handleAddPlayerRequest(WDK_GUI gui) {
        DraftDataManager cdm = gui.getDataManager();
        ObservableList <Players> players  = cdm.getPlayers();
        
        ad.showAddPlayerDialog();
        
//        // DID THE USER CONFIRM?
        if (ad.wasCompleteSelected()) {
//            // GET THE SCHEDULE ITEM
//            Team a = ad.getTeam();
//              
//            
//            // AND ADD IT AS A ROW TO THE TABLE
//            course.addAssignment(a);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
//            // WE DO NOTHING
        }
    }
    
    public void handleEditPlayerRequest(WDK_GUI gui, Team teamToEdit) {
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
    
    public void handleRemovePlayerRequest(WDK_GUI gui, Players playerToRemove) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) { 
            gui.getDataManager().getDraft().removePlayer(playerToRemove);
        }
    }
    
}
