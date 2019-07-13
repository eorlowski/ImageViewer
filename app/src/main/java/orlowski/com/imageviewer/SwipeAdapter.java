package orlowski.com.imageviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by pnleo064 on 26-6-2019.
 */

    public class SwipeAdapter extends FragmentStatePagerAdapter{
        ArrayList<String> fileArray;

        public SwipeAdapter(FragmentManager fm) {
            super(fm);
        }

        public SwipeAdapter(ArrayList<String> fileArray, FragmentManager fm) {
            super(fm);
            this.fileArray = fileArray;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new PageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("count", position + 1);
            if (fileArray != null) {
                if (fileArray.size() > 0) {
                    bundle.putString("fileName", fileArray.get(position));
                }
                else {
                    bundle.putString("fileName", "No files");
                }
            }
            else
                bundle.putString("fileName", "no files");
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            if (fileArray != null) {
                return fileArray.size();
            }
            else {
                return 1;
            }
        }

        public void setFiles(ArrayList<String> files) {
            fileArray = files;
        }
}
