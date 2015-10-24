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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MaquinaMolde extends AppCompatActivity {
    private Intent ventanaCamara;
    private Button botonAtras,botonEnviar;
    private ImageView imagenCamara;
    private String idMolde;
    private String idMaquina;
    private String opcionNovedad,opcionSolucion;
    private Bitmap bmp;
    private Spinner spinnerNovedad;
    private Spinner spinnerolucion;
    private ArrayAdapter<CharSequence> adapterNovedad;
    private ArrayAdapter<CharSequence> adapterSolucion;

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
        spinnerNovedad = (Spinner)findViewById(R.id.id_tipo_novedad);
        spinnerolucion = (Spinner)findViewById(R.id.id_tipo_solucion);

        adapterNovedad = ArrayAdapter.createFromResource(this,R.array.opcionesNovedad, android.R.layout.simple_spinner_item);
        adapterNovedad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterSolucion = ArrayAdapter.createFromResource(this,R.array.opcionesSolución, android.R.layout.simple_spinner_item);
        adapterSolucion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNovedad.setAdapter(adapterNovedad);
        spinnerolucion.setAdapter(adapterSolucion);

        spinnerNovedad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + "ha sido seleccionado", Toast.LENGTH_LONG);
                //opcionNovedad = (String) Array.get(spinnerNovedad,position);
            }
        });

        spinnerolucion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + "ha sido seleccionado", Toast.LENGTH_LONG);
                //opcionSolucion = (String) Array.get(spinnerolucion,position);
            }
        });

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
