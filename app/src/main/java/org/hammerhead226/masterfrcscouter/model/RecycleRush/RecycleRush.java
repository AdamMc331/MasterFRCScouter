package org.hammerhead226.masterfrcscouter.model.RecycleRush;

import android.os.Parcel;
import android.os.Parcelable;

import com.adithyasairam.utils.annotations.Exclude;

import org.hammerhead226.masterfrcscouter.model.Match;
import org.hammerhead226.masterfrcscouter.model.MatchDataCalculator;

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

    protected RecycleRush(Parcel in) {
        super(in);
        autonMode = in.readString();
        numberOfAcquiredBinsInAuton = in.readInt();
        numberOfAutonFoulPoints = in.readInt();
        numAutonCansAttemptedToBurgle = in.readInt();
        numAutonCansBurgled = in.readInt();
        canBurglingSpeed = in.readDouble();
        wasACOOPSetScoredInTeleop = readValue(in.readInt());
        wasACOOPStackScoredInTeleop = readValue(in.readInt());
        areStacksDown = readValue(in.readInt());
        robotDidCap= readValue(in.readInt());
        numberOfCaps = in.readInt();
        numberOfStacksScoredInTeleop = in.readInt();
        toteSource = in.readString();
        stacks = in.readArrayList(null);
        numberOfTeleopFoulPoints = in.readInt();
        thisRobotsAproxAutonScore = in.readInt();
        thisRobotsAproxTeleopScore = in.readInt();
        thisRobotsAproxCOOPScore = in.readInt();
        thisRobotsAproxTotalScore = in.readInt();
        totalAllianceScore = in.readInt();
    }

    @Override
    public MatchDataCalculator<?> initMatchDataCalculator() {
        return new RecycleRushDataCalculator((RecycleRush)getMatch());
    }

    @Override
    public void init() {
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
    public void abstractWriteToParcel(Parcel out, int flags) {
        out.writeString(autonMode);
        out.writeInt(numberOfAcquiredBinsInAuton);
        out.writeInt(numberOfAutonFoulPoints);
        out.writeInt(numAutonCansAttemptedToBurgle);
        out.writeInt(numAutonCansBurgled);
        out.writeDouble(canBurglingSpeed);
        out.writeInt(writeVal(wasACOOPSetScoredInTeleop));
        out.writeInt(writeVal(wasACOOPStackScoredInTeleop));
        out.writeInt(writeVal(areStacksDown));
        out.writeInt(writeVal(robotDidCap));
        out.writeInt(numberOfCaps);
        out.writeInt(numberOfStacksScoredInTeleop);
        out.writeString(toteSource);
        out.writeList(stacks);
        out.writeInt(numberOfTeleopFoulPoints);
        out.writeInt(thisRobotsAproxAutonScore);
        out.writeInt(thisRobotsAproxTeleopScore);
        out.writeInt(thisRobotsAproxCOOPScore);
        out.writeInt(thisRobotsAproxTotalScore);
        out.writeInt(totalAllianceScore);
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
        thisRobotsAproxAutonScore = getMatchDataCalculator().calculateThisRobotsAproxAutonScore();
        thisRobotsAproxTeleopScore = getMatchDataCalculator().calculateThisRobotsAproxTeleopScore();
        thisRobotsAproxCOOPScore = getMatchDataCalculator().calculateThisRobotsAproxCOOPScore();
        thisRobotsAproxTotalScore = getMatchDataCalculator().calculateThisRobotsAproxTotalScore();
        totalAllianceScore = allianceScore;
    }
    
    public void putExtras(String notes, boolean badDriving) {
        comments = notes;
        robotWasPoorlyDriven = badDriving;
    }

    public static final Parcelable.Creator<RecycleRush> CREATOR
            = new Parcelable.Creator<RecycleRush>() {
        public RecycleRush createFromParcel(Parcel in) {
            return new RecycleRush(in);
        }

        public RecycleRush[] newArray(int size) {
            return new RecycleRush[size];
        }
    };

    private boolean readValue(int val) {
        if (val == 1) { return true; }
        return false;
    }

    private int writeVal(boolean val) {
        if (val) { return 1; }
        return 0;
    }
}