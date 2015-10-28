package org.hammerhead226.masterfrcscouter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.TBA.TBARequest;

import org.hammerhead226.masterfrcscouter.android.R;

//TODO: Work on
public class TheBlueAllianceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_blue_alliance);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return true;
    }

    /**
     * @param item The item to cache.
     * @param args Object[]: (String)args[0]: teamKey, (String)args[1]: eventKey, (String)args[2]: matchKey, (int)args[3]: year.
     */
    //"PLS SAX, PLS" - Reanna John, 2015
    @Changeable(source = TheBlueAllianceActivity.class,
            when = Changeable.When.YEARLY, priority = Changeable.Priority.LOW, type = Changeable.Type.METHOD)
    public void cacheItem(int item, Object[] args) {
        /*args format checking: */
        {
            if (args.length != 4) {
                throw new IllegalArgumentException("Args length != 4");
            }
            for (int i = 0; i <= 2; i++) {
                if ((!(args[i] instanceof String)) && args[i] != null) {
                    throw new IllegalArgumentException("Bad arg " + i);
                }
            }
            if ((!(args[3] instanceof Integer)) && args[3] != null) {
                throw new IllegalArgumentException("Bad arg 3");
            }
        }
        switch (item) {
            case 1:
                TBARequest.GetTeamEventAwards((String) args[0], (String) args[1], true);
                break;
            case 2:
                TBARequest.GetTeamEventMatches((String) args[0], (String) args[1], true);
                break;
            case 3:
                TBARequest.GetTeamEvents((String) args[0], (int) args[3], true);
                break;
            case 4:
                TBARequest.GetTeamHistoricalAwards((String) args[0], true);
                break;
            case 5:
                TBARequest.GetTeamHistoricalEvents((String) args[0], true);
                break;
            case 6:
                TBARequest.GetTeamInformation((String) args[0], true);
                break;
            case 7:
                TBARequest.GetTeamMediaLocations((String) args[0], (int) args[3], true);
                break;
            case 8:
                TBARequest.GetEventInformation((String) args[1], true);
                break;
            case 9:
                TBARequest.GetEventAwards((String) args[1], true);
                break;
            case 10:
                TBARequest.GetEventMatches((String) args[1], true);
                break;
            case 11:
                TBARequest.GetEventRankings((String) args[1], true);
                break;
            case 12:
                TBARequest.GetEventTeamsList((String) args[1], true);
                break;
            case 13:
                TBARequest.GetEvents((int) args[3], true);
                break;
            case 14:
                TBARequest.GetMatchInformation2015((String) args[2], true);
                break;
        }
    }
}
