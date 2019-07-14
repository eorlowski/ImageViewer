package orlowski.com.imageviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView myImage;
    Bundle bundle;
    ArrayList<String> fileList;
    SwipeAdapter swipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                myImage = (ImageView) findViewById(R.id.myimage);
                Bitmap bitmap;
                try {
                    //
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse("file:///storage/emulated/0/DCIM/Camera/20170409_154124.jpg")));
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 4096, 4096, true);
                    myImage.setImageBitmap(scaledBitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        ); */
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            bundle = new Bundle();
            bundle.putStringArrayList("fileList", new ArrayList<String>());
            //setContentView(R.layout.settingsview);
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            settingsIntent.putExtras(bundle);
            startActivityForResult(settingsIntent, 1);
            return true;
        } else if (id == R.id.action_options) {
            Intent optionsIntent = new Intent(this, OptionsActivity.class);
            startActivity(optionsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This method is invoked when target activity return result data back.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);

        switch (requestCode) {
            case 1:
                fileList = dataIntent.getStringArrayListExtra("fileList");
                TextView textView = (TextView) findViewById(R.id.mytext2);
                if (resultCode == RESULT_OK && fileList.size() > 0) {
                    textView.setText(fileList.get(0));
                    //bundle.putStringArray("fileList", (String[]) fileList.toArray());
                    if (swipeAdapter != null) {
                        viewPager = (ViewPager) findViewById(R.id.view_pager);
                        swipeAdapter = new SwipeAdapter(fileList, getSupportFragmentManager());
//                        swipeAdapter.setFiles((String[]) fileList.toArray());
                        viewPager.setAdapter(swipeAdapter);
                    }
                    else {
                        viewPager = (ViewPager) findViewById(R.id.view_pager);
                        swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
                        swipeAdapter.setFiles(fileList);
                        viewPager.setAdapter(swipeAdapter);
                    }
                }
                else
                    textView.setText("No files found");
                break;
            default:
                TextView textView2 = (TextView) findViewById(R.id.mytext2);
                textView2.setText("We got there");
        }
    }
}
