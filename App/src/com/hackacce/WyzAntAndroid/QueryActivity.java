package com.hackacce.WyzAntAndroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class QueryActivity extends Activity {
    private static final String TAG = "QueryActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Spinner distSpinner = (Spinner) findViewById(R.id.distField);
        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> distAdapter =
                ArrayAdapter.createFromResource(this, R.array.options_distance, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        distSpinner.setAdapter(distAdapter);

        final Spinner genderSpinner = (Spinner) findViewById(R.id.genderField);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter =
                ArrayAdapter.createFromResource(this, R.array.options_gender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderAdapter);

        final EditText subjectField = (EditText) findViewById(R.id.subjectField);
        final EditText zipField = (EditText) findViewById(R.id.zipField);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        //Range SEEK bar
        final TextView lowerRange = (TextView)findViewById(R.id.lowerRateRange);
        final TextView upperRange = (TextView)findViewById(R.id.upperRateRange);
        lowerRange.setText("$"+(getString(R.string.rateMin)));
        upperRange.setText("$"+(getString(R.string.rateMax)));
        RangeSeekBar<Integer> rateSeekBar = new RangeSeekBar<>
                (Integer.parseInt(getString(R.string.rateMin)),(Integer.parseInt(getString(R.string.rateMax))), this);
        rateSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new rate range values: MIN=" + minValue + ", MAX=" + maxValue);
                lowerRange.setText("$" + minValue);
                upperRange.setText("$" + maxValue);
            }
        });
        //Age SEEK bar
        final TextView lowerAge = (TextView)findViewById(R.id.lowerAgeRange);
        final TextView upperAge = (TextView)findViewById(R.id.upperAgeRange);
        lowerAge.setText(getString((R.string.ageMin)));
        upperAge.setText(getString((R.string.ageMax)));
        RangeSeekBar<Integer> ageSeekBar = new RangeSeekBar<>
                (Integer.parseInt(getString(R.string.ageMin)),(Integer.parseInt(getString(R.string.ageMax))), this);
        ageSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new age range values: MIN=" + minValue + ", MAX=" + maxValue);
                lowerAge.setText(""+minValue);
                upperAge.setText(""+maxValue);
            }
        });

        ViewGroup rateLayout = (ViewGroup) findViewById(R.id.rateRange);
        rateLayout.addView(rateSeekBar);

        ViewGroup ageLayout = (ViewGroup) findViewById(R.id.ageRange);
        ageLayout.addView(ageSeekBar);

        ImageView mBanner;
        mBanner = (ImageView) findViewById(R.id.imageBanner);
        mBanner.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(Intent.ACTION_VIEW);
                   i.setData(Uri.parse(getString(R.string.baseUrl)));
                   startActivity(i);
               }
           });

        Button mStringBuilder;
        mStringBuilder = (Button) findViewById(R.id.search);
        mStringBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Convert user input to string

                StringBuilder query = new StringBuilder (getString(R.string.baseUrl)).append(getString(R.string.baseQuery))
                        .append("?kw=").append(subjectField.getText().toString())
                        .append("&z=").append(zipField.getText().toString())
                        .append("&d=").append(distSpinner.getSelectedItem().toString())
                        .append("&mina=").append(lowerAge.getText().toString())
                        .append("&maxa=").append(upperAge.getText().toString())
                        .append("&minh=").append(lowerRange.getText().toString().substring(1))
                        .append("&maxh=").append(upperRange.getText().toString().substring(1))
                        .append("&im=").append(genderSpinner.getLastVisiblePosition() - 1)
                        .append("&bgCheck=").append(checkBox.isChecked() ? "true" : "false");

                TextView test = (TextView) findViewById(R.id.queryPreview);
                test.setText(query);
/*
                // Launches browser intent

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(query.toString()));
                startActivity(i);
*/
                Intent i = new Intent(QueryActivity.this, TutorListActivity.class);
                i.putExtra("URL", query.toString());
                startActivity(i);

            }

        });
    }
}


