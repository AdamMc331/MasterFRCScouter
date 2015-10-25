package com.adithyasairam.masterfrcscouter.Backend.Scouting;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Adi on 10/19/2015.
 */
public abstract class Match implements Serializable, Comparable<Match> {
    private static final long serialVersionUID = 201510192315L; /* Date Stamp: 2015-10-19 - 23:15 */

    public int matchNumber;
    public int teamNumber;
    public String randomID = UUID.randomUUID().toString();
    final public String scoutingPosition = (Scouter.isRedScouter ? "Red" : "Blue") + Scouter.scoutingPosition;
    final public String scouterName = Scouter.scouterName;

    public Match(int mN, int tN) {
        matchNumber = mN;
        teamNumber = tN;
        defaults(); //set default values
    }

    private static long defaultSerialVersionUID() { return 197001010001L; /* Date Stamp: 1970-01-01 - 00:01 */ }

    @Override public abstract int compareTo(@NonNull Match o);
    public abstract String[] getHeaders();
    public abstract String[] getData();
    public abstract void defaults();
    public abstract void fieldReset();
    public abstract int calculateThisRobotsAproxAutonScore();
    public abstract int calculateThisRobotsAproxTeleopScore();
    public abstract int calculateThisRobotsAproxCOOPScore();
    public abstract int calculateThisRobotsAproxTotalScore();
}
