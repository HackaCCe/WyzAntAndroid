package com.hackacce.WyzAntAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class TutorListActivity extends Activity {
    private static final String TAG = "TutorListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                } catch (IOException ioe) {
                    Log.e(TAG, ioe.getMessage());
                }
            }
        };
        downloadThread.start();

    }
}
