/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import wdk.error.ErrorHandling;
import wdk.gui.WDK_GUI;

/**
 *
 * @author halaamenasy
 */
public class DraftEditController {
    private boolean enabled; 
    public void enable(boolean enableSetting) {
        enabled = enableSetting;
    }
     public void handleCourseChangeRequest(WDK_GUI gui) {
        if (enabled) {
            try {
                // UPDATE THE COURSE, VERIFYING INPUT VALUES
     //           gui.updateDraftInfo(gui.getDataManager().getDraft());
                
                // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
                // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
                // THE SAVE BUTTON IS ENABLED
                gui.getFileController().markAsEdited(gui);
            } catch (Exception e) {
                // SOMETHING WENT WRONG
                ErrorHandling eH = ErrorHandling.getErrorHandler();
                eH.handleUpdateCourseError();
            }
        }
    }
}
