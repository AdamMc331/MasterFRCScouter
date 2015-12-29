package org.hammerhead226.masterfrcscouter.backend;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.adithyasairam.utils.ezio.csv.CSVWriter;

import org.hammerhead226.masterfrcscouter.android.P;
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
        saveMatchAsCSV(match);
        String number = P.number.get();
        if (number == null || number.equals("null")) { /* No number to use for sending Match over SMS, so skip it. */}
        else { sendMessageAsSMS(match, number); }
        match.fieldReset();
        Toast.makeText(c, "Match Saving Complete!", Toast.LENGTH_SHORT).show();
    }

    public static void saveMatchAsCSV(Match match) {
        CSVWriter csvWriter = new CSVWriter(MainActivity.csvFile);
        if (MainActivity.csvFile.length() < 1) {
            csvWriter.writeArray(match.getMatchDataCalculator().getHeaders());
        }
        csvWriter.writeArray(match.getMatchDataCalculator().getData());
        csvWriter.closeStream();
        DataRW.addMapEntry("csvFile", MainActivity.csvFile);
    }

    public static void sendMessageAsSMS(Match match, String number) {
        SmsManager smsManager = SmsManager.getDefault();
        String data = match.compressToString();
        if (data.length() > 140) { smsManager.sendMultipartTextMessage(number, null, smsManager.divideMessage(data), null, null); }
        else { smsManager.sendTextMessage(number, null, data, null, null); }
    }
}
