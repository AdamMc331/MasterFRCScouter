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

import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataStorage;

import org.hammerhead226.masterfrcscouter.android.R;

public class MatchScoutSubmitActivity extends AppCompatActivity implements View.OnClickListener {
    Button submit;
    Switch poorlyDrivenRobot;
    EditText allianceScore;
    TextView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_scout_submit);
        submit = (Button) (findViewById(R.id.nextBttn));
        submit.setOnClickListener(this);
        comments = (TextView) (findViewById(R.id.commentsTextArea));
        allianceScore = (EditText) (findViewById(R.id.allianceSelectionET));
        poorlyDrivenRobot = (Switch) (findViewById(R.id.badDrivingSwitch));
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
                    DataStorage.addMatch(getApplicationContext());
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
            DataParsing.setExtraInfo(commentsText, aScore, badDriving);
        } catch (Exception e) {
        }
    }
}
