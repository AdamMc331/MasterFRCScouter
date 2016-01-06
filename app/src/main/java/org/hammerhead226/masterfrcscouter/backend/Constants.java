package org.hammerhead226.masterfrcscouter.backend;


import com.adithyasairam.android.android_commons.RealStoragePathLibrary;

import org.hammerhead226.masterfrcscouter.MasterFRCScouterApplication;
import org.hammerhead226.masterfrcscouter.android.P;

import java.io.File;

/**
 * Created by Adi on 7/13/2015.
 */
public final class Constants {
    public static final Boolean SUPERUSER = P.superuser.get(); //Is Advanced User?
    public static final String TWITTER_KEY = "tGe6TbDafn8TvzJBfx77gKm1m"; //Twitter API Key
    public static final String TWITTER_SECRET = "Ze9BvF5OBjgsAcMo7hUnmPwHRdu3Uni5Mw2ZGVnbfbf3Uy0cGE"; //Twitter API Secret
    public static final String SMOOCH_KEY = "8o14mwv1miubio0e3zgx2o7qd"; //Smooch API Key
    public static final String MATCH_KEY = "org.hammerhead226.masterfrcscouter.android.MATCH"; //Key for Intent Data transfer
    public static final String MATCH_HEADER = "Match-HEAD"; //Header used for sending Matches
    public static final String MATCH_FOOTER = "Match-FOOT"; //Footer used for sending Matches
    public static String GoogleFormsURL = "http://www.google.com"; //Google Forms URL for Pit scouting [Placeholder]

    public static File getAppDir() {
        RealStoragePathLibrary storagePathLibrary = new RealStoragePathLibrary(MasterFRCScouterApplication.getStaticApplicationContext());
        File appDir = new File(storagePathLibrary.getInbuiltStorageAppSpecificDirectoryPath());
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
