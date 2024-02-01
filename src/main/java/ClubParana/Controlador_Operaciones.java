package ClubParana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controlador_Operaciones implements Initializable {
    @FXML
    public Label lbErrorNombre, lbErrorApellido,lbErrorDni;
    @FXML
    public Button btnAgregarSocio, btnModifSocio, btnGuardarSocio, btnEliminarSocio, btnIngresarPago, btnModifPago, btnEliminarPago;
    @FXML
    public AnchorPane panelDatosSocio;
    @FXML
    public Pane panelBotonesSocio;
    @FXML
    public TableView lstPagosSocio;
    @FXML
    public static String rutaArchivoDatosSocio = "src\\main\\resources\\Archivos\\DatosSocio.txt";
    @FXML
    public static String rutaArchivoPagos = "src\\main\\resources\\Archivos\\Pagos.txt";


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
    @FXML
    public TableView<PagoObservable> tablaPagos;
    @FXML
    public TableColumn<PagoObservable,String> colFecha;
    @FXML
    public TableColumn<PagoObservable,String> colMonto;
    @FXML
    public TableColumn<PagoObservable,String> colConcepto;

    public List<Socio> lstSocios = new ArrayList<>();
    public List <SocioObservable> lstSocioObservable;
    public List<Pago> lstPagos = new ArrayList();
    public List <PagoObservable> lstPagosObservable;
    public ObservableList<PagoObservable> lstPagosObservablesFront;
    public  ObservableList<SocioObservable> lstSociosObservableFront;

    private boolean modifica;
    private boolean inicio=true;

    @FXML
    public TextField campoBuscarSocioPorDNI, campoBuscarSocioPorNombre, campoNombre, campoApellido, campoTelefono, campoEdad, campoMatricula,
            campoNacionalidad, campoDni, campoEc, campoProfesion, campoDomicilio,
            campoLocalidad, campoEmail, campoTutor, campoTelTutor, campoGrupoFamiliar,
            campoNroActaAdmision, campoNroActaRenuncia, campoEstadoSocio;
    @FXML
    TextField campoMonto, campoConcepto;
    @FXML
    DatePicker dtPickerFechaPago, campoFechaNacimiento, campoFechaAdmision, campoFechaRenuncia;


    public Controlador_Operaciones(){}
    @FXML
    public void setStatusInicial() throws FileNotFoundException {
        if(inicio){
            campoBuscarSocioPorDNI.requestFocus();
        }
        modifica = false;
        lstPagos = ListarPagos();
        lstSocios = ListarSocios();
        if (!lstSocios.equals(null) && !lstPagos.equals((null))) {
            LlenarTablaSociosObservable(lstSocios);
        }
        LimpiarCampos();
        DeshabilitarCamposYBotones();
    }

    private void LimpiarCampos() {
        campoNombre.clear();campoApellido.clear();campoEdad.clear();campoMatricula.clear();
        campoNacionalidad.clear();campoDni.clear();campoEc.clear();campoProfesion.clear();campoFechaNacimiento.setValue(null);
        campoDomicilio.clear();campoLocalidad.clear();campoEmail.clear();campoTutor.clear(); campoEstadoSocio.clear();
        campoTelTutor.clear();campoGrupoFamiliar.clear(); campoFechaAdmision.setValue(null);campoNroActaAdmision.clear();
        campoFechaRenuncia.setValue(null); campoFechaRenuncia.setValue(null); campoNroActaRenuncia.clear(); campoTelefono.clear();
        tablaPagos.getItems().clear(); tablaSocios.refresh();lbErrorApellido.setText("");lbErrorNombre.setText("");lbErrorDni.setText("");
        campoNombre.setStyle("-fx-border-color: transparent;"); campoApellido.setStyle("-fx-border-color: transparent;");
        campoDni.setStyle("-fx-border-color: transparent;");
    }

    private void DeshabilitarCamposYBotones() {
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
        campoEstadoSocio.setStyle("");
        tablaPagos.setDisable(true);
        btnIngresarPago.setDisable(true);
        btnEliminarPago.setDisable(true);
        btnModifPago.setDisable(true);
    }

    @FXML
    public void AgregarSocio (){
        btnModifSocio.setDisable(true);
        btnGuardarSocio.setDisable(false);
        btnEliminarSocio.setDisable(true);
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
        //campoEstadoSocio.setDisable(false);
        campoEstadoSocio.setStyle("");
        tablaPagos.setDisable(false);
        btnIngresarPago.setDisable(false);
        btnEliminarPago.setDisable(false);
        btnModifPago.setDisable(false);
        campoNombre.clear();campoApellido.clear();campoTelefono.clear();campoEdad.clear();campoMatricula.clear();
        campoNacionalidad.clear();campoDni.clear();campoEc.clear();campoProfesion.clear();campoFechaNacimiento.setValue(null);
        campoDomicilio.clear();campoLocalidad.clear();campoEmail.clear();campoTutor.clear();
        campoTelTutor.clear();campoGrupoFamiliar.clear(); campoFechaAdmision.setValue(null);campoNroActaAdmision.clear();
        campoFechaRenuncia.setValue(null); campoFechaRenuncia.setValue(null); campoNroActaRenuncia.clear();campoEstadoSocio.clear();
        tablaPagos.getItems().clear();
    }
    @FXML
    public void GuardarDatosSocio() throws IOException {
        Socio socio = new Socio();
        boolean datosCompletos=true;

        if (!campoNombre.getText().isEmpty()) {
            lbErrorNombre.setText("");
            //campoNombre.getStyleClass().clear();
            campoNombre.setStyle("-fx-border-color: transparent;");
            socio.setNombre(campoNombre.getText());
        }else{
            lbErrorNombre.setText("Nombre no puede estar vacío");
            campoNombre.setStyle("-fx-border-color: red;");
            lbErrorNombre.setStyle("-fx-text-fill: red;");
            datosCompletos = false;

        }
        if (!campoApellido.getText().isEmpty()) {
            lbErrorApellido.setText("");
            campoApellido.setStyle("-fx-border-color: transparent;");
            //campoApellido.getStyleClass().clear();
            socio.setApellido(campoApellido.getText());
        }else{
            lbErrorApellido.setText("Apellido no puede estar vacío");
            campoApellido.setStyle("-fx-border-color: red;");
            lbErrorApellido.setStyle("-fx-text-fill: red;");
            datosCompletos = false;
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
        if (campoDni.getText().toString().length() != 8) {
            lbErrorDni.setText("Cantidad de Números Incorrecta");
            campoDni.setStyle("-fx-border-color: red;");
            lbErrorDni.setStyle("-fx-text-fill: red;");
            datosCompletos = false;
        }else{
            if (!campoDni.getText().isEmpty()) {
                lbErrorDni.setText("");
                campoDni.setStyle("-fx-border-color: transparent;");
                //campoDni.getStyleClass().clear();
                socio.setDni(campoDni.getText());
            } else if (campoDni.getText().isEmpty()) {
                lbErrorDni.setText("Debe completar");
                campoDni.setStyle("-fx-border-color: red;");
                lbErrorDni.setStyle("-fx-text-fill: red;");
                datosCompletos = false;
            }
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
        if (campoFechaNacimiento.getValue()!=null) {
            socio.setFechaNacimiento(campoFechaNacimiento.getValue().toString());
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
        if (campoFechaAdmision.getValue()!=null) {
            socio.setFechaAdmision(campoFechaAdmision.getValue().toString());
        }else{
            socio.setFechaAdmision("null");
        }
        if (!campoNroActaAdmision.getText().isEmpty()) {
            socio.setNroActaAdmision(campoNroActaAdmision.getText());
        }else{
            socio.setNroActaAdmision("null");
        }
        if (campoFechaRenuncia.getValue()!=null) {
            socio.setFechaRenuncia(campoFechaRenuncia.getValue().toString());
        }else{
            socio.setFechaRenuncia("null");
        }
        if (!campoNroActaRenuncia.getText().isEmpty()) {
            socio.setNroActaRenuncia(campoNroActaRenuncia.getText());
        }else{
            socio.setNroActaRenuncia("null");
        }
        if (datosCompletos) {
            GuardarEnArchivoTexto(socio);
            setStatusInicial();
        }
    }
    @FXML
    public void GuardarEnArchivoTexto(Socio socio) throws IOException {
        boolean socioRepetido = false;
        boolean socioEncontrado = false;
        int posSocioEncontrado=0;
        try{
            List<String> lstLineasDatosSociosCSV = new ArrayList<>();
            BufferedReader lectorCSV = new BufferedReader(new FileReader(rutaArchivoDatosSocio));
            String lineaCSV;
            while((lineaCSV = lectorCSV.readLine()) != null ){
                if (lineaCSV.length() >= 7) {
                    String dniSocioCSV = lineaCSV.substring(0,8);
                    if(dniSocioCSV.equals(socio.getDni()) && !modifica){
                        Alert socioExistente = new Alert(Alert.AlertType.WARNING);
                        socioExistente.setTitle("Socio Existente");
                        socioExistente.setContentText("Ya existe un socio con el Dni ingresado.");
                        socioExistente.showAndWait();
                        socioRepetido = true;
                        break;
                    }
                    if (dniSocioCSV.equals(socio.getDni()) && modifica) {
                        socioEncontrado = true;
                    }
                }
                lstLineasDatosSociosCSV.add(lineaCSV);
                if (!socioEncontrado) {
                    posSocioEncontrado++;
                }
            }
            if (!socioRepetido) {
                if (!socioEncontrado) {
                    StringBuilder constructorNuevaLinea = new StringBuilder();
                    constructorNuevaLinea.append(socio.getDni()).append("**").append(socio.getNombre()).append("**").append(socio.getApellido()).append("**").
                            append(socio.getTelefono()).append("**").append(socio.getEdad()).append("**").append(socio.getNroSocio()).append("**").append(socio.getNacionalidad()).append("**")
                            .append(socio.getEc()).append("**").append(socio.getProfesion()).append("**").append(socio.getFechaNacimiento())
                            .append("**").append(socio.getDomicilio()).append("**").append(socio.getLocalidad()).append("**").append(socio.getEmail()).append("**").append(socio.getNomTutor()).append("**")
                            .append(socio.getTelTutor()).append("**").append(socio.getGrupoFamiliar()).append("**").append(socio.getFechaAdmision()).append("**")
                            .append(socio.getNroActaAdmision()).append("**").append(socio.getFechaRenuncia()).append("**").append(socio.getNroActaRenuncia());
                    lstLineasDatosSociosCSV.add(constructorNuevaLinea.toString());
                } else if (socioEncontrado && modifica) {
                    StringBuilder constructorNuevaLinea = new StringBuilder();
                    constructorNuevaLinea.append(socio.getDni()).append("**").append(socio.getNombre()).append("**").append(socio.getApellido()).append("**").
                            append(socio.getTelefono()).append("**").append(socio.getEdad()).append("**").append(socio.getNroSocio()).append("**").append(socio.getNacionalidad()).append("**")
                            .append(socio.getEc()).append("**").append(socio.getProfesion()).append("**").append(socio.getFechaNacimiento())
                            .append("**").append(socio.getDomicilio()).append("**").append(socio.getLocalidad()).append("**").append(socio.getEmail()).append("**").append(socio.getNomTutor()).append("**")
                            .append(socio.getTelTutor()).append("**").append(socio.getGrupoFamiliar()).append("**").append(socio.getFechaAdmision()).append("**")
                            .append(socio.getNroActaAdmision()).append("**").append(socio.getFechaRenuncia()).append("**").append(socio.getNroActaRenuncia());
                    lstLineasDatosSociosCSV.set(posSocioEncontrado,constructorNuevaLinea.toString());
                }
                BufferedWriter escribirCSV = new BufferedWriter(new FileWriter(rutaArchivoDatosSocio));
                for (String linea : lstLineasDatosSociosCSV) {
                    escribirCSV.write(linea);
                    escribirCSV.newLine();
                }
                escribirCSV.close();
                if (!modifica) {
                    Alert datosGuardados = new Alert(Alert.AlertType.INFORMATION);
                    datosGuardados.setTitle("Datos Guardados");
                    datosGuardados.setContentText("Los datos se han guardado con éxito.");
                    datosGuardados.showAndWait();
                }else{
                    Alert datosGuardados = new Alert(Alert.AlertType.INFORMATION);
                    datosGuardados.setTitle("Datos Modificados");
                    datosGuardados.setContentText("Se han modificado con éxito los datos del socio.");
                    datosGuardados.showAndWait();
                }
                setStatusInicial();
            }

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

                listaSocios.add(socio);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaSocios;
    }

    @FXML
    public static List<Pago> ListarPagos() throws FileNotFoundException {
        List<Pago> listaPagos = new ArrayList<>();

        try (BufferedReader lectorCSV = new BufferedReader(new FileReader(rutaArchivoPagos))) {
            String lineaCSV;
            while ((lineaCSV = lectorCSV.readLine()) != null) {

                String[] datosPago = lineaCSV.split("\\*\\*");
                Pago pago = new Pago();
                pago.setDni(datosPago[0]);
                pago.setFecha(datosPago[1]);
                pago.setMonto(datosPago[2]);
                pago.setConcepto(datosPago[3]);

                listaPagos.add(pago);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaPagos;
    }


    @FXML
    public void LlenarTablaSociosObservable(List<Socio> lstSocios){
        lstSocioObservable = new ArrayList<>();
        int i=0;
        for (Socio socio: lstSocios) {

            SocioObservable socioObservable = new SocioObservable(socio.getDni(), socio.getNombre(), socio.getApellido(),ObtenerEstadoDelSocio(socio.getDni()));
            lstSocioObservable.add(socioObservable);
        }
        lstSociosObservableFront = FXCollections.observableArrayList(lstSocioObservable);
        tablaSocios.setItems(lstSociosObservableFront);

    }

    private String ObtenerEstadoDelSocio(String dni) {
        String estado = "";
        for (Pago pago : lstPagos) {
            if (dni.equals(pago.getDni())) {
                SimpleDateFormat formato = new SimpleDateFormat("d-M-yyyy");
                Date dateFechaPago = null;
                try {
                    dateFechaPago = formato.parse(pago.getFecha());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date fechaActual = new Date();
                // Calcular la diferencia en milisegundos entre las dos fechas
                long diferenciaEnMilisegundos = fechaActual.getTime() - dateFechaPago.getTime();

                // Convertir la diferencia a días
                long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);
                if(dni.equals(pago.getDni()) && diferenciaEnDias <= 31){
                    estado = "Activo";
                    break;
                } else if (dni.equals(pago.getDni()) && diferenciaEnDias > 31) {
                    estado = "Inactivo";
                }
            }
        }
        return estado;
    }

    @FXML
    public void LlenarTablaPagosObservable(List<Pago> lstPagos){
        lstPagosObservable = new ArrayList<>();
        for (Pago pago: lstPagos) {
            String dniSocioBuscado = campoDni.getText();
            String dniSocioActual = pago.getDni();
            if (dniSocioBuscado.equals(dniSocioActual)) {
                PagoObservable pagoObservable = new PagoObservable(pago.getFecha(), pago.getMonto(),pago.getConcepto());
                lstPagosObservable.add(pagoObservable);
            }
        }
        lstPagosObservablesFront = FXCollections.observableArrayList(lstPagosObservable);
        tablaPagos.setItems(lstPagosObservablesFront);
    }

    @FXML
    public void ModificarDatosSocio (){
        modifica = true;
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
        //campoEstadoSocio.setDisable(false);
        lstPagosSocio.setDisable(false);
        btnIngresarPago.setDisable(false);
        btnEliminarPago.setDisable(false);
        btnModifPago.setDisable(false);
    }
    @FXML
    public void EliminarSocio() {
        Alert datosGuardados = new Alert(Alert.AlertType.CONFIRMATION);
        datosGuardados.setTitle("Eliminar Socio");
        datosGuardados.setContentText("¿Esta seguro que desea borrar la información del Socio permanentemente?");
        datosGuardados.showAndWait().ifPresent(response -> {
            try {
                List<String> lstLineasDatosSociosCSV = new ArrayList<>();
                BufferedReader lectorCSV = new BufferedReader(new FileReader(rutaArchivoDatosSocio));
                String lineaCSV;
                while ((lineaCSV = lectorCSV.readLine()) != null) {
                    if (lineaCSV.length() >= 7) {
                        String dniSocioCSV = lineaCSV.substring(0, 8);
                        if (!dniSocioCSV.equals(campoDni.getText())) {
                            lstLineasDatosSociosCSV.add(lineaCSV);
                        }
                    }
                }
                BufferedWriter escribirCSV = new BufferedWriter(new FileWriter(rutaArchivoDatosSocio));
                for (String linea : lstLineasDatosSociosCSV) {
                    escribirCSV.write(linea);
                    escribirCSV.newLine();
                }
                escribirCSV.close();
                setStatusInicial();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        colDni.setStyle("");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
        colEstado.setCellFactory(column -> new TableCell<SocioObservable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    setText(item);
                    if (item.equals("Activo")) {
                        setStyle("-fx-background-color: green; -fx-border-color: white; -fx-border-width: 5px; -fx-text-fill: white");
                    } else if (item.equals("Inactivo")) {
                        setStyle("-fx-background-color: red; -fx-border-color: white; -fx-border-width: 5px; -fx-text-fill: white");
                    } else {
                        setStyle(""); // Restablece el estilo por defecto si no coincide con ninguno de los valores
                    }
                }
            }
        });

        colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("Monto"));
        colConcepto.setCellValueFactory(new PropertyValueFactory<>("Concepto"));



        colDni.setStyle("-fx-alignment: CENTER_LEFT;");
        colNombre.setStyle("-fx-alignment: CENTER_LEFT;");
        colApellido.setStyle("-fx-alignment: CENTER_LEFT;");
        colEstado.setStyle("-fx-alignment: CENTER_LEFT;");

        tablaSocios.setOnMouseClicked(event -> {
            SocioObservable socioObservableSeleccionado = tablaSocios.getSelectionModel().getSelectedItem();
            if(socioObservableSeleccionado != null){
                String dni = socioObservableSeleccionado.getDni();
                LimpiarCampos();
                DeshabilitarCamposYBotones();
                LlenarDatosSocio(dni);
                btnEliminarSocio.setDisable(false);
                btnModifSocio.setDisable(false);
                btnIngresarPago.setDisable(false);
                btnModifPago.setDisable(false);
                btnEliminarPago.setDisable(false);
                tablaPagos.setDisable(false);
            }
        });
        tablaSocios.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                SocioObservable socioObservableSeleccionado = tablaSocios.getSelectionModel().getSelectedItem();
                if(socioObservableSeleccionado != null){
                    String dni = socioObservableSeleccionado.getDni();
                    LimpiarCampos();
                    DeshabilitarCamposYBotones();
                    LlenarDatosSocio(dni);
                    btnEliminarSocio.setDisable(false);
                    btnModifSocio.setDisable(false);
                    btnIngresarPago.setDisable(false);
                    btnModifPago.setDisable(false);
                    btnEliminarPago.setDisable(false);
                    tablaPagos.setDisable(false);
                }
            }
        });
    }

    private void LlenarDatosSocio(String dni) {
        int pos = 0;
        for(Socio socio : lstSocios){
            if(socio.getDni().equals(dni)){
                campoNombre.setText(socio.getNombre());
                campoNombre.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoApellido.setText(socio.getApellido());
                campoApellido.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoTelefono.setText(socio.getTelefono());
                campoTelefono.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoEdad.setText(socio.getEdad());
                campoEdad.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoMatricula.setText(socio.getNroSocio());
                campoMatricula.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoNacionalidad.setText(socio.getNacionalidad());
                campoNacionalidad.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoDni.setText(socio.getDni());
                campoDni.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoEc.setText(socio.getEc());
                campoEc.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoProfesion.setText(socio.getProfesion());
                campoProfesion.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoFechaNacimiento.setValue(StringAFecha(socio.getFechaNacimiento()));
                campoFechaNacimiento.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoDomicilio.setText(socio.getDomicilio());
                campoDomicilio.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoLocalidad.setText(socio.getLocalidad());
                campoLocalidad.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoEmail.setText(socio.getEmail());
                campoEmail.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoTutor.setText(socio.getNomTutor());
                campoTutor.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoTelTutor.setText(socio.getTelTutor());
                campoTelTutor.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoGrupoFamiliar.setText(socio.getGrupoFamiliar());
                campoGrupoFamiliar.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoFechaAdmision.setValue(StringAFecha(socio.getFechaAdmision()));
                campoFechaAdmision.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoNroActaAdmision.setText(socio.getNroActaAdmision());
                campoNroActaAdmision.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoFechaRenuncia.setValue(StringAFecha(socio.getNroActaRenuncia()));
                campoFechaRenuncia.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoNroActaRenuncia.setText(socio.getNroActaRenuncia());
                campoNroActaRenuncia.setStyle("-fx-text-fill: black; -fx-opacity: 0.585; -fx-font-weight: bold;");
                campoEstadoSocio.setText(ObtenerEstadoDelSocio(dni));
                if (campoEstadoSocio.getText().equals("Activo")) {
                    campoEstadoSocio.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-opacity: 0.9");
                } else if (campoEstadoSocio.getText().equals("Inactivo")) {
                    campoEstadoSocio.setStyle("-fx-background-color: red;  -fx-text-fill: white; -fx-opacity: 0.9");
                } else {
                    campoEstadoSocio.setStyle("");
                }
                break;
            }else{
                pos++;
            }
        }
        LlenarTablaPagosObservable(lstPagos);
       /* int finalPos = pos;
        btnEliminarSocio.setOnAction(actionEvent -> {
            EliminarSocio(lstSocios.get(finalPos));
        });*/
    }

    @FXML
    public void AbrirIngresoDePago() throws IOException {
        Stage ventanaPago = new Stage();
        VBox root = new VBox();
        root.setPrefSize(748, 120);
        root.setPadding(new Insets(10));

        // Título "Ingreso de Pago"
        Label titulo = new Label("Ingreso de Pago");
        titulo.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-font-style: italic; -fx-underline: true;");
        VBox.setMargin(titulo, new Insets(10, 0, 10, 260)); // Alineación del título
        root.getChildren().add(titulo);

        // Campo Socio
        Label labelSocio = new Label("Socio:");
        labelSocio.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-font-style: italic; -fx-underline: true;");
        Label nombreSocio = new Label(campoNombre.getText() + " " + campoApellido.getText()); // Aquí debes establecer el nombre del socio
        nombreSocio.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        HBox socioBox = new HBox(labelSocio, nombreSocio);
        socioBox.setSpacing(20);
        VBox.setMargin(socioBox, new Insets(10, 0, 0, 10)); // Alineación del campo Socio
        root.getChildren().add(socioBox);

        // Campos de Monto, Fecha y Concepto
        Label labelMonto = new Label("Monto");
        campoMonto = new TextField();

        Label labelFecha = new Label("Fecha");
        dtPickerFechaPago = new DatePicker(LocalDate.now());

        Label labelConcepto = new Label("Concepto");
        campoConcepto = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.addRow(0, labelMonto, campoMonto, labelFecha, dtPickerFechaPago, labelConcepto, campoConcepto);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        root.getChildren().add(gridPane);

        // Botón "Aplicar Pago"
        Button btnAplicarPago = new Button("Aplicar Pago");
        btnAplicarPago.setMaxWidth(Double.MAX_VALUE);
        btnAplicarPago.setPrefHeight(30);
        VBox.setMargin(btnAplicarPago, new Insets(10, 10, 0, 10)); // Alineación del botón
        root.getChildren().add(btnAplicarPago);

        ventanaPago.setTitle("Club Atlético Paraná");
        ventanaPago.setScene(new Scene(root));
        ventanaPago.show();

        btnAplicarPago.setOnAction(actionEvent -> {
            try {
                GuardarPago(campoDni.getText());
                ventanaPago.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void GuardarPago(String dni) throws IOException {
        List<String> lstLineaspagosCSV = new ArrayList<>();
        BufferedReader lectorCSV = new BufferedReader(new FileReader(rutaArchivoPagos));
        String lineaCSV;
        while((lineaCSV = lectorCSV.readLine()) != null ){
            lstLineaspagosCSV.add(lineaCSV);
        }

        LocalDate fechaSeleccionada = dtPickerFechaPago.getValue();
        String fechaFormateada = fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        BufferedWriter escribirCSV = new BufferedWriter(new FileWriter(rutaArchivoPagos));
        StringBuilder constructorNuevaLinea = new StringBuilder();

        constructorNuevaLinea.append(campoDni.getText()).append("**").append(fechaFormateada).append("**").append(campoMonto.getText())
                .append("**").append(campoConcepto.getText());

        lstLineaspagosCSV.add(constructorNuevaLinea.toString());

        for (String pagos : lstLineaspagosCSV) {
            escribirCSV.write(pagos);
            escribirCSV.newLine();
        }
        escribirCSV.close();
        lstPagos = ListarPagos();
        lstSocios = ListarSocios();
        LlenarTablaPagosObservable(lstPagos);
        LlenarTablaSociosObservable(lstSocios);
        LlenarDatosSocio(dni);

        Alert datosGuardados = new Alert(Alert.AlertType.INFORMATION);
        datosGuardados.setTitle("Pago Guardado");
        datosGuardados.setContentText("El pago se ha guardado con éxito.");
        datosGuardados.showAndWait();
    }

    private String FechaAString(DatePicker datePicker){

        LocalDate dtFecha = datePicker.getValue();
        String strFecha;
        if (!dtFecha.equals(null)) {
            strFecha = dtFecha.format(DateTimeFormatter.ofPattern("d-M-yyyy"));
        }else{
            strFecha = "null";
        }
        return strFecha;
    }
    private LocalDate StringAFecha(String strFecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        // Convertir el string a LocalDate
        LocalDate fechaLocalDate;
        if (!strFecha.equals("null")) {
            fechaLocalDate = LocalDate.parse(strFecha, formatter);
        }else{
            fechaLocalDate = null;
        }
        return fechaLocalDate;
    }

}
