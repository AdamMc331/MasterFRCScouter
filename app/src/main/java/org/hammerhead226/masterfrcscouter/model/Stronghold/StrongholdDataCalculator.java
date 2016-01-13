package org.hammerhead226.masterfrcscouter.model.Stronghold;

import org.hammerhead226.masterfrcscouter.model.MatchDataCalculator;
import org.hammerhead226.masterfrcscouter.model.Stronghold.GameComponents.GameComponent;

/**
 * Created by AdiSai on 1/7/16.
 */
public class StrongholdDataCalculator extends MatchDataCalculator<Stronghold> {
    public StrongholdDataCalculator(Stronghold stronghold) { super(stronghold);}

    @Override
    public String[] getHeaders() {
        return new String[]{
                "Match Number",
                "Scouter Name",
                "Team Number",
                "Scouting Position",
                "Random ID"};
    }

    @Override
    public String[] getData() {
        return new String[]{
                Integer.toString(match.matchNumber),
                match.scouterName,
                Integer.toString(match.teamNumber),
                match.scoutingPosition,
                match.randomID};
    }

    @Override
    public int calculateThisRobotsAproxAutonScore() {
        int score = 0;
        for (GameComponent gC : match.gameComponents) {
            if (gC.getGameMode().equals("Auton")) { score += gC.getScore(); }
        }
        return score;
    }

    @Override
    public int calculateThisRobotsAproxTeleopScore() {
        int score = 0;
        for (GameComponent gC : match.gameComponents) {
            if (gC.getGameMode().equals("Teleop")) { score += gC.getScore(); }
        }
        return score;
    }

    @Override
    public int calculateThisRobotsAproxCOOPScore() { return Integer.valueOf(null); /* Not used for 2016 */ }

    @Override
    public int calculateThisRobotsAproxTotalScore() {
        return calculateThisRobotsAproxAutonScore() + calculateThisRobotsAproxTeleopScore();
    }

    @Override
    public int compareTo(Stronghold other) {
        if (match.thisRobotsAproxTotalScore > other.thisRobotsAproxTotalScore) {
            return 1;
        }
        else if (match.thisRobotsAproxTotalScore < other.thisRobotsAproxTotalScore) {
            return -1;
        }
        else { return 0; }
    }
}
