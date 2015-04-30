/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import static wdk.WDK_StartupConstants.PATH_SITES;
import wdk.data.Draft;
import wdk.data.DraftPage;
/**
 *
 * @author halaamenasy
 */

public class DraftExporter {
    
   public static final String SLASH = "/";
        
    // THESE ARE THE POSSIBLE SITE PAGES OUR SCHEDULE PAGE
    // MAY NEED TO LINK TO
    public static String AVAILABLEPLAYERS_PAGE = "index.html";
    public static String FANTASYTEAMS_PAGE = "fantasyteams.html";
    public static String FANTASYSTANDINGS_PAGE = "fantasystandings.html";
    public static String DRAFTSUMMARY_PAGE = "draftsummary.html";
    public static String MLBTEAMS_PAGE = "mlbteams.html";
    
    // THESE ARE THE DIRECTORIES WHERE OUR BASE SCHEDULE
    // FILE IS AND WHERE OUR COURSE SITES WILL BE EXPORTED TO
    String baseDir;
    String sitesDir;

     
    public DraftExporter(String initBaseDir, String initSitesDir) {
        baseDir = initBaseDir;
        sitesDir = initSitesDir;
    }
    
     public String getPageURLPath(Draft draft, DraftPage cP) {
        String urlPath = PATH_SITES + draft
                + SLASH + this.getLink(cP);
        File webPageFile = new File(urlPath);
        try {
            URL pageURL = webPageFile.toURI().toURL();
            return pageURL.toString();
        } catch (MalformedURLException murle) {
            return null;
        }
    }
     
     private String getLink(DraftPage page) {
        if (page == DraftPage.AVAILABLEPLAYERS) {
            return AVAILABLEPLAYERS_PAGE;
        } else if (page == DraftPage.FANTASYTEAMS) {
            return FANTASYTEAMS_PAGE;
        } else if (page == DraftPage.FANTASYSTANDINGS) {
            return FANTASYSTANDINGS_PAGE;
        } else if (page == DraftPage.DRAFTSUMMARY) {
            return DRAFTSUMMARY_PAGE;
        } else {
            return MLBTEAMS_PAGE;
        }
    }
    
}
