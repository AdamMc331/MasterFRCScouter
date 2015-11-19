package org.hammerhead226.masterfrcscouter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.android.androidcommons.Utils.Intents;
import com.adithyasairam.masterfrcscouter.Backend.Constants;
import com.adithyasairam.masterfrcscouter.Backend.Scouting.RecycleRush.RecycleRush;

import org.hammerhead226.masterfrcscouter.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Changeable(source = AutonMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH, changeType = Changeable.ChangeType.REMOVE)
public class CanBurgeledAutonActivity extends AppCompatActivity {
    RecycleRush match;

    @Bind(R.id.numCansAttempted) EditText numCansAttempted;
    @Bind(R.id.numCansGrabbed) EditText numCansGrabbed;
    @Bind(R.id.canBurglingSpeed) EditText canBurglingSpeed;
    //@Bind(R.id.donePlsButton) Button donePlsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            match = com.adithyasairam.android.androidcommons.Utils.Intents.IntentProperties.getSerializable(Constants.MATCH_KEY, getIntent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_can_burgeled_auton);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_can_burgeled_auton, menu);
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

    @OnClick(R.id.donePlsButton) public void imDoneSoYouGucci() {
        try {
            int numAttempted = Integer.parseInt(numCansAttempted.getText().toString());
            int numGrabbed = Integer.parseInt(numCansGrabbed.getText().toString());
            double speed = Double.parseDouble(canBurglingSpeed.getText().toString());
            match.putCanAutonInfo(numAttempted, numGrabbed, speed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent data = new Intents.IntentBuilder().withSerializable(Constants.MATCH_KEY, match).build();
        setResult(RESULT_OK, data);
        finish();
    }
}
