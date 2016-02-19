package com.example.ben.oblig1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ben.oblig1.classes.PersonArchive;

import java.util.List;

/**
 * This class represent the logic behind the "List of people" screen in the application.
 */
public class ListOfPersonActivity extends AppCompatActivity {

    private final int NEW_PERSON_FLAG = 0;
    private final int EDIT_PERSON_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListAdapter listAdapter = new CustomAdapter(this, PersonArchive.ITEMS);
        ListView list = (ListView) findViewById(R.id.personList);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CreatePerson.class);
                intent.putExtra("flag", EDIT_PERSON_FLAG);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreatePerson.class);
                intent.putExtra("flag", NEW_PERSON_FLAG);
                startActivity(intent);
            }
        });
    }
}
