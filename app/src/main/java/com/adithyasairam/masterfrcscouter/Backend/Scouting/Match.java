package com.adithyasairam.masterfrcscouter.Backend.Scouting;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Adi on 10/19/2015.
 */
public abstract class Match implements Serializable, Parcelable, Comparable<Match> {
    private static final long serialVersionUID = 201510192315L; /* Date Stamp: 2015-10-19 - 23:15 */

    public int matchNumber;
    public int teamNumber;
    public String randomID = UUID.randomUUID().toString();
    final public String scoutingPosition = (Scouter.isRedScouter ? "Red" : "Blue") + Scouter.scoutingPosition;
    final public String scouterName = Scouter.scouterName;

    public Match(int mN, int tN) {
        matchNumber = mN;
        teamNumber = tN;
        init(); //set default values
    }

    protected Match(Parcel in) {
        matchNumber = in.readInt();
        teamNumber = in.readInt();
        randomID = in.readString();
    }

    private static long defaultSerialVersionUID() { return 197001010001L; /* Date Stamp: 1970-01-01 - 00:01 */ }

    public abstract String[] getHeaders();
    public abstract String[] getData();
    public abstract void init();
    public abstract void fieldReset();
    public abstract int calculateThisRobotsAproxAutonScore();
    public abstract int calculateThisRobotsAproxTeleopScore();
    public abstract int calculateThisRobotsAproxCOOPScore();
    public abstract int calculateThisRobotsAproxTotalScore();
    @Override public abstract int compareTo(Match o);
    @Override public void writeToParcel(Parcel out, int flags) {
        out.writeInt(matchNumber);
        out.writeInt(teamNumber);
        out.writeString(randomID);
    }
    @Override public int describeContents() { return 0; }
}
