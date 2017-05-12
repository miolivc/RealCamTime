package br.edu.ifpb.pdm.miolivc.realcamtime;

import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.IOException;

import br.edu.ifpb.pdm.miolivc.realcamtime.controlller.CameraPreview;

public class WebCam extends AppCompatActivity {

    private Camera camera;
    private CameraPreview cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_cam);
        //
        camera = Camera.open();//
        //
        camera.setDisplayOrientation(90);
        cameraPreview = new CameraPreview(getApplicationContext(), camera, new CameraPreview.CameraPreviewListener() {
            @Override
            public void exec() {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null && bundle.containsKey("gravar") &&  bundle.getBoolean("gravar")) {
                    cameraPreview.gravarVideo(new CameraPreview.CameraPreviewListener(){
                        @Override
                        public void exec() {
                            WebCam.this.startActivity(new Intent(WebCam.this, MainActivity.class));
                        }
                    });
                    //

                }
            }
        });
        //
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.webCamSurface);
        frameLayout.addView(cameraPreview);
    }


}
