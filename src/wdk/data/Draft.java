/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author halaamenasy
 */


public class Draft {
        // THESE COURSE DETAILS DESCRIBE WHAT'S REQUIRED BY
    // THE COURSE SITE PAGES
    
    ObservableList<Players>players;
    ObservableList <Team> team;
    ObservableList<Players> draftList;
   
    private String title;
    private List<DraftPage> pages;

    public Draft(){
       players= FXCollections.observableArrayList();
       pages=new ArrayList();
       team=FXCollections.observableArrayList();
       draftList=FXCollections.observableArrayList();
       
     
    }

    public void clearPages() {
        getPages().clear();
    }
   
    public void addPlayer(Players a) {
        players.add(a);
    
    }

    
     public List<DraftPage> getPages() {
        return pages;
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the team
     */
    public ObservableList <Team> getTeam() {
        return team;
    }

    
    /**
     * @param team the team to set
     */
//    public void setTeam(Team team) {
//        this.team = team;
//    }
    
   
    /**
     * @param pages the pages to set
     */
    public void setPages(List<DraftPage> pages) {
        this.pages = pages;
    }

    public Team findTeam(String teamName){
        for (int i=0; i<team.size();i++){
            if(team.get(i).getTeamName().equals(teamName)){
                return team.get(i);
            }
        }
        return null;
        
    }
    
    public ObservableList<Team> getTeams(){
        return team;
    }
    
    public ObservableList<Players> getDraftList(){
        return draftList;
    }
    public ObservableList<Players>getPlayers(){
        return players;
    }
    
    public void clearTeams(){
        for (Team t:team){
            ObservableList<Players> player=t.getPlayers();
             for (Players p:player){
            players.add(p);
        }
             t.getPlayers().clear();
    }
     team.clear();
}
}