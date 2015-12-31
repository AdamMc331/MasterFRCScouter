package org.hammerhead226.masterfrcscouter.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.io.Files;

import org.hammerhead226.masterfrcscouter.MasterFRCScouterApplication;
import org.hammerhead226.masterfrcscouter.android.R;
import org.hammerhead226.masterfrcscouter.backend.Constants;
import org.hammerhead226.masterfrcscouter.backend.DataStorage;
import org.hammerhead226.masterfrcscouter.model.Match;
import org.hammerhead226.masterfrcscouter.model.RecycleRush.RecycleRush;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.providers.android.telephony.Sms;
import me.everything.providers.android.telephony.TelephonyProvider;

public class SuperuserActivity extends AppCompatActivity {
    @Bind(R.id.importFromTextsButton) Button importFromTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superuser);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.importFromTextsButton) public void importMatchesFromTexts() {
        synchronized (this) {
            //Blocking operation!!
            //THIS WILL BLOCK ALL OTHER THREADS!
            Log.i("IMPORTMATCHES", "START: " + System.currentTimeMillis());
            Toast.makeText(getApplicationContext(), "Please note that this will take a while.", Toast.LENGTH_SHORT).show();
            TelephonyProvider telephonyProvider = new TelephonyProvider(this);
            List<Sms> allMessages = telephonyProvider.getSms(TelephonyProvider.Filter.INBOX).getList();
            List<String> filtered = new ArrayList<>();
            List<Match> matches = new ArrayList<>();
            if (allMessages.size() != 0) {
                for (Sms sms : allMessages) {
                    String data = sms.body;
                    if (data.contains(Constants.MATCH_HEADER) || data.contains(Constants.MATCH_FOOTER)) {
                        filtered.add(data);
                    }
                }
                if (filtered.size() != 0) {
                    for (String string : filtered) {
                        String[] data = string.split("\n");
                        matches.add(new RecycleRush(data));
                    }
                    List<String> currentLines = null;
                    try {
                        if (MasterFRCScouterApplication.getCSVFile().exists()) {
                            currentLines = Files.readLines(MasterFRCScouterApplication.getCSVFile(), Charset.defaultCharset());
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                    if (matches.size() != 0) {
                        for (Match match : matches) {
                            boolean contains = false;
                            if (currentLines != null) {
                                for (String s : currentLines) { if (s.equals(match.randomID)) { contains = true; } }
                            }
                            if (!contains) { DataStorage.saveMatchAsCSV(match); }
                        }
                    }
                }
            }
            allMessages = null;
            filtered = null;
            matches = null;
            Toast.makeText(getApplicationContext(), "Completed!", Toast.LENGTH_SHORT).show();
            Log.i("IMPORTMATCHES", "END: " + System.currentTimeMillis());
        }
    }
}
