package com.example.ben.oblig1;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testMainGalleryButtonNotNull(){
        Button button = (Button) activity.findViewById(R.id.mainGalleryButton);
        assertNotNull(button);
    }

    @SmallTest
    public  void testMainListButtonNotNull(){
        Button button = (Button) activity.findViewById(R.id.mainListButton);
        assertNotNull(button);
    }

    @SmallTest
    public  void testMainLearningButtonNotNull(){
        Button button = (Button) activity.findViewById(R.id.mainLearningButton);
        assertNotNull(button);
    }

    //Expresso test
    public void testListButtonClicked(){

    }
}