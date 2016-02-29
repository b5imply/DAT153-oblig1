package com.example.ben.oblig1.classes;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a container class. It contains the logic behind a static container that is
 * accessible from everywhere in the application.
 */
public class PersonArchive {

    public static final List<People> ITEMS = new ArrayList<People>();
    public static final Map<String, People> ITEM_MAP = new HashMap<String, People>();
    public static final String DEFAULT_URI =
            "android.resource://com.example.ben.oblig1/drawable/img_profile_default";
    private static final String DEFAULT_NAME = "Benjamin";
    private static int id_count = 0;

    /**
     * Method for adding a person to the archive with given name and image. ID is a integer counter
     * that represent the ID of the person.
     *
     * @param name name of the person.
     * @param imagePath  URL string path to the image of the person.
     */
    public static void addPerson(String name, String imagePath) {
        if (name != DEFAULT_NAME) {
            final int id = id_count;
            id_count++;
            People person = new People(Integer.toString(id), name, imagePath);
            ITEMS.add(person);
            ITEM_MAP.put(person.id, person);
        }
    }

    /**
     * Method for adding a person to the archive with given name and image. ID is a integer counter
     * that represent the ID of the person.
     *
     * @param name name of the person.
     * @param imagePath  URL string path to the image of the person.
     */
    public static People addPersonReturn(String name, String imagePath) {
        People person = null;
        if (name != DEFAULT_NAME) {
            final int id = id_count;
            id_count++;
            person = new People(Integer.toString(id), name, imagePath);
            ITEMS.add(person);
            ITEM_MAP.put(person.id, person);
        }
        return person;
    }

    /**
     * Method for clearing every entry in the lists and maps.
     */
    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
        id_count = 0;
    }

    /**
     * Method that returns if the list in the @PersonArchive is empty.
     *
     * @return returns a boolean true if the list is empty.
     */
    public static boolean isEmpty() {
        boolean isEmpty = true;
        if (!ITEMS.isEmpty() || !ITEM_MAP.isEmpty())
            isEmpty = false;
        return isEmpty;
    }

    /**
     * Just a static default person that is added
     */
    static {
        //PersonArchive.addPerson(DEFAULT_NAME, DEFAULT_URI);
    }


    /**
     * A Person class
     */
    public static class People {
        public final String id;
        public final String name;
        public final String imgPath;

        public People(String id, String name, String imagePath) {
            this.id = id;
            this.name = name;
            this.imgPath = imagePath;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
