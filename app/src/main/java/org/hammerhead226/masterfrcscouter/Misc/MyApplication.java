package org.hammerhead226.masterfrcscouter.Misc;

import android.support.multidex.MultiDexApplication;

import com.adithyasairam.Utils.SimpleLogger.Logger;
import com.adithyasairam.masterfrcscouter.Backend.Scouting.Constants;

import org.hammerhead226.masterfrcscouter.android.P;

import java.io.File;

/**
 * Created by Adi on 9/6/2015.
 * Source: http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
 */
public class MyApplication extends MultiDexApplication { //Extends MultiDexApplication to allow for Multi Dex Support
    @Override
    public void onCreate() {
        super.onCreate();
        P.init(this);
    }

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
