package br.edu.ifpb.pdm.miolivc.realcamtime;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.widget.FrameLayout;
import android.widget.Toast;

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
        camera.setDisplayOrientation(90);
        this.cameraPreview = new CameraPreview(getApplicationContext(), camera);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.webCamSurface);
        frameLayout.addView(cameraPreview);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("gravar") &&  bundle.getBoolean("gravar")) {
            cameraPreview.gravarVideo();
        }
    }


}
