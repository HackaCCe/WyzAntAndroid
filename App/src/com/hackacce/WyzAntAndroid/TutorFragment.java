package com.hackacce.WyzAntAndroid;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TutorFragment extends ListFragment {
    private static final String TAG = "TutorFragment";
    public Tutor tutor;

    public void setTutor(Tutor t) {
        tutor = t;
    }

    private void viewThisTutor() {
        Log.d(TAG, "view tutor ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_item_tutor, container, false);
        Button viewButton = (Button)v.findViewById(R.id.viewThisTutorButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewThisTutor();
            }
        });
        return v;
    }
}
