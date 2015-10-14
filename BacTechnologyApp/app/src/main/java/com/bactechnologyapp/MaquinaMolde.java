package com.bactechnologyapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MaquinaMolde extends AppCompatActivity {
    private Button botonImagen;
    private ImageView imagen;
    private String idMolde;
    private String idMaquina;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_molde);

        idMaquina = (String) getIntent().getExtras().getString("idMaquina");
        idMolde = (String) getIntent().getExtras().getString("idMolde");

        TextView textId = (TextView) findViewById(R.id.textIdMaquinaMolde);

        textId.setText("Maquina : " + idMaquina + "\nMolde : " + idMolde);
    }
}
