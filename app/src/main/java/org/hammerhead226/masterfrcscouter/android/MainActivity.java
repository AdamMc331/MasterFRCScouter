package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.adithyasairam.masterfrcscouter.Scouting.Scouter;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import org.hammerhead226.masterfrcscouter.Utils.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";

    public static Scouter instance;

    protected MyApplication app;

    public static File csvFile;

    Button matchScout, pitScout, info, TBABtn, exportData, logOut;

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits());
        instance = new Scouter();
        app = (MyApplication) getApplication();
        csvFile = new File(Constants.getMatchDataDir(), "Matches.csv");
        setContentView(R.layout.activity_main);
        //pitScout.setOnClickListener(this);
        info = (Button) (findViewById(R.id.info));
        //info.setOnClickListener(this);
        //TBABtn.setOnClickListener(this);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void addDrawerItems() {
        String[] osArray = { "Match Scout", "Pit Scout", "About", "The Blue Alliance","Log out"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, MatchScoutActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, PitScoutActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, InfoActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, TheBlueAllianceActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ExportDataActivity.class));
                        break;
                    case 5:
                        instance.endSession();
                        Log.i(TAG, "Scouting session ended at: " + instance.sessionEndTime + ".");
                        Log.i(TAG, "Scouting session lasted: " + Math.abs(TimeUnit.MILLISECONDS.toMinutes(instance.getTotalTimeScouted())) + " minutes.");
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        break;
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
