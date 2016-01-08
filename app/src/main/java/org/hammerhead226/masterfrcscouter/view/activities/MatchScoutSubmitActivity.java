package org.hammerhead226.masterfrcscouter.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.adithyasairam.android.android_commons.Intents;
import com.adithyasairam.utils.annotations.Changeable;

import org.hammerhead226.masterfrcscouter.android.R;
import org.hammerhead226.masterfrcscouter.backend.Constants;
import org.hammerhead226.masterfrcscouter.backend.DataStorage;
import org.hammerhead226.masterfrcscouter.model.Match;

import butterknife.Bind;
import butterknife.ButterKnife;

@Changeable(source = AutonMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class MatchScoutSubmitActivity extends AppCompatActivity implements View.OnClickListener {

    /*Type*/ Match match;
    @Bind(R.id.nextBttn) Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            match = Intents.IntentProperties.getSerializable(Constants.MATCH_KEY, getIntent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_match_scout_submit);
        ButterKnife.bind(this);
        submit.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_match_scout_submit, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBttn:
                try {
                    parseData();
                    DataStorage.addMatch(match, getApplicationContext());
                    startActivity(new Intent(this, MatchScoutActivity.class));
                    finish();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private void parseData() {
        try {

        } catch (Exception e) { e.printStackTrace(); }
    }
}
