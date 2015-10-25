package com.adithyasairam.masterfrcscouter.Backend.Scouting.RecycleRush;

import com.adithyasairam.Utils.Annotations.Exclude;
import com.adithyasairam.masterfrcscouter.Backend.Scouting.Match;

import java.util.List;
import java.util.UUID;

/**
 * Created by Adi on 7/16/2015.
 */
public class RecycleRush extends Match {
    //Auton:
    public String autonMode;
    public int numberOfAcquiredBinsInAuton;
    public int numberOfAutonFoulPoints;

    //Can Burgling Auton Only!!!
    public int numAutonCansAttemptedToBurgle;
    public int numAutonCansBurgled;
    public double canBurglingSpeed;

    //Teleop:
    public boolean wasACOOPSetScoredInTeleop;
    public boolean wasACOOPStackScoredInTeleop;
    public boolean areStacksDown;
    public boolean robotDidCap;
    public int numberOfCaps;
    public int numberOfStacksScoredInTeleop;
    public String toteSource;
    @Exclude public List<RRStack> stacks;
    public int numberOfTeleopFoulPoints;

    //Scoring:
    public int thisRobotsAproxAutonScore;
    public int thisRobotsAproxTeleopScore;
    public int thisRobotsAproxCOOPScore;
    public int thisRobotsAproxTotalScore;
    public int totalAllianceScore;

    //Other
    public boolean robotWasPoorlyDriven;
    public String comments;

    public RecycleRush(int mN, int tN) {
        super(mN, tN);
    }

    public void putAutonInfo(String mode, int numAcquiredBins, int numFouls) {
        autonMode = mode;
        numberOfAcquiredBinsInAuton = numAcquiredBins;
        numberOfAutonFoulPoints = numFouls;
    }

    public void putCanAutonInfo(int numAttempted, int numGrabbed, double speed) {
        numAutonCansAttemptedToBurgle = numAttempted;
        numAutonCansBurgled = numGrabbed;
        canBurglingSpeed = speed;
    }

    public void putTeleopInfo(boolean coopSet, boolean coopStack, boolean stacksDown, boolean didCap, int numCaps,
                              String toteInput, int numFouls, List<RRStack> stackList) {
        wasACOOPSetScoredInTeleop = coopSet;
        wasACOOPStackScoredInTeleop = coopStack;
        areStacksDown = stacksDown;
        robotDidCap = didCap;
        numberOfCaps = numCaps;
        toteSource = toteInput;
        numberOfTeleopFoulPoints = numFouls;
        stacks = stackList;
        numberOfStacksScoredInTeleop = stackList.size();
    }
    
    public void putScores(int allianceScore) {
        thisRobotsAproxAutonScore = calculateThisRobotsAproxAutonScore();
        thisRobotsAproxTeleopScore = calculateThisRobotsAproxTeleopScore();
        thisRobotsAproxCOOPScore = calculateThisRobotsAproxCOOPScore();
        thisRobotsAproxTotalScore = calculateThisRobotsAproxTotalScore();
        totalAllianceScore = allianceScore;
    }
    
    public void putExtras(String notes, boolean badDriving) {
        comments = notes;
        robotWasPoorlyDriven = badDriving;
    }

    @Override
    public int compareTo(Match o) {
        RecycleRush other = (RecycleRush)o;
        if (thisRobotsAproxTotalScore > other.thisRobotsAproxTotalScore) {
            return 1;
        } else if (thisRobotsAproxTotalScore < other.thisRobotsAproxTotalScore) {
            return -1;
        } else return 0;
    }

    @Override
    public String[] getHeaders() {
        return new String[]{
                "Match Number",
                "Scouter Name",
                "Team Number",
                "Scouting Position",
                "Random ID",
                "Auton Mode",
                "Number of Acquired Bins in Auton",
                "Number of Auton Foul Points",
                "Num Auton Cans Attempted to Burgle",
                "Num Auton Cans Burgled",
                "Can Burgling Speed",
                "Was a COOP Set Scored in Teleop",
                "Was a COOP Stack Scored in Teleop",
                "Are Stacks Down",
                "Robot Was Poorly Driven",
                "Robot Did Cap",
                "Number of Caps",
                "Number of Stacks Scored in Teleop",
                "Tote Source",
                "Number of Teleop Foul Points",
                "This Robots Aprox Auton Score",
                "This Robots Aprox Teleop Score",
                "This Robots Aprox COOP Score",
                "This Robots Aprox Total Score",
                "Total Alliance Score",
                "Comments"
        };
    }

