package br.edu.ifpb.pdm.miolivc.realcamtime.controlller;

import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Camera camera;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        this.camera.setDisplayOrientation(90);
        holder = getHolder();
        holder.addCallback(this);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            Log.d("miolivc", "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if(holder.getSurface() != null) {
            camera.release();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        if (holder.getSurface() == null){
            // preview surface does not exist
            return;
        }
        try {
            camera.stopPreview();
        } catch (Exception e){
        }

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch (Exception e){
            Log.d("miolivc", "Error starting camera preview: " + e.getMessage());
        }
    }


    public void gravarVideo() {

        MediaRecorder mediaRecorder = new MediaRecorder();

        camera.unlock();
        //
        mediaRecorder.setCamera(camera);

        Log.e("asasas", "asssou pelac criacao do media");

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        //mediaRecorder.setMaxDuration(10000);
        //
//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "video.mp4");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mediaRecorder.setOutputFile(file.getAbsolutePath());
        mediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());
        //
        Log.e("iosd", "setou properties");

        //mediaRecorder.setPreviewDisplay(CameraPreview.this.getHolder().getSurface());

        Log.e("miolivc", "recuperou surface");
        //
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
           Log.e("AGDebug", "Erro na camera", e);
        }
        //
        mediaRecorder.start();

    }
}