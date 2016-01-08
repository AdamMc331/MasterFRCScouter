package org.hammerhead226.masterfrcscouter.view.activities;

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

import com.adithyasairam.android.android_commons.Intents;
import com.adithyasairam.utils.annotations.Changeable;

import org.hammerhead226.masterfrcscouter.android.R;
import org.hammerhead226.masterfrcscouter.backend.Constants;
import org.hammerhead226.masterfrcscouter.model.Match;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Changeable(source = AutonMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class AutonMatchScoutActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String autonSelection = "";
    CharSequence origText = null;

    /*Type*/ Match match;

    @Bind(R.id.autonItems) ListView autonListView;
    @Bind(R.id.numAutoFouls) EditText autoFouls;
    @Bind(R.id.goToTeleop) Button goToTeleop;
    @Bind(R.id.autonSelectionTV) TextView autonSelectionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            match = Intents.IntentProperties.getSerializable(Constants.MATCH_KEY, getIntent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_auton_match_scout);
        ButterKnife.bind(this);
        autonListView = (ListView) findViewById(R.id.autonItems);
        final List<String> values = Arrays.asList("Did Nothing");
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, values);
        autonListView.setAdapter(adapter);
        autonListView.setOnItemClickListener(this);
        origText = autonSelectionTV.getText(); //don't change pls?
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String[] values = new String[]{"Did Nothing"};
        autonSelection = values[position]; //Shady
        updateAutonTVText();
        if (Arrays.asList(values).indexOf(autonSelection) == 1) {
            ///WTF this should never happen
        }
        if (autonSelection.equals("")) {
            setBlank();
            Intent intent = new Intents.IntentBuilder().toClass(null).withContext(this).withSerializable(Constants.MATCH_KEY, match).build();
            startActivityForResult(intent, RESULT_OK);
        }
        if (autonSelection.equals("Did Nothing")) {
            setBlank();
        }
        parseData();
    }

    @OnClick(R.id.goToTeleop) public void goToTeleop() {
        startActivity(new Intents.IntentBuilder().toClass(TeleopMatchScoutActivity.class).withContext(this).withSerializable(Constants.MATCH_KEY, match).build());
    }

    private void parseData() {
        try {

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void updateAutonTVText() {
        autonSelectionTV.setText(origText + "\n" + autonSelection);
    }

    private void setBlank() {
        autoFouls.setText("0");
    }
}
