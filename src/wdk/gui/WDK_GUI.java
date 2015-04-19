
package wdk.gui;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import static wdk.WDK_StartupConstants.*;
import wdk.controller.DraftEditController;
import wdk.WDK_PropertyType;
import wdk.data.DraftDataView;
import wdk.controller.FileController;
import wdk.data.DraftDataManager;
import wdk.data.DraftDataView;
import wdk.data.DraftPage;
import wdk.data.Draft;
import wdk.file.DraftFileManager;
import wdk.file.DraftExporter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author halaamenasy
 */
public class WDK_GUI implements DraftDataView{
    
    // THESE CONSTANTS ARE FOR TYING THE PRESENTATION STYLE OF
    // THIS GUI'S COMPONENTS TO A STYLE SHEET THAT IT USES

    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "wdk_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_SUBJECT_PANE = "subject_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;
    
   // THIS IS THE APPLICATION WINDOW
    Stage primaryStage;

    // THIS IS THE STAGE'S SCENE GRAPH
    Scene primaryScene;
    
    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane wdkPane;
    
    //For the playerTabPane
    VBox playerTab;
    //The pane where our draft info goes
    boolean workspaceActivated;

    // THIS MANAGES ALL OF THE APPLICATION'S DATA
    DraftDataManager dataManager;

    //MANAGES EDITING A DRAFT
    DraftEditController draftEditController;
    
    // THIS MANAGES COURSE FILE I/O
    DraftFileManager draftFileManager;

    // THIS MANAGES EXPORTING OUR SITE PAGES
    DraftExporter siteExporter;

    // THIS HANDLES INTERACTIONS WITH FILE-RELATED CONTROLS
    FileController fileController;
    
    // WE'LL PUT THIS IN THE TOP OF THE WORKSPACE, IT WILL
    // HOLD TWO OTHER PANES (radio pane and player search pane) FULL OF CONTROLS AS WELL AS A LABEL
    VBox topWorkSpacePane;
    Label availablePlayerHeadingLabel;
   
    //These are the two panes in the top work space pane
    HBox radioButtonPane;
    HBox playerSearchPane;
    
    //this is what is inside the playerSearchPane
    Label searchLabel;
    TextField searchPlayerTextField;
    HBox playerToolBar;
    Button addPlayerButton;
    Button removePlayerButton; 
 
    
    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newCourseButton;
    Button loadCourseButton;
    Button saveCourseButton;
    Button exportSiteButton;
    Button exitButton;

    // THIS REGION IS FOR MANAGING PLAYERS
   
    TableView<Player> playerTable;
    TableColumn firstNameCol;
    TableColumn lasNameCol;
    TableColumn proTeamCol;
    TableColumn postionCol;
    TableColumn yearOfBirthCol;
    TableColumn R_WCol;
    TableColumn HR_SVCol;
    TableColumn RBI_KCol;
    TableColumn SB_ERACol;
    TableColumn BA_WHIPCol;
    TableColumn EstimatedValuCol;
    TableColumn notesCol;
    TextField playerTextField;
    
    // AND TABLE COLUMNS fix it according to json at some point
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
    
    //This is whats inside the radio pane
    HBox playerOptionsToolBar;
    RadioButton all;
    RadioButton c;
    RadioButton one_B;
    RadioButton ci;
    RadioButton three_B;
    RadioButton two_B;
    RadioButton MI;
    RadioButton SS;
    RadioButton OF;
    RadioButton U;
    RadioButton P;

    //TabPane FOR FIVE SCREENS
    TabPane tabPane;
    Tab player;
    Tab fantasyTeam;
    Tab fantasyStandings;
    Tab draftSummary;
    Tab MLBTeams;
    
    //HEADING FOR FIVE SCREENS
    Label availablePlayers;
    Label fantasyTeamLabel;
    Label fantasyStandingLabel;
    Label draftSummaryLabel;
    Label MLBTeamsLabel;

    // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;

    /**
     * Constructor for making this GUI, note that it does not initialize the UI
     * controls. To do that, call initGUI.
     *
     * @param initPrimaryStage Window inside which the GUI will be displayed.
     */
    public WDK_GUI(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }
    
