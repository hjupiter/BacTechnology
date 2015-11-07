package com.feria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.sql.SQLException;

import conexion.AdaptadorDB;

public class InsertarContacto extends AppCompatActivity {
    private AdaptadorDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_contacto);

        db = new AdaptadorDB(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insertar_contacto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        EditText nombre = (EditText) findViewById(R.id.edtNombre);
        EditText cargo = (EditText) findViewById(R.id.edtCargo);
        EditText empresa = (EditText) findViewById(R.id.edtEmpresa);
        EditText ciudad = (EditText) findViewById(R.id.edtCiudad);
        EditText telefono = (EditText) findViewById(R.id.edtTelefono);
        EditText correo = (EditText) findViewById(R.id.edtCorreo);
        EditText interes = (EditText) findViewById(R.id.edtInteres);

        String nombreIn = nombre.getText().toString();
        String cargoIn = cargo.getText().toString();
        String empresaIn = empresa.getText().toString();
        String ciudadIn = ciudad.getText().toString();
        String telefonoIn = telefono.getText().toString();
        String correoIn = correo.getText().toString();
        String interesIn = interes.getText().toString();


        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.itemAceptar){
            if (nombreIn.length() != 0 && cargoIn.length() != 0 && empresaIn.length() != 0 && ciudadIn.length() != 0 && telefonoIn.length() != 0 && interesIn.length() != 0) {
                try {
                    db.abrir();
                    db.insertarContacto(nombreIn, cargoIn, empresaIn, ciudadIn, telefonoIn, correoIn, interesIn);
                    db.cerrar();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                refreshMain();
                this.finish();
                return true;
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Error");
                alert.setMessage("Debe rellenar todos los campos para continuar");
                alert.setPositiveButton("OK", null);
                alert.create();
                alert.show();
            }
        }
        if(item.getItemId() == R.id.itemCancelar){
            refreshMain();
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshMain(){
        Intent inte;
        inte = new Intent(this,MainActivity.class);
        startActivity(inte);
    }
}
