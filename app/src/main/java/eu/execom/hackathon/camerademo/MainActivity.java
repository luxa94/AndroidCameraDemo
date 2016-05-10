package eu.execom.hackathon.camerademo;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final String PHOTO_PATH = "PHOTO_PATH";

    String currentPhotoPath;

    @Bean
    FileUtils fileUtils;

    @ViewById
    SimpleDraweeView image;

    @ViewById
    Button button;

    @Click
    void button() {
        // sends the intent for the usage of camera.
        // DO NOT FORGET PERMISSIONS IN THE MANIFEST
        // DO NOT FORGET PERMISSIONS IN THE MANIFEST
        // DO NOT FORGET PERMISSIONS IN THE MANIFEST
        // DO NOT FORGET PERMISSIONS IN THE MANIFEST
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                // gets a file for the photo to be stored in
                final File photoFile = fileUtils.createImageFile();

                // we will need its path for later.
                currentPhotoPath = photoFile.getAbsolutePath();
                Log.d(TAG, currentPhotoPath);

                // tell the system what file to write to if the picture is taken.
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                // this starts up the camera.
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    /**
     * This method gets called when the user has taken a photo or canceled out of that screen.
     *
     * @param resultCode equals RESULT_OK if the photo has been taken.
     * @param data
     */
    @OnActivityResult(REQUEST_IMAGE_CAPTURE)
    void onResult(int resultCode, Intent data) {
        // do stuff if the photo has been taken.
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, currentPhotoPath, Toast.LENGTH_LONG).show();
            Log.d(TAG, currentPhotoPath);

            // the path to the image is in our variable, but we need the uri to load it.
            image.setImageURI(new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(currentPhotoPath).build());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(PHOTO_PATH, currentPhotoPath);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentPhotoPath = savedInstanceState.getString(PHOTO_PATH);
        }
    }

}
