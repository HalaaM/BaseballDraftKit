/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import wdk.data.Draft;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import wdk.data.Players;

/**
 *
 * @author halaamenasy
 */
public interface DraftFileManager {
    public void                 saveDraft(Draft draftToSave) throws IOException;
    public void                 loadDraft(Draft draftToLoad, String coursePath) throws IOException;
    public void                 saveMultipleDrafts(List<Object> drafts, String filePath) throws IOException;
    public ArrayList<String>    loadOneOfTheDrafts(String filePath) throws IOException;
    public List<Players>        loadPitchers(String jsonFilePath)throws IOException; 
    public List<Players>        loadHitters(String jsonFilePath) throws IOException;
}
