package com.example.ben.oblig1;

import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ben.oblig1.classes.PersonArchive;

/**
 * Tests the CreatePerson activity
 * Created by Ben on 29-Feb-16.
 */
public class CreatePersonTest extends ActivityInstrumentationTestCase2<CreatePerson> {

    CreatePerson activity;

    public CreatePersonTest() {
        super(CreatePerson.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public void testGetRealPathFromURI() throws Exception {
        Uri dummyUri = null;
        String path = activity.getRealPathFromURI(dummyUri);
        assertNull(dummyUri);
        assertNull(path);
        dummyUri = Uri.parse(PersonArchive.DEFAULT_URI);
        path = activity.getRealPathFromURI(dummyUri);
        assertNotNull(dummyUri);
        assertNotNull(path);
    }

    @SmallTest
    public void testEditTextNotNull() {
        EditText editText = (EditText) activity.findViewById(R.id.personCreate_nameInput);
        assertNotNull(editText);
    }

    @SmallTest
    public void testImageButtonNotNull() {
        Button imageButton = (Button) activity.findViewById(R.id.personCreate_imageButton);
        assertNotNull(imageButton);
    }

    @SmallTest
    public void testCameraButtonNotNull() {
        Button cameraButton = (Button) activity.findViewById(R.id.personCreate_takePhotoBtn);
        assertNotNull(cameraButton);
    }

    @SmallTest
    public void testSubmitButtonNotNull() {
        Button submitButton = (Button) activity.findViewById(R.id.personCreate_submitButton);
        assertNotNull(submitButton);
    }

    @SmallTest
    public void testImageViewNotNull() {
        ImageView imageView = (ImageView) activity.findViewById(R.id.personCreate_imageView);
        assertNotNull(imageView);
    }
}