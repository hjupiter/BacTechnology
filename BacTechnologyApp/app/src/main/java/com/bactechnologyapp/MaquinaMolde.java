package com.bactechnologyapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MaquinaMolde extends AppCompatActivity {
    private Intent ventanaCamara;
    private Button botonImagen;
    private ImageView imagenCamara;
    private String idMolde;
    private String idMaquina;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_molde);

        idMaquina = (String) getIntent().getExtras().getString("idMaquina");
        idMolde = (String) getIntent().getExtras().getString("idMolde");
        imagenCamara = (ImageView)findViewById(R.id.id_imagen);

        TextView textId = (TextView) findViewById(R.id.textIdMaquinaMolde);

        textId.setText("Maquina : " + idMaquina + "\nMolde : " + idMolde);
    }

    public void setBotonCamara(View v){
        ventanaCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(ventanaCamara, 0);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Bundle extra = data.getExtras();
            bmp = (Bitmap)extra.get("data");
            imagenCamara.setImageBitmap(bmp);
        }
    }

}
