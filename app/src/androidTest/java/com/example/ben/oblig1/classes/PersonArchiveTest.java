package com.example.ben.oblig1.classes;

import junit.framework.TestCase;

/**
 * Created for unit test the Person Archive.
 * Created by Ben on 29-Feb-16.
 */
public class PersonArchiveTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testAddPerson() throws Exception {
        PersonArchive.clear();
        String name = "testName";
        String imgPath = "dummyPath";
        PersonArchive.addPerson(name, imgPath);
        assertNotNull(PersonArchive.ITEMS);
        assertNotNull(PersonArchive.ITEM_MAP);
        assertFalse(PersonArchive.ITEMS.isEmpty());
        assertFalse(PersonArchive.ITEM_MAP.isEmpty());
    }

    public void testAddPersonReturn() throws Exception {
        PersonArchive.clear();
        String name = "testName";
        String imgPath = "dummyPath";
        PersonArchive.People person = new PersonArchive.People("",name, imgPath);
        PersonArchive.People actualPerson = PersonArchive.addPersonReturn(name, imgPath);
        assertNotNull(actualPerson);
        assertEquals(person.name, actualPerson.name);
        assertEquals(person.imgPath, actualPerson.imgPath);
    }

    public void testClear() throws Exception {
        String id = "";
        String name = "testName";
        String imgPath = "dummyPath";
        PersonArchive.People person = new PersonArchive.People(id, name, imgPath);
        PersonArchive.ITEMS.add(person);
        PersonArchive.ITEM_MAP.put(person.id, person);
        assertFalse(PersonArchive.ITEMS.isEmpty());
        assertFalse(PersonArchive.ITEM_MAP.isEmpty());
        PersonArchive.clear();
        assertTrue(PersonArchive.ITEMS.isEmpty());
        assertTrue(PersonArchive.ITEM_MAP.isEmpty());
    }

    public void testIsEmpty() throws Exception {
        String id = "";
        String name = "testName";
        String imgPath = "dummyPath";
        PersonArchive.People person = new PersonArchive.People(id, name, imgPath);
        PersonArchive.ITEMS.add(person);
        PersonArchive.ITEM_MAP.put(person.id, person);
        assertFalse(PersonArchive.ITEM_MAP.isEmpty());
        assertFalse(PersonArchive.ITEMS.isEmpty());
        PersonArchive.clear();
        assertTrue(PersonArchive.ITEM_MAP.isEmpty());
        assertTrue(PersonArchive.ITEMS.isEmpty());
    }
}