/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;


import wdk.file.DraftFileManager;
/**
 *
 * @author halaamenasy
 */

public class DraftDataManager {
    
    Draft draft;
     // THIS IS THE UI, WHICH MUST BE UPDATED
    // WHENEVER OUR MODEL'S DATA CHANGES
    DraftDataView view;
    
    // THIS HELPS US LOAD THINGS FOR OUR COURSE
    DraftFileManager fileManager;
    
    public void reset() {
        // CLEAR ALL THE COURSE VALUES
      
        draft.clearPages();
        
        // AND THEN FORCE THE UI TO RELOAD THE UPDATED COURSE
        view.reloadDraft(draft);
    }
    
    public Draft getDraft() {
        return draft;
    }
    
    
}
