package org.hammerhead226.masterfrcscouter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;

import org.hammerhead226.masterfrcscouter.android.R;

public class CanBurgeledAutonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText NumAtt, NumGrabbed, Speed;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_burgeled_auton);
        NumAtt = (EditText) (findViewById(R.id.numCansAttempted));
        NumGrabbed = (EditText) (findViewById(R.id.numCansGrabbed));
        Speed = (EditText) (findViewById(R.id.canBurglingSpeed));
        done = (Button) (findViewById(R.id.donePlsButton));
        done.setOnClickListener(this); //WTF that's all I missed
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.donePlsButton:
                parseData();
                this.setResult(RESULT_OK);
                finish();
                break;
        }
    }

    public void parseData() {
        try {
            int numAttempted = Integer.parseInt(NumAtt.getText().toString());
            int numGrabbed = Integer.parseInt(NumGrabbed.getText().toString());
            double speed = Double.parseDouble(Speed.getText().toString());
            DataParsing.setCanBurglingAutonInfo(numAttempted, numGrabbed, speed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
