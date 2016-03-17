package com.bactechnologyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaMaquina extends ActionBarActivity  {

    private ListView listaMaquina;
    private String[] maquinas;
    private EditText buscarMaquina;

    private List moldes;
    private ProgressDialog progressDialog;
    private Context context;
    private int pos;
    private int posMenu;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_maquina);

        context = this;
        maquinas = getIntent().getExtras().getStringArray("array");
        usuario = getIntent().getExtras().getString("usuario");
        posMenu = getIntent().getExtras().getInt("posicion");

        listaMaquina = (ListView)findViewById(R.id.id_lista_maquina);
        buscarMaquina = (EditText)findViewById(R.id.id_buscar_maquina);

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, maquinas);
        listaMaquina.setAdapter(adaptador);

        buscarMaquina.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listaMaquina.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                /*String idMaquina = maquinas[position];
                Toast.makeText(getApplicationContext(), idMaquina, Toast.LENGTH_SHORT).show();
                Intent newActivity = new Intent(ListaMaquina.this, ListaMolde.class);
                newActivity.putExtra("idMaquina", idMaquina);
                startActivity(newActivity);
                */
                pos = position;
                new DownloadTask2().execute("");
                progressDialog = ProgressDialog.show(context, "Por favor espere", "Cargando Molde");
            }

        });

    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home

                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    /*
    @Override
    public void onBackPressed() {
        Toast.makeText(ListaMaquina.this, "HOLA", Toast.LENGTH_SHORT).show();
    }
    */

    private class DownloadTask2 extends AsyncTask<String,Void,Object> {

        protected Integer doInBackground(String... args) {
            cargaDatosWS ws = new cargaDatosWS();
            moldes = new ArrayList();
            moldes = ws.getMoldes();
            return 1;
        }

        protected void onPostExecute(Object result) {
            progressDialog.dismiss();
            String idMaquina = maquinas[pos];
            String[] arrayMoldes = convLisToArray(moldes);
            Intent ventanaMoldes = new Intent(ListaMaquina.this, ListaMolde.class);
            ventanaMoldes.putExtra("array", arrayMoldes);
            ventanaMoldes.putExtra("idMaquina", idMaquina);
            ventanaMoldes.putExtra("usuario",usuario);
            ventanaMoldes.putExtra("posicion",posMenu);
            startActivity(ventanaMoldes);
            //ListaMaquina.this.finish();
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
