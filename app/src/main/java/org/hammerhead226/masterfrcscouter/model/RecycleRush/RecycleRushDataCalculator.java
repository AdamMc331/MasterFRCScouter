package org.hammerhead226.masterfrcscouter.model.RecycleRush;

import org.hammerhead226.masterfrcscouter.model.MatchDataCalculator;

/**
 * Created by Adi on 12/18/2015.
 */
public class RecycleRushDataCalculator extends MatchDataCalculator<RecycleRush> {
    public RecycleRushDataCalculator(RecycleRush recycleRush) { super(recycleRush); }

    @Override
    public String[] getHeaders() {
        return new String[]{
                "Match Number",
                "Scouter Name",
                "Team Number",
                "Scouting Position",
                "Random ID",
                "Auton Mode",
                "Number of Acquired Bins in Auton",
                "Number of Auton Foul Points",
                "Num Auton Cans Attempted to Burgle",
                "Num Auton Cans Burgled",
                "Can Burgling Speed",
                "Was a COOP Set Scored in Teleop",
                "Was a COOP Stack Scored in Teleop",
                "Are Stacks Down",
                "Robot Was Poorly Driven",
                "Robot Did Cap",
                "Number of Caps",
                "Number of Stacks Scored in Teleop",
                "Tote Source",
                "Number of Teleop Foul Points",
                "This Robots Aprox Auton Score",
                "This Robots Aprox Teleop Score",
                "This Robots Aprox COOP Score",
                "This Robots Aprox Total Score",
                "Total Alliance Score",
                "Comments"
        };
    }

    @Override
    public String[] getData() {
        if (match.comments == null || match.comments.length() == 0) { match.comments = "None";}
        return new String[]{
                Integer.toString(match.matchNumber),
                match.scouterName,
                Integer.toString(match.teamNumber),
                match.scoutingPosition,
                match.randomID,
                match.autonMode,
                Integer.toString(match.numberOfAcquiredBinsInAuton),
                Integer.toString(match.numberOfAutonFoulPoints),
                Integer.toString(match.numAutonCansAttemptedToBurgle),
                Integer.toString(match.numAutonCansBurgled),
                Double.toString(match.canBurglingSpeed),
                Boolean.toString(match.wasACOOPSetScoredInTeleop),
                Boolean.toString(match.wasACOOPStackScoredInTeleop),
                Boolean.toString(match.areStacksDown),
                Boolean.toString(match.robotWasPoorlyDriven),
                Boolean.toString(match.robotDidCap),
                Integer.toString(match.numberOfCaps),
                Integer.toString(match.numberOfStacksScoredInTeleop),
                match.toteSource,
                Integer.toString(match.numberOfTeleopFoulPoints),
                Integer.toString(match.thisRobotsAproxAutonScore),
                Integer.toString(match.thisRobotsAproxTeleopScore),
                Integer.toString(match.thisRobotsAproxCOOPScore),
                Integer.toString(match.thisRobotsAproxTotalScore),
                Integer.toString(match.totalAllianceScore),
                match.comments
        };
    }

    @Override
    public int calculateThisRobotsAproxAutonScore() {
        int score = 0;
        if (match.autonMode.equals("Set Scored")) {
            score += 4;
        }
        if (match.autonMode.equals("Tote Set Scored")) {
            score += 6;
        }
        if (match.autonMode.equals("Stacked Tote Set Scored")) {
            score += 20;
        } //Buzz Buzz Buz
        score -= match.numberOfAutonFoulPoints;
        return score;
    }

    @Override
    public int calculateThisRobotsAproxTeleopScore() {
        int score = 0;
        for (RRStack r : match.stacks) {
            score += r.calculateStackScore();
        }
        score -= match.numberOfTeleopFoulPoints;
        return score;
    }

    @Override
    public int calculateThisRobotsAproxCOOPScore() {
        int score = 0;
        if (match.wasACOOPSetScoredInTeleop) {
            score += 20;
        }
        if (match.wasACOOPStackScoredInTeleop) {
            score += 40;
        }
        return score;
    }

    @Override
    public int calculateThisRobotsAproxTotalScore() {
        return calculateThisRobotsAproxAutonScore() +
                calculateThisRobotsAproxTeleopScore() +
                calculateThisRobotsAproxCOOPScore();
    }

    @Override
    public int compareTo(RecycleRush other) {
        if (match.thisRobotsAproxTotalScore > other.thisRobotsAproxTotalScore) {
            return 1;
        } else if (match.thisRobotsAproxTotalScore < other.thisRobotsAproxTotalScore) {
            return -1;
        } else return 0;
    }
}
