package org.hammerhead226.masterfrcscouter.Misc;

import android.support.multidex.MultiDexApplication;

import com.adithyasairam.Utils.SimpleLogger.Logger;
import com.adithyasairam.masterfrcscouter.Backend.Scouting.Constants;
import com.bugsnag.android.Bugsnag;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import org.hammerhead226.masterfrcscouter.android.P;

import java.io.File;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Adi on 9/6/2015.
 * Source: http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
 */
public class MasterFRCScouterApplication extends MultiDexApplication { //Extends MultiDexApplication to allow for Multi Dex Support
    @Override
    public void onCreate() {
        super.onCreate();
        P.init(this);
        initFabric();
        initBugsnag();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits());
    }

    public void initBugsnag() { Bugsnag.init(this); }

    public Logger initLogger() {
        Logger logger = null;
        Logger.Config config = new Logger.Config() {
            @Override
            public String LOG_FILE_NAME() { return "LOG.log"; }

            @Override
            public File LOG_FILE_PATH() { return Constants.getInternalDataDir(); }

            @Override
            public String LOG_STREAM_URL() { return null; }

            @Override
            public int MAX_LOG_SIZE() { return 10; }

            @Override
            public int MIN_LOGGING_LEVEL() { return Logger.LogLevels.DEBUG; }

            @Override
            public boolean APPEND_TO_FILE() { return true; }
        };
        logger = new Logger.LoggerFactory().config(config).build();
        return logger;
    }
}
