package com.feria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.sql.SQLException;

import conexion.AdaptadorDB;

public class UptContacto extends AppCompatActivity {
    private long id;
    private EditText nombre;
    private EditText cargo;
    private EditText empresa;
    private EditText ciudad;
    private EditText telefono;
    private EditText correo;
    private EditText interes;
    private AdaptadorDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upt_contacto);

        db = new AdaptadorDB(this);

        Bundle bundle = getIntent().getExtras();
        nombre = (EditText)findViewById(R.id.edtDatosNombre);
        nombre.setText(bundle.getString("nombre"));
        nombre.setEnabled(false);

        cargo = (EditText)findViewById(R.id.edtDatosCargo);
        cargo.setText(bundle.getString("cargo"));
        cargo.setEnabled(false);

        empresa = (EditText)findViewById(R.id.edtDatosEmpresa);
        empresa.setText(bundle.getString("empresa"));
        empresa.setEnabled(false);

        ciudad = (EditText)findViewById(R.id.edtDatosCiudad);
        ciudad.setText(bundle.getString("ciudad"));
        ciudad.setEnabled(false);

        telefono = (EditText)findViewById(R.id.edtDatosTelefono);
        telefono.setText(bundle.getString("telefono"));
        telefono.setEnabled(false);

        correo = (EditText)findViewById(R.id.edtDatosCorreo);
        correo.setText(bundle.getString("correo"));
        correo.setEnabled(false);

        interes = (EditText)findViewById(R.id.edtDatosInteres);
        interes.setText(bundle.getString("interes"));
        interes.setEnabled(false);

        id = (bundle.getLong("id"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datos_contacto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String nombr = nombre.getText().toString();
        String carg = cargo.getText().toString();
        String empres = empresa.getText().toString();
        String ciuda = ciudad.getText().toString();
        String telefon = telefono.getText().toString();
        String corre = correo.getText().toString();
        String intere = interes.getText().toString();
        //noinspection SimplifiableIfStatement
        try {
            db.abrir();
            if(id == R.id.itemEditarContacto) {
                nombre.setEnabled(true);
                cargo.setEnabled(true);
                empresa.setEnabled(true);
                ciudad.setEnabled(true);
                telefono.setEnabled(true);
                correo.setEnabled(true);
                interes.setEnabled(true);
                return true;
            }
            if(id == R.id.itemAceptarEditar){
                db.updateContacto(nombr, carg, empres, ciuda, telefon, corre,intere, this.id);
                refreshMain();
                this.finish();
                return true;
            }
            db.cerrar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshMain(){
        Intent inte;
        inte = new Intent(this,MainActivity.class);
        startActivity(inte);
    }
}
