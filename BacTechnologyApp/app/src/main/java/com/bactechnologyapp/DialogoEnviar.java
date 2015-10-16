package com.bactechnologyapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by bakamedi on 11/10/2015.
 */
public class DialogoEnviar extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the message to display.
        builder.setMessage("¿Esta seguro de enviar el reporte?");

        // Set a listener to be invoked when the positive button of the dialog
        // is pressed.
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a listener to be invoked when the negative button of the dialog
        // is pressed.
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
