package com.feria;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Contacto;
import conexion.AdaptadorDB;

public class MainActivity extends AppCompatActivity {
    private ListView listaContactos;
    private ArrayList<Contacto> lista = new ArrayList<Contacto>();
    private AdaptadorDB db;
    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listaContactos = (ListView) findViewById(R.id.lstContactos);

        db = new AdaptadorDB(this);
        try {
            db.abrir();

            Cursor c = db.obtenerTodosLosContactos();
            if(c.moveToFirst()){
                do{
                    contacto = new Contacto(Long.parseLong(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
                    lista.add(contacto);
                }while(c.moveToNext());
            }
            ArrayAdapter<Contacto> adapter = new ArrayAdapter<Contacto>(this,android.R.layout.simple_list_item_1,lista);
            listaContactos.setAdapter(adapter);
            db.cerrar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                init(position);
            }
        });
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(item.getItemId()){
            case R.id.itemAñadirContacto:
                initAñadirContacto();
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initAñadirContacto(){
        Intent inte;
        inte = new Intent(this,InsertarContacto.class);
        startActivity(inte);
    }

    public void init(int position){
        Intent inte;
        inte = new Intent(this, UptContacto.class);
        inte.putExtra("id",lista.get(position).getId());
        inte.putExtra("nombre",lista.get(position).getNombre());
        inte.putExtra("cargo",lista.get(position).getCargo());
        inte.putExtra("empresa",lista.get(position).getEmpresa());
        inte.putExtra("ciudad",lista.get(position).getCiudad());
        inte.putExtra("telefono",lista.get(position).getTelefono());
        inte.putExtra("correo",lista.get(position).getCorreo());
        inte.putExtra("interes",lista.get(position).getInteres());
        startActivity(inte);
    }
}
