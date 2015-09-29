package com.adithyasairam.masterfrcscouter.Scouting;

import org.hammerhead226.masterfrcscouter.Activities.LoginActivity;

import java.util.Calendar;

/**
 * Created by Adi on 7/13/2015.
 */

public class Scouter {
    public static String scouterName = LoginActivity.scouterName;
    public static int scoutingPosition = LoginActivity.scoutingPosition;
    public static boolean isRedScouter = LoginActivity.isRedScouter;
    public static long sessionStartTime;
    public long sessionEndTime;
    public long breakTimeStart;
    public long breakTimeEnd;
    public long breakTime;
    public Scouter() {
        sessionStartTime = System.currentTimeMillis();
        sessionEndTime = 0;
        breakTimeStart = 0;
        breakTimeEnd = 0;
        breakTime = 0;
    }
    public static void startSession() { sessionStartTime = Calendar.getInstance().getTimeInMillis(); }

    public void startABreak() {
        breakTimeStart = System.currentTimeMillis();
    }

    public void endABreak() {
        breakTimeEnd = System.currentTimeMillis();
        breakTime += (breakTimeEnd - breakTimeStart);
        breakTimeStart = 0;
        breakTimeEnd = 0;
    }

    public void endSession() {
        sessionEndTime = System.currentTimeMillis();
    }

    public long getTotalTimeScouted() { return ((sessionEndTime - sessionStartTime) - breakTime); }
}
