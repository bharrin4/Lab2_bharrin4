package com.a40333.bharrin4.lab2_bharrin4;

import android.content.ContentValues;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bchaudhr on 4/5/2017.
 */

public class GalleryActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    long team_id;
    GridView gridview;
    File photoFile = null;
    String mCurrentPhotoPath;
    String photoName;
    String timeStamp;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        dbHelper = new DatabaseHelper(this);
        Intent i = getIntent();
        team_id = i.getExtras().getLong("team_id");
        System.out.println("Gallery ID= " + team_id);


        gridview = (GridView) findViewById(R.id.gridview);
        populateGridView();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to ImageActivity
                Intent i = new Intent(GalleryActivity.this, ImageViewActivity.class);
                i.putExtra("id", position);
                System.out.println("ID being passed to ImageView= " + position);
                startActivity(i);
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        photoName = imageFileName;

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "com.a40333.bharrin4.lab2_bharrin4.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                Uri imageUri = FileProvider.getUriForFile(getApplicationContext(), "com.a40333.bharrin4.lab2_bharrin4.fileprovider", photoFile);
                photoGalleryIntent.setData(imageUri);

                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);

                    File imagePath = new File(getApplicationContext().getFilesDir(), "images");
                    File imageFile = new File( imagePath, getPictureName());
                    byte[]  byteArray = getBytes(imageUri, 150);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                    String iDate = sdf.format(new Date()).toString();


                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseHelper.IMAGE_TEAM_ID, team_id);
                    contentValues.put(DatabaseHelper.COL_IMAGE, byteArray);
                    contentValues.put(DatabaseHelper.COL_DATE, iDate);
                    contentValues.put(DatabaseHelper.COL_URI, imageUri.toString());
                    dbHelper.insertImageData(DatabaseHelper.IMAGES_NAME, contentValues);
                    System.out.println("image saved");
                    populateGridView();

                } catch (FileNotFoundException e) {
                    System.out.println("error in galley activity");
                    e.printStackTrace();
                }
            }
        }
    }

    private void setTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        timeStamp = sdf.format(new Date());
    }

    private String getPictureName() {
        String imageName = "BookImages" + timeStamp + ".jpg";
        return imageName;
    }


    private byte[] getBytes(Uri imageUri, int maxSize) {
        System.out.println("image saved" + imageUri);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        Bitmap imageBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        return stream.toByteArray();
    }


    private void populateGridView() {
        String[] fields = new String[]{DatabaseHelper.IMAGE_COL_ID, DatabaseHelper.COL_IMAGE, DatabaseHelper.COL_DATE};
        String where = " team_id = ?";
        String[] args = new String[]{Long.toString(team_id)};
        int[] items = new int[] {R.id.book_image};

        Cursor cursor = dbHelper.getSelectEntries(DatabaseHelper.IMAGES_NAME, fields, where, args, DatabaseHelper.IMAGE_COL_ID + " DESC");
        SimpleCursorAdapter galleryCursorAdapter = new SimpleCursorAdapter (this, R.layout.image_layout, cursor, fields, items, 0);;

        galleryCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue (View view, Cursor cursor, int columnIndex){
                if (view.getId() == R.id.book_image) {
                    ImageView imageView=(ImageView) view;
                    byte[] imageArray = cursor.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageArray,0,imageArray.length);
                    imageView.setImageBitmap(bitmap);
                    return true;
                }
                return false;
            }});

        gridview.setAdapter(galleryCursorAdapter);
    }
}