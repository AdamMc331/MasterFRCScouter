package org.hammerhead226.masterfrcscouter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.android.androidcommons.Utils.Intents;
import com.adithyasairam.masterfrcscouter.Backend.Constants;
import com.adithyasairam.masterfrcscouter.Backend.DataStorage;
import com.adithyasairam.masterfrcscouter.Backend.Scouting.RecycleRush.RecycleRush;

import org.hammerhead226.masterfrcscouter.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@Changeable(source = AutonMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class MatchScoutSubmitActivity extends AppCompatActivity implements View.OnClickListener {

    RecycleRush match;

    @Bind(R.id.badDrivingSwitch)
    Switch poorlyDrivenRobot;
    @Bind(R.id.allianceSelectionET)
    EditText allianceScore;
    @Bind(R.id.commentsTextArea)
    TextView comments;
    @Bind(R.id.nextBttn)
    Button submit;

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
            String commentsText = comments.getText().toString();
            int aScore = Integer.parseInt(allianceScore.getText().toString());
            boolean badDriving = poorlyDrivenRobot.isChecked();
            match.putExtras(commentsText, badDriving);
            match.putScores(aScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
