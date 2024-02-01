package ClubParana;

import javafx.beans.property.SimpleStringProperty;

public class SocioObservable {
    private SimpleStringProperty dni;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty estado;

    public SocioObservable(String dni, String nombre, String apellido, String estado){
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.estado = new SimpleStringProperty(estado);
    }

    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni = new SimpleStringProperty(dni);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre) ;
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido = new SimpleStringProperty(apellido);
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado = new SimpleStringProperty(estado);
    }
}
