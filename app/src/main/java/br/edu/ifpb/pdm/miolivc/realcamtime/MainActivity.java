package br.edu.ifpb.pdm.miolivc.realcamtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button iniciarCam = (Button) findViewById(R.id.iniciarComoCam);

        iniciarCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebCam.class);
                startActivity(intent);
            }
        });

        Button iniciarControle = (Button) findViewById(R.id.iniciarComoControle);

        iniciarControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfiguracaoControle.class);
                startActivity(intent);
            }
        });

    }
}
