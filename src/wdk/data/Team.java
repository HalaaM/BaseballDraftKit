/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import com.sun.javaws.exceptions.InvalidArgumentException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author halaamenasy
 */
public class Team implements Serializable {

    private DraftDataManager ddm;
    private ObservableList<Players> players;
    private ObservableList<Players> taxiSquad;
    private String teamName;
    private String teamOwnerName;

    //2 for C;       1 each for 1B, CI, 3B, 2B, MI, SS, & U;      5 for OF;     9 for P
    HashMap<String, Integer> positionCounters;
    HashMap<String, Integer> maxAmountPerPositionCounter;

    public Team() {
        // this.players=ddm.getPlayers();
        this.teamName = "";
        this.teamOwnerName = "";
        players = FXCollections.observableArrayList();
        positionCounters = new HashMap();
        maxAmountPerPositionCounter = new HashMap();

        taxiSquad=FXCollections.observableArrayList();
        
        positionCounters.put("C", 0);
        positionCounters.put("1B", 0);
        positionCounters.put("CI", 0);
        positionCounters.put("3B", 0);
        positionCounters.put("2B", 0);
        positionCounters.put("MI", 0);
        positionCounters.put("SS", 0);
        positionCounters.put("U", 0);
        positionCounters.put("OF", 0);
        positionCounters.put("P", 0);
        positionCounters.put("Taxi", 0);

        maxAmountPerPositionCounter.put("C", 2);
        maxAmountPerPositionCounter.put("1B", 1);
        maxAmountPerPositionCounter.put("CI", 1);
        maxAmountPerPositionCounter.put("3B", 1);
        maxAmountPerPositionCounter.put("2B", 1);
        maxAmountPerPositionCounter.put("MI", 1);
        maxAmountPerPositionCounter.put("SS", 1);
        maxAmountPerPositionCounter.put("U", 1);
        maxAmountPerPositionCounter.put("OF", 5);
        maxAmountPerPositionCounter.put("P", 9);
        maxAmountPerPositionCounter.put("Taxi", 8);

    }

    public Team(String teamName, String teamOwnerName) {
        this.teamName = teamName;
        this.teamOwnerName = teamOwnerName;

    }

    public Team(String teamName, String teamOwnerName, Players[] list) {
        this.teamName = teamName;
        this.teamOwnerName = teamOwnerName;
        players = FXCollections.observableArrayList();
        taxiSquad=FXCollections.observableArrayList();
        
        positionCounters = new HashMap();
        maxAmountPerPositionCounter = new HashMap();

        positionCounters.put("C", 0);
        positionCounters.put("1B", 0);
        positionCounters.put("CI", 0);
        positionCounters.put("3B", 0);
        positionCounters.put("2B", 0);
        positionCounters.put("MI", 0);
        positionCounters.put("SS", 0);
        positionCounters.put("U", 0);
        positionCounters.put("OF", 0);
        positionCounters.put("P", 0);
  

        for (int i = 0; i < list.length; i++) {
            int counter = positionCounters.remove(list[i].getPositionOnTeam());
            counter++;
            positionCounters.put(list[i].getPositionOnTeam(), counter);
        }

        maxAmountPerPositionCounter.put("C", 2);
        maxAmountPerPositionCounter.put("1B", 1);
        maxAmountPerPositionCounter.put("CI", 1);
        maxAmountPerPositionCounter.put("3B", 1);
        maxAmountPerPositionCounter.put("2B", 1);
        maxAmountPerPositionCounter.put("MI", 1);
        maxAmountPerPositionCounter.put("SS", 1);
        maxAmountPerPositionCounter.put("U", 1);
        maxAmountPerPositionCounter.put("OF", 5);
        maxAmountPerPositionCounter.put("P", 9);
        players.addAll(list);

    }

    /**
     * @return the ddm
     */
    public DraftDataManager getDdm() {
        return ddm;
    }

    /**
     * @param ddm the ddm to set
     */
    public void setDdm(DraftDataManager ddm) {
        this.ddm = ddm;
    }

    /**
     * @return the players
     */
    public ObservableList<Players> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ObservableList<Players> players) {
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the teamOwnerName
     */
    public String getTeamOwnerName() {
        return teamOwnerName;
    }

    /**
     * @param teamOwnerName the teamOwnerName to set
     */
    public void setTeamOwnerName(String teamOwnerName) {
        this.teamOwnerName = teamOwnerName;
    }

    public String toString() {
        return teamName;
    }

    public void addPlayer(Players playerToAdd) throws Exception {
        String pos = playerToAdd.getPositionOnTeam();

        //if position is not full, add player
        if(getHashMapTotal() >= 23){
            if(taxiSquad.size()<8){
            taxiSquad.add(playerToAdd);
            }
            else{
                throw new Exception();
            }
        }
        else if (positionCounters.get(pos) < maxAmountPerPositionCounter.get(pos)) {
            players.add(playerToAdd);
            int counter = positionCounters.remove(pos);
            positionCounters.put(pos, counter + 1);
        } else if (positionCounters.get(pos) == maxAmountPerPositionCounter.get(pos) && (pos.contains("1B") || pos.contains("3B"))) {
            if (positionCounters.get("CI") < maxAmountPerPositionCounter.get("CI")) {
                playerToAdd.setPositionOnTeam("CI");
                players.add(playerToAdd);
                int counter = positionCounters.remove("CI");
                positionCounters.put("CI", counter + 1);  
            } 
            else if (positionCounters.get("U") < maxAmountPerPositionCounter.get("U")) {
                playerToAdd.setPositionOnTeam("U");
                players.add(playerToAdd);
                int counter = positionCounters.remove("U");
                positionCounters.put("U", counter + 1);
            } else {
                throw new Exception();
            }
        } else if (positionCounters.get(pos) == maxAmountPerPositionCounter.get(pos) && (pos.contains("2B") || pos.contains("SS"))) {
            if (positionCounters.get("MI") < maxAmountPerPositionCounter.get("MI")) {
                playerToAdd.setPositionOnTeam("MI");
                players.add(playerToAdd);
                int counter = positionCounters.remove("MI");
                positionCounters.put("MI", counter + 1);
            } else if (positionCounters.get("U") < maxAmountPerPositionCounter.get("U")) {
                playerToAdd.setPositionOnTeam("U");
                players.add(playerToAdd);
                int counter = positionCounters.remove("U");
                positionCounters.put("U", counter + 1);
            } else {
                throw new Exception();
            } 
        } else if (positionCounters.get(pos) == maxAmountPerPositionCounter.get(pos) && (!pos.contains("P"))) {
            if (positionCounters.get("U") < maxAmountPerPositionCounter.get("U")) {
                playerToAdd.setPositionOnTeam("U");
                players.add(playerToAdd);
                int counter = positionCounters.remove("U");
                positionCounters.put("U", counter + 1);
            } else {
                throw new Exception();
            }
        }
          
        else {
            throw new Exception();
        } 

    }

    public int getHashMapTotal() {
        int total = 0;
        Set<String> keysset = positionCounters.keySet();
        String[] keys = keysset.toArray(new String[0]);
        for (int i = 0; i < keys.length; i++) {
            total = total + positionCounters.get(keys[i]);
        }
        return total;
    }

    public void removePlayer(Players playerToRemove) {
        String pos = playerToRemove.getPositionOnTeam();
        players.remove(playerToRemove);
        int counter = positionCounters.remove(pos);
        positionCounters.put(pos, counter - 1);

    }
    public ObservableList<Players> getTaxiSquad(){
        return taxiSquad;
    }

}
