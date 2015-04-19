/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import static wdk.WDK_StartupConstants.PATH_COURSES;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonValue;


import wdk.data.Draft;
import wdk.data.DraftPage;


/**
 *
 * @author halaamenasy
 */
public class JsonDraftFileManager implements DraftFileManager {
       // JSON FILE READING AND WRITING CONSTANTS
    String JSON_DRAFT = "Draft";
    String JSON_HITTERS = "Hitters";
    String JSON_HITTERSTEAM = "TEAM";
    String JSON_HITTERLASTNAME = "LAST_NAME";
    String JSON_HITTERFIRSTNAME = "FIRST_NAME";
    String JSON_QP = "QP";
    String JSON_AB = "AB";
    String JSON_R = "R";
    String JSON_H = "H";
    String JSON_HR = "HR";
    String JSON_RBI = "RBI";
    String JSON_SB = "SB";
    String JSON_NOTES = "NOTES";
    String JSON_YEAROFBIRTH= "YEAR_OF_BIRTH";
    String JSON_NATIONOFBIRTH = "NATION_OF_BIRTH";
    
    String JSON_PITCHERS = "Pitchers";
    String JSON_PITCHERSTEAM = "TEAM";
    String JSON_PITCHERSLASTNAME = "LAST_NAME";
    String JSON_PITCHERSFIRSTNAME = "FIRST_NAME";
    String JSON_IP = "IP";
    String JSON_ER = "ER";
    String JSON_W = "W";
    String JSON_SV= "SV";
    String JSON_PITCHERS_H = "H";
    String JSON_BB = "BB";
    String JSON_K = "K";
    String JSON_PITCHERSNOTES = "NOTES";
    String JSON_YEAROFBIRTH_PITCHER="YEAR_OF_BIRTH";
    String JSON_NATIONOFBIRTH_PITCHER="NATION_OF_BIRTH";
    String JSON_EXT = ".json";
    String SLASH = "/";

 
    /**
     * This method saves all the data associated with a course to
     * a JSON file.
     * 
     * @param draftToSave
     * @param courseToSave The course whose data we are saving.
     * 
     * @throws IOException Thrown when there are issues writing
     * to the JSON file.
     */
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
        // BUILD THE FILE PATH
        String draftListing = "" + draftToSave.getDraft();
        String jsonFilePath = PATH_COURSES + SLASH + draftListing + JSON_EXT;
        
        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);  
        
        // MAKE A JSON ARRAY FOR THE PAGES ARRAY
        JsonArray pagesJsonArray = makePagesJsonArray(draftToSave.getPages());
         

    }



    @Override
    public void saveMultipleDrafts(List<Object> drafts, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> loadOneOfTheDrafts(String filePath) throws IOException {
      
        return null;
    }
    
    
     public JsonArray makePagesJsonArray(List<DraftPage> data) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (DraftPage cP : data) {
           jsb.add(cP.toString());
        }
        JsonArray jA = jsb.build();
        return jA;        
    }
     
     private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }    
     
    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
        JsonObject json = loadJSONFile(jsonFilePath);
        ArrayList<String> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        for (JsonValue jsV : jsonArray) {
            items.add(jsV.toString());
        }
        return items;
    }

    @Override
    public void loadDraft(Draft draftToLoad, String coursePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
