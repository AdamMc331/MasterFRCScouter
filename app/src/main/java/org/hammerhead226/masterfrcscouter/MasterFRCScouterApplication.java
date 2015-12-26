package org.hammerhead226.masterfrcscouter;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.bugsnag.android.Bugsnag;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import org.hammerhead226.masterfrcscouter.android.P;
import org.hammerhead226.masterfrcscouter.backend.Constants;

import io.fabric.sdk.android.Fabric;
import io.smooch.core.Smooch;

/**
 * Created by Adi on 9/6/2015.
 * Source: http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
 */
public class MasterFRCScouterApplication extends MultiDexApplication { //Extends MultiDexApplication to allow for Multi Dex Support
    private static MasterFRCScouterApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        P.init(this);
        initFabric();
        initBugsnag();
        initSmooch();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits());
    }

    private void initBugsnag() { Bugsnag.init(this); }

    private void initSmooch() { Smooch.init(this, Constants.SMOOCH_KEY); }

    public static MasterFRCScouterApplication getInstance() { return instance; }

    public static Context getStaticApplicationContext() { return instance.getApplicationContext(); }
}
