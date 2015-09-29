package org.hammerhead226.masterfrcscouter.Utils;

import android.support.multidex.MultiDexApplication;

import org.hammerhead226.masterfrcscouter.android.P;

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
}
