/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import java.util.Random;
import javafx.collections.ObservableList;
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
public class AutomatedDraftController {
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
    public AutomatedDraftController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        
        primaryStage=initPrimaryStage;
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    // THESE ARE FOR ASSIGNMENT ITEMS
    
    public void handleSelectPlayerRequest(WDK_GUI gui, ObservableList <Team> team) throws Exception {
  
        Players player;
        DraftDataManager cdm = gui.getDataManager();
        ObservableList <Players> players = cdm.getPlayers();
        player=selectRandomPlayer(players);
        int numberOfTeams=team.size();
        team.get(0).addPlayer(player);
        
 
    }
    
    public void handleStartAutomatedDraftRequest(WDK_GUI gui) {
        
        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();
 
    }
    
    public void handlePauseAutomatedRequest(WDK_GUI gui) {

    }
     public Players selectRandomPlayer(ObservableList<Players> list){
        Random rand = new Random();
        int randomNum = rand.nextInt((647 - 1) + 1) + 1;
        return list.get(randomNum);     
    }
    
}
