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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author halaamenasy
 */
public class Team implements Serializable{
    
    private DraftDataManager ddm;
    private ObservableList <Players> players;
    private String teamName;
    private String teamOwnerName;
   
    //2 for C;       1 each for 1B, CI, 3B, 2B, MI, SS, & U;      5 for OF;     9 for P
    HashMap<String,Integer> positionCounters;
    HashMap<String, Integer> maxAmountPerPositionCounter;

    public Team(){
       // this.players=ddm.getPlayers();
        this.teamName="";
        this.teamOwnerName="";
        players= FXCollections.observableArrayList();
        positionCounters= new HashMap();
        maxAmountPerPositionCounter=new HashMap();
        
       positionCounters.put("C",0);
       positionCounters.put("1B",0);
       positionCounters.put("CI",0);
       positionCounters.put("3B",0);
       positionCounters.put("2B",0);
       positionCounters.put("MI",0);
       positionCounters.put("SS",0);
       positionCounters.put("U",0);
       positionCounters.put("OF",0);
       positionCounters.put("P",0);
       
       maxAmountPerPositionCounter.put("C",2); 
       maxAmountPerPositionCounter.put("1B",1);
       maxAmountPerPositionCounter.put("CI",1);
       maxAmountPerPositionCounter.put("3B",1);
       maxAmountPerPositionCounter.put("2B",1);
       maxAmountPerPositionCounter.put("MI",1);
       maxAmountPerPositionCounter.put("SS",1);
       maxAmountPerPositionCounter.put("U",1);
       maxAmountPerPositionCounter.put("OF",5);
       maxAmountPerPositionCounter.put("P",9);
        
 
    }
    public Team(String teamName, String teamOwnerName){
        this.teamName=teamName;
        this.teamOwnerName=teamOwnerName;
        
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
    public ObservableList <Players> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ObservableList <Players> players) {
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
    
    public String toString(){
        return teamName;
    }
    
    public void addPlayer(Players playerToAdd) throws Exception{
        String pos=playerToAdd.getPositions();
        
        //if position is not full, add player
        if (positionCounters.get(pos)<maxAmountPerPositionCounter.get(pos)){
        players.add(playerToAdd);
        int counter= positionCounters.get(pos);
        positionCounters.put(pos, counter+1);
        }    
        else{
           throw new Exception();
        }
    }
    
    public void removePlayer(Players playerToRemove){
         String pos=playerToRemove.getPositions();
         players.remove(playerToRemove);
         int counter= positionCounters.get(pos);
         positionCounters.put(pos, counter-1);
 
        
    }
    
}
