package org.hammerhead226.masterfrcscouter.model.RecycleRush;

import android.os.Parcel;
import android.os.Parcelable;

import com.adithyasairam.utils.annotations.Exclude;

import org.hammerhead226.masterfrcscouter.model.Match;
import org.hammerhead226.masterfrcscouter.model.MatchDataCalculator;

import java.util.ArrayList;
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
    
    public RecycleRush(String[] data) {
        super(data);
        autonMode = data[6];
        numberOfAcquiredBinsInAuton = Integer.parseInt(data[7]);
        numberOfAutonFoulPoints = Integer.parseInt(data[8]);
        numAutonCansAttemptedToBurgle = Integer.parseInt(data[9]);
        numAutonCansBurgled = Integer.parseInt(data[10]);
        canBurglingSpeed = Double.parseDouble(data[11]);
        wasACOOPSetScoredInTeleop = Boolean.getBoolean(data[12]);
        wasACOOPStackScoredInTeleop = Boolean.getBoolean(data[13]);
        areStacksDown = Boolean.getBoolean(data[14]);
        robotDidCap = Boolean.getBoolean(data[15]);
        numberOfCaps = Integer.parseInt(data[16]);
        numberOfStacksScoredInTeleop = Integer.parseInt(data[17]);
        toteSource = data[18];
        stacks = new ArrayList<>();
        String[] split = data[19].split(";");
        for (int i = 0; i < split.length; i++) {
            String string = split[i];
            if (i == 0) {
                int h = Integer.parseInt(string.substring(1, 2));
                boolean cOTWL = Boolean.getBoolean(string.substring(3, 4));
                boolean cOT = Boolean.getBoolean(string.substring(5, 6));
                stacks.add(new RRStack(h, cOTWL, cOT));
            }
            if (i == 1) {
                int h = Integer.parseInt(string.substring(2, 3));
                boolean cOTWL = Boolean.getBoolean(string.substring(3, 4));
                boolean cOT = Boolean.getBoolean(string.substring(5, 6));
                stacks.add(new RRStack(h, cOTWL, cOT));
            }
            else {
                //Abort
            }
        }
        numberOfTeleopFoulPoints = Integer.parseInt(data[20]);
        thisRobotsAproxAutonScore = Integer.parseInt(data[21]);
        thisRobotsAproxTeleopScore = Integer.parseInt(data[22]);
        thisRobotsAproxCOOPScore = Integer.parseInt(data[23]);
        thisRobotsAproxTotalScore = Integer.parseInt(data[24]);
        totalAllianceScore = Integer.parseInt(data[25]);
        robotWasPoorlyDriven = Boolean.getBoolean(data[26]);
        comments = data[27];
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

    @Override
    public String abstractCompressToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(autonMode);
        stringBuilder.append("\n");
        stringBuilder.append(numberOfAcquiredBinsInAuton);
        stringBuilder.append("\n");
        stringBuilder.append(numberOfAutonFoulPoints);
        stringBuilder.append("\n");
        stringBuilder.append(numAutonCansAttemptedToBurgle);
        stringBuilder.append("\n");
        stringBuilder.append(numAutonCansBurgled);
        stringBuilder.append("\n");
        stringBuilder.append(canBurglingSpeed);
        stringBuilder.append("\n");
        stringBuilder.append(wasACOOPSetScoredInTeleop);
        stringBuilder.append("\n");
        stringBuilder.append(wasACOOPStackScoredInTeleop);
        stringBuilder.append("\n");
        stringBuilder.append(areStacksDown);
        stringBuilder.append("\n");
        stringBuilder.append(robotDidCap);
        stringBuilder.append("\n");
        stringBuilder.append(numberOfCaps);
        stringBuilder.append("\n");
        stringBuilder.append(numberOfStacksScoredInTeleop);
        stringBuilder.append("\n");
        stringBuilder.append(toteSource);
        stringBuilder.append("\n");
        stringBuilder.append(stacks);
        stringBuilder.append("\n");
        stringBuilder.append(numberOfTeleopFoulPoints);
        stringBuilder.append("\n");
        stringBuilder.append(thisRobotsAproxAutonScore);
        stringBuilder.append("\n");
        stringBuilder.append(thisRobotsAproxTeleopScore);
        stringBuilder.append("\n");
        stringBuilder.append(thisRobotsAproxCOOPScore);
        stringBuilder.append("\n");
        stringBuilder.append(thisRobotsAproxTotalScore);
        stringBuilder.append("\n");
        stringBuilder.append(totalAllianceScore);
        stringBuilder.append("\n");
        stringBuilder.append(comments);
        return stringBuilder.toString();
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
        if (stackList == null) { //handle null!!
            stacks = new ArrayList<>();
        }
        else {
            stacks = stackList;
        }
        numberOfStacksScoredInTeleop = stacks.size();
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