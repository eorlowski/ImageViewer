package orlowski.com.imageviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

/**
 * Created by pnleo064 on 26-6-2019.
 */

public class PageFragment extends Fragment {
    TextView textView;

    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        ImageView myImage;
        textView = (TextView) view.findViewById(R.id.mytext);
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        textView.setText(message + ": " + bundle.getString("fileName"));
        myImage = (ImageView) view.findViewById(R.id.myimage);
        Bitmap bitmap;
        try {
            //
            bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(Uri.parse("file:///storage/emulated/0/DCIM/Camera/" + bundle.getString("fileName"))));
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, true);
            myImage.setImageBitmap(scaledBitmap);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return view;
    }

}
