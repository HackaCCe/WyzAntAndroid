package com.hackacce.WyzAntAndroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
//import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TutorListActivity extends Activity {
    private static final String TAG = "TutorListActivity";
    private ArrayList<Tutor> mTutors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_activity_fragment);

        Intent i = getIntent();
        String query = i.getStringExtra("URL");

//        String query = savedInstanceState.getString("URL");
        //System.out.println("URL: " + query);
//        Toast.makeText(TutorListActivity.this, "query is: " + query, Toast.LENGTH_LONG).show();
        Log.d(TAG, "query is: " + query);

        Thread downloadThread = new Thread() {
            @Override
            public void run() {
                try {
//                    Document doc = Jsoup.connect("http://www.wyzant.com/tutorsearch?kw=&z=90048").get();
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
//                    Toast.makeText(TutorListActivity.this, doc.title(), Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Title is: " + doc.title());

//                    mTutors = new ArrayList<>();
                    Looper.prepare();

                    FragmentManager fm = getFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

                    if (fragment == null) {
                        fragment = createFragment();
                        fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
                    }

                    Elements tutors = doc.getElementsByClass("TutorResult");

                    mTutors = new ArrayList<>();
                    for (Element tutorElement : tutors) {
//                        System.out.print("#"+(i+1)+": ");
                        Tutor tutor = new Tutor(tutorElement);
                        mTutors.add(tutor);
//                        TutorFragment tutorFragment = new TutorFragment();
//                        tutorFragment.setTutor(t);
//                        fm.beginTransaction().add(R.id.fragmentContainer, tutorFragment).commit();
//                        System.out.println(t.toString());
                    } // for

                } catch (IOException ioe) {
                    Log.e(TAG, ioe.getMessage());
                } // try
            } // run()
        }; // Thread()
        downloadThread.start();

    } // onCreate(Bundle savedInstanceState)

    protected Fragment createFragment() {
        return new TutorListFragment();
    }
}
