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
import wdk.gui.EditPlayerDialog;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author halaamenasy
 */
public class PlayerEditController {
    AddNewPlayerDialog ad;
    EditPlayerDialog ed;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    Stage primaryStage;
    Draft draft;
    
    /**
     *
     * @param initPrimaryStage
     * @param player
     * @param initMessageDialog
     * @param initYesNoCancelDialog
     */
    public PlayerEditController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        
        primaryStage=initPrimaryStage;
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    // THESE ARE FOR ASSIGNMENT ITEMS
    
    public void handleAddPlayerRequest(WDK_GUI gui) {
        Players player= new Players();
        DraftDataManager cdm = gui.getDataManager();
        ObservableList <Players> players = cdm.getPlayers();
        ad = new AddNewPlayerDialog(primaryStage, player,messageDialog);
        
        ad.showAddPlayerDialog();
        
//        // DID THE USER CONFIRM?
        if (ad.wasCompleteSelected()) {
            //get the player
          Players playerToAdd=ad.getPlayer();
//            
//            // AND ADD IT AS A ROW TO THE TABLE
           draft.addPlayer(playerToAdd);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
//            // WE DO NOTHING
        }
    }
    
    public void handleEditPlayerRequest(WDK_GUI gui, Players player) {
        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();
        ed= new EditPlayerDialog(primaryStage, player,messageDialog);
       ed.showEditPlayerDialog(player);
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
