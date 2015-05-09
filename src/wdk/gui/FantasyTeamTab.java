/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import properties_manager.PropertiesManager;
import wdk.WDK_PropertyType;
import static wdk.WDK_StartupConstants.PATH_IMAGES;
import wdk.controller.FileController;
import wdk.controller.TeamEditController;
import wdk.data.Draft;
import wdk.data.MLBTeams;
import wdk.data.Players;
import wdk.data.DraftDataManager;
import wdk.data.Team;
import static wdk.gui.WDK_GUI.CLASS_SUBHEADING_LABEL;
import wdk.gui.WDK_GUI;

/**
 *
 * @author halaamenasy
 */
public class FantasyTeamTab {
   
    //This is pertaining to all the FANYASY team screen stuff********************
    ScrollPane fantasyTeamScrollablePane; //put everything in here to make it scrollable 
    VBox fantasyTeamMainPane; //add main pane to scrollable pane, this will have controlls
    VBox fantasyStartingLineUpPane;//this will have label and startup team table view
    VBox fantasyTaxiSquadPane;//this will have label and taxi squad
    TableView <Players> fantasyTeamTable;
    TableView <Players> taxiSquadTable;
    HBox fantasyTeamToolBar;
    Button addTeamButton;
    Button removeTeamButton;
    Button editTeamButton;
    Label draftNameLabel;
    public TextField draftTextField;
    Label selectFantasyTeamLabel;
    public ComboBox <Team> fantasyTeamsComboBox;
    Label startingLineUpLabel;
    Label taxiSquadLabel;
    Label fantasyTeamLabel;
    DraftDataManager dataManager;
    
    TeamEditController teamController;
    Stage primaryStage;

    
    static final String COL_PLAYER_POSITION = "Position"; //position on team
    static final String COL_FIRSTNAME = "First";
    static final String COL_LASTNAME = "Last";
    static final String COL_PROTEAM = "Pro Team";
    static final String COL_POSITION = "Positions";
    static final String COL_YEAROFBIRTH = "Year of Birth";
    static final String COL_RW = "R/W";
    static final String COL_HRSV = "HR/SV";
    static final String COL_RBIK = "RBI/K";
    static final String COL_SBERA = "SB/ERA";
    static final String COL_BAWHIP = "BA/WHIP";
    static final String COL_ESTIMATEDVAL = "Estimated Value";
    static final String COL_NOTES = "Notes";
    static final String COL_CONTRACT= "Contract";
    static final String COL_SALARY="Salary";
    
    TableColumn<Players, String> firstNameCol;
    TableColumn positionOfPlayerOnTeamCol;
    TableColumn<Players,String> lasNameCol;
    TableColumn <Players, String>proTeamCol;
    TableColumn <Players, String> postionCol;
    TableColumn yearOfBirthCol;
    TableColumn <Players, Number> R_WCol;
    TableColumn <Players, Number> HR_SVCol;
    TableColumn <Players, Number>RBI_KCol;
    TableColumn <Players, Number>SB_ERACol;
    TableColumn<Players, Number> BA_WHIPCol;
    TableColumn EstimatedValuCol;
    TableColumn notesCol;
    TableColumn salaryCol;
    TableColumn contractCol;
    
        // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    WDK_GUI gui;
    
