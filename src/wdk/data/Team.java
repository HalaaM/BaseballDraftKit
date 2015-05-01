/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.collections.ObservableList;

/**
 *
 * @author halaamenasy
 */
public class Team {
    
    private DraftDataManager ddm;
    private ObservableList <Players> players;
    private String teamName;
    private String teamOwnerName;
    

    

    public Team(){
       // this.players=ddm.getPlayers();
        this.teamName="";
        this.teamOwnerName="";
        
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
    
    public void addPlayer(Players playerToAdd){
        players.add(playerToAdd);
             
    }
    
    public void removePlayer(Players playerToRemove){
        players.remove(playerToRemove);
    }
    
}
