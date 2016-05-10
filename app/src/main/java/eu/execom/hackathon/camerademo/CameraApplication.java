package eu.execom.hackathon.camerademo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;

/**
 * Created by nikolalukic on 5/10/16.
 */
@EApplication
public class CameraApplication extends Application {

    @AfterInject
    void setupFresco() {
        // initializes the image loading library.
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true).build();
        Fresco.initialize(this, config);
    }

}
