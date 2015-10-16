package com.bactechnologyapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;

public class MaquinaMolde extends AppCompatActivity {
    private Intent ventanaCamara;
    private Button botonAtras,botonEnviar;
    private ImageView imagenCamara;
    private String idMolde;
    private String idMaquina;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_molde);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        idMaquina = (String) getIntent().getExtras().getString("idMaquina");
        idMolde = (String) getIntent().getExtras().getString("idMolde");
        imagenCamara = (ImageView)findViewById(R.id.id_imagen);
        botonEnviar = (Button)findViewById(R.id.id_boton_enviar);

        TextView textId = (TextView) findViewById(R.id.textIdMaquinaMolde);

        textId.setText("Maquina : " + idMaquina + "\nMolde : " + idMolde);

        imagenCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventanaCamara = new Intent(MaquinaMolde.this, ImagenCamara.class);
                ventanaCamara.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                ventanaCamara.putExtra("foto", bmp);
                startActivity(ventanaCamara);
            }
        });

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fg = getFragmentManager();
                DialogoEnviar dialogoEnviar = new DialogoEnviar();
                dialogoEnviar.show(fg,"EnviarReporte");
            }

        });

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

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        //return;
    }

}
