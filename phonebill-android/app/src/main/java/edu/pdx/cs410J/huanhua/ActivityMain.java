package edu.pdx.cs410J.huanhua;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // a button floating on the screen
        FloatingActionButton fab = findViewById(R.id.button_help);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to help activity
                Intent intent = new Intent(ActivityMain.this, ActivityHelp.class);

                // startActivity(intent);
                startActivityForResult(intent, 11);
            }
        });
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu
     * @return bool
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     *
     * @param item
     * @return bool
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            // get current shown fragment in this activity
            Fragment ff = getSupportFragmentManager().getPrimaryNavigationFragment();


            // List<Fragment> f = getSupportFragmentManager().getFragments();
            NavHostFragment.findNavController(ff)
                    .navigate(R.id.action_main_to_create);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}