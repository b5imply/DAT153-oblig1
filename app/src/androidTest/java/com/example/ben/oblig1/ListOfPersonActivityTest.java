package com.example.ben.oblig1;

import android.support.design.widget.FloatingActionButton;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ListView;

import java.util.List;

/**
 * Tests the list of person activity.
 * Created by Ben on 29-Feb-16.
 */
public class ListOfPersonActivityTest extends ActivityInstrumentationTestCase2<ListOfPersonActivity> {

    ListOfPersonActivity activity;

    public ListOfPersonActivityTest(Class<ListOfPersonActivity> activityClass) {
        super(activityClass);
    }

    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testAddPersonButton(){
        FloatingActionButton button = (FloatingActionButton) activity.findViewById(R.id.fab);
        assertNotNull(button);
    }

    @SmallTest
    public void testListViewNotNull(){
        ListView listView = (ListView) activity.findViewById(R.id.personList);
        assertNotNull(listView);
    }

}