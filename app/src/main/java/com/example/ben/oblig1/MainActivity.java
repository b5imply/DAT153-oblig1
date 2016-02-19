package com.example.ben.oblig1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.ben.oblig1.classes.IOClass;
import com.example.ben.oblig1.classes.PersonArchive;

/**
 * Main activity class for the main and first screen of the App.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PersonArchive.isEmpty())
            IOClass.load(getApplicationContext());
    }

    /**
     * Method for when the "Learning" button is clicked
     * @param view
     */
    public void mainOnLearningClicked(View view) {
        if (!PersonArchive.isEmpty()) {
            Intent intent = new Intent(this, LearningActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "The archive is empty!\nAdd persons first", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method for when the "List of persons" button is clicked
     * @param view
     */
    public void mainOnListClicked(View view) {
        Intent intent = new Intent(this, ListOfPersonActivity.class);
        startActivity(intent);
    }

    /**
     * Method for when the "gallery" button is clicked
     * @param view
     */
    public void mainOnGalleryClicked(View view) {
        if (!PersonArchive.isEmpty()) {
            Intent intent = new Intent(this, Gallery.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "The archive is empty! \nAdd persons first", Toast.LENGTH_LONG).show();
        }
    }

    public void onClearButton(View view) {
        IOClass.clearStorage(getApplicationContext());
    }
}