    public FantasyTeamTab(Tab tab,WDK_GUI gui){
        this.gui=gui;
       
        fantasyTeamMainPane= new VBox();
        
        fantasyTeamLabel = new Label("Fantasy Teams");
        fantasyTeamLabel.setStyle("-fx-font-size:40px;-fx-text-fill:#FF0000;");
        fantasyTeamMainPane.getChildren().add(fantasyTeamLabel);
 
        fantasyTeamToolBar= new HBox();
        addTeamButton = initChildButton(fantasyTeamToolBar, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_ITEM_TOOLTIP, false);
        removeTeamButton = initChildButton(fantasyTeamToolBar, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_ITEM_TOOLTIP, false);   
        editTeamButton = initChildButton(fantasyTeamToolBar, WDK_PropertyType.EDIT_ICON, WDK_PropertyType.EDIT_ITEM_TOOLTIP, false);  

        draftNameLabel= new Label("DraftName");
        draftTextField= new TextField();
        
        draftTextField.setText(gui.getDataManager().getDraft().getTitle());
        
        fantasyTeamMainPane.getChildren().add(draftNameLabel);
        fantasyTeamToolBar.getChildren().add(draftTextField);
      
        
        //add tool bar and search box/textfield
        fantasyTeamMainPane.getChildren().add(fantasyTeamToolBar);
        
        fantasyTeamTable=new TableView();
        positionOfPlayerOnTeamCol= new TableColumn(COL_PLAYER_POSITION);
        firstNameCol= new TableColumn(COL_FIRSTNAME);
        lasNameCol = new TableColumn(COL_LASTNAME);
        proTeamCol=new TableColumn(COL_PROTEAM);
        postionCol=new TableColumn(COL_POSITION);
        yearOfBirthCol=new TableColumn(COL_YEAROFBIRTH);
        R_WCol=new TableColumn(COL_RW);
        HR_SVCol=new TableColumn(COL_HRSV);
        RBI_KCol=new TableColumn(COL_RBIK );
        SB_ERACol=new TableColumn(COL_SBERA);
        BA_WHIPCol=new TableColumn(COL_BAWHIP);
        EstimatedValuCol=new TableColumn(COL_ESTIMATEDVAL);
        notesCol=new TableColumn(COL_NOTES);
        contractCol= new TableColumn(COL_CONTRACT);
        salaryCol= new TableColumn(COL_SALARY);
        
        firstNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Players, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Players, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getFirstName());
            }
        });
        
        lasNameCol.setCellValueFactory(new PropertyValueFactory<Players, String>("lastName"));
        proTeamCol.setCellValueFactory(new PropertyValueFactory<Players, String>("team"));
        postionCol.setCellValueFactory(new PropertyValueFactory<Players, String>("positions"));
        yearOfBirthCol.setCellValueFactory(new PropertyValueFactory<Integer, String>("yearOfBirth"));
        R_WCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Players, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Players, Number> param) {
                if(param.getValue().getR()>param.getValue().getW()){
                    return new ReadOnlyIntegerWrapper(param.getValue().getR());
                }
                else return new ReadOnlyIntegerWrapper(param.getValue().getW());
            }
        });
        HR_SVCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Players, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Players, Number> param) {
               if(param.getValue().getHR()>param.getValue().getSV()){
                   return new ReadOnlyIntegerWrapper(param.getValue().getHR());
               }
               else return new ReadOnlyIntegerWrapper(param.getValue().getSV());
            }
        });
        RBI_KCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Players, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Players, Number> param) {
               if (param.getValue().getRBI()>param.getValue().getK()){
                   return new ReadOnlyIntegerWrapper(param.getValue().getRBI());
               }
               else return new ReadOnlyIntegerWrapper(param.getValue().getK());
            }
        });
        SB_ERACol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Players, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Players, Number> param) {
                if(param.getValue().getSB()>param.getValue().getERA()){
                    return new ReadOnlyIntegerWrapper(param.getValue().getSB());
                }
                else return new ReadOnlyDoubleWrapper(param.getValue().getERA());
            }
        });
        BA_WHIPCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Players, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Players, Number> param) {
                if(param.getValue().getBA()>param.getValue().getWHIP()){
                    return new ReadOnlyDoubleWrapper(param.getValue().getBA());
                }
                else return new ReadOnlyDoubleWrapper(param.getValue().getWHIP());
            }
        });
        EstimatedValuCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_ESTIMATEDVAL));
        notesCol.setCellValueFactory(new PropertyValueFactory<String, String>("Notes"));
        contractCol.setCellValueFactory(new PropertyValueFactory<Players, String>("contract"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<Integer, String>("salary"));
        fantasyTeamTable.getSelectionModel().setCellSelectionEnabled(true);
        fantasyTeamTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        notesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fantasyTeamTable.setEditable(true);
        
        fantasyTeamTable.getColumns().add(firstNameCol);
        fantasyTeamTable.getColumns().add(lasNameCol);
        fantasyTeamTable.getColumns().add(proTeamCol);
        fantasyTeamTable.getColumns().add(postionCol);
        fantasyTeamTable.getColumns().add(yearOfBirthCol);
        fantasyTeamTable.getColumns().add(R_WCol);
        fantasyTeamTable.getColumns().add(HR_SVCol);
        fantasyTeamTable.getColumns().add(RBI_KCol);
        fantasyTeamTable.getColumns().add(SB_ERACol);
        fantasyTeamTable.getColumns().add(BA_WHIPCol);
        fantasyTeamTable.getColumns().add(EstimatedValuCol);
        fantasyTeamTable.getColumns().add(notesCol);
        fantasyTeamTable.getColumns().add(contractCol);
        fantasyTeamTable.getColumns().add(salaryCol);
      
          fantasyTeamTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                // OPEN UP THE SCHEDULE ITEM EDITOR
                Players p = fantasyTeamTable.getSelectionModel().getSelectedItem();
                gui.playerController.handleEditPlayerRequest(gui, p);
            }
        });
        
        fantasyTeamsComboBox= new ComboBox();

        for ( Team s: gui.dataManager.getDraft().getTeams()) {
            fantasyTeamsComboBox.getItems().add(s);
            
        }
        
        
        fantasyTeamsComboBox.setOnAction(e->{
            
            fantasyTeamTable.setItems(fantasyTeamsComboBox.getSelectionModel().getSelectedItem().getPlayers());
        });
        
        fantasyTeamToolBar.getChildren().add(fantasyTeamsComboBox);
        
        fantasyTeamMainPane.setStyle("-fx-background-color:#FFC0CB");  
        startingLineUpLabel= new Label("Starting Line Up");
        fantasyStartingLineUpPane= new VBox();
        fantasyStartingLineUpPane.getChildren().add(startingLineUpLabel);
        fantasyStartingLineUpPane.getChildren().add(fantasyTeamTable);
        
        taxiSquadLabel= new Label("Taxi Squad");
        taxiSquadTable=new TableView();
        fantasyTaxiSquadPane= new VBox();
        fantasyTaxiSquadPane.getChildren().add(taxiSquadLabel);
      //  fantasyTaxiSquadPane.getChildren().add(taxiSquadTable);
        
        fantasyTeamMainPane.getChildren().add(fantasyStartingLineUpPane);
        fantasyTeamMainPane.getChildren().add(fantasyTaxiSquadPane);
        
    
       
        tab.setContent(fantasyTeamMainPane);
        initEventHandlers();
        
    }
    
     public Button getAddTeamButton(){
         return addTeamButton;
     }
     
     public void initEventHandlers(){
    
//         // AND NOW THE team ADDING AND EDITING CONTROLS
        teamController = new TeamEditController(gui.primaryStage, gui.messageDialog, gui.yesNoCancelDialog);
        addTeamButton.setOnAction(e -> {
            teamController.handleAddTeamRequest(gui);
        });
        removeTeamButton.setOnAction(e -> {
            teamController.handleRemoveTeamRequest(gui,fantasyTeamsComboBox.getSelectionModel().getSelectedItem());
        });
         editTeamButton.setOnAction(e -> {
            teamController.handleEditTeamRequest(gui,fantasyTeamsComboBox.getSelectionModel().getSelectedItem());
        });
     }

    
    private Button initChildButton(Pane toolbar, WDK_PropertyType icon, WDK_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
       
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }
    
    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(WDK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

}
