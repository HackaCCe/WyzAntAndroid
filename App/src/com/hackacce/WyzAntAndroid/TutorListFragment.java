package com.hackacce.WyzAntAndroid;

import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
            portrait.setImageURI(Uri.parse(t.getImageurl()));

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
    }
}
