package conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Angel on 06/11/2015.
 */
public class AdaptadorDB {
    //Globales
    private static final String KEY_IDFILA = "id";

    //Cotacto
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_CARGO = "cargo";
    private static final String KEY_EMPRESA = "empresa";
    private static final String KEY_CIUDAD = "ciudad";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_CORREO = "correo";
    private static final String KEY_INTERES = "interes";

    //Tablas de la base de datos
    private static final String TABLA_CONTACTO ="contacto";


    private static final String TAG = "AdaptadorDB";
    private static final String NOMBRE_BASE = "MiDB";
    private static final int VERSION_BASE = 1;

    //Query para la creacion de bases
    private static final String CREAR_TABLE_CONTACTO = "CREATE TABLE contacto   (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," +
            "                                                        nombre     VARCHAR(30) NOT NULL," +
            "                                                        cargo      VARCHAR(30) NOT NULL," +
            "                                                        empresa    VARCHAR(30) NOT NULL," +
            "                                                        ciudad     VARCHAR(30) NOT NULL," +
            "                                                        telefono   VARCHAR(30) NOT NULL," +
            "                                                        correo     VARCHAR(30) NOT NULL," +
            "                                                        interes    VARCHAR(30) NOT NULL);";

    //dbhelper
    private static class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context) {
            super(context, NOMBRE_BASE, null, VERSION_BASE);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREAR_TABLE_CONTACTO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    //adaptador para la inicializacion del dbhelper
    public AdaptadorDB(Context ctx){
        this.context = ctx;
        DBHelper = new DataBaseHelper(context);
    }

    //abrir conexion
    public AdaptadorDB abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //cerrar conexion
    public void cerrar(){
        DBHelper.close();
    }




    //obtener todas las contactos de la base de datos
    public Cursor obtenerTodosLosContactos(){
        String[] contacto = new String[]{KEY_IDFILA,KEY_NOMBRE,KEY_CARGO,KEY_EMPRESA,KEY_CIUDAD,KEY_TELEFONO,KEY_CORREO, KEY_INTERES};
        return db.query(TABLA_CONTACTO,contacto,null,null,null,null,null);
    }

    public void insertarContacto(String nombre, String cargo, String empresa, String ciudad, String telefono, String correo, String interes){
        ContentValues valores = new ContentValues();
        valores.put(KEY_NOMBRE,nombre);
        valores.put(KEY_CARGO,cargo);
        valores.put(KEY_EMPRESA,empresa);
        valores.put(KEY_CIUDAD,ciudad);
        valores.put(KEY_TELEFONO,telefono);
        valores.put(KEY_CORREO,correo);
        valores.put(KEY_INTERES,interes);
        db.insert(TABLA_CONTACTO,null,valores);
    }

    public Cursor obtenerContacto(long idFila){
        String[] contacto = new String[]{KEY_IDFILA,KEY_NOMBRE,KEY_CARGO,KEY_EMPRESA,KEY_CIUDAD,KEY_TELEFONO,KEY_CORREO,KEY_INTERES};
        Cursor mCursor = db.query(true,TABLA_CONTACTO,contacto,KEY_IDFILA+"="+idFila,
                null,null,null,null,null);
        if(mCursor!=null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //actualizar un contacto en la base
    public void updateContacto(String nombre,String cargo, String empresa, String ciudad, String telefono, String correo, String interes,long id){
        ContentValues valores = new ContentValues();
        valores.put(KEY_NOMBRE,nombre);
        valores.put(KEY_CARGO,cargo);
        valores.put(KEY_EMPRESA,empresa);
        valores.put(KEY_CIUDAD,ciudad);
        valores.put(KEY_TELEFONO,telefono);
        valores.put(KEY_CORREO,correo);
        valores.put(KEY_INTERES,interes);

        db.update(TABLA_CONTACTO,valores,KEY_IDFILA+"="+id,null);
    }


}

