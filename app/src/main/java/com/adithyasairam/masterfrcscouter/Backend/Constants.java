package com.adithyasairam.masterfrcscouter.Backend;


import com.adithyasairam.android.androidcommons.Utils.RealStoragePathLibrary;

import org.hammerhead226.masterfrcscouter.Misc.MasterFRCScouterApplication;

import java.io.File;

/**
 * Created by Adi on 7/13/2015.
 */
public final class Constants {
    public static final String TWITTER_KEY = APIKeys.TWITTER_KEY; //Twitter API Key
    public static final String TWITTER_SECRET = APIKeys.TWITTER_SECRET; //Twitter API Secret
    public static final String SMOOCH_KEY = APIKeys.SMOOCH_KEY;
    public static final String MATCH_KEY = "org.hammerhead226.masterfrcscouter.android.MATCH";
    public static String GoogleFormsURL = "http://www.google.com"; //Put Google Forms URL here for Pit scouting

    public static File getAppDir() {
        RealStoragePathLibrary storagePathLibrary = new RealStoragePathLibrary(MasterFRCScouterApplication.getStaticApplicationContext());
        File appDir = null;
        appDir = new File(storagePathLibrary.getInbuiltStorageAppSpecificDirectoryPath());
        //appDir = new File(Environment.getExternalStorageDirectory() + "/MasterFRCScouter");
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

    public static File getInternalDataDir() {
        File appDir = getAppDir();
        File objectDir = new File(appDir.getAbsolutePath() + "/InternalData");
        objectDir.mkdirs();
        return objectDir;
    }
}
