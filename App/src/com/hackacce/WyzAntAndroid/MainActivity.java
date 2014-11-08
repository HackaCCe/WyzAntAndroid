package com.hackacce.WyzAntAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Spinner distSpinner = (Spinner)findViewById(R.id.distField);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> distAdapter =
                ArrayAdapter.createFromResource(this, R.array.options_distance, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        distSpinner.setAdapter(distAdapter);

        Spinner genderSpinner = (Spinner)findViewById(R.id.genderField);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter =
                ArrayAdapter.createFromResource(this, R.array.options_gender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderAdapter);
    }
}
