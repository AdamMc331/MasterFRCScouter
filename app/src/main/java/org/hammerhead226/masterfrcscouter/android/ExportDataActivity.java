package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.adithyasairam.masterfrcscouter.Scouting.Scouter;
import com.google.common.io.Files;

import org.hammerhead226.masterfrcscouter.Utils.Constants;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ExportDataActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ExportDataActivity";
    Button exportData, mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_data);
        exportData = (Button)(findViewById(R.id.exportDataButton));
        exportData.setOnClickListener(this);
        mainMenu = (Button) (findViewById(R.id.goHomeButtonDos));
        mainMenu.setOnClickListener(this);
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
            case R.id.exportDataButton:
                sendEmail(getFilesToSend());
                break;
            case R.id.goHomeButtonDos:
                backupFile();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public List<File> getFilesToSend() {
        File files = Constants.getMatchDataDir();
        return Arrays.asList(files.listFiles());
    }

    public void sendEmail(List<File> filesToAttach) {
        try {
            String email = P.email.get();
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email}); // recipients
            for (File f : filesToAttach) {
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
            }
            intent.putExtra(Intent.EXTRA_SUBJECT, Scouter.scouterName + "'s scouting data");
            startActivity(intent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backupFile() {
        try {
            long millis = System.currentTimeMillis();
            File backupFile = new File(Constants.getMatchDataBackupDir(), "Matches-" + millis + ".csv");
            Files.move(MainActivity.csvFile, backupFile);
            MainActivity.csvFile.delete(); //Delete old CSV file
            MainActivity.csvFile = new File(Constants.getMatchDataDir(), "Matches.csv"); //Init new CSV file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
