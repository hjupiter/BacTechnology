package com.bactechnologyapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private Button login;
    private ProgressDialog progressDialog;
    private Context context;
    public EditText usuario;
    private EditText contraseña;
    private boolean autenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_login);
        usuario = (EditText)findViewById(R.id.txtUsuario);
        contraseña = (EditText)findViewById(R.id.txtContraseña);
        login = (Button)findViewById(R.id.btnLogin);
        login.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DownloadTask2().execute("");
            progressDialog = ProgressDialog.show(context, "Por favor espere", "Autenticando");
        }
    };


    private class DownloadTask2 extends AsyncTask<String,Void,Object> {

        protected Integer doInBackground(String... args) {
            cargaDatosWS ws = new cargaDatosWS();
            autenticacion = ws.getAutenticacion(usuario.getText().toString(), contraseña.getText().toString());
            return 1;
        }

        protected void onPostExecute(Object result){
            progressDialog.dismiss();
            if(autenticacion) {
                Intent login = new Intent(Login.this, ListaMantenimiento.class);
                login.putExtra("usuario",usuario.getText().toString());
                startActivity(login);
            }else{
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Error en Usuario o Contraseña");
                alert.setMessage("Asegurese si tiene mayusculas o minusculas su usuario o contraseña");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
            }
            super.onPostExecute(result);
        }

    }


}
