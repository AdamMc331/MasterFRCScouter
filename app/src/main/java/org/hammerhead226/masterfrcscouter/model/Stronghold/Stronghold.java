package org.hammerhead226.masterfrcscouter.model.Stronghold;

import android.os.Parcel;

import org.hammerhead226.masterfrcscouter.model.Match;
import org.hammerhead226.masterfrcscouter.model.MatchDataCalculator;

/**
 * Created by AdiSai on 1/7/16.
 */
public class Stronghold extends Match {
    public Stronghold(int mN, int tN) {
        super(mN, tN);
    }

    public Stronghold(String[] data) {
        super(data);
    }

    protected Stronghold(Parcel in) {
        super(in);
    }

    @Override
    public MatchDataCalculator<?> initMatchDataCalculator() {
        return new StrongholdDataCalculator((Stronghold)getMatch());
    }

    @Override
    public void init() {

    }

    @Override
    public void fieldReset() {

    }

    @Override
    protected String abstractCompressToString() {
        return null;
    }

    @Override
    protected void abstractWriteToParcel(Parcel out, int flags) {

    }
}
