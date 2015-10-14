package com.bactechnologyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaMolde extends AppCompatActivity {
    private String idMaquina;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_molde);

        final String[] moldes = {"121 PALA DE BASURA CHICA", "14-83 ESPRIMIDOR PICA"
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
        final ListView listaMoldes;

        idMaquina = (String) getIntent().getExtras().getString("idMaquina");


        listaMoldes = (ListView)findViewById(R.id.id_lista_molde);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, moldes);
        listaMoldes.setAdapter(adaptador);

        listaMoldes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Ha pulsado el item " + moldes[position], Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Maquina:" + idMaquina, Toast.LENGTH_SHORT).show();
                String idMolde = moldes[position];
                Intent ventanaMaquinaMolde = new Intent(ListaMolde.this, MaquinaMolde.class);

                ventanaMaquinaMolde.putExtra("idMolde", idMolde);
                ventanaMaquinaMolde.putExtra("idMaquina", idMaquina);
                startActivity(ventanaMaquinaMolde);
            }
        });


    }
}
