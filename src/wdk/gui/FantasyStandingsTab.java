/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wdk.controller.TeamEditController;
import wdk.data.DraftDataManager;
import wdk.data.Players;
import wdk.data.Team;

/**
 *
 * @author halaamenasy
 */
public class FantasyStandingsTab {
    
    VBox fantasyStandingsMainPane; //add main pane to scrollable pane, this will have controll
    TableView <Players> fantasyStandingsTable;
    Label fantasyStandingsLabel;
    Label startingLineUpLabel;
    DraftDataManager dataManager;
    
    TeamEditController teamController;
    Stage primaryStage;
    
            // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    WDK_GUI gui;
    

    
    static final String COL_TEAM_NAME = "Team Name"; //position on team
    static final String COL_PLAYERS_NEEDED = "Players Needed";
    static final String COL_MONEY_LEFT = "$ Left";
    static final String COL_PP = "$PP";
    static final String COL_R = "R";
    static final String COL_HR = "HR";
    static final String COL_RBI = "RBI";
    static final String COL_SB = "SB";
    static final String COL_BA = "BA";
    static final String COL_W = "W";
    static final String COL_SV = "SV";
    static final String COL_K= "K";
    static final String COL_ERA="ERA";
    static final String COL_WHIP = "WHIP";
    static final String COL_TOTAL_POINTS = "Total Points";
    
    TableColumn<Players, String> teamNameCol;
    TableColumn playersNeededCol;
    TableColumn<Players,Number> moneyLeftCol;
    TableColumn <Players, Number>ppCol;
    TableColumn <Players, Number> RCol;
    TableColumn <Players, Number> HRCol;
    TableColumn <Players, Number>RBICol;
    TableColumn <Players, Number>SBCol;
    TableColumn<Players, Number> BACol;
    TableColumn<Players, Number> WCol;
    TableColumn<Players, Number> SVCol;
    TableColumn<Players, Number> KCol;
    TableColumn<Players, Number> ERACol;
    TableColumn<Players, Number> WHIPCol;
    TableColumn<Players, Number> TotalCol;
    
     public FantasyStandingsTab(Tab tab,WDK_GUI gui){
        fantasyStandingsTable=new TableView();
        teamNameCol= new TableColumn(COL_TEAM_NAME );
        playersNeededCol= new TableColumn(COL_PLAYERS_NEEDED);
        moneyLeftCol = new TableColumn(COL_MONEY_LEFT);
        ppCol=new TableColumn(COL_PP);
        RCol=new TableColumn(COL_R );
        HRCol=new TableColumn(COL_HR);
        RBICol=new TableColumn(COL_RBI);
        SBCol=new TableColumn(COL_SB);
        BACol=new TableColumn(COL_BA);
        WCol=new TableColumn(COL_W);
        SVCol=new TableColumn(COL_SV);
        KCol=new TableColumn(COL_K);
        ERACol= new TableColumn(COL_ERA);
        WHIPCol=new TableColumn(COL_WHIP);
        TotalCol= new TableColumn(COL_TOTAL_POINTS);
        
        fantasyStandingsTable.getColumns().add(teamNameCol);
        fantasyStandingsTable.getColumns().add(playersNeededCol);
        fantasyStandingsTable.getColumns().add(moneyLeftCol);
        fantasyStandingsTable.getColumns().add(ppCol);
        fantasyStandingsTable.getColumns().add(RCol);
        fantasyStandingsTable.getColumns().add(HRCol);
        fantasyStandingsTable.getColumns().add(RBICol);
        fantasyStandingsTable.getColumns().add(SBCol);
        fantasyStandingsTable.getColumns().add(BACol);
        fantasyStandingsTable.getColumns().add(WCol);
        fantasyStandingsTable.getColumns().add(SVCol);
        fantasyStandingsTable.getColumns().add(KCol);
        fantasyStandingsTable.getColumns().add(ERACol);
        fantasyStandingsTable.getColumns().add(WHIPCol);
        fantasyStandingsTable.getColumns().add(TotalCol);
        
        fantasyStandingsLabel=new Label("Fantasy Standings Estimates");
        fantasyStandingsLabel.setStyle("-fx-font-size:40px;-fx-text-fill:#FF0000;");  
        
        fantasyStandingsMainPane= new VBox();
        fantasyStandingsMainPane.setStyle("-fx-background-color:#FFC0CB");
        fantasyStandingsMainPane.getChildren().add(fantasyStandingsLabel);
        fantasyStandingsMainPane.getChildren().add(fantasyStandingsTable);
        
        
        tab.setContent(fantasyStandingsMainPane);
     
}
}

