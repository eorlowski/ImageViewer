package orlowski.com.imageviewer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        ArrayList<String> fileList = bundle.getStringArrayList("fileList");
        setContentView(R.layout.settingsview);
        String path = Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        StringBuilder sb;
        if (files != null) {
            Log.d("Files", "Size: " + files.length);
            sb = new StringBuilder();
            for (int i = 0; i < files.length; i++) {
                sb.append(files[i].getName() + "\n");
                fileList.add(files[i].getName());
            }
        }
        else {
            sb = new StringBuilder("No files found");
        }
        TextView directoryView = (TextView) findViewById(R.id.directory);
        directoryView.setText(path);
        TextView textView = (TextView) findViewById(R.id.file_list);
        textView.setText(sb.toString());
        // Documentation says it's better to create new Intent instead of reusing existing intent
        Intent mainIntent;
        mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtras(bundle);
        setResult(RESULT_OK, mainIntent);
        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // back button
                Intent resultIntent = new Intent(this, MainActivity.class);
                resultIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
