package br.edu.ifpb.pdm.miolivc.realcamtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by aluno on 10/05/17.
 */

public class ListaGravacoes extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravacoes);

        ListView gravacoes = (ListView) findViewById(R.id.videosGravados);



    }
}
