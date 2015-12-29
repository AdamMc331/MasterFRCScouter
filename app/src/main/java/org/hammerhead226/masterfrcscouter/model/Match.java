package org.hammerhead226.masterfrcscouter.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.hammerhead226.masterfrcscouter.backend.Constants;
import org.hammerhead226.masterfrcscouter.backend.Scouter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Adi on 10/19/2015.
 */
public abstract class Match implements Serializable, Parcelable {
    private static final long serialVersionUID = 201510192315L; /* Date Stamp: 2015-10-19 - 23:15 */
    private Match instance;
    private MatchDataCalculator<?> matchDataCalculator;
    public int matchNumber;
    public int teamNumber;
    public String randomID = UUID.randomUUID().toString();
    final public String scoutingPosition = (Scouter.isRedScouter ? "Red" : "Blue") + Scouter.scoutingPosition;
    final public String scouterName = Scouter.scouterName;

    public Match(int mN, int tN) {
        instance = this;
        matchDataCalculator = initMatchDataCalculator();
        matchNumber = mN;
        teamNumber = tN;
        init(); //set default values
    }
    protected Match(Parcel in) {
        instance = (Match)in.readSerializable();
        matchDataCalculator = (MatchDataCalculator<?>)in.readSerializable();
        matchNumber = in.readInt();
        teamNumber = in.readInt();
        randomID = in.readString();
    }

    public Match getMatch() { return instance; }
    public MatchDataCalculator<?> getMatchDataCalculator() { return matchDataCalculator; }
    public String compressToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.MATCH_HEADER);
        stringBuilder.append("\n");
        stringBuilder.append(abstractCompressToString());
        stringBuilder.append("\n");
        stringBuilder.append(Constants.MATCH_FOOTER);
        return stringBuilder.toString();
    }
    public abstract MatchDataCalculator<?> initMatchDataCalculator();
    public abstract void init();
    public abstract void fieldReset();
    protected abstract String abstractCompressToString();
    protected abstract void abstractWriteToParcel(Parcel out, int flags);
    @Override public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(instance);
        out.writeSerializable(matchDataCalculator);
        out.writeInt(matchNumber);
        out.writeInt(teamNumber);
        out.writeString(randomID);
        abstractWriteToParcel(out, flags);
    }
    @Override public int describeContents() { return 0; }
}
