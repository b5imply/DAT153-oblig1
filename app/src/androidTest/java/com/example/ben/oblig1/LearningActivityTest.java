package com.example.ben.oblig1;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import junit.framework.TestCase;

/**
 * Created by Ben on 29-Feb-16.
 */
public class LearningActivityTest extends ActivityInstrumentationTestCase2<LearningActivity> {

    LearningActivity activity;

    public LearningActivityTest(Class<LearningActivity> activityClass) {
        super(activityClass);
    }

    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testGuessButtonNotNull(){
        Button guessButton = (Button) activity.findViewById(R.id.learningButtonSubmit);
        assertNotNull(guessButton);
    }

    @SmallTest
    public void testGuessedNameTextNotNull(){
        EditText editText = (EditText) activity.findViewById(R.id.learningNameText);
        assertNotNull(editText);
    }

    @SmallTest
    public void testImageViewNotNull(){
        ImageView imageView = (ImageView) activity.findViewById(R.id.learningImgView);
        assertNotNull(imageView);
    }
}