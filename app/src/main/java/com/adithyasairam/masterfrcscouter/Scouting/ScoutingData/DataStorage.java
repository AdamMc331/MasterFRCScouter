package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import android.content.Context;
import android.widget.Toast;

import com.adithyasairam.Utils.EzIO.CSV.CSVWriter;

import org.hammerhead226.masterfrcscouter.Activities.MainActivity;
import org.hammerhead226.masterfrcscouter.Utils.DataRW;

/**
 * Created by Adi on 8/30/2015.
 */
public class DataStorage {
    public DataStorage() {
    }

    public static void addMatch(Context c) {
        appendAMatchToCSVFile();
        Match.fieldReset(); //FTA stuff
        Toast.makeText(c, "Match Saving Complete!", Toast.LENGTH_SHORT).show();
    }

    public static void appendAMatchToCSVFile() {
        CSVWriter csvWriter = new CSVWriter(MainActivity.csvFile);
        if (MainActivity.csvFile.length() <= 1) {
            csvWriter.writeArray(getHeaders());
        }
        csvWriter.writeArray(getData());
        csvWriter.closeStream();
        DataRW.addMapEntry("csvFile", MainActivity.csvFile);
    }

    private static String[] getHeaders() {
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

    private static String[] getData() {
        Match match = new Match();
        if (Match.Comments == null || Match.Comments.length() == 0) { Match.Comments = "None";}
        return new String[]{
                Integer.toString(Match.MatchNumber),
                Match.ScouterName,
                Integer.toString(Match.TeamNumber),
                Match.ScoutingPosition,
                Match.RandomID,
                Match.AutonMode,
                Integer.toString(Match.NumberOfAcquiredBinsInAuton),
                Integer.toString(Match.NumberOfAutonFoulPoints),
                Integer.toString(Match.NumAutonCansAttemptedToBurgle),
                Integer.toString(Match.NumAutonCansBurgled),
                Double.toString(Match.CanBurglingSpeed),
                Boolean.toString(Match.WasACOOPSetScoredInTeleop),
                Boolean.toString(Match.WasACOOPStackScoredInTeleop),
                Boolean.toString(Match.AreStacksDown),
                Boolean.toString(Match.RobotWasPoorlyDriven),
                Boolean.toString(Match.RobotDidCap),
                Integer.toString(Match.NumberOfCaps),
                Integer.toString(Match.NumberOfStacksScoredInTeleop),
                Match.ToteSource,
                Integer.toString(Match.NumberOfTeleopFoulPoints),
                Integer.toString(Match.ThisRobotsAproxAutonScore),
                Integer.toString(Match.ThisRobotsAproxTeleopScore),
                Integer.toString(Match.ThisRobotsAproxCOOPScore),
                Integer.toString(Match.ThisRobotsAproxTotalScore),
                Integer.toString(Match.TotalAllianceScore),
                Match.Comments
        };
    }
}
