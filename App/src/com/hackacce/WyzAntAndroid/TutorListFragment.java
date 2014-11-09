package com.hackacce.WyzAntAndroid;

import android.app.ListFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TutorListFragment extends ListFragment {
    private ArrayList<Tutor> mTutors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.tutors_title);
        mTutors = TutorDrawer.get(getActivity()).getTutors();

        TutorAdapter adapter = new TutorAdapter(mTutors);
        setListAdapter(adapter);
    }

    private class TutorAdapter extends ArrayAdapter<Tutor> {
        private static final String TAG = "TutorAdapter";

        public TutorAdapter(ArrayList<Tutor> tutors) {
            super(getActivity(), 0, tutors); // 0 for no predefined layout
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // inflate a view if not given one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_tutor, null);
            }

            // configure the View for this Crime
            Tutor t = getItem(position);

            ImageView portrait = (ImageView)convertView.findViewById(R.id.portrait);
            //portrait.setImageURI(Uri.parse(t.getImageurl()));
            Thread downloadThread = new Thread() {
                @Override
                public void run() {
                    try {
                        portrait.setImageBitmap(getImageBitmap(t.getImageurl()));
                    } catch (IOException ioe) {
                        Log.e(TAG, ioe.getMessage());
                    } // try
                } // run
            }; // downloadThread

            TextView titleTextView = (TextView)convertView.findViewById(R.id.tutorName);
            titleTextView.setText(t.getName());

            TextView rateTextView = (TextView)convertView.findViewById(R.id.tutorRate);
            rateTextView.setText(t.getHourlyRate());

            TextView distanceTextView = (TextView)convertView.findViewById(R.id.tutorDistance);
            distanceTextView.setText(t.getDistance());

            TextView taglineTextView = (TextView)convertView.findViewById(R.id.tutorTagline);
            taglineTextView.setText(t.getTagline());

            return convertView;
        }

        private Bitmap getImageBitmap(String url) throws IOException {
            Bitmap bm = null;
            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "Error getting bitmap", e);
            }
            return bm;
        }
    } // TutorAdapter
}
