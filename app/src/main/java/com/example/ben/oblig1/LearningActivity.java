package com.example.ben.oblig1;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ben.oblig1.classes.PersonArchive;

import java.util.List;
import java.util.Random;

/**
 * This class represent the logic in the Learning game of the application.
 */
public class LearningActivity extends AppCompatActivity {

    List<PersonArchive.People> availablePeople;
    PersonArchive.People randomPickedPerson;
    ImageView imageView;
    EditText guessedText;
    int indexOfGuessedPerson = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_avtivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.learningImgView);
        guessedText = (EditText) findViewById(R.id.learningNameText);

        setNewRandomPerson();
    }

    /**
     * Method for when the "guess" button is clicked
     *
     * @param view
     */
    public void onGuessButtonClicked(View view) {
        String guessedName = guessedText.getText().toString();
        if (guessedName == randomPickedPerson.name) {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
            waitNumberOfSeconds(3);
            setNewRandomPerson();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong! Try again", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method for setting a random person and setting the imageView.
     */
    private void setNewRandomPerson() {
        getAvailablePeople();
        randomPickedPerson = getRandomPerson();
        imageView.setImageURI(Uri.parse(randomPickedPerson.imgPath));
    }

    /**
     * Method for finding people objects that have valid path to images.
     */
    private void getAvailablePeople() {
        for (PersonArchive.People person : PersonArchive.ITEMS) {
            if (person.imgPath != null)
                availablePeople.add(person);
        }
    }

    /**
     * Method for picking a random person from the @PersonArchive list.
     *
     * @return a random person from the @PersonArchive list.
     */
    private PersonArchive.People getRandomPerson() {
        Random rand = new Random();
        indexOfGuessedPerson = rand.nextInt(availablePeople.size());
        return availablePeople.get(indexOfGuessedPerson);
    }

    /**
     * Method for make the application wait for given seconds.
     *
     * @param seconds
     */
    private void waitNumberOfSeconds(int seconds) {
        try {
            wait(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
