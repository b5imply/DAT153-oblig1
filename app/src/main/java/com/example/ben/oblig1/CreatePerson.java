package com.example.ben.oblig1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ben.oblig1.classes.IOClass;
import com.example.ben.oblig1.classes.PersonArchive;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represent the logic behind the creation menu for a person.
 */
public class CreatePerson extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 10;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private ImageView imagePicture;
    private EditText editText;
    private static Uri chosenImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_person);

        //Get access to image view.
        imagePicture = (ImageView) findViewById(R.id.personCreate_imageView);
        editText = (EditText) findViewById(R.id.personCreate_nameInput);

        int flag = savedInstanceState.getInt("flag");
        int id = savedInstanceState.getInt("id");
        setEditParameters(id, flag);
    }

    private void setEditParameters(int id, int flag) {
        if (flag == 1) {
            PersonArchive.People person = PersonArchive.ITEM_MAP.get(id);
            editText.setText(person.name);
            imagePicture.setImageURI(Uri.parse(person.imgPath));
        }
    }

    /**
     * This method will be invoked when the user clicks the 'gallery' button.
     *
     * @param view
     */
    public void btnGalleryClicked(View view) {
        //Invoke the image gallery using an implicit intent.
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), IMAGE_GALLERY_REQUEST);
    }

    /**
     * This method will be invoked when the user clicks the 'Take a photo' button.
     *
     * @param view
     */
    public void btnTakePhotoClicked(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    /**
     * This method wil be invoked when the user is finished and submits the details for a person.
     *
     * @param view
     */
    public void onNewPersonSubmitted(View view) {
        Intent intent = new Intent(this, ListOfPersonActivity.class);

        EditText editTextField = (EditText) findViewById(R.id.personCreate_nameInput);
        final String name = editTextField.getText().toString();

        if (name != null && chosenImageUri != null) {
            String imagePath = getRealPathFromURI(chosenImageUri);
            IOClass.saveSinglePerson(getApplicationContext(), PersonArchive.addPersonReturn(name, imagePath));
            IOClass.load(getApplicationContext());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Error submitting person, try again!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imageUri = data.getData();
        if (resultCode == RESULT_OK && imageUri != null) {
            if (requestCode == CAMERA_REQUEST) {
                cameraActivity(imageUri);
            }
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                chooseImage(imageUri);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Error saving/choosing image...",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Sets the field Uri image variable to the given data.
     *
     * @param imageUri Data is the result data from @onActivityResult. In this case an Image.
     */
    private void chooseImage(Uri imageUri) {
        //Image is chosen in the gallery (already exists)
        chosenImageUri = imageUri;
        imagePicture.setImageURI(chosenImageUri);
    }

    /**
     * Takes the image file(Uri), chooses it (@chooseImage), and then saves it(@saveImageToFile)
     *
     * @param imageUri Data is the result data from @onActivityResult. In this case an Image.
     */
    private void cameraActivity(Uri imageUri) {
        //Image is taken with a camera
        chooseImage(imageUri);
        saveImageToFile(chosenImageUri);
    }

    /**
     * Generates a picture name for the taken photo.
     *
     * @return String returns the new name
     */
    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String timestamp = sdf.format(new Date());
        return "Oblig1photo" + timestamp + ".jpeg";
    }

    /**
     * Saves the given Image, in this case an URI, to the Internal Storage
     *
     * @param uri URI to the given image
     */
    private void saveImageToFile(Uri uri) {
        if (uri != null) {
            try {
                FileOutputStream fos = openFileOutput(getPictureName(), Context.MODE_PRIVATE);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Unable to find file", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error Saving File.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getApplicationContext().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
