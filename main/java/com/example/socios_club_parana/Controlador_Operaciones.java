package com.example.socios_club_parana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controlador_Operaciones implements Initializable {

    @FXML
    public Button btnAgregarSocio, btnModifSocio, btnGuardarSocio, btnEliminarSocio, btnIngresarPago, btnModifPago, btnEliminarPago;
    @FXML
    public AnchorPane panelDatosSocio;
    @FXML
    public Pane panelBotonesSocio;
    @FXML
    public ListView lstPagosSocio;
    @FXML
    public static String rutaArchivoDatosSocio = "src\\main\\resources\\com\\example\\socios_club_parana\\Archivos\\DatosSocio.txt";

    @FXML
    public TableView<SocioObservable> tablaSocios;
    @FXML
    public TableColumn<SocioObservable,String> colDni;
    @FXML
    public TableColumn<SocioObservable,String> colNombre;
    @FXML
    public TableColumn<SocioObservable,String> colApellido;
    @FXML
    public TableColumn<SocioObservable,String> colEstado;

    public List<Socio> lstSocios = new ArrayList<>();
    public List <SocioObservable> lstSocioObservable;
    public  ObservableList<SocioObservable> lstSociosObservableFront;



    @FXML
    public TextField campoBuscarSocioPorDNI, campoBuscarSocioPorNombre, campoNombre, campoApellido, campoTelefono, campoEdad, campoMatricula,
            campoNacionalidad, campoDni, campoEc, campoProfesion, campoDomicilio,
            campoLocalidad, campoEmail, campoTutor, campoTelTutor, campoGrupoFamiliar,campoFechaNacimiento, campoFechaAdmision,
            campoNroActaAdmision, campoFechaRenuncia, campoNroActaRenuncia, campoEstadoSocio;
    public Controlador_Operaciones(){}
    @FXML
    public void setStatusInicial() throws FileNotFoundException {
        lstSocios = ListarSocios();
        if (!lstSocios.equals(null)) {
            LlenarTablaSociosObservable(lstSocios);
        }
        campoNombre.clear();campoApellido.clear();campoEdad.clear();campoMatricula.clear();
        campoNacionalidad.clear();campoDni.clear();campoEc.clear();campoProfesion.clear();campoFechaNacimiento.clear();
        campoDomicilio.clear();campoLocalidad.clear();campoEmail.clear();campoTutor.clear(); campoEstadoSocio.clear();
        campoTelTutor.clear();campoGrupoFamiliar.clear(); campoFechaAdmision.clear();campoNroActaAdmision.clear();
        campoFechaRenuncia.clear(); campoFechaRenuncia.clear(); campoNroActaRenuncia.clear(); campoTelefono.clear();
        btnModifSocio.setDisable(true);
        btnGuardarSocio.setDisable(true);
        btnEliminarSocio.setDisable(true);
        campoNombre.setDisable(true);
        campoApellido.setDisable(true);
        campoTelefono.setDisable(true);
        campoEdad.setDisable(true);
        campoMatricula.setDisable(true);
        campoNacionalidad.setDisable(true);
        campoDni.setDisable(true);
        campoEc.setDisable(true);
        campoProfesion.setDisable(true);
        campoFechaNacimiento.setDisable(true);
        campoDomicilio.setDisable(true);
        campoLocalidad.setDisable(true);
        campoEmail.setDisable(true);
        campoTutor.setDisable(true);
        campoTelTutor.setDisable(true);
        campoGrupoFamiliar.setDisable(true);
        campoFechaAdmision.setDisable(true);
        campoNroActaAdmision.setDisable(true);
        campoFechaRenuncia.setDisable(true);
        campoNroActaRenuncia.setDisable(true);
        campoEstadoSocio.setDisable(true);
        lstPagosSocio.setDisable(true);
        btnIngresarPago.setDisable(true);
        btnEliminarPago.setDisable(true);
        btnModifPago.setDisable(true);

    }
    @FXML
    public void AgregarSocio (){
        btnModifSocio.setDisable(false);
        btnGuardarSocio.setDisable(false);
        btnEliminarSocio.setDisable(false);
        campoNombre.setDisable(false);
        campoApellido.setDisable(false);
        campoTelefono.setDisable(false);
        campoEdad.setDisable(false);
        campoMatricula.setDisable(false);
        campoNacionalidad.setDisable(false);
        campoDni.setDisable(false);
        campoEc.setDisable(false);
        campoProfesion.setDisable(false);
        campoFechaNacimiento.setDisable(false);
        campoDomicilio.setDisable(false);
        campoLocalidad.setDisable(false);
        campoEmail.setDisable(false);
        campoTutor.setDisable(false);
        campoTelTutor.setDisable(false);
        campoGrupoFamiliar.setDisable(false);
        campoFechaAdmision.setDisable(false);
        campoNroActaAdmision.setDisable(false);
        campoFechaRenuncia.setDisable(false);
        campoNroActaRenuncia.setDisable(false);
        campoEstadoSocio.setDisable(false);
        lstPagosSocio.setDisable(false);
        btnIngresarPago.setDisable(false);
        btnEliminarPago.setDisable(false);
        btnModifPago.setDisable(false);
        campoNombre.clear();campoApellido.clear();campoTelefono.clear();campoEdad.clear();campoMatricula.clear();
        campoNacionalidad.clear();campoDni.clear();campoEc.clear();campoProfesion.clear();campoFechaNacimiento.clear();
        campoDomicilio.clear();campoLocalidad.clear();campoEmail.clear();campoTutor.clear();
        campoTelTutor.clear();campoGrupoFamiliar.clear(); campoFechaAdmision.clear();campoNroActaAdmision.clear();
        campoFechaRenuncia.clear(); campoFechaRenuncia.clear(); campoNroActaRenuncia.clear();campoEstadoSocio.clear();
    }
    @FXML
    public void GuardarDatosSocio() throws IOException {
        Socio socio = new Socio();

        if (!campoNombre.getText().isEmpty()) {
            socio.setNombre(campoNombre.getText());
        }else{
            socio.setNombre("null");
        }
        if (!campoApellido.getText().isEmpty()) {
            socio.setApellido(campoApellido.getText());
        }else{
            socio.setApellido("null");
        }
        if (!campoTelefono.getText().isEmpty()) {
            socio.setTelefono(campoTelefono.getText());
        }else{
            socio.setTelefono("null");
        }
        if (!campoEdad.getText().isEmpty()) {
            socio.setEdad(campoEdad.getText());
        }else{
            socio.setEdad("null");
        }
        if (!campoMatricula.getText().isEmpty()) {//nro Socio = nro Matricula
            socio.setNroSocio(campoMatricula.getText());
        }else{
            socio.setNroSocio("null");
        }
        if (!campoNacionalidad.getText().isEmpty()) {
            socio.setNacionalidad(campoNacionalidad.getText());
        }else{
            socio.setNacionalidad("null");
        }
        if (!campoDni.getText().isEmpty()) {
            socio.setDni(campoDni.getText());
        }else{
            socio.setDni("null");
        }
        if (!campoEc.getText().isEmpty()) {
            socio.setEc(campoEc.getText());
        }else{
            socio.setEc("null");
        }
        if (!campoProfesion.getText().isEmpty()) {
            socio.setProfesion(campoProfesion.getText());
        }else{
            socio.setProfesion("null");
        }
        if (!campoFechaNacimiento.getText().isEmpty()) {
            socio.setFechaNacimiento(campoFechaNacimiento.getText());
        }else{
            socio.setFechaNacimiento("null");
        }
        if (!campoLocalidad.getText().isEmpty()) {
            socio.setLocalidad(campoLocalidad.getText());
        }else{
            socio.setLocalidad("null");
        }
        if (!campoEmail.getText().isEmpty()) {
            socio.setEmail(campoEmail.getText());
        }else{
            socio.setEmail("null");
        }
        if (!campoDomicilio.getText().isEmpty()) {
            socio.setDomicilio(campoDomicilio.getText());
        }else{
            socio.setDomicilio("null");
        }
        if (!campoTutor.getText().isEmpty()) {
            socio.setNomTutor(campoTutor.getText());
        }else{
            socio.setNomTutor("null");
        }
        if (!campoTelTutor.getText().isEmpty()) {
            socio.setTelTutor(campoTelTutor.getText());
        }else{
            socio.setTelTutor("null");
        }
        if (!campoGrupoFamiliar.getText().isEmpty()) {
            socio.setGrupoFamiliar(campoGrupoFamiliar.getText());
        }else{
            socio.setGrupoFamiliar("null");
        }
        if (!campoFechaAdmision.getText().isEmpty()) {
            socio.setFechaAdmision(campoFechaAdmision.getText());
        }else{
            socio.setFechaAdmision("null");
        }
        if (!campoNroActaAdmision.getText().isEmpty()) {
            socio.setNroActaAdmision(campoNroActaAdmision.getText());
        }else{
            socio.setNroActaAdmision("null");
        }
        if (!campoFechaRenuncia.getText().isEmpty()) {
            socio.setFechaRenuncia(campoFechaRenuncia.getText());
        }else{
            socio.setFechaRenuncia("null");
        }
        if (!campoNroActaRenuncia.getText().isEmpty()) {
            socio.setNroActaRenuncia(campoNroActaRenuncia.getText());
        }else{
            socio.setNroActaRenuncia("null");
        }
        if (!campoEstadoSocio.getText().isEmpty()) {
            socio.setEstadoSocio(campoEstadoSocio.getText());
        }else{
            socio.setEstadoSocio("null");
        }
        GuardarEnArchivoTexto(socio);
    }
    @FXML
    public void GuardarEnArchivoTexto(Socio socio) throws IOException {
        boolean socioEncontrado = false;
        try{
            List<String> lstLineasDatosSociosCSV = new ArrayList<>();

            BufferedReader lectorCSV = new BufferedReader(new FileReader(rutaArchivoDatosSocio));

            String lineaCSV;
            while((lineaCSV = lectorCSV.readLine()) != null){
                if (lineaCSV.length() >= 7) {
                    String dniSocioCSV = lineaCSV.substring(0,7);
                    if(dniSocioCSV.equals(socio.getDni())){
                        Alert socioExistente = new Alert(Alert.AlertType.WARNING);
                        socioExistente.setTitle("Socio Existente");
                        socioExistente.setContentText("Ya existe un socio con el Dni ingresado.");
                        socioExistente.showAndWait();
                        socioEncontrado = true;
                        break;
                    }
                }
                lstLineasDatosSociosCSV.add(lineaCSV);
            }
            if(!socioEncontrado){
                StringBuilder constructorNuevaLinea = new StringBuilder();
                constructorNuevaLinea.append(socio.getDni()).append("**").append(socio.getNombre()).append("**").append(socio.getApellido()).append("**").
                        append(socio.getTelefono()).append("**").append(socio.getEdad()).append("**").append(socio.getNroSocio()).append("**").append(socio.getNacionalidad()).append("**")
                        .append(socio.getEc()).append("**").append(socio.getProfesion()).append("**").append(socio.getFechaNacimiento())
                        .append("**").append(socio.getDomicilio()).append("**").append(socio.getLocalidad()).append("**").append(socio.getEmail()).append("**").append(socio.getNomTutor()).append("**")
                        .append(socio.getTelTutor()).append("**").append(socio.getGrupoFamiliar()).append("**").append(socio.getFechaAdmision()).append("**")
                        .append(socio.getNroActaAdmision()).append("**").append(socio.getFechaRenuncia()).append("**").append(socio.getNroActaRenuncia()).append("**").append(socio.getEstadoSocio());
                lstLineasDatosSociosCSV.add(constructorNuevaLinea.toString());
            }
            BufferedWriter escribirCSV = new BufferedWriter(new FileWriter(rutaArchivoDatosSocio));
            for (String linea : lstLineasDatosSociosCSV) {
                escribirCSV.write(linea);
                escribirCSV.newLine();
            }
            escribirCSV.close();
            setStatusInicial();
            Alert datosGuardados = new Alert(Alert.AlertType.INFORMATION);
            datosGuardados.setTitle("Datos Guardados");
            datosGuardados.setContentText("Los datos se han guardado con éxito.");
            datosGuardados.showAndWait();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public static List<Socio> ListarSocios() throws FileNotFoundException {
        List<Socio> listaSocios = new ArrayList<>();

        try (BufferedReader lectorCSV = new BufferedReader(new FileReader(rutaArchivoDatosSocio))) {
            String lineaCSV;
            while ((lineaCSV = lectorCSV.readLine()) != null) {

                String[] datosSocio = lineaCSV.split("\\*\\*");
                Socio socio = new Socio();
                socio.setDni(datosSocio[0]);
                socio.setNombre(datosSocio[1]);
                socio.setApellido(datosSocio[2]);
                socio.setTelefono(datosSocio[3]);
                socio.setEdad(datosSocio[4]);
                socio.setNroSocio(datosSocio[5]);
                socio.setNacionalidad(datosSocio[6]);
                socio.setEc(datosSocio[7]);
                socio.setProfesion(datosSocio[8]);
                socio.setFechaNacimiento(datosSocio[9]);
                socio.setDomicilio(datosSocio[10]);
                socio.setLocalidad(datosSocio[11]);
                socio.setEmail(datosSocio[12]);
                socio.setNomTutor(datosSocio[13]);
                socio.setTelTutor(datosSocio[14]);
                socio.setGrupoFamiliar(datosSocio[15]);
                socio.setFechaAdmision(datosSocio[16]);
                socio.setNroActaAdmision(datosSocio[17]);
                socio.setFechaRenuncia(datosSocio[18]);
                socio.setNroActaRenuncia(datosSocio[19]);
                socio.setEstadoSocio(datosSocio[20]);

                listaSocios.add(socio);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaSocios;
    }

    @FXML
    public void LlenarTablaSociosObservable(List<Socio> lstSocios){
        lstSocioObservable = new ArrayList<>();
        int i=0;
        for (Socio socio: lstSocios) {
            SocioObservable socioObservable = new SocioObservable(socio.getDni(), socio.getNombre(), socio.getApellido(),socio.getEstadoSocio());
            lstSocioObservable.add(socioObservable);
            System.out.println(lstSocioObservable.get(i).getDni() + " " + lstSocioObservable.get(i).getNombre() + " " +
                    lstSocioObservable.get(i).getApellido() + " " + lstSocioObservable.get(i).getEstado());
            i++;
        }
        lstSociosObservableFront = FXCollections.observableArrayList(lstSocioObservable);
        tablaSocios.setItems(lstSociosObservableFront);
    }

    @FXML
    public void ModificarDatosSocio (){
        btnModifSocio.setDisable(false);
        btnGuardarSocio.setDisable(false);
        btnEliminarSocio.setDisable(false);
        campoNombre.setDisable(false);
        campoApellido.setDisable(false);
        campoTelefono.setDisable(false);
        campoEdad.setDisable(false);
        campoMatricula.setDisable(false);
        campoNacionalidad.setDisable(false);
        campoDni.setDisable(false);
        campoEc.setDisable(false);
        campoProfesion.setDisable(false);
        campoFechaNacimiento.setDisable(false);
        campoDomicilio.setDisable(false);
        campoLocalidad.setDisable(false);
        campoEmail.setDisable(false);
        campoTutor.setDisable(false);
        campoTelTutor.setDisable(false);
        campoGrupoFamiliar.setDisable(false);
        campoFechaAdmision.setDisable(false);
        campoNroActaAdmision.setDisable(false);
        campoFechaRenuncia.setDisable(false);
        campoNroActaRenuncia.setDisable(false);
        campoEstadoSocio.setDisable(false);
        lstPagosSocio.setDisable(false);
        btnIngresarPago.setDisable(false);
        btnEliminarPago.setDisable(false);
        btnModifPago.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        colDni.setStyle("");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
        String cssFilePath = getClass().getResource("styles.css").toExternalForm();
        tablaSocios.getStylesheets().add(cssFilePath);

        colDni.setStyle("-fx-alignment: CENTER_LEFT;");
        colNombre.setStyle("-fx-alignment: CENTER_LEFT;");
        colApellido.setStyle("-fx-alignment: CENTER_LEFT;");
        colEstado.setStyle("-fx-alignment: CENTER_LEFT;");

        tablaSocios.setOnMouseClicked(event -> {
            SocioObservable socioObservableSeleccionado = tablaSocios.getSelectionModel().getSelectedItem();
            if(socioObservableSeleccionado != null){
                String dni = socioObservableSeleccionado.getDni();
                try {
                    setStatusInicial();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                LlenarDatosSocio(dni);
                btnModifSocio.setDisable(false);
                btnIngresarPago.setDisable(false);
                btnModifPago.setDisable(false);
                btnEliminarPago.setDisable(false);
            }
        });
        tablaSocios.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                SocioObservable socioObservableSeleccionado = tablaSocios.getSelectionModel().getSelectedItem();
                if(socioObservableSeleccionado != null){
                    String dni = socioObservableSeleccionado.getDni();
                    try {
                        setStatusInicial();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    LlenarDatosSocio(dni);
                    btnModifSocio.setDisable(false);
                    btnIngresarPago.setDisable(false);
                    btnModifPago.setDisable(false);
                    btnEliminarPago.setDisable(false);

                }
            }
        });
    }

    private void LlenarDatosSocio(String dni) {
        for(Socio socio : lstSocios){
            if(socio.getDni().equals(dni)){
                campoNombre.setText(socio.getNombre());
                campoApellido.setText(socio.getApellido());
                campoTelefono.setText(socio.getTelefono());
                campoEdad.setText(socio.getEdad());
                campoMatricula.setText(socio.getNroSocio());
                campoNacionalidad.setText(socio.getNacionalidad());
                campoDni.setText(socio.getDni());
                campoEc.setText(socio.getEc());
                campoProfesion.setText(socio.getProfesion());
                campoFechaNacimiento.setText(socio.getFechaNacimiento());
                campoDomicilio.setText(socio.getDomicilio());
                campoLocalidad.setText(socio.getLocalidad());
                campoEmail.setText(socio.getEmail());
                campoTutor.setText(socio.getNomTutor());
                campoTelTutor.setText(socio.getTelTutor());
                campoGrupoFamiliar.setText(socio.getGrupoFamiliar());
                campoFechaAdmision.setText(socio.getFechaAdmision());
                campoNroActaAdmision.setText(socio.getNroActaAdmision());
                campoFechaRenuncia.setText(socio.getNroActaRenuncia());
                campoNroActaRenuncia.setText(socio.getNroActaRenuncia());
                campoEstadoSocio.setText(socio.getEstadoSocio());
            }

        }
    }

}