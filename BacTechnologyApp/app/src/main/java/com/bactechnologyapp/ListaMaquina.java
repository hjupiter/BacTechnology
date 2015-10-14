package com.bactechnologyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaMaquina extends AppCompatActivity {

    private String[] maquinas = {"Y-01/15 (HT-120)", "Y-02/15 (HT-120)", "Y-03 (NB-750) ", "Y-04 (750 B) NO EQUIPO HIDRAULICO ", "Y-05/15 (HT-200)","Y-06  (R-300)", "Y-07 (V-17)", "Y-10   (HT-200) ", "Y-11  (HT-160) ", "Y-12 (JM 368)","Y-13 (NB 190)","Y-14 (V-22)"};
    private ListView listaMaquina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_maquina);


        listaMaquina = (ListView)findViewById(R.id.id_lista_maquina);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, maquinas);
        listaMaquina.setAdapter(adaptador);

        listaMaquina.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                String idMaquina = maquinas[position];
                Toast.makeText(getApplicationContext(), idMaquina, Toast.LENGTH_SHORT).show();
                Intent newActivity = new Intent(ListaMaquina.this, ListaMolde.class);
                newActivity.putExtra("idMaquina", idMaquina);
                startActivity(newActivity);
            }

        });

    }
}
