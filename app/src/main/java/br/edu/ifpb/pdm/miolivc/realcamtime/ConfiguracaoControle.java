package br.edu.ifpb.pdm.miolivc.realcamtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfiguracaoControle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_controle);

        Button iniciarGravacao = (Button) findViewById(R.id.gravar);
        iniciarGravacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfiguracaoControle.this, WebCam.class);
                startActivity(intent);
            }
        });

        Button verGravacoes = (Button) findViewById(R.id.verGravacoes);
        verGravacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfiguracaoControle.this, Gravacoes.class);
                startActivity(intent);
            }
        });

        Button assistirAoVivo = (Button) findViewById(R.id.assistirAoVivo);
        assistirAoVivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfiguracaoControle.this, CamAoVivo.class);
                startActivity(intent);
            }
        });
    }
}
