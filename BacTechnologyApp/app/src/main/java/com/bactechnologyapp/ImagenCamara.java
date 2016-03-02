package com.bactechnologyapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImagenCamara extends AppCompatActivity {

    Bitmap bmp;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_camara);



        bmp = (Bitmap) getIntent().getExtras().get("foto");

        foto = (ImageView)findViewById(R.id.id_foto);
        foto.setImageBitmap(bmp);

    }
}
