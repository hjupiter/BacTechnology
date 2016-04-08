package com.bactechnologyapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListaMolde extends AppCompatActivity {

    private String idMaquina;
    private String idMolde;
    private EditText buscarMolde;
    private String[] moldes;
    private ListView listaMoldes;
    private String usuario;
    private int posMenu;
    private View idLayoutSinMolde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_molde);

        //android.app.ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        moldes = getIntent().getExtras().getStringArray("array");
        idMaquina = (String) getIntent().getExtras().getString("idMaquina");

        usuario = getIntent().getExtras().getString("usuario");

        posMenu = getIntent().getExtras().getInt("posicion");
        idLayoutSinMolde = (View)findViewById(R.id.ID_LAYOUT_SIN_MOLDE);
        if(posMenu == 0){
            idLayoutSinMolde.setEnabled(true);
            Button idbtn = (Button)findViewById(R.id.id_btn_sin_molde);
            idbtn.setEnabled(true);
        }
        if(posMenu == 1){
            idLayoutSinMolde.setVisibility(View.GONE);
            Button idbtn = (Button)findViewById(R.id.id_btn_sin_molde);
            idbtn.setEnabled(false);
        }

        listaMoldes = (ListView)findViewById(R.id.id_lista_molde);
        buscarMolde = (EditText)findViewById(R.id.id_buscar_molde);

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, moldes);
        listaMoldes.setAdapter(adaptador);

        buscarMolde.addTextChangedListener(new TextWatcher() {
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

        listaMoldes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + moldes[position], Toast.LENGTH_SHORT).show();
                idMolde = moldes[position];
                Toast.makeText(getApplicationContext(), " MENU " + posMenu, Toast.LENGTH_SHORT).show();
                eligeVentanaMaquinaMolde(posMenu,idMolde);
            }
        });
    }

    public void ventanaSinMolde(View v){
        eligeVentanaMaquinaMolde(posMenu,"000-000");
    }

    private void eligeVentanaMaquinaMolde(int posMenu,String idMolde){
        if(posMenu == 0){
            Intent ventanaMaquinaMolde = new Intent(ListaMolde.this, MaquinaMolde.class);
            ventanaMaquinaMolde.putExtra("idMolde", idMolde);
            ventanaMaquinaMolde.putExtra("idMaquina", idMaquina);
            ventanaMaquinaMolde.putExtra("usuario", usuario);
            startActivity(ventanaMaquinaMolde);
            ListaMolde.this.finish();
        }

        if(posMenu == 1){
            Intent ventanaMantenimientoMolde = new Intent(ListaMolde.this, MantenimientoMolde.class);
            ventanaMantenimientoMolde.putExtra("idMolde", idMolde);
            ventanaMantenimientoMolde.putExtra("idMaquina", idMaquina);
            ventanaMantenimientoMolde.putExtra("usuario", usuario);
            startActivity(ventanaMantenimientoMolde);
            ListaMolde.this.finish();
        }
    }
}
