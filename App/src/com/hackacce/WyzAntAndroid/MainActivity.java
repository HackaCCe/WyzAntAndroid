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

    Button mStringBuilder;

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
        EditText subjectField = (EditText) findViewById(R.id.subjectField);
        EditText zipField = (EditText) findViewById(R.id.zipField);
        Spinner distField = (Spinner) findViewById(R.id.distField);
        mStringBuilder = (Button)findViewById(R.id.search);
        mStringBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//Convert user input to string
               String query ="http://www.wyzant.com/tutorsearch?kw="+ subjectField.getText().toString()
                       + "&z=" + zipField.getText().toString()
                       + "&d=" + distField.getSelectedItem().toString();
              TextView test = (TextView)findViewById(R.id.queryPreview);
                test.setText(query);

//launches browser intent

                String url = query;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
