/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;


import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wdk.WDK_StartupConstants.JSON_FILE_PATH_HITTERS;
import static wdk.WDK_StartupConstants.JSON_FILE_PATH_PITCHERS;
import wdk.file.DraftFileManager;
import wdk.file.JsonDraftFileManager;
/**
 *
 * @author halaamenasy
 */

public class DraftDataManager implements Serializable{
    
    

    // THIS IS THE COURSE BEING EDITED
    Draft draft;
    // THIS IS THE UI, WHICH MUST BE UPDATED
    // WHENEVER OUR MODEL'S DATA CHANGES
    DraftDataView view;
    
    // THIS HELPS US LOAD THINGS FOR OUR COURSE
    DraftFileManager fileManager;
   
    ObservableList <Players>list;
    
    public DraftDataManager(DraftDataView initView, JsonDraftFileManager fileManager) {
        this.fileManager=fileManager;
        view = initView;
        draft = new Draft();
        initData();
        
    }
    
    public void initData() {
        list= FXCollections.observableArrayList();
        
        try{
        list.addAll(fileManager.loadHitters(JSON_FILE_PATH_HITTERS));
        list.addAll(fileManager.loadPitchers(JSON_FILE_PATH_PITCHERS));
        }
        catch(IOException e){
   
        }
    }
//    
    /**
     * Accessor method for getting the Course that this class manages.
     */
    public Draft getDraft() {
        return draft;
    }
    public void setDraft(Draft draft){
        this.draft=draft;
    }
    
    
    /**
     * Accessor method for getting the file manager, which knows how
     * to read and write course data from/to files.
     */
    public DraftFileManager getFileManager() {
        return fileManager;
    }

    /**
     * Resets the course to its default initialized settings, triggering
     * the UI to reflect these changes.
     */
    public void reset() {
        // CLEAR ALL THE COURSE VALUES
      
        draft.clearPages();
        
        // AND THEN FORCE THE UI TO RELOAD THE UPDATED COURSE
        view.reloadDraft(draft);
    }
    
    public ObservableList getPlayers(){
        return list;
    }
    
 
    
}
