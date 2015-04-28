/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;


import java.util.List;
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
    
    public Draft(){
       
     
    }
    private Draft draft;
    private String title;
    private Team team;
    private List<DraftPage> pages;

    public void clearPages() {
        getPages().clear();
    }
    public Draft getDraft() {
        return draft;
    }
     public List<DraftPage> getPages() {
        return pages;
    }

    /**
     * @param draft the draft to set
     */
    public void setDraft(Draft draft) {
        this.draft = draft;
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
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }
    
   
    /**
     * @param pages the pages to set
     */
    public void setPages(List<DraftPage> pages) {
        this.pages = pages;
    }

    public void removeTeam(Team teamToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removePlayer(Players playerToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
