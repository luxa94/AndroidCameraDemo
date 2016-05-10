package eu.execom.hackathon.camerademo;

import android.os.Environment;
import android.util.Log;

import org.androidannotations.annotations.EBean;

import java.io.File;
import java.io.IOException;

/**
 * Created by nikolalukic on 5/10/16.
 */
@EBean
public class FileUtils {

    // creates a file for the image to be stored in.
    public File createImageFile() throws IOException {
        // Create an image file name
        final String timeStamp = String.valueOf(System.currentTimeMillis());
        final String imageFileName = "JPEG_" + timeStamp + "_";
        Log.d("imageFileName", imageFileName);
        final File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        final File instagramLiteStorageDir = new File(String.format("%s/CameraDemo", storageDir.getAbsolutePath()));
        instagramLiteStorageDir.mkdir();

        return File.createTempFile(
                imageFileName,           /* prefix    */
                ".jpg",                  /* suffix    */
                instagramLiteStorageDir  /* directory */
        );
    }

}
