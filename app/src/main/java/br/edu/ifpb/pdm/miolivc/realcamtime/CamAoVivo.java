package br.edu.ifpb.pdm.miolivc.realcamtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.FrameLayout;

public class CamAoVivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_ao_vivo);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.camAoVivo);


    }
}
