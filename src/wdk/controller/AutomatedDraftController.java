/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import java.util.Random;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    Stage primaryStage;
    Draft draft;
    int teamno = 0;

    /**
     *
     * @param initPrimaryStage
     * @param player
     * @param initMessageDialog
     * @param initYesNoCancelDialog
     */
    public AutomatedDraftController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {

        primaryStage = initPrimaryStage;
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    // THESE ARE FOR ASSIGNMENT ITEMS
    public void handleSelectPlayerRequest(WDK_GUI gui) {

        DraftDataManager cdm = gui.getDataManager();
        ObservableList<Players> players = cdm.getPlayers();
        ObservableList<Team> teamList = cdm.getDraft().getTeam();
        try {
            //add 2 C's
            if (teamList.get(teamno).getHashMapTotal() == 0 || teamList.get(teamno).getHashMapTotal() == 1) {
                Players player = filterByC(gui).get(0);
                player.setPositionOnTeam("C");
                player.setSalary(1);
                teamList.get(teamno).addPlayer(player);
                players.remove(player);
            } //add 1 1B
            else if (teamList.get(teamno).getHashMapTotal() == 2) {
                Players player2 = filterBy1B(gui).get(0);
                player2.setPositionOnTeam("1B");
                player2.setSalary(1);
                teamList.get(teamno).addPlayer(player2);
                players.remove(player2);
            } else if (teamList.get(teamno).getHashMapTotal() == 3) {
                //add 1 CI
                Players player3 = filterByci(gui).get(0);
                player3.setPositionOnTeam("CI");
                player3.setSalary(1);
                teamList.get(teamno).addPlayer(player3);
                players.remove(player3);
            } //add 1 3B
            else if (teamList.get(teamno).getHashMapTotal() == 4) {
                Players player4 = filterBy3b(gui).get(0);
                player4.setPositionOnTeam("3B");
                player4.setSalary(1);
                teamList.get(teamno).addPlayer(player4);
                players.remove(player4);
            } //add 1 2B
            else if (teamList.get(teamno).getHashMapTotal() == 5) {
                Players player5 = filterBy2b(gui).get(0);
                player5.setPositionOnTeam("2B");
                player5.setSalary(1);
                teamList.get(teamno).addPlayer(player5);
                players.remove(player5);
            } //add 1 MI
            else if (teamList.get(teamno).getHashMapTotal() == 6) {
                Players player6 = filterByMI(gui).get(0);
                player6.setPositionOnTeam("MI");
                player6.setSalary(1);
                teamList.get(teamno).addPlayer(player6);
                players.remove(player6);
            } //add 1 SS
            else if (teamList.get(teamno).getHashMapTotal() == 7) {
                Players player7 = filterBySS(gui).get(0);
                player7.setPositionOnTeam("SS");
                player7.setSalary(1);
                teamList.get(teamno).addPlayer(player7);
                players.remove(player7);
            }
            // add 1 U
             else if (teamList.get(teamno).getHashMapTotal() == 8) {
                Players player7 = filterByU(gui).get(0);
                player7.setPositionOnTeam("U");
                player7.setSalary(1);
                teamList.get(teamno).addPlayer(player7);
                players.remove(player7);
            }
            else if (teamList.get(teamno).getHashMapTotal()>=9&&teamList.get(teamno).getHashMapTotal()<14 ) {
                //add 5 OF
                Players player8 = filterByOF(gui).get(0);
                player8.setPositionOnTeam("OF");
                player8.setSalary(1);
                teamList.get(teamno).addPlayer(player8);
                players.remove(player8);
            } //add 9 P
            else if (teamList.get(teamno).getHashMapTotal() >=14  && teamList.get(teamno).getHashMapTotal() <23) {
                Players player9 = filterByP(gui).get(0);
                player9.setPositionOnTeam("P");
                player9.setSalary(1);
                teamList.get(teamno).addPlayer(player9);
                players.remove(player9);
            } 
            else if(teamList.get(teamno).getHashMapTotal()>=23&&teamList.get(teamno).getTaxiSquad().size()<9) {
                Players player10 = filterByAll(gui).get(0);
                String [] positions= player10.getPositionsEligible().split("_");
                player10.setPositionOnTeam(positions[0]);
                player10.setSalary(1);
                teamList.get(teamno).getTaxiSquad().add(player10);
                players.remove(player10);
            }
            else if (teamno<teamList.size()){
                teamno++;
                
            }
            

        } catch (Exception e) {
            System.out.print("Error");
        }

    }

    public void handleStartAutomatedDraftRequest(WDK_GUI gui) {

        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();

    }

    public void handlePauseAutomatedRequest(WDK_GUI gui) {

    }

    public Players selectRandomPlayer(ObservableList<Players> list) {
        Random rand = new Random();
        int randomNum = rand.nextInt((647 - 1) + 1) + 1;
        return list.get(randomNum);
    }

    private SortedList<Players> filterByAll(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            return true;
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);
        return sortedData;

    }

    private SortedList<Players> filterByC(WDK_GUI gui) {

        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("C")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);
        return sortedData;

    }

    private SortedList<Players> filterBy1B(WDK_GUI gui) {

        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("1B")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }

    private SortedList<Players> filterByci(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("1B") || player.getPositionsEligible().contains("3B")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);
        return sortedData;

    }

    private SortedList<Players> filterBy3b(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("3B")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);
        return sortedData;

    }

    private SortedList<Players> filterBy2b(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("2B")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        return sortedData;
    }

    private SortedList<Players> filterByMI(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("2B") || player.getPositionsEligible().contains("IF") || player.getPositionsEligible().contains("SS")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        return sortedData;
    }

    private SortedList<Players> filterBySS(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("SS")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        return sortedData;
    }

    private SortedList<Players> filterByOF(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("OF")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        return sortedData;
    }

    private SortedList<Players> filterByU(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (!player.getPositionsEligible().contains("P")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        return sortedData;
    }

    private SortedList<Players> filterByP(WDK_GUI gui) {
        FilteredList<Players> filteredData = new FilteredList<>(gui.getDataManager().getPlayers(), p -> true);

        filteredData.setPredicate(player -> {
            if (player.getPositionsEligible().contains("P")) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Players> sortedData = new SortedList<>(filteredData);

        return sortedData;
    }

}
