package com.example.bakamedi.pruebadiseno;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent ventanaCamara;
    ImageView imagenItemMaquina,imagenItemMolde,imagenItemReporte,imagenUser,imagenBuscar,imagenBuscar2,imagenCamara;
    Bitmap bmp;
    TableLayout layoutMaquina,layoutMolde,layoutReporte;
    ListView listMaquina,listMolde;
    final String[] maquinas = {"Y-01/15 (HT-120)", "Y-02/15 (HT-120)", "Y-03 (NB-750) ", "Y-04 (750 B) NO EQUIPO HIDRAULICO ", "Y-05/15 (HT-200)","Y-06  (R-300)", "Y-07 (V-17)", "Y-10   (HT-200) ", "Y-11  (HT-160) ", "Y-12 (JM 368)","Y-13 (NB 190)","Y-14 (V-22)"};
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            imagenItemMaquina   = (ImageView)findViewById(R.id.id_imagen_item_maquina);
            imagenItemMolde     = (ImageView)findViewById(R.id.id_imagen_item_molde);
            imagenItemReporte   = (ImageView)findViewById(R.id.id_imagen_item_reporte);
            imagenUser          = (ImageView)findViewById(R.id.id_imagen_user);
            imagenBuscar        = (ImageView)findViewById(R.id.id_imagen_buscar);
            imagenBuscar2       = (ImageView)findViewById(R.id.id_imagen_buscar_2);
            imagenCamara        = (ImageView)findViewById(R.id.id_imagen_camara);

            listMaquina         = (ListView)findViewById(R.id.id_list_view_maquina);
            listMolde           = (ListView)findViewById(R.id.id_list_view_molde);

            layoutMaquina       = (TableLayout)findViewById(R.id.id_layout_maquina);
            layoutMolde         = (TableLayout)findViewById(R.id.id_layout_molde);
            layoutReporte        = (TableLayout)findViewById(R.id.id_layout_reporte);

            final ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, maquinas);
            listMaquina.setAdapter(adaptador1);
            final ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, moldes);
            listMolde.setAdapter(adaptador2);

            imagenItemMaquina.setImageResource(R.drawable.ic_action_next_item);
            imagenItemMolde.setImageResource(R.drawable.ic_action_about);
            imagenItemReporte.setImageResource(R.drawable.ic_action_about);
            imagenUser.setImageResource(R.drawable.ic_action_person);
            imagenBuscar.setImageResource(R.drawable.ic_action_search);
            imagenBuscar2.setImageResource(R.drawable.ic_action_search);

            listMaquina.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    // TODO Auto-generated method stub
                    String idMaquina = maquinas[position];
                    Toast.makeText(getApplicationContext(), idMaquina, Toast.LENGTH_SHORT).show();
                    imagenItemMaquina.setImageResource(R.drawable.ic_action_accept);
                    imagenItemMolde.setImageResource(R.drawable.ic_action_next_item);
                    layoutMaquina.setVisibility(View.GONE);
                    layoutMolde.setVisibility(View.VISIBLE);
                }

            });

            listMolde.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String idMolde = moldes[position];
                    Toast.makeText(getApplicationContext(), idMolde, Toast.LENGTH_SHORT).show();
                    imagenItemMolde.setImageResource(R.drawable.ic_action_accept);
                    imagenItemReporte.setImageResource(R.drawable.ic_action_next_item);
                    layoutMolde.setVisibility(View.GONE);
                    layoutReporte.setVisibility(View.VISIBLE);
                }
            });

    }

    public void setBotonAtras(View v){
        Toast.makeText(getApplicationContext(),"hola mundo",Toast.LENGTH_SHORT);
        if(layoutMolde.getVisibility() == View.VISIBLE && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            imagenItemMolde.setImageResource(R.drawable.ic_action_about);
            imagenItemMaquina.setImageResource(R.drawable.ic_action_next_item);
            layoutMolde.setVisibility(View.GONE);
            layoutMaquina.setVisibility(View.VISIBLE);
        }
        else if(layoutReporte.getVisibility() == View.VISIBLE && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            imagenItemMolde.setImageResource(R.drawable.ic_action_next_item);
            imagenItemReporte.setImageResource(R.drawable.ic_action_about);
            layoutReporte.setVisibility(View.GONE);
            layoutMolde.setVisibility(View.VISIBLE);
        }
    }

    public void setBotonCamara(View v){
        ventanaCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(ventanaCamara, 0);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == Activity.RESULT_OK){
            Bundle extra = data.getExtras();
            bmp = (Bitmap)extra.get("data");
            imagenCamara.setImageBitmap(bmp);
        }
    }


    public void cargaReporte(){

    }

    public void llenaDatosMaquina(){

    }

    public void llenaDatosMolde(){

    }
}