    @Override
    public String[] getData() {
        if (comments == null || comments.length() == 0) { comments = "None";}
        return new String[]{
                Integer.toString(matchNumber),
                scouterName,
                Integer.toString(teamNumber),
                scoutingPosition,
                randomID,
                autonMode,
                Integer.toString(numberOfAcquiredBinsInAuton),
                Integer.toString(numberOfAutonFoulPoints),
                Integer.toString(numAutonCansAttemptedToBurgle),
                Integer.toString(numAutonCansBurgled),
                Double.toString(canBurglingSpeed),
                Boolean.toString(wasACOOPSetScoredInTeleop),
                Boolean.toString(wasACOOPStackScoredInTeleop),
                Boolean.toString(areStacksDown),
                Boolean.toString(robotWasPoorlyDriven),
                Boolean.toString(robotDidCap),
                Integer.toString(numberOfCaps),
                Integer.toString(numberOfStacksScoredInTeleop),
                toteSource,
                Integer.toString(numberOfTeleopFoulPoints),
                Integer.toString(thisRobotsAproxAutonScore),
                Integer.toString(thisRobotsAproxTeleopScore),
                Integer.toString(thisRobotsAproxCOOPScore),
                Integer.toString(thisRobotsAproxTotalScore),
                Integer.toString(totalAllianceScore),
                comments
        };
    }

    @Override
    public void fieldReset() {
        matchNumber++;
        teamNumber = 0;
        randomID = UUID.randomUUID().toString();

        //Auton:
        autonMode = "";
        numberOfAcquiredBinsInAuton = 0;
        numberOfAutonFoulPoints = 0;

        //Can Burgling Auton Only!!!
        numAutonCansAttemptedToBurgle = 0;
        numAutonCansBurgled = 0;
        canBurglingSpeed = 0;

        //Teleop:
        wasACOOPSetScoredInTeleop = false;
        wasACOOPStackScoredInTeleop = false;
        areStacksDown = false;
        robotDidCap = false;
        numberOfCaps = 0;
        numberOfStacksScoredInTeleop = 0;
        toteSource = "";
        stacks = null;
        numberOfTeleopFoulPoints = 0;

        //Scoring:
        thisRobotsAproxAutonScore = 0;
        thisRobotsAproxTeleopScore = 0;
        thisRobotsAproxCOOPScore = 0;
        thisRobotsAproxTotalScore = 0;
        totalAllianceScore = 0;

        //Other
        comments = "";
        robotWasPoorlyDriven = false;
    }

    @Override
    public void defaults() {
        //Auton:
        autonMode = "";
        numberOfAcquiredBinsInAuton = 0;
        numberOfAutonFoulPoints = 0;

        //Can Burgling Auton Only!!!
        numAutonCansAttemptedToBurgle = 0;
        numAutonCansBurgled = 0;
        canBurglingSpeed = 0;

        //Teleop:
        wasACOOPSetScoredInTeleop = false;
        wasACOOPStackScoredInTeleop = false;
        areStacksDown = false;
        robotDidCap = false;
        numberOfCaps = 0;
        numberOfStacksScoredInTeleop = 0;
        toteSource = "";
        stacks = null;
        numberOfTeleopFoulPoints = 0;

        //Scoring:
        thisRobotsAproxAutonScore = 0;
        thisRobotsAproxTeleopScore = 0;
        thisRobotsAproxCOOPScore = 0;
        thisRobotsAproxTotalScore = 0;
        totalAllianceScore = 0;

        //Other
        comments = "";
        robotWasPoorlyDriven = false;
    }

    @Override
    public int calculateThisRobotsAproxAutonScore() {
        int score = 0;
        if (autonMode.equals("Set Scored")) {
            score += 4;
        }
        if (autonMode.equals("Tote Set Scored")) {
            score += 6;
        }
        if (autonMode.equals("Stacked Tote Set Scored")) {
            score += 20;
        } //Buzz Buzz Buz
        score -= numberOfAutonFoulPoints;
        return score;
    }

    @Override
    public int calculateThisRobotsAproxTeleopScore() {
        int score = 0;
        for (RRStack r : stacks) {
            score += r.calculateStackScore();
        }
        score -= numberOfTeleopFoulPoints;
        return score;
    }

    @Override
    public int calculateThisRobotsAproxCOOPScore() {
        int score = 0;
        if (wasACOOPSetScoredInTeleop) {
            score += 20;
        }
        if (wasACOOPStackScoredInTeleop) {
            score += 40;
        }
        return score;
    }

    @Override
    public int calculateThisRobotsAproxTotalScore() {
        return calculateThisRobotsAproxAutonScore() +
                calculateThisRobotsAproxTeleopScore() +
                calculateThisRobotsAproxCOOPScore();
    }
}