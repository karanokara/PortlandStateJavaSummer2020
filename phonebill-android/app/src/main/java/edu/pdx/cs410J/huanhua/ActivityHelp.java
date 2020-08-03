package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView view = findViewById(R.id.activity_help_view);

        view.setText("This is a README.");

        // go back to main activity
        // finishActivity(11);

    }
}