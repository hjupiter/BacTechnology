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
import android.widget.TabHost;
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
        //tab1
        TabHost TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup();                                                         //lo activamos

        TabHost.TabSpec tab1 = TbH.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = TbH.newTabSpec("tab2");
        TabHost.TabSpec tab3 = TbH.newTabSpec("tab3");

        tab1.setIndicator("UNO");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.ejemplo1); //definimos el id de cada Tab (pestaña)

        tab2.setIndicator("DOS");
        tab2.setContent(R.id.ejemplo2);

        tab3.setIndicator("TRES");
        tab3.setContent(R.id.ejemplo3);

        TbH.addTab(tab1); //añadimos los tabs ya programados
        TbH.addTab(tab2);
        TbH.addTab(tab3);
    }
}
