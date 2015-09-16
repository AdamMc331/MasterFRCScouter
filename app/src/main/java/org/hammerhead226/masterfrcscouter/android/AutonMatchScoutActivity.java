package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;

import java.util.Arrays;
import java.util.List;

@Changeable(source = AutonMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class AutonMatchScoutActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText acquiredStepBins, autoFouls;
    TextView autonSelectionTV;
    ListView autonListView;
    Button goToTeleop;

    String autonSelection = "";
    CharSequence origText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton_match_scout);
        autonListView = (ListView) findViewById(R.id.autonItems);
        final List<String> values = Arrays.asList("Drove to Auto Zone",
                "Set Scored", "Tote Set Scored", "Stacked Tote Set Scored", "Bin Set", "Can Burgled", "Did Nothing");
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, values);
        autonListView.setAdapter(adapter);
        autonListView.setOnItemClickListener(this);
        acquiredStepBins = (EditText)(findViewById(R.id.acquiredStepBins));
        autonSelectionTV = (TextView) (findViewById(R.id.autonSelectionTV));
        origText = autonSelectionTV.getText(); //don't change pls?
        autoFouls = (EditText)(findViewById(R.id.numAutoFouls));
        goToTeleop = (Button)(findViewById(R.id.goToTeleop));
        goToTeleop.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goToTeleop:
                parseData();
                startActivity(new Intent(this, TeleopMatchScoutActivity.class));
                break;
        }
    }

    //FIXME TEST
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String[] values = new String[]{"Drove to Auto Zone",
                "Set Scored", "Tote Set Scored", "Stacked Tote Set Scored", "Bin Set", "Can Burgled", "Did Nothing"};
        autonSelection = values[position]; //Shady
        updateAutonTVText();
        if (Arrays.asList(values).indexOf(autonSelection) == -1) {
            throw new AssertionError();
        }
        if (autonSelection.equals("Can Burgled")) {
            Intent intent = new Intent(this, CanBurgeledAutonActivity.class);
            startActivityForResult(intent, RESULT_OK);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void parseData() {
        try {
            int aB = Integer.parseInt(acquiredStepBins.getText().toString());
            int aF = Integer.parseInt(autoFouls.getText().toString());
            DataParsing.setAutonInfo(autonSelection, aB, aF);
        }
        catch (Exception e) { }
    }

    private void updateAutonTVText() {
        autonSelectionTV.setText(origText + "\n" + autonSelection);
    }
}
