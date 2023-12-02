package com.example.socios_club_parana;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import java.util.Date;

public class Socio {
    @FXML
    private String nombre, apellido, telefono, edad, nroSocio, nacionalidad, dni, ec, profesion,
             domicilio, localidad, email, nomTutor, telTutor, grupoFamiliar,
             nroActaAdmision,  nroActaRenuncia, estadoSocio, fechaNacimiento, fechaAdmision, fechaRenuncia;


    private StringProperty tblViewDni = new SimpleStringProperty();
    private StringProperty tblViewNombre = new SimpleStringProperty();
    private StringProperty tblViewApellido = new SimpleStringProperty();
    private StringProperty tblViewEstado = new SimpleStringProperty();


    public Socio(){

    }



    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNroSocio() {
        return nroSocio;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getDni() {
        return dni;
    }

    public String getEc() {
        return ec;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getEmail() {
        return email;
    }

    public String getNomTutor() {
        return nomTutor;
    }

    public String getTelTutor() {
        return telTutor;
    }

    public String getGrupoFamiliar() {
        return grupoFamiliar;
    }

    public String getNroActaAdmision() {
        return nroActaAdmision;
    }

    public String getNroActaRenuncia() {
        return nroActaRenuncia;
    }

    public String getEstadoSocio() {
        return estadoSocio;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaAdmision() {
        return fechaAdmision;
    }

    public String getFechaRenuncia() {
        return fechaRenuncia;
    }

    public String getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNroSocio(String nroSocio) {
        this.nroSocio = nroSocio;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNomTutor(String nomTutor) {
        this.nomTutor = nomTutor;
    }

    public void setTelTutor(String telTutor) {
        this.telTutor = telTutor;
    }

    public void setGrupoFamiliar(String grupoFamiliar) {
        this.grupoFamiliar = grupoFamiliar;
    }

    public void setNroActaAdmision(String nroActaAdmision) {
        this.nroActaAdmision = nroActaAdmision;
    }

    public void setNroActaRenuncia(String nroActaRenuncia) {
        this.nroActaRenuncia = nroActaRenuncia;
    }

    public void setEstadoSocio(String estadoSocio) {
        this.estadoSocio = estadoSocio;
    }

    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public void setFechaAdmision(String fechaAdmision) {this.fechaAdmision = fechaAdmision;}

    public void setFechaRenuncia(String fechaRenuncia) {
        this.fechaRenuncia = fechaRenuncia;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public StringProperty tblViewDniProperty() {
        return tblViewDni;
    }

    public StringProperty tblViewNombreProperty() {
        return tblViewNombre;
    }

    public StringProperty tblViewApellidoProperty() {
        return tblViewApellido;
    }

    public StringProperty tblViewEstadoProperty() {return tblViewEstado;}
    public void actualizarPropiedadesTableView() {
        tblViewDni.set(this.dni);
        tblViewNombre.set(this.nombre);
        tblViewApellido.set(this.apellido);
        tblViewEstado.set(this.estadoSocio);
        // Actualizar otras propiedades si es necesario
    }


}


