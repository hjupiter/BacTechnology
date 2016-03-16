package com.bactechnologyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.kobjects.base64.Base64;
import org.ksoap2.serialization.MarshalBase64;

public class MaquinaMolde extends AppCompatActivity {
    private static final String TAG = MaquinaMolde.class.getName();
    private static ArrayList<Activity> activities=new ArrayList<Activity>();
    private Intent ventanaCamara;
    private Button botonAtras,botonEnviar;
    private ImageView imagenCamara;
    private String idMolde;
    private String idMaquina;
    private Bitmap bmp = null;
    private Spinner spinnerNovedad;
    private Spinner spinnerolucion;
    private ArrayAdapter<CharSequence> adapterNovedad;
    private ArrayAdapter<CharSequence> adapterSolucion;

    public EditText reporteNovedad;
    public EditText reporteSolucion;

    EditText novedadDetectada;

    public String opNovedad,opSolucion;

    public ProgressDialog progressDialog;

    public Context context;

    public String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_molde);
        context = this;

        idMaquina = (String) getIntent().getExtras().getString("idMaquina");
        idMolde = (String) getIntent().getExtras().getString("idMolde");
        usuario = getIntent().getExtras().getString("usuario");
        imagenCamara = (ImageView)findViewById(R.id.id_imagen);
        botonEnviar = (Button)findViewById(R.id.id_boton_enviar);
        spinnerNovedad = (Spinner)findViewById(R.id.id_tipo_novedad);
        spinnerolucion = (Spinner)findViewById(R.id.id_tipo_solucion);

        novedadDetectada = (EditText)findViewById(R.id.txtNovedadDetectada);
        reporteNovedad = (EditText)findViewById(R.id.txtReporteNovedad);
        reporteSolucion = (EditText)findViewById(R.id.txtReporteSolucion);


        adapterNovedad = ArrayAdapter.createFromResource(this,R.array.opcionesNovedad, android.R.layout.simple_spinner_item);
        adapterNovedad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterSolucion = ArrayAdapter.createFromResource(this,R.array.opcionesSolución, android.R.layout.simple_spinner_item);
        adapterSolucion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNovedad.setAdapter(adapterNovedad);
        spinnerolucion.setAdapter(adapterSolucion);

        spinnerNovedad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(parent.getContext(), (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                opNovedad = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerolucion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(parent.getContext(), (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                opSolucion = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                //FragmentManager fg = getFragmentManager();
                //DialogoEnviar dialogoEnviar = new DialogoEnviar();
                //dialogoEnviar.show(fg,"EnviarReporte");
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                if(bmp != null){
                    if(reporteNovedad.getText().length() !=0 && reporteSolucion.getText().length() !=0 && novedadDetectada.getText().length() !=0){
                        alert.setTitle("Guardar Reporte");
                        alert.setMessage("Esta seguro de realizar esta accion");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new DownloadTask2().execute("");
                                progressDialog = ProgressDialog.show(context, "Por favor espere", "Enviando Datos");
                                //Toast.makeText(getApplicationContext(), "NO SE ENVIO", Toast.LENGTH_SHORT).show();
                                //finishAll();
                                //restartActivity();
                            }
                        });
                        alert.show();
                    }else{
                        Toast.makeText(getApplicationContext(), reporteNovedad.getText(), Toast.LENGTH_SHORT).show();
                        alert.setTitle("Error al Enviar");
                        alert.setMessage("No debe de haber campos vacios");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alert.show();
                    }
                }else{
                    alert.setTitle("Error en Imagen");
                    alert.setMessage("Se debe de tener una foto de la maquinaria/molde");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                }
            }

        });

    }

    /*
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }
    */

    public static void finishAll()
    {
        for(Activity activity:activities)
            activity.finish();
    }


    private void restartActivity(){
        Intent intent = getIntent();
        finish();
        Intent i = new Intent(MaquinaMolde.this,ListaMantenimiento.class);
        startActivity(i);
        //startActivity(intent);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("bitmap", bmp);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bmp = (Bitmap) savedInstanceState.getParcelable("bitmap");
        imagenCamara.setImageBitmap(bmp);
    }

    /*
    @Override
    public void onBackPressed() {
        // do something on back.
        this.finish();
        //return;
    }
    */

    private class DownloadTask2 extends AsyncTask<String,Void,Object> {

        protected Integer doInBackground(String... args) {

            cargaDatosWS ws = new cargaDatosWS();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
            byte[] imagebyte = out.toByteArray();

            String strBase64 = Base64.encode(imagebyte);
            //String novedadDetectada ="jbvbvlj";
            System.out.println(idMaquina);
            System.out.println(idMolde);
            System.out.println(usuario);
            System.out.println(reporteNovedad.getText().toString());
            System.out.println(reporteSolucion.getText().toString());
            System.out.println(opNovedad);
            System.out.println(opSolucion);
            System.out.println(novedadDetectada.getText().toString());
            System.out.println(strBase64);
            Boolean a = ws.guardarReporte(idMaquina,idMolde,usuario,strBase64,
                                            reporteNovedad.getText().toString(),
                                            reporteSolucion.getText().toString(),
                                            opNovedad,opSolucion,novedadDetectada.getText().toString());
            System.out.println(a);
            /*
            cargaDatosWS ws = new cargaDatosWS();
            String a = ws.hello();
            System.out.println(a);```
            */
            return 1;
        }



        protected void onPostExecute(Object result) {
            progressDialog.dismiss();
            //MaquinaMolde.this.finish();
            super.onPostExecute(result);
        }
    }

}
