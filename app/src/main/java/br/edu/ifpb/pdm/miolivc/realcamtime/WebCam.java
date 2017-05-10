package br.edu.ifpb.pdm.miolivc.realcamtime;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import br.edu.ifpb.pdm.miolivc.realcamtime.controlller.CameraPreview;

public class WebCam extends AppCompatActivity {

    private Camera camera;
    private MediaRecorder mediaRecorder;
    private CameraPreview cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_cam);

        camera = Camera.open();
        this.cameraPreview = new CameraPreview(getApplicationContext(), camera);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.webCamSurface);
        frameLayout.addView(cameraPreview);

    }

}
