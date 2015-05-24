/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author halaamenasy
 */
public class Players implements Serializable{

    private String firstName;
    private String lastName;
    private String positionsEligible;
    private String positionOnTeam;
    private int yearOfBirth;
    private int salary;
    private int estimatedValue;
    private String Notes;
    private String contract;
    private String team;
    private String nationality;
    private String fantasyTeam;
    
    //hitters extra qualities
    private int atBats;
    private int R; // Runs
    private int H; //Hits
    private int HR; //(Home Runs) 
    private int RBI;//Runs Batted In
    private int SB; //Stolen Bases
    private double BA; //Batting Average

    //pitchers extra qualities 
    private int W; //Wins
    private int K; //Strikeouts
    private int SV; // Saves 
    private double ERA; //Earned Run Average
    private double ER; //Earned Runs
    private double WHIP; // Walks+Hits/Innings Pitched
    private double IP; // (Innings Pitched) 
    private int BB; //Bases on Balls

    //default constructors
    public Players(){
         this.team = "";
        this.lastName = "";
        this.firstName = "";
        this.positionsEligible = "";
        this.Notes = "";
        this.nationality="";
        this.estimatedValue=0;
        this.contract="";
        this.fantasyTeam="";
    }
    //Hitters constructor
    public Players(String team, String lastName, String firstName, String positionsEligible, int atBats, int R, int H, int HR, int RBI, int SB, String Notes, int yearOfBirth, String nationality) {
        this.team = team;
        this.lastName = lastName;
        this.firstName = firstName;
        this.positionsEligible = positionsEligible;
        this.atBats = atBats;
        this.R = R;
        this.H = H;
        this.HR = HR;
        this.RBI = RBI;
        this.SB = SB;
        this.Notes = Notes;
        //Hits/At Bats
        if(atBats!=0){
        this.BA = ((int)(((double) H / atBats)*1000))/1000.0;
        }
        else{
            this.BA=0;
        }
        this.yearOfBirth=yearOfBirth;
        this.nationality=nationality;
        this.fantasyTeam="";
        this.contract="";
        this.salary=0;
    }

    //pitchers constructor
    public Players(String team, String lastName, String firstName, double IP, int ER,int W, int SV, int H, int BB, int K, String Notes, int yearOfBirth, String nationality) {
      
       this.team = team;
       this.lastName = lastName;
       this.firstName = firstName;
       this.positionsEligible = "P";
       this.IP=IP;
       this.ER=ER;
       this.W=W;
       this.SV=SV;
       this.H=H;
       this.BB=BB;
       this.K=K;
       this.Notes=Notes;
       //Earned Runs * 9/Innings Pitched
       if(IP!=0){
       this.ERA= ((int)((((double)ER *9/IP)*1000)))/1000.0;
       //Walks+Hits/Innings Pitched
       this.WHIP= ((int)((((double)W +H/IP)*1000)))/1000.0;      
       }
       else{
           this.ERA=0;
           this.WHIP=0;
       }
       this.yearOfBirth=yearOfBirth;
       this.nationality=nationality;
       this.fantasyTeam="";
       this.contract="";
       this.salary=0;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @return the positions
     */
    public String getPositionsEligible() {
        return positionsEligible;
    }


    /**
     * @return the yearOfBirth
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }


    /**
     * @return the salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @return the estimatedValue
     */
    public int getEstimatedValue() {
        return estimatedValue;
    }

    /**
     * @return the Notes
     */
    public String getNotes() {
        return Notes;
    }


    /**
     * @return the contract
     */
    public String getContract() {
        return contract;
    }

    /**
     * @return the R
     */
    public int getR() {
        return R;
    }


    /**
     * @return the HR
     */
    public int getHR() {
        return HR;
    }

    /**
     * @return the RBI
     */
    public int getRBI() {
        return RBI;
    }


    /**
     * @return the SB
     */
    public int getSB() {
        return SB;
    }

    /**
     * @return the BA
     */
    public double getBA() {

        return BA;

    }


    /**
     * @return the role
     */
    /**
     * @return the W
     */
    public int getW() {
        return W;
    }


    /**
     * @return the K
     */
    public int getK() {
        return K;
    }

    /**
     * @return the SV
     */
    public int getSV() {
        return SV;
    }


    /**
     * @return the ERA
     */
    public double getERA() {
        return ERA;
    }

    /**
     * @return the WHIP
     */
    public double getWHIP() {
        return WHIP;
    }


    /**
     * @return the team
     */
    public String getTeam() {
        return team;
    }


    /**
     * @return the atBats
     */
    public int getAtBats() {
        return atBats;
    }

    /**
     * @return the H
     */
    public int getH() {
        return H;
    }

    /**
     * @return the IP
     */
    public double getIP() {
        return IP;
    }

    /**
     * @return the ER
     */
    public double getER() {
        return ER;
    }

    /**
     * @return the BB
     */
    public int getBB() {
        return BB;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositionsEligible(String positions) {
        this.positionsEligible = positions;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * @param estimatedValue the estimatedValue to set
     */
    public void setEstimatedValue(int estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    /**
     * @param Notes the Notes to set
     */
    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(String contract) {
        this.contract = contract;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(String team) {
        this.team = team;
    }

    public String getFantasyTeam(){
        return fantasyTeam;
    }
    
    public void setFantasyTeam(String fantasyTeam){
        this.fantasyTeam=fantasyTeam;
    }
    
    public void setPositionOnTeam(String positionOnTeam){
        this.positionOnTeam=positionOnTeam;
    }
    public String getPositionOnTeam(){
        return positionOnTeam;
                
    }
}
