/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import wdk.WDK_PropertyType;
import static wdk.WDK_StartupConstants.PATH_IMAGES;
import wdk.data.Players;
import wdk.data.Team;

/**
 *
 * @author halaamenasy
 */
public class DraftSummaryTab {
    
    VBox draftSummaryMainPane;
    TableView <Players> draftTable;
    HBox draftToolBar;
    Button selectPlayerButton;
    Button startDraftButton;
    Button pauseDraftButton;
    Label draftSummaryLabel;
    
    static final String COL_PICK = "Pick #"; //pick on team
    static final String COL_FIRSTNAME = "First";
    static final String COL_LASTNAME = "Last";
    static final String COL_FANTASY_TEAM = "Fantasy Team";
    static final String COL_CONTRACT = "Contract";
    static final String COL_SALARY = "Salary";
   
    TableColumn pickNumberCol;
    TableColumn<Players, String> firstNameCol;
    TableColumn<Players,String> lasNameCol;
    TableColumn <Players, String>fantasyTeamCol;
    TableColumn contractCol;
    TableColumn salaryCol;
    
  // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    WDK_GUI gui;
    
      public DraftSummaryTab(Tab tab,WDK_GUI gui){
      this.gui=gui;
      draftSummaryMainPane=new VBox();
      draftSummaryLabel = new Label("Draft Summary");
      draftSummaryLabel.setStyle("-fx-font-size:40px;-fx-text-fill:#FF0000;");
      draftSummaryMainPane.getChildren().add(draftSummaryLabel);
      
       draftToolBar= new HBox();
       selectPlayerButton = initChildButton(draftToolBar, WDK_PropertyType.SELECT_PLAYER_ICON, WDK_PropertyType.SELECT_PLAYER_TOOLTIP, false);
       startDraftButton = initChildButton(draftToolBar, WDK_PropertyType.START_DRAFT_ICON, WDK_PropertyType.START_DRAFT_TOOLTIP, false);   
       pauseDraftButton = initChildButton(draftToolBar, WDK_PropertyType.PAUSE_DRAFT_ICON, WDK_PropertyType.PAUSE_DRAFT_TOOLTIP, false);  
      
       draftSummaryMainPane.getChildren().add(draftToolBar);
       draftSummaryMainPane.setStyle("-fx-background-color:#FFC0CB");  
       
        draftTable=new TableView();
        pickNumberCol= new TableColumn(COL_PICK);
        firstNameCol= new TableColumn(COL_FIRSTNAME);
        lasNameCol = new TableColumn(COL_LASTNAME);
        fantasyTeamCol=new TableColumn(COL_FANTASY_TEAM);
        contractCol=new TableColumn(COL_CONTRACT);
        salaryCol=new TableColumn(COL_SALARY);
        
        draftTable.getColumns().add(pickNumberCol);
        draftTable.getColumns().add(firstNameCol);
        draftTable.getColumns().add(lasNameCol);
        draftTable.getColumns().add(fantasyTeamCol);
        draftTable.getColumns().add(contractCol);
        draftTable.getColumns().add(salaryCol);
        
        draftSummaryMainPane.getChildren().add(draftTable);
        
         tab.setContent(draftSummaryMainPane);
       
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
}
