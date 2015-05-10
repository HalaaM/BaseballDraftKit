/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

/**
 *
 * @author halaamenasy
 */
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import static wdk.gui.FantasyTeamTab.COL_PLAYER_POSITION;
import static wdk.gui.WDK_GUI.CLASS_SUBHEADING_LABEL;
import wdk.gui.WDK_GUI;

public class MLBTeamsTab {

    VBox MLBTeamMainPane; //add main pane to scrollable pane, this will have controlls
    VBox tablepane;
    TableView<Players> MLBplayerTable;
    Label MLBteamsLabel;
    Label selectProTeamLabel;
    public ComboBox<String> MLBTeamsComboBox;
  
    static final String COL_PLAYER_POSITION = "Positions"; //position on team
    static final String COL_FIRSTNAME = "First";
    static final String COL_LASTNAME = "Last";
    
    String choice;

    TableColumn<Players, String> firstNameCol;
    TableColumn<Players, String> lasNameCol;
    TableColumn<Players, String> postionCol;

    // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    WDK_GUI gui;

    public MLBTeamsTab(Tab tab, WDK_GUI gui) {
        this.gui = gui;
        MLBTeamMainPane = new VBox();

        MLBteamsLabel = new Label("MLB Teams");
        MLBteamsLabel.setStyle("-fx-font-size:40px;-fx-text-fill:#FF0000;");
        MLBTeamMainPane.getChildren().add(MLBteamsLabel);

        selectProTeamLabel = new Label("Select Pro Team: ");
        MLBTeamMainPane.getChildren().add(selectProTeamLabel);

        MLBplayerTable = new TableView();

        tablepane = new VBox();
        tablepane.setPrefWidth(400);
        postionCol = new TableColumn(COL_PLAYER_POSITION);
        firstNameCol = new TableColumn(COL_FIRSTNAME);
        lasNameCol = new TableColumn(COL_LASTNAME);

        firstNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Players, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Players, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getFirstName());
            }
        });

        lasNameCol.setCellValueFactory(new PropertyValueFactory<Players, String>("lastName"));
        postionCol.setCellValueFactory(new PropertyValueFactory<Players, String>("positions"));

        MLBplayerTable.getColumns().add(firstNameCol);
        MLBplayerTable.getColumns().add(lasNameCol);
        MLBplayerTable.getColumns().add(postionCol);
        MLBplayerTable.resize(400, 600);
        MLBplayerTable.setItems(gui.getDataManager().getPlayers());

        ObservableList<String> teamChoices = FXCollections.observableArrayList();
        //    //ATL, AZ, CHC, CIN, COL, LAD, MIA, MIL, NYM, PHI, PIT, SD, SF, STL, WAS
        teamChoices.add("ATL");
        teamChoices.add("AZ");
        teamChoices.add("CHC");
        teamChoices.add("CIN");
        teamChoices.add("COL");
        teamChoices.add("LAD");
        teamChoices.add("MIA");
        teamChoices.add("MIL");
        teamChoices.add("NYM");
        teamChoices.add("PHI");
        teamChoices.add("PIT");
        teamChoices.add("SD");
        teamChoices.add("SF");
        teamChoices.add("STL");
        teamChoices.add("WAS");
        
        MLBTeamsComboBox = new ComboBox(teamChoices);

        MLBTeamMainPane.setStyle("-fx-background-color:#FFC0CB");
        MLBTeamMainPane.getChildren().add(MLBTeamsComboBox);
        tablepane.getChildren().add(MLBplayerTable);
        MLBTeamMainPane.getChildren().add(tablepane);
        
        tab.setContent(MLBTeamMainPane);
        initTableFilters();
        
    }

    private void initTableFilters() {
       
        
          MLBTeamsComboBox.setOnAction(e->{
            choice=MLBTeamsComboBox.getSelectionModel().getSelectedItem();
        if(choice.equalsIgnoreCase("ATL")){
          filterByATL();  
        }

        if(choice.equalsIgnoreCase("AZ")){
          filterByAZ();  
        }
        else if(choice.equalsIgnoreCase("CHC")){
          filterByCHC();  
        }
        else if (choice.equalsIgnoreCase("CIN")){
           filterByCIN();
        }
        else if(choice.equalsIgnoreCase("COL")){
            filterByCOL();
        }
        else if(choice.equalsIgnoreCase("LAD")){
            filterByLAD();
        }
         else if(choice.equalsIgnoreCase("MIA")){
            filterByMIA();
        }
          else if(choice.equalsIgnoreCase("MIL")){
            filterByMIL();
        }
           else if(choice.equalsIgnoreCase("NYM")){
            filterByNYM();
        }
           // //ATL, AZ, CHC, CIN, COL, LAD, MIA, MIL, NYM, PHI, PIT, SD, SF, STL, WAS 
            else if(choice.equalsIgnoreCase("PHI")){
            filterByPHI();
        }
             else if(choice.equalsIgnoreCase("PIT")){
            filterByPIT();
        }
              else if(choice.equalsIgnoreCase("SD")){
            filterBySD();
        }
               else if(choice.equalsIgnoreCase("SF")){
            filterBySF();
        }
                else if(choice.equalsIgnoreCase("STL")){
            filterBySTL();
        }
                 else if(choice.equalsIgnoreCase("WAS")){
            filterByWAS();
        }
        else{
            
        }
        
        });

    }

    private void filterByATL() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("ATL")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByAZ() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("AZ")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);

    }

    private void filterByCHC() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("CHC")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByCIN() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("CIN")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByCOL() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("COL")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByLAD() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("LAD")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByMIA() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("MIA")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByMIL() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("MIL")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByNYM() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("NYM")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByPHI() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("PHI")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByPIT() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("PIT")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    //ATL, AZ, CHC, CIN, COL, LAD, MIA, MIL, NYM, PHI, PIT, SD, SF, STL, WAS

    private void filterBySD() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("SD")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterBySF() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("SF")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterBySTL() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("STL")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

    private void filterByWAS() {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getTeam().contains("WAS")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(MLBplayerTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MLBplayerTable.setItems(sortedData);
    }

}
