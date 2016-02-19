package com.example.ben.oblig1.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * IOSClass used to save and load settings.
 */
public class IOClass {

    public static final String FILENAME = "person_archive";
    //public static SharedPreferences sharedPreferences;

    /**
     * Main save method that saves the settings (archive) to internal private storage.
     *
     * @param context context which the current activity provides
     * @return returns a safety boolean
     */
    public static void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        final List<PersonArchive.People> list = PersonArchive.ITEMS;
        Gson gson = new Gson();
        saveListAsJSON(editor, list, gson);
        editor.apply();
    }

    /**
     * Saves the given list to shared preferences as JSON text
     *
     * @param editor editor object that writes to shared preferences
     * @param list   list of which shall be saved
     * @param gson   Gson serializer/deserializer for JSON files
     */
    private static void saveListAsJSON(SharedPreferences.Editor editor, List<PersonArchive.People> list, Gson gson) {
        for (PersonArchive.People people : list) {
            editor.putString(people.id, gson.toJson(people));
        }
    }

    /**
     * Same as @save method, but saves a single entry instead of an entire list
     *
     * @param context context which the current activity provides
     * @param person  a Person object which shall be saved
     */
    public static void saveSinglePerson(Context context, PersonArchive.People person) {
        if (person != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            editor.putString(person.id, gson.toJson(person));
            editor.apply();
            Toast.makeText(context, "Person: " + person.name + " is saved.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Main load method that loads the archive from file.
     *
     * @param context context which the current activity provides
     */
    public static void load(Context context) {
        PersonArchive.clear();
        SharedPreferences load = context.getSharedPreferences(FILENAME, 0);
        Map<String, ?> map = load.getAll();
        Gson gson = new Gson();
        parseEachEntry(map, gson);
    }

    /**
     * Cycles through each entry in a Map variable and operates thereafter.
     *
     * @param map  Map of strings and ?(@PersonArchive.People)
     * @param gson Gson serializer/deserializer for JSON files
     */
    private static void parseEachEntry(Map<String, ?> map, Gson gson) {
        if (!map.isEmpty()) {
            for (Map.Entry<String, ?> personEntry : map.entrySet()) {
                parseEntry(gson, personEntry);
            }
        }
    }

    /**
     * Operates the entries of the Map
     *
     * @param gson        Gson serializer/deserializer for JSON files
     * @param personEntry entry of the map which shall be parsed
     */
    private static void parseEntry(Gson gson, Map.Entry<String, ?> personEntry) {
        PersonArchive.People personObject = gson.fromJson((String) personEntry.getValue(),
                PersonArchive.People.class);
        PersonArchive.addPerson(personObject.name, personObject.imgPath);
    }

    /**
     * Clears the cleared Storage
     *
     * @param context context which the current activity provides
     */
    public static void clearStorage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        PersonArchive.clear();
        Toast.makeText(context, "Archive cleared", Toast.LENGTH_LONG).show();
    }
}
