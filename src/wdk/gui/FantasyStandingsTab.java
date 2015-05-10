/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    TableView<Team> fantasyStandingsTable;
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
    static final String COL_K = "K";
    static final String COL_ERA = "ERA";
    static final String COL_WHIP = "WHIP";
    static final String COL_TOTAL_POINTS = "Total Points";

    TableColumn<Team, String> teamNameCol;
    TableColumn playersNeededCol;
    TableColumn<Team, Number> moneyLeftCol;
    TableColumn<Team, Number> ppCol;
    TableColumn<Team, Number> RCol;
    TableColumn<Team, Number> HRCol;
    TableColumn<Team, Number> RBICol;
    TableColumn<Team, Number> SBCol;
    TableColumn<Team, Number> BACol;
    TableColumn<Team, Number> WCol;
    TableColumn<Team, Number> SVCol;
    TableColumn<Team, Number> KCol;
    TableColumn<Team, Number> ERACol;
    TableColumn<Team, Number> WHIPCol;
    TableColumn<Team, Number> TotalCol;

    public FantasyStandingsTab(Tab tab, WDK_GUI gui) {
        fantasyStandingsTable = new TableView();
        teamNameCol = new TableColumn(COL_TEAM_NAME);
        playersNeededCol = new TableColumn(COL_PLAYERS_NEEDED);
        moneyLeftCol = new TableColumn(COL_MONEY_LEFT);
        ppCol = new TableColumn(COL_PP);
        RCol = new TableColumn(COL_R);
        HRCol = new TableColumn(COL_HR);
        RBICol = new TableColumn(COL_RBI);
        SBCol = new TableColumn(COL_SB);
        BACol = new TableColumn(COL_BA);
        WCol = new TableColumn(COL_W);
        SVCol = new TableColumn(COL_SV);
        KCol = new TableColumn(COL_K);
        ERACol = new TableColumn(COL_ERA);
        WHIPCol = new TableColumn(COL_WHIP);
        TotalCol = new TableColumn(COL_TOTAL_POINTS);
           
        this.gui = WDK_GUI.getGUI();
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

        teamNameCol.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
        playersNeededCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                //ReadOnlyIntegerWrapper(param.getValue().getK())
                return new ReadOnlyIntegerWrapper(23-param.getValue().getHashMapTotal());
            }
        });

        moneyLeftCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getRemainingMoney());
            }
        });
        
        ppCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getPP());
            }
        });
        
        RCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getR());
            }
        });
        HRCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getHR());
            }
        });
        RBICol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getRBI());
            }
        });
        
        SBCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getSB());
            }
        });
        BACol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyDoubleWrapper(param.getValue().getBA());
            }
        });
        
        WCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyDoubleWrapper(param.getValue().getW());
            }
        });
        SVCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getSV());
            }
        });
        KCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyIntegerWrapper(param.getValue().getK());
            }
        });
        ERACol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyDoubleWrapper(param.getValue().getERA());
            }
        });
        WHIPCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Team, Number> param) {
                return new ReadOnlyDoubleWrapper(param.getValue().getWHIP());
            }
        });
        

        fantasyStandingsTable.setItems(gui.dataManager.getDraft().getTeams());

        fantasyStandingsLabel = new Label("Fantasy Standings Estimates");
        fantasyStandingsLabel.setStyle("-fx-font-size:40px;-fx-text-fill:#FF0000;");

        fantasyStandingsMainPane = new VBox();
        fantasyStandingsMainPane.setStyle("-fx-background-color:#FFC0CB");
        fantasyStandingsMainPane.getChildren().add(fantasyStandingsLabel);
        fantasyStandingsMainPane.getChildren().add(fantasyStandingsTable);

        tab.setContent(fantasyStandingsMainPane);

    }
    
    public void refresh(){
        fantasyStandingsTable.getColumns().get(0).setVisible(false);
        fantasyStandingsTable.getColumns().get(0).setVisible(true);
        
    }
            
}
