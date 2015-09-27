package org.hammerhead226.masterfrcscouter.Utils;


import android.os.Environment;

import java.io.File;

/**
 * Created by Adi on 7/13/2015.
 */
public final class Constants {
    public static final String TWITTER_KEY = APIKeys.TWITTER_KEY; //Twitter API Key
    public static final String TWITTER_SECRET = APIKeys.TWITTER_SECRET; //Twitter API Secret
    public static String GoogleFormsURL = ""; //Put Google Forms URL here for Pit scouting

    public static File getAppDir() {
        File appDir = new File(Environment.getExternalStorageDirectory() + "/MasterFRCScouter");
        appDir.mkdirs();
        return appDir;
    }

    public static File getMatchDataDir() {
        File appDir = getAppDir();
        File matchDir = new File(appDir.getAbsolutePath() + "/MatchData");
        matchDir.mkdirs();
        return matchDir;
    }

    public static File getMatchDataBackupDir() {
        File appDir = getAppDir();
        File matchDir = new File(appDir.getAbsolutePath() + "/MatchDataBackup");
        matchDir.mkdirs();
        return matchDir;
    }

    public static File getObjectDataDir() {
        File appDir = getAppDir();
        File objectDir = new File(appDir.getAbsolutePath() + "/ObjectData");
        objectDir.mkdirs();
        return objectDir;
    }
}
