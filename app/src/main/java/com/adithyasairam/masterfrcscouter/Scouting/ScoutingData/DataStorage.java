package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import org.hammerhead226.masterfrcscouter.Utils.DataRW;
import org.hammerhead226.masterfrcscouter.android.MainActivity;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Adi on 8/30/2015.
 */
public class DataStorage {

    public DataStorage() {
    }

    public static void addMatch() {
        appendAMatchToCSVFile();
        appendAMatchToRealmDB();
    }

    public static void appendAMatchToCSVFile() {
        Match match = new Match();
        String[] header;
        try {
            Field[] fields = match.getClass().getFields();
            int tmp = match.getStacks().size();
            header = new String[fields.length + tmp];
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                if (f.getName().equals("Stacks")) {
                    List<RRStack> stacks = (List<RRStack>) f.get(null);
                    for (int j = 0; j < stacks.size(); j++) {
                        header[i] = "stack" + j;
                    }
                } else {
                    header[i] = f.getName();
                }
            }
            ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(new FileWriter(MainActivity.csvFile),
                    CsvPreference.EXCEL_PREFERENCE);
            csvBeanWriter.writeHeader(header);
            csvBeanWriter.write(match, header);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataRW.addMapEntry("csvFile", MainActivity.csvFile);
    }


    public static void appendAMatchToRealmDB() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MatchData matchData = realm.createObject(MatchData.class);
        realm.commitTransaction();
    }
}
