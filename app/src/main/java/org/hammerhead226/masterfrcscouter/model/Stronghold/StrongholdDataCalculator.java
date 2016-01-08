package org.hammerhead226.masterfrcscouter.model.Stronghold;

import org.hammerhead226.masterfrcscouter.model.MatchDataCalculator;

/**
 * Created by AdiSai on 1/7/16.
 */
public class StrongholdDataCalculator extends MatchDataCalculator<Stronghold> {
    public StrongholdDataCalculator(Stronghold stronghold) { super(stronghold);}

    @Override
    public String[] getHeaders() {
        return new String[0];
    }

    @Override
    public String[] getData() {
        return new String[0];
    }

    @Override
    public int calculateThisRobotsAproxAutonScore() {
        return 0;
    }

    @Override
    public int calculateThisRobotsAproxTeleopScore() {
        return 0;
    }

    @Override
    public int calculateThisRobotsAproxCOOPScore() {
        return 0;
    }

    @Override
    public int calculateThisRobotsAproxTotalScore() {
        return 0;
    }

    @Override
    public int compareTo(Stronghold other) {
        return 0;
    }
}
