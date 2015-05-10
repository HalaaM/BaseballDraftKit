/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import java.io.File;
import static wdk.WDK_StartupConstants.PATH_COURSES;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonValue;
import static wdk.WDK_StartupConstants.JSON_FILE_PATH_HITTERS;
import static wdk.WDK_StartupConstants.JSON_FILE_PATH_PITCHERS;
import static wdk.WDK_StartupConstants.PATH_DATA;


import wdk.data.Draft;
import wdk.data.DraftPage;
import wdk.data.Players;
import wdk.data.Team;
import wdk.gui.WDK_GUI;


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

 
    ObservableList <Players>list;
    DraftFileManager fileManager;
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
     String draftName= WDK_GUI.getGUI().fantasyTab.draftTextField.getText();
     File file = new File(PATH_DATA+draftName+".halaa");
     file.delete();
     FileOutputStream fos= new FileOutputStream(file);
     
     ObjectOutputStream oos= new ObjectOutputStream(fos);
     oos.writeObject(draftName);
     int teamSize= draftToSave.getTeams().size();
     oos.writeObject(teamSize);
     
     for (int i=0; i<teamSize;i++){
     Team team =draftToSave.getTeams().get(i);
     oos.writeObject(team.getTeamName());
     oos.writeObject(team.getTeamOwnerName());
     Players [] playerList= team.getPlayers().toArray(new Players[0]);
     oos.writeObject(playerList); 
     Players [] taxiSquad=team.getTaxiSquad().toArray(new Players[0]);
     oos.writeObject(taxiSquad);
     }
     oos.flush();
     oos.close();
    }
    
    @Override
    public void loadDraft(Draft draftToLoad, String draftPath) throws IOException {
    try{
    File file= new File(draftPath);
    FileInputStream fis= new FileInputStream(file);
    
    ObjectInputStream ois= new ObjectInputStream(fis);
    String draftName= (String) ois.readObject();
    draftToLoad.setTitle(draftName);
    
    int teamSize= (int)ois.readObject();
    
    for (int i=0;i<teamSize;i++){
        String teamName=(String)ois.readObject();
        String teamOwner= (String)ois.readObject();
        
        Players[]playerList= (Players[]) ois.readObject();
        Players [] taxiSquad= (Players[]) ois.readObject();
        Team team = new Team(teamName, teamOwner, playerList, taxiSquad);
        draftToLoad.getTeams().add(team); 
    }
    ois.close();
    }catch(ClassNotFoundException e){
        
    }
    
    
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
    public List<Players> loadHitters(String jsonFilePath) throws IOException{
        JsonObject json = loadJSONFile(jsonFilePath);
        ArrayList <Players> hitterList= new ArrayList<Players>();
        JsonArray jsonHittersArray = json.getJsonArray(JSON_HITTERS);
        for (int i = 0; i < jsonHittersArray.size(); i++){
            JsonObject jsonHitter=jsonHittersArray.getJsonObject(i);
            String team= jsonHitter.getString(JSON_HITTERSTEAM);
            String firstName= jsonHitter.getString(JSON_HITTERFIRSTNAME);
            String lastName= jsonHitter.getString(JSON_HITTERLASTNAME);
            String positions= jsonHitter.getString(JSON_QP);
            int atBats=Integer.parseInt(jsonHitter.getString(JSON_AB));
            int R= Integer.parseInt(jsonHitter.getString(JSON_R));
            int H= Integer.parseInt(jsonHitter.getString(JSON_H));
            int HR= Integer.parseInt(jsonHitter.getString(JSON_HR));
            int RBI= Integer.parseInt(jsonHitter.getString(JSON_RBI));
            int SB= Integer.parseInt(jsonHitter.getString(JSON_SB));
            String Notes= jsonHitter.getString(JSON_NOTES);
            int yearOfBirth= Integer.parseInt(jsonHitter.getString(JSON_YEAROFBIRTH_PITCHER));
            String nationality= jsonHitter.getString(JSON_NATIONOFBIRTH);
            
            
            Players hitter= new Players(team,lastName, firstName, positions, atBats, R, H, HR, RBI, SB,Notes,yearOfBirth,nationality);
            hitterList.add(hitter);
        }     
        return hitterList;
    }
    
    @Override
    public List<Players> loadPitchers(String jsonFilePath) throws IOException{
         JsonObject json = loadJSONFile(jsonFilePath);
         ArrayList <Players> pitcherList= new ArrayList<Players>();
         JsonArray jsonPitcherArray = json.getJsonArray(JSON_PITCHERS);
         for (int i = 0; i < jsonPitcherArray.size(); i++){
            JsonObject jsonPitcher=jsonPitcherArray.getJsonObject(i);
            String team= jsonPitcher.getString(JSON_PITCHERSTEAM);
            String firstName= jsonPitcher.getString(JSON_PITCHERSLASTNAME);
            String lastName= jsonPitcher.getString(JSON_PITCHERSFIRSTNAME );
            String IP= jsonPitcher.getString(JSON_IP);
            int ER=Integer.parseInt(jsonPitcher.getString(JSON_ER));
            int W= Integer.parseInt(jsonPitcher.getString(JSON_W));
            int SV= Integer.parseInt(jsonPitcher.getString(JSON_SV));
            int H= Integer.parseInt(jsonPitcher.getString(JSON_H));;
            int BB= Integer.parseInt(jsonPitcher.getString(JSON_BB));
            int K= Integer.parseInt(jsonPitcher.getString(JSON_K));
            String Notes= jsonPitcher.getString(JSON_PITCHERSNOTES);
            int yearOfBirth= Integer.parseInt(jsonPitcher.getString(JSON_YEAROFBIRTH));
            String nationality= jsonPitcher.getString(JSON_NATIONOFBIRTH);
            
            
            Players pitcher= new Players(team, lastName, firstName, Double.parseDouble(IP), ER, W, SV,  H, BB, K, Notes,  yearOfBirth,nationality);
            pitcherList.add(pitcher);
        }     
        
        return pitcherList;
        
        
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
    
    
}
