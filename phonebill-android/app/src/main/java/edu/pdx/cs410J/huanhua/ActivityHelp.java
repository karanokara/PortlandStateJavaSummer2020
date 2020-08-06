package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActivityHelp extends AppCompatActivity {

    private View thisView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView view = findViewById(R.id.activity_help_view);

        // read file
        BufferedInputStream bis = new BufferedInputStream(getResources().openRawResource(R.raw.readme)); // buffer the input read from another InputStream
        // DataInputStream dis = new DataInputStream(bis); // used to read Java's primitive types
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);

        String content = "";
        try {
            String line = br.readLine();
            while (line != null) {
                content += line + "\n";
                line = br.readLine();
            }
        } catch (IOException e) {
            // using any view inside this activity
            Snackbar.make(view, e.getMessage(), 5000)
                    .setAction("Action", null).show();
        }

        view.setText(content);
        // go back to main activity
        // finishActivity(11);

    }
}