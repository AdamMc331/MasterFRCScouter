package org.hammerhead226.masterfrcscouter;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.bugsnag.android.Bugsnag;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.optimizely.Optimizely;
import com.squareup.leakcanary.LeakCanary;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import org.hammerhead226.masterfrcscouter.android.P;
import org.hammerhead226.masterfrcscouter.android.R;
import org.hammerhead226.masterfrcscouter.backend.Constants;

import java.io.File;

import io.fabric.sdk.android.Fabric;
import io.smooch.core.Smooch;

/**
 * Created by Adi on 9/6/2015.
 * Source: http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
 */
public class MasterFRCScouterApplication extends MultiDexApplication { //Extends MultiDexApplication to allow for Multi Dex Support
    private static MasterFRCScouterApplication instance;
    private static File csvFile;

    @Override
    public void onCreate() {
        super.onCreate();
        P.init(this);
        initFabric();
        initOptimizely();
        initBugsnag();
        initSmooch();
        initLeakcanary();
        instance = this;
        csvFile = new File(Constants.getMatchDataDir(), "Matches.csv");
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits());
    }

    private void initOptimizely() {
        Optimizely.startOptimizelyWithAPIToken(
                getString(R.string.com_optimizely_api_key), this);
    }

    private void initBugsnag() { Bugsnag.init(this); }

    private void initSmooch() { Smooch.init(this, Constants.SMOOCH_KEY); }

    private void initLeakcanary() { LeakCanary.install(this); }

    public static File getCSVFile() { return csvFile; }

    public static void setCSVFile(File file) { csvFile = file; }

    public static MasterFRCScouterApplication getInstance() { return instance; }

    public static Context getStaticApplicationContext() { return instance.getApplicationContext(); }
}
