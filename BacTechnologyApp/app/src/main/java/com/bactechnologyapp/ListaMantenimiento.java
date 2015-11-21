package com.bactechnologyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaMantenimiento extends AppCompatActivity {

    private ListViewMantenimientoAdapter adapter;
    private List maquinarias;
    private ProgressDialog progressDialog;
    private Context context;
    private String[] nombreLitasMantenimeinto = new String[]{"Mantenimiento de Moldes","Mantenimiento de Maquina/Inyectores"};
    private int[] imagenListaMantenimiento = {R.drawable.icon01,R.drawable.icon02};

    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mantenimiento);
        context = this;
        usuario = getIntent().getExtras().getString("usuario");
        final ListView listaMantenimiento = (ListView) findViewById(R.id.id_lista_mantenimiento);
        adapter = new ListViewMantenimientoAdapter(this, nombreLitasMantenimeinto, imagenListaMantenimiento);
        listaMantenimiento.setAdapter(adapter);


        listaMantenimiento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "presiono " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    new DownloadTask2().execute("");
                    progressDialog = ProgressDialog.show(context, "Por favor espere", "Cargando Maquinarias");
                }
            }
        });

    }

    private class DownloadTask2 extends AsyncTask<String,Void,Object> {

        protected Integer doInBackground(String... args) {
            cargaDatosWS ws = new cargaDatosWS();
            maquinarias = new ArrayList();
            maquinarias = ws.getMaquinarias();
            return 1;
        }

        protected void onPostExecute(Object result) {
            progressDialog.dismiss();
            String[] arrayMaquinarias = convLisToArray(maquinarias);
            Intent ventanaMaquina = new Intent(ListaMantenimiento.this, ListaMaquina.class);
            ventanaMaquina.putExtra("array", arrayMaquinarias);
            ventanaMaquina.putExtra("usuario",usuario);
            startActivity(ventanaMaquina);
            super.onPostExecute(result);
        }
    }

    private String[] convLisToArray(List list){
        String [] array = null;
        array =  new String[list.size()];
        for(int i = 0;i<list.size();i++){
            array[i] = list.get(i).toString();
            System.out.println(array[i]);
        }
        return array;
    }
}
