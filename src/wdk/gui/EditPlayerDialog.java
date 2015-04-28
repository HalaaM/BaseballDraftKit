/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import wdk.data.DraftDataManager;
import wdk.data.Players;

/**
 *
 * @author halaamenasy
 */
public class EditPlayerDialog {
    
     DraftDataManager ddm;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label fantasyTeamLabel;
    ComboBox fantasyTeamComboBox;
    Label positionLabel;
    ComboBox positionComboBox;
    Label contractLabel;
    ComboBox contractComboBox;
    Label salaryLabel;
    TextField salaryTextField;
    
    Label playerNameLabel;
    Label positionsEligibleLabel;
    Image playerImage;
    Image playerFlag;
    
    
    
    Button completeButton;
    Button cancelButton;
    
}
