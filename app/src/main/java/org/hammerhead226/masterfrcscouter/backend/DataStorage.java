package org.hammerhead226.masterfrcscouter.backend;

import android.content.Context;
import android.widget.Toast;

import com.adithyasairam.utils.ezio.csv.CSVWriter;

import org.hammerhead226.masterfrcscouter.model.Match;
import org.hammerhead226.masterfrcscouter.view.activities.MainActivity;

/**
 * Created by Adi on 8/30/2015.
 */
public class DataStorage {
    private DataStorage() {
        throw new IllegalAccessError("No Instances!");
    }

    public static void addMatch(Match match, Context c) {
        CSVWriter csvWriter = new CSVWriter(MainActivity.csvFile);
        if (MainActivity.csvFile.length() <= 1) {
            csvWriter.writeArray(match.getMatchDataCalculator().getHeaders());
        }
        csvWriter.writeArray(match.getMatchDataCalculator().getData());
        csvWriter.closeStream();
        DataRW.addMapEntry("csvFile", MainActivity.csvFile);
        match.fieldReset();
        Toast.makeText(c, "Match Saving Complete!", Toast.LENGTH_SHORT).show();
    }
}
