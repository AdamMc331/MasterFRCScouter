package org.hammerhead226.masterfrcscouter.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import org.hammerhead226.masterfrcscouter.android.R;
import org.hammerhead226.masterfrcscouter.backend.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

//TODO: Refactor
public class PitScoutActivity extends AppCompatActivity {

    @Bind(R.id.webView)
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scout);
        ButterKnife.bind(this);
        myWebView.loadUrl(Constants.GoogleFormsURL);
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
}
