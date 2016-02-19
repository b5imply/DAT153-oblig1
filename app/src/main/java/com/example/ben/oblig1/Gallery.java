package com.example.ben.oblig1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ben.oblig1.classes.PersonArchive;

/**
 * This class represent the logic behind the Gallery of people in the application.
 */
public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridView galleryView = (GridView) findViewById(R.id.galleryView);
        galleryView.setAdapter(new ImageAdapter(this, PersonArchive.ITEMS));

        galleryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemPicked = "You have selected: " +
                        parent.getItemAtPosition(position).toString();
                Toast.makeText(Gallery.this, itemPicked, Toast.LENGTH_LONG).show();
            }
        });

    }

}
