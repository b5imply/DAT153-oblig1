package com.example.ben.oblig1;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.GridView;


/**
 * Tests the gallery
 * Created by Ben on 29-Feb-16.
 */
public class GalleryTest extends ActivityInstrumentationTestCase2<Gallery> {

    Gallery activity;

    public GalleryTest(Class<Gallery> activityClass) {
        super(activityClass);
        activity = getActivity();
    }

    public void setUp() throws Exception {
        super.setUp();

    }

    @SmallTest
    public void testGridViewNotNull(){
        GridView gridView = (GridView) activity.findViewById(R.id.galleryView);
        assertNotNull(gridView);
    }
}