     /**
     * Accessor method for the file controller.
     *
     * @return The FileController used by this UI.
     */
    public FileController getFileController() {
        return fileController;
    }
     /**
     * Accessor method for the data manager.
     *
     * @return The CourseDataManager used by this UI.
     */
    public DraftDataManager getDataManager() {
        return dataManager;
    }

    /**
     * Accessor method for the course file manager.
     *
     * @return The CourseFileManager used by this UI.
     */
    public DraftFileManager getCourseFileManager() {
        return draftFileManager;
    }

    /**
     * Accessor method for the site exporter.
     *
     * @return The CourseSiteExporter used by this UI.
     */
    public DraftExporter getSiteExporter() {
        return siteExporter;
    }

    /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }
    
    public MessageDialog getMessageDialog() {
        return messageDialog;
    }
    
    public YesNoCancelDialog getYesNoCancelDialog() {
        return yesNoCancelDialog;
    }

    /**
     * Mutator method for the data manager.
     *
     * @param initDataManager The CourseDataManager to be used by this UI.
     */
    public void setDataManager(DraftDataManager initDataManager) {
        dataManager = initDataManager;
    }

    /**
     * Mutator method for the course file manager.
     *
     * @param initCourseFileManager The CourseFileManager to be used by this UI.
     */
    public void setDraftFileManager(DraftFileManager initCourseFileManager) {
        draftFileManager = initCourseFileManager;
    }

    /**
     * Mutator method for the site exporter.
     *
     * @param initSiteExporter The CourseSiteExporter to be used by this UI.
     */
    public void setSiteExporter(DraftExporter initSiteExporter) {
        siteExporter = initSiteExporter;
    }

    /**
     * This method fully initializes the user interface for use.
     *
     * @param windowTitle The text to appear in the UI window's title bar.
     * @param drafts
     * @throws IOException Thrown if any initialization files fail to load.
     */
    // add that in at some point ArrayList<String> drafts
   
    //for now im just setting up GUI eventually im going to pass it a player object
    public void initGUI(String windowTitle) throws IOException {
 
        // INIT THE TOOLBAR
        initFileToolbar();
        //initWorkSpace(tabPane);
        initDialogs();
        initEventHandlers();
        initWindow(windowTitle);

    }
     public void activateWorkspace() {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            wdkPane.setTop(tabPane);
            workspaceActivated = true;
        }
    }
      @Override
    public void reloadDraft(Draft draftToReload) {
        if (!workspaceActivated) {
            activateWorkspace();
        draftEditController.enable(false);
    
        }
        draftEditController.enable(true);
        
    }
      public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveCourseButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
        loadCourseButton.setDisable(false);
        exportSiteButton.setDisable(false);

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }

    /****************************************************************************/
    /* BELOW ARE ALL THE PRIVATE HELPER METHODS WE USE FOR INITIALIZING OUR GUI */
    /****************************************************************************/
     private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
    }
    
    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newCourseButton = initChildButton(fileToolbarPane, WDK_PropertyType.NEW_COURSE_ICON, WDK_PropertyType.NEW_COURSE_TOOLTIP, false);
        loadCourseButton = initChildButton(fileToolbarPane, WDK_PropertyType.LOAD_COURSE_ICON, WDK_PropertyType.LOAD_COURSE_TOOLTIP, false);
        saveCourseButton = initChildButton(fileToolbarPane, WDK_PropertyType.SAVE_COURSE_ICON, WDK_PropertyType.SAVE_COURSE_TOOLTIP, true);
        exportSiteButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXPORT_PAGE_ICON, WDK_PropertyType.EXPORT_PAGE_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXIT_ICON, WDK_PropertyType.EXIT_TOOLTIP, false);
    }

     
    /****************************************************************************/
    /* BELOW ARE ALL THE PRIVATE HELPER METHODS WE USE FOR INITIALIZING OUR GUI */
    /****************************************************************************/
     
    //calls the TabPane to manage all the screens
     private void initWorkSpace(TabPane tabPane) {
          initTabPane();
          
          
         
//
//        // NOTE THAT WE HAVE NOT PUT THE WORKSPACE INTO THE WINDOW,
//        // THAT WILL BE DONE WHEN THE USER EITHER CREATES A NEW
//        // COURSE OR LOADS AN EXISTING ONE FOR EDITING
//        workspaceActivated = false;
               
    }
    //this pane has home which is available players, fantasy teams, fantasy standings,draft summary
    //mlb teams
    private void initTabPane(){
        tabPane=new TabPane();
        player=new Tab();
        fantasyTeam=new Tab();
        fantasyStandings=new Tab();
        draftSummary=new Tab();
        MLBTeams= new Tab();
    
        fantasyTeam.setText("fantasy team");
        player.setText("available players");
        fantasyStandings.setText("fantasy Standings");
        draftSummary.setText("draft summary");
        MLBTeams.setText("MLB teams");
        
        tabPane.getTabs().add(fantasyTeam);
        tabPane.getTabs().add(player);
        tabPane.getTabs().add(fantasyStandings);
        tabPane.getTabs().add(draftSummary);
        tabPane.getTabs().add(MLBTeams);
        
        initAvailablePlayerTab(player);
        initFantasyTeamTab(fantasyTeam);
        initFantasyStandingsTab(fantasyStandings);
        initDraftSummaryTab(draftSummary);
        initMLBTeamsTab(MLBTeams);
       
        
    }
    private void initAvailablePlayerTab(Tab tab){

        //Make and initialize all buttons for RadioButtonPane
        ToggleGroup toggle= new ToggleGroup();
        topWorkSpacePane= new VBox();
        availablePlayerHeadingLabel= new Label("Available Players");
        radioButtonPane= new HBox();
        
        all= new RadioButton("All");
        all.setToggleGroup(toggle);
        c= new RadioButton("C");
        c.setToggleGroup(toggle);
        one_B= new RadioButton("1_B");
        one_B.setToggleGroup(toggle);
        ci=new RadioButton("CI");
        ci.setToggleGroup(toggle);
        three_B= new RadioButton ("3_B");
        three_B.setToggleGroup(toggle);
        two_B=new RadioButton("2_B");
        two_B.setToggleGroup(toggle);
        MI=new RadioButton("MI");
        MI.setToggleGroup(toggle);
        SS=new RadioButton("SS");
        SS.setToggleGroup(toggle);
        OF= new RadioButton("OF");
        OF.setToggleGroup(toggle);
        U= new RadioButton("U");
        U.setToggleGroup(toggle);
        P=new RadioButton("P");
        P.setToggleGroup(toggle);
        
        radioButtonPane.getChildren().add(all);
        radioButtonPane.getChildren().add(c);
        radioButtonPane.getChildren().add(one_B);
        radioButtonPane.getChildren().add(ci);
        radioButtonPane.getChildren().add(three_B);
        radioButtonPane.getChildren().add(two_B);
        radioButtonPane.getChildren().add(MI);
        radioButtonPane.getChildren().add(SS);
        radioButtonPane.getChildren().add(OF);
        radioButtonPane.getChildren().add(U);
        radioButtonPane.getChildren().add(P);
        
        //Make playerSearchPane 
        playerSearchPane= new HBox();
        
        // make toolbar to put into playerSearchPane
        playerToolBar= new HBox();
        addPlayerButton = initChildButton(playerToolBar, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_ITEM_TOOLTIP, false);
        removePlayerButton = initChildButton(playerToolBar, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_ITEM_TOOLTIP, false);   
       
        //add tool bar and search box/textfield
        playerSearchPane.getChildren().add(playerToolBar);
        
        searchLabel=initLabel(WDK_PropertyType. SEARCH_LABEL, CLASS_SUBHEADING_LABEL);
        searchPlayerTextField=new TextField();
        playerSearchPane.getChildren().add(searchLabel);
        playerSearchPane.getChildren().add(searchPlayerTextField);
       
        
        //add radio and search pane to top work space
        topWorkSpacePane.getChildren().add(playerSearchPane);
        topWorkSpacePane.getChildren().add(radioButtonPane);
        
        //make tableview for players
        playerTable=new TableView();
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

        //json property
        firstNameCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_FIRSTNAME));
        lasNameCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_LASTNAME));
        proTeamCol.setCellValueFactory(new PropertyValueFactory<LocalDate, String>(COL_PROTEAM));
        postionCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_POSITION));
        yearOfBirthCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_YEAROFBIRTH));
        R_WCol.setCellValueFactory(new PropertyValueFactory<LocalDate, String>(COL_RW));
        HR_SVCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_HRSV));
        RBI_KCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_RBIK));
        SB_ERACol.setCellValueFactory(new PropertyValueFactory<LocalDate, String>(COL_SBERA));
        BA_WHIPCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_BAWHIP));
        EstimatedValuCol.setCellValueFactory(new PropertyValueFactory<String, String>(COL_ESTIMATEDVAL));
        notesCol.setCellValueFactory(new PropertyValueFactory<LocalDate, String>(COL_NOTES));
        
        playerTable.getColumns().add(firstNameCol);
        playerTable.getColumns().add(lasNameCol);
        playerTable.getColumns().add(proTeamCol);
        playerTable.getColumns().add(postionCol);
        playerTable.getColumns().add(yearOfBirthCol);
        playerTable.getColumns().add(R_WCol);
        playerTable.getColumns().add(HR_SVCol);
        playerTable.getColumns().add(RBI_KCol);
        playerTable.getColumns().add(SB_ERACol);
        playerTable.getColumns().add(BA_WHIPCol);
        playerTable.getColumns().add(EstimatedValuCol);
        playerTable.getColumns().add(notesCol);
        
        //add table view and topworkspace to  player tab V box (which i still need to make)
        playerTab= new VBox();
        availablePlayerHeadingLabel = initLabel(WDK_PropertyType. AVAILABLE_PLAYER_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        playerTab.getChildren().add(availablePlayerHeadingLabel);
        playerTab.getChildren().add(topWorkSpacePane);
        playerTab.getChildren().add(playerTable);
       
     
        //set tab content to player tab v box
        tab.setContent(playerTab);
      
        

    }

      private void initFantasyTeamTab( Tab fantasyTeam) {
        fantasyTeamLabel = initLabel(WDK_PropertyType. FANTASY_TEAM_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        fantasyTeam.setContent(fantasyTeamLabel);
    }  
       private void initFantasyStandingsTab( Tab fantasyStandings) {
        fantasyStandingLabel = initLabel(WDK_PropertyType. FANTASY_STANDING_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        fantasyStandings.setContent(fantasyStandingLabel);
    }
       
     private void initDraftSummaryTab( Tab draftSummary) {
         draftSummaryLabel = initLabel(WDK_PropertyType. DRAFT_SUMMARY_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
         draftSummary.setContent(draftSummaryLabel);
    }
      private void initMLBTeamsTab( Tab MLBTeams) {
        MLBTeamsLabel = initLabel(WDK_PropertyType.  MLB_TEAMS_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        MLBTeams.setContent(MLBTeamsLabel);
    }
   
     //method only for available player screen only  
     private void initPlayerControls() {

//       // NOW THE CONTROLS FOR ADDING ASSIGNMENT ITEMS
//        playerBox = new VBox();
//        playerToolBar = new HBox();
//        playerLabel = initLabel(WDK_PropertyType.HWS_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
//        addPlayerButton = initChildButton(playerToolBar, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_ITEM_TOOLTIP, false);
//        removePlayerButton = initChildButton(playerToolBar, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_ITEM_TOOLTIP, false);        
//        playerTable = new TableView();
//        playerBox.getChildren().add(playerLabel);
//        playerBox.getChildren().add(playerToolBar);
//        playerBox.getChildren().add(playerTable);
//        playerBox.getStyleClass().add(CLASS_BORDERED_PANE);
//        
 
      
  
    }
     
      private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        wdkPane = new BorderPane();
        wdkPane.setTop(fileToolbarPane);
//        wdkPane.setCenter(tabPane);
//        tabPane.setVisible(false);
        primaryScene = new Scene(wdkPane);
        
        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
       private void initEventHandlers() throws IOException {
        // FIRST THE FILE CONTROLS
        fileController = new FileController(messageDialog, yesNoCancelDialog, draftFileManager, siteExporter);
        newCourseButton.setOnAction(e -> {
    
                initWorkSpace(tabPane);
                 wdkPane.setCenter(tabPane);
           

//tabPane.setVisible(true);
//fileController.handleNewDraftRequest(this);
        });

          // registerTextFieldController(playerTextField);
       }
        // REGISTER THE EVENT LISTENER FOR A TEXT FIELD
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            draftEditController.handleCourseChangeRequest(this);
        });
    }
    
    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
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

   
   
}
