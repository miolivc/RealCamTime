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
    private MediaRecorder mediaRecorder;
    private CameraPreviewListener listener;

    public CameraPreview(Context context, Camera camera, CameraPreviewListener listener) {
        super(context);
        this.camera = camera;
        this.camera.setDisplayOrientation(90);
        this.holder = getHolder();
        this.holder.addCallback(this);
        this.mediaRecorder = new MediaRecorder();
        this.listener = listener;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            //
            Log.d("AGDebug", "Surface created");
            //
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
        //
        if (holder.getSurface() == null){
            return;
        }
        //
        try {
            camera.stopPreview();
        } catch (Exception e){
            Log.e("AGDebug", "Erro ao alterar o surface.", e);
        }

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            //
            listener.exec();
        }
        catch (Exception e){
            Log.e("AGDebug", "Error starting camera preview. ", e);
        }
    }


    public void gravarVideo(final CameraPreviewListener cameraPreviewListener) {
        //
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }
        mediaRecorder = new MediaRecorder();
        //
        camera.unlock();
        //
        mediaRecorder.setCamera(camera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "video.mp4");
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        //
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        //
        mediaRecorder.setPreviewDisplay(getHolder().getSurface());
        //
        Log.e("AGDebug", "Configurou a camera.");
        //
        mediaRecorder.setMaxDuration(10000);
        //
        Log.e("AGDebug", "Definiu o surface.");
        //
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
           Log.e("AGDebug", "Erro na camera", e);
        }
        //
        Log.e("AGDebug", "Iniciando a gravação");
        //
        mediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener(){
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {
                if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED){
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    Log.e("AGDebug", "Finalizado a gravação");
                    cameraPreviewListener.exec();
                }
            }
        });
        //
        mediaRecorder.start();
    }


    public interface CameraPreviewListener {
        void exec();
    }

}