/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    HBox fantasyTeamToolBar;
    Button addTeamButton;
    Button removeTeamButton;
    Button editTeamButton;
    Label draftNameLabel;
    TextField draftTextField;
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
       
      
        
        fantasyTeamsComboBox= new ComboBox();
        ObservableList<String> teamChoices = FXCollections.observableArrayList();
        //add team to combo box here
        
        for ( Team s: gui.dataManager.getDraft().getTeam()) {
            teamChoices.add(s.getTeamName());
        }
        
        fantasyTeamToolBar.getChildren().add(fantasyTeamsComboBox);
        
          
        startingLineUpLabel= new Label("Starting Line Up");
        fantasyStartingLineUpPane= new VBox();
        fantasyStartingLineUpPane.getChildren().add(startingLineUpLabel);
        fantasyStartingLineUpPane.getChildren().add(fantasyTeamTable);
        
        taxiSquadLabel= new Label("Taxi Squad");
        fantasyTaxiSquadPane= new VBox();
        fantasyTaxiSquadPane.getChildren().add(taxiSquadLabel);
        
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
        //teamController = new TeamEditController(gui.primaryStage, messageDialog, yesNoCancelDialog);
        removeTeamButton.setOnAction(e -> {
            teamController.handleRemoveTeamRequest(gui,fantasyTeamsComboBox.getSelectionModel().getSelectedItem());
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
    
    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, WDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }

    // INIT A LABEL AND PUT IT IN A TOOLBAR
    private Label initChildLabel(Pane container, WDK_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }
    
    // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }
        private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }
    
}
