package org.hammerhead226.masterfrcscouter.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.adithyasairam.android.android_commons.Intents;
import com.adithyasairam.utils.annotations.Changeable;

import org.hammerhead226.masterfrcscouter.android.R;
import org.hammerhead226.masterfrcscouter.backend.Constants;
import org.hammerhead226.masterfrcscouter.model.RecycleRush.RRStack;
import org.hammerhead226.masterfrcscouter.model.RecycleRush.RecycleRush;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

@Changeable(source = TeleopMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class TeleopMatchScoutActivity extends AppCompatActivity implements View.OnClickListener {


    RecycleRush match;
    public static ArrayList<RRStack> rrStackArrayList;
    @Bind(R.id.submitStack)
    Button stackSubmit;
    @Bind(R.id.stackHeightET)
    EditText stackHeight;
    @Bind(R.id.canOnTopWithLitterCB)
    CheckBox canOnTopWithLitter;
    @Bind(R.id.canOnTopCB)
    CheckBox canOnTop;
    @Bind(R.id.totesFromHumanFeeder)
    CheckBox totesFromHF;
    @Bind(R.id.totesFromLandfill)
    CheckBox totesFromLF;
    @Bind(R.id.canCappedSwitch)
    Switch didCapCans;
    @Bind(R.id.numCansCappedET)
    EditText numCansCapped;
    @Bind(R.id.coopSetSwitch)
    Switch coopSet;
    @Bind(R.id.coopStackSwitch)
    Switch coopStack;
    @Bind(R.id.knockedStackSwitch)
    Switch knockedOverStacks;
    @Bind(R.id.numTeleFoulsET)
    EditText numTeleFouls;
    @Bind(R.id.nextBttn)
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            match = Intents.IntentProperties.getSerializable(Constants.MATCH_KEY, getIntent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        rrStackArrayList = new ArrayList<RRStack>(5);
        setContentView(R.layout.activity_teleop_match_scout);
        ButterKnife.bind(this);

        stackSubmit.setOnClickListener(this);

        nextButton.setOnClickListener(this);
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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitStack:
                try {
                    int sH = Integer.parseInt(stackHeight.getText().toString());
                    boolean cOTWL = canOnTopWithLitter.isChecked();
                    boolean cOT = canOnTop.isChecked();
                    if (cOTWL && cOT) {
                        Toast.makeText(getApplicationContext(), "You have checked that the Stack has both a Can on Top and a Can on Top with Litter", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    rrStackArrayList.add(new RRStack(sH, cOTWL, cOT));
                    stackHeight.setText("");
                    canOnTopWithLitter.setChecked(false);
                    canOnTop.setChecked(false);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case R.id.nextBttn:
                try {
                    parseData();
                    startActivity(new Intents.IntentBuilder().toClass(MatchScoutSubmitActivity.class).withContext(this).withSerializable(Constants.MATCH_KEY, match).build());
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private void parseData() {
        try {
            int nTF = Integer.parseInt(numTeleFouls.getText().toString());
            int nCC = Integer.parseInt(numCansCapped.getText().toString());
            boolean cSet = coopSet.isChecked();
            boolean cStack = coopStack.isChecked();
            boolean stackDown = knockedOverStacks.isChecked();
            boolean didCap = didCapCans.isChecked();
            String toteSource = getToteSource();
            match.putTeleopInfo(cSet, cStack, stackDown, didCap, nCC, toteSource, nTF, rrStackArrayList);
        } catch (Exception e) {
        }
    }

    public String getToteSource() {
        String source = "";
        boolean HF = totesFromHF.isChecked();
        boolean LF = totesFromLF.isChecked();
        if (HF) {
            return "Human Feeder";
        } else if (LF) {
            return "Land Fill";
        } else {
            return "Both";
        }
    }
}
