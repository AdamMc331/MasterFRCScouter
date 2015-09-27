package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import android.content.Context;
import android.widget.Toast;

import com.adithyasairam.Utils.EzIO.CSV.CSVWriter;

import org.hammerhead226.masterfrcscouter.Utils.DataRW;
import org.hammerhead226.masterfrcscouter.android.MainActivity;

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
                "MatchNumber",
                "ScouterName",
                "TeamNumber",
                "ScoutingPosition",
                "RandomID",
                "AutonMode",
                "NumberOfAcquiredBinsInAuton",
                "NumberOfAutonFoulPoints",
                "NumAutonCansAttemptedToBurgle",
                "NumAutonCansBurgled",
                "CanBurglingSpeed",
                "WasACOOPSetScoredInTeleop",
                "WasACOOPStackScoredInTeleop",
                "AreStacksDown",
                "RobotWasPoorlyDriven",
                "RobotDidCap",
                "NumberOfCaps",
                "NumberOfStacksScoredInTeleop",
                "ToteSource",
                "NumberOfTeleopFoulPoints",
                "ThisRobotsAproxAutonScore",
                "ThisRobotsAproxTeleopScore",
                "ThisRobotsAproxCOOPScore",
                "ThisRobotsAproxTotalScore",
                "TotalAllianceScore",
                "Comments"
        };
    }

    private static String[] getData() {
        Match match = new Match();
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
