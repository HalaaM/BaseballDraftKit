/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.error;

/**
 *
 * @author halaamenasy
 */

import wdk.data.Draft;
import wdk.gui.MessageDialog;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wdk.WDK_StartupConstants.CLOSE_BUTTON_LABEL;
import static wdk.WDK_StartupConstants.PROPERTIES_FILE_ERROR_MESSAGE;


/**
 * This class provides a response for every type of error we anticipate
 * inside of our program. It's convenient to do this in one place so
 * that we have a common format for dealing with problems.
 * 
 * @author Richard McKenna
 */
public class ErrorHandling {
    // THIS CLASS USES A SINGLETON DESIGN PATTER, WHICH IS CONVENIENT
    // BECAUSE IT NEEDS TO BE USED BY SO MANY OTHER CLASSES
    static ErrorHandling singleton;
    
    // WE'LL MAKE USE OF THIS DIALOG TO PROVIDE OUR MESSAGE FEEDBACK
    MessageDialog messageDialog;
    
    // THE PROPERTIES MANAGER WILL GIVE US THE TEXT TO DISPLAY
    PropertiesManager properties;

    /**
     * Note that this constructor is private and so can never be called
     * outside of this class.
     */
    private ErrorHandling() {
        // THIS HELPS US KEEP TRACK OF WHETHER WE NEED TO
        // CONSTRUCT THE SINGLETON OR NOT EACH TIME IT'S ACCESSED
        singleton = null;
        
        // WE ONLY NEED TO GET THE SINGLETON ONCE
        properties = PropertiesManager.getPropertiesManager();
    }
    
    /**
     * This method initializes this error handler's message dialog
     * so that it may provide feedback when errors occur.
     * 
     * @param owner The parent window for the modal message dialog.
     */
    public void initMessageDialog(Stage owner) {
        // WE'LL USE THIS DIALOG TO PROVIDE FEEDBACK WHEN ERRORS OCCUR
        messageDialog = new MessageDialog(owner, CLOSE_BUTTON_LABEL);        
    }

    /**
     * Accessor method for getting this singleton.
     * 
     * @return The singleton ErrorHandler used by the entire
     * application for responding to error conditions.
     */
    public static ErrorHandling getErrorHandler() {
        // INITIALIZE THE SINGLETON ONLY THE FIRST TIME
        if (singleton == null)
            singleton = new ErrorHandling();
        
        // BUT ALWAYS RETURN IT
        return singleton;
    }
    
    public void handleNewDraftError() {
        
    }
    
    public void handleLoadDraftError() {
        
    }
    
    public void handleSaveDraftError() {
        
    }

//    public void handleViewSchedulePageError(String pageURL) {
//        
//    }
     
//    public void handleExportCourseError(Draft draftBeingExported) {
//        
//    }
        
    public void handleExitError() {
        
    }

    public void handleUpdateDraftError() {
        
    }
    
     public void handlePropertiesFileError() {
        messageDialog.show(properties.getProperty(PROPERTIES_FILE_ERROR_MESSAGE));
    }
}

