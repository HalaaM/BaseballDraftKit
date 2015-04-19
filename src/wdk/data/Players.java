/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.util.List;

/**
 *
 * @author halaamenasy
 */
public class Players {

    private String firstName;
    private String lastName;
    private String positions;
    private int yearOfBirth;
    private int salary;
    private String estimatedValue;
    private String Notes;
    private String contract;
    private String team;

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

    //Hitters constructor
    public Players(String team, String lastName, String firstName, String positions, int atBats, int R, int H, int HR, int RBI, int SB, String Notes, int yearOfBirth) {
        this.team = team;
        this.lastName = lastName;
        this.firstName = firstName;
        this.positions = positions;
        this.atBats = atBats;
        this.R = R;
        this.H = H;
        this.HR = HR;
        this.RBI = RBI;
        this.SB = SB;
        this.Notes = Notes;
        //Hits/At Bats
        if(atBats!=0){
        this.BA = (double) H / atBats;
        }
        else{
            this.BA=0;
        }
        this.yearOfBirth=yearOfBirth;

    }

    //pitchers constructor
    public Players(String team, String lastName, String firstName, double IP, int ER,int W, int SV, int H, int BB, int K, String Notes, int yearOfBirth) {
      
       this.team = team;
       this.lastName = lastName;
       this.firstName = firstName;
       this.positions = "P";
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
       this.ERA= (double)ER *9/IP;
       //Walks+Hits/Innings Pitched
       this.WHIP= (double)W +H/IP;      
       }
       else{
           this.ERA=0;
           this.WHIP=0;
       }
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
    public String getPositions() {
        return positions;
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
    public String getEstimatedValue() {
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

}
