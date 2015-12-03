package com.bactechnologyapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListaMolde extends AppCompatActivity {
    private String idMaquina;
    private EditText buscarMolde;
   /* final String[] moldes = {"121 PALA DE BASURA CHICA", "14-83 ESPRIMIDOR PICA"
            ,"46-2000	BOLSILLO PLATERO","229	EMBUDO CHICO", "9-98	BASE MACETERO JAZMIN PEQ PLATO", "124-2004	BASE JABONERA",
            "141-2004	TAPA JABONERA", "23-93	JARRO PEKES", "25-92	TAPA POMO 2-4 LITROS", "37-99	JABONERA NUEVA DISH",
            "03-2001	MACETERO JAZMIN CHICO TULIPAN (plato)", "129	TAZON BALUN PEQUEÑO", "112-81	ASA BALDE IND CHICO",
            "4F-2010	TAPA REPOSTERO RED LUNCH BOX",
            "4E-2010	BASE TARRINA LONCHERA TERMICA 0,5LT",
            "3C03-2012	TAPA EXPRIMIDOR ORANGE JUICER",
            "3C01-2012	BASE EXPRIMIDOR ORANGE JUICER",
            "46-2000	BOLSILLO PLATERO",
            "229	EMBUDO CHICO",
            "9-98	BASE MACETERO JAZMIN PEQ PLATO",
            "124-2004	BASE JABONERA",
            "141-2004	TAPA JABONERA",
            "23-93	JARRO PEKES",
            "25-92	TAPA POMO 2-4 LITROS",};
            */
    private String[] moldes;
    private ListView listaMoldes;

    private String usuario;

    private int posMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_molde);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        moldes = getIntent().getExtras().getStringArray("array");
        idMaquina = (String) getIntent().getExtras().getString("idMaquina");

        usuario = getIntent().getExtras().getString("usuario");

        posMenu = getIntent().getExtras().getInt("posicion");

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
                Toast.makeText(getApplicationContext(), "Ha pulsado el item " + moldes[position], Toast.LENGTH_SHORT).show();
                String idMolde = moldes[position];

                if(posMenu == 0){
                    Intent ventanaMaquinaMolde = new Intent(ListaMolde.this, MaquinaMolde.class);
                    ventanaMaquinaMolde.putExtra("idMolde", idMolde);
                    ventanaMaquinaMolde.putExtra("idMaquina", idMaquina);
                    ventanaMaquinaMolde.putExtra("usuario",usuario);
                    startActivity(ventanaMaquinaMolde);
                }

                if(posMenu == 1){
                    Intent ventanaMantenimientoMolde = new Intent(ListaMolde.this, MantenimientoMolde.class);
                    ventanaMantenimientoMolde.putExtra("idMolde", idMolde);
                    ventanaMantenimientoMolde.putExtra("idMaquina", idMaquina);
                    ventanaMantenimientoMolde.putExtra("usuario",usuario);
                    startActivity(ventanaMantenimientoMolde);
                }
            }
        });
    }
}
