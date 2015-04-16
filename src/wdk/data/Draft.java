/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author halaamenasy
 */


public class Draft {
        // THESE COURSE DETAILS DESCRIBE WHAT'S REQUIRED BY
    // THE COURSE SITE PAGES
    
    TabPane tabPane;
    Tab player;
    Tab fantasyTeam;
    Tab fantasyStandings;
    Tab draftSummary;
    Tab MLBTeams;
    
    public Draft(){
        
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
        
        tabPane.getTabs().add(player);
        tabPane.getTabs().add(fantasyTeam);
        tabPane.getTabs().add(fantasyStandings);
        tabPane.getTabs().add(draftSummary);
        tabPane.getTabs().add(MLBTeams);
     
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
    
    public void setTabPane(TabPane tab){
        this.tabPane=tab;
        
    }
    public TabPane getTabPane(){
        return tabPane;
    }
    /**
     * @param pages the pages to set
     */
    public void setPages(List<DraftPage> pages) {
        this.pages = pages;
    }
     
}
