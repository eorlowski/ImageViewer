package orlowski.com.imageviewer;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
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
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
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

}
