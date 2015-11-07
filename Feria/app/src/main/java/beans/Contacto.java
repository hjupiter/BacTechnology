package beans;

/**
 * Created by Angel on 06/11/2015.
 */
public class Contacto {
    private long id;
    private String nombre;
    private String cargo;
    private String empresa;
    private String ciudad;
    private String telefono;
    private String correo;
    private String interes;

    public Contacto(long id,String nombre, String cargo, String empresa, String ciudad, String telefono, String correo, String interes){
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.empresa = empresa;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.correo = correo;
        this.interes = interes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre;
    }


}
