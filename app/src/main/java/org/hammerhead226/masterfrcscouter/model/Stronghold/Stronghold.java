package org.hammerhead226.masterfrcscouter.model.Stronghold;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.Bagger;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import org.hammerhead226.masterfrcscouter.model.Match;
import org.hammerhead226.masterfrcscouter.model.MatchDataCalculator;
import org.hammerhead226.masterfrcscouter.model.Stronghold.GameComponents.GameComponent;
import org.hammerhead226.masterfrcscouter.model.Stronghold.GameComponents.GameComponentArrayBagger;

/**
 * Created by AdiSai on 1/7/16.
 */
@ParcelablePlease
public class Stronghold extends Match implements Parcelable {
    @Bagger(GameComponentArrayBagger.class) public GameComponent[] gameComponents;
    //Scoring:
    public int thisRobotsAproxAutonScore;
    public int thisRobotsAproxTeleopScore;
    public int thisRobotsAproxTotalScore;
    public int totalAllianceScore;

    public Stronghold(int mN, int tN) {
        super(mN, tN);
    }

    public Stronghold(String[] data) {
        super(data);
    }

    @Override
    public MatchDataCalculator<?> initMatchDataCalculator() {
        return new StrongholdDataCalculator((Stronghold)getMatch());
    }

    @Override
    public void init() { gameComponents = new GameComponent[30]; }

    @Override
    public void fieldReset() {
        gameComponents = null;
    }

    @Override
    protected String abstractCompressToString() {
        StringBuilder sB = new StringBuilder();
        for (GameComponent gC : gameComponents) { sB.append(gC); }
        return sB.toString();
    }

    public void putScores(int allianceScore) {
        thisRobotsAproxAutonScore = getMatchDataCalculator().calculateThisRobotsAproxAutonScore();
        thisRobotsAproxTeleopScore = getMatchDataCalculator().calculateThisRobotsAproxTeleopScore();
        thisRobotsAproxTotalScore = getMatchDataCalculator().calculateThisRobotsAproxTotalScore();
        totalAllianceScore = allianceScore;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        StrongholdParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Stronghold> CREATOR = new Creator<Stronghold>() {
        public Stronghold createFromParcel(Parcel source) {
            Stronghold target = new Stronghold(source.readInt(), source.readInt());
            StrongholdParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Stronghold[] newArray(int size) {
            return new Stronghold[size];
        }
    };
}
