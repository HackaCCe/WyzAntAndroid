package com.hackacce.WyzAntAndroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TutorListActivity extends Activity {
    private static final String TAG = "TutorListActivity";
    private static ArrayList<Tutor> sTutors = new ArrayList<>();
    public static ArrayList<Tutor> getTutors() {return sTutors;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_activity_fragment);

        Intent i = getIntent();
        String query = i.getStringExtra("URL");

//        String query = savedInstanceState.getString("URL");
        Log.d(TAG, "query is: " + query);

        Thread downloadThread = new Thread() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(query).get();
        /*
                            Document doc = Jsoup
                                    .connect(getString(R.string.baseUrl) + getString(R.string.baseQuery))
                                    .data(
                                            "kw", subjectField.getText().toString(),
                                            "z", zipField.getText().toString(),
                                            "d", distSpinner.getSelectedItem().toString(),
                                            "mina", lowerAge.getText().toString(),
                                            "maxa", upperAge.getText().toString(),
                                            "minh", lowerRange.getText().toString().substring(1),
                                            "maxh", upperRange.getText().toString().substring(1),
                                            "im", String.format("%d", genderSpinner.getLastVisiblePosition() - 1),
                                            "bgCheck", (checkBox.isChecked() ? "true" : "false")
                                    )
                                    .get();
        */
                    Log.d(TAG, "Title is: " + doc.title());

//                    mTutors = new ArrayList<>();
                    Looper.prepare();

                    Elements tutors = doc.getElementsByClass("TutorResult");
                    for (Element tutorElement : tutors) {
                        Tutor tutor = new Tutor(tutorElement);
                        Log.d(TAG, tutor.getName());
                        sTutors.add(tutor);
                    } // for

                    FragmentManager fm = getFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

                    if (fragment == null) {
                        fragment = createFragment();
                        fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
                    }


                } catch (IOException ioe) {
                    Log.e(TAG, ioe.getMessage());
                } // try
            } // run()
        }; // Thread()
        downloadThread.start();

    } // onCreate(Bundle savedInstanceState)

    protected Fragment createFragment() {
        TutorListFragment tutorListFragment = new TutorListFragment();
/*
        Bundle bundle = new Bundle();
        bundle.putSerializable("TUTORS", tutors);
        tutorListFragment.setArguments(bundle);
*/
        return tutorListFragment;
    }

    class SerializableElements extends Elements implements Serializable {}
}
