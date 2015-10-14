package com.bactechnologyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListaMantenimiento extends AppCompatActivity {

    private ListViewMantenimientoAdapter adapter;
    private String[] nombreLitasMantenimeinto = new String[]{"Mantenimiento de Moldes","Mantenimiento de Maquina/Inyectores"};
    private int[] imagenListaMantenimiento = {R.drawable.icon01,R.drawable.icon02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mantenimiento);

        final ListView listaMantenimiento = (ListView) findViewById(R.id.id_lista_mantenimiento);
        adapter = new ListViewMantenimientoAdapter(this, nombreLitasMantenimeinto, imagenListaMantenimiento);
        listaMantenimiento.setAdapter(adapter);


        listaMantenimiento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "presiono " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Intent ventanaMaquina = new Intent(ListaMantenimiento.this, ListaMaquina.class);
                    startActivity(ventanaMaquina);
                }
            }
        });

    }
}
