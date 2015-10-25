package com.adithyasairam.masterfrcscouter.Backend;

import android.content.Context;
import android.widget.Toast;

import com.adithyasairam.Utils.EzIO.CSV.CSVWriter;
import com.adithyasairam.masterfrcscouter.Backend.Scouting.RecycleRush.RecycleRush;

import org.hammerhead226.masterfrcscouter.Activities.MainActivity;

/**
 * Created by Adi on 8/30/2015.
 */
public class DataStorage {
    private DataStorage() {
        throw new IllegalAccessError("No Instances!");
    }

    public static void addMatch(RecycleRush match, Context c) {
        appendAMatchToCSVFile(match);
        match.fieldReset();
        Toast.makeText(c, "Match Saving Complete!", Toast.LENGTH_SHORT).show();
    }

    public static void appendAMatchToCSVFile(RecycleRush match) {
        CSVWriter csvWriter = new CSVWriter(MainActivity.csvFile);
        if (MainActivity.csvFile.length() <= 1) {
            csvWriter.writeArray(match.getHeaders());
        }
        csvWriter.writeArray(match.getData());
        csvWriter.closeStream();
        DataRW.addMapEntry("csvFile", MainActivity.csvFile);
    }

}
