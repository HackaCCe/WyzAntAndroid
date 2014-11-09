package com.hackacce.WyzAntAndroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
        */

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
        final EditText ageLowerField = (EditText) findViewById(R.id.ageLowerField);
        final EditText ageUpperField = (EditText) findViewById(R.id.ageUpperField);
        final EditText rateLowerField = (EditText) findViewById(R.id.rateLowerField);
        final EditText rateUpperField = (EditText) findViewById(R.id.rateUpperField);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

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
                        .append("&mina=").append(ageLowerField.getText().toString())
                        .append("&maxa=").append(ageUpperField.getText().toString())
                        .append("&maxh=").append(rateUpperField.getText().toString())
                        .append("&minh=").append(rateLowerField.getText().toString())
                        .append("&im=").append(genderSpinner.getLastVisiblePosition() - 1)
                        .append("&bgCheck=").append(checkBox.isChecked() ? "true" : "false");

                TextView test = (TextView) findViewById(R.id.queryPreview);
                test.setText(query);

                // Launches browser intent

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(query.toString()));
                startActivity(i);
            }

        });
    }
}


