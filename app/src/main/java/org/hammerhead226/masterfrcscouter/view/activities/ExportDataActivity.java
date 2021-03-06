package org.hammerhead226.masterfrcscouter.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.io.Files;

import org.hammerhead226.masterfrcscouter.MasterFRCScouterApplication;
import org.hammerhead226.masterfrcscouter.android.P;
import org.hammerhead226.masterfrcscouter.android.R;
import org.hammerhead226.masterfrcscouter.backend.Constants;
import org.hammerhead226.masterfrcscouter.backend.Scouter;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExportDataActivity extends AppCompatActivity {

    private static final String TAG = "ExportDataActivity";
    @Bind(R.id.exportDataButton) Button exportData;
    @Bind(R.id.goHomeButtonDos) Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_data);
        ButterKnife.bind(this);
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

    @OnClick(R.id.exportDataButton) public void sendFilesByEmail() {
        try {
            String email = P.email.get();
            if (email == null || email.equals("null")) {
                Toast.makeText(this, "Null Email Recipient. Please Configure it in settings.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SettingsActivity.class));
            } else {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email}); // recipients
                for (File f : getFilesToSend()) {
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                }
                intent.putExtra(Intent.EXTRA_SUBJECT, Scouter.scouterName + "'s scouting data");
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.goHomeButtonDos) public void goHome() {
        try {
            long millis = System.currentTimeMillis();
            File backupFile = new File(Constants.getMatchDataBackupDir(), "Matches-" + millis + ".csv");
            Files.move(MasterFRCScouterApplication.getCSVFile(), backupFile);
            MasterFRCScouterApplication.getCSVFile().delete(); //Delete old CSV file
            MasterFRCScouterApplication.setCSVFile(new File(Constants.getMatchDataDir(), "Matches.csv")); //Init new CSV file
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
    }

    public List<File> getFilesToSend() {
        File files = Constants.getMatchDataDir();
        return Arrays.asList(files.listFiles());
    }
}
