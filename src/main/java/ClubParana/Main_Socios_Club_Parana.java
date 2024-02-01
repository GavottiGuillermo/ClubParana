package ClubParana;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main_Socios_Club_Parana extends Application {
    boolean ventanaAbierta = false;
    @FXML
    Button btnAgregarSocio;
    @FXML
    Pane panLstSocios;
    @FXML
    ScrollPane scrPaneDatosSocio;
    @FXML
    HBox hBoxDatos;

    @Override
    public void start(Stage stage) throws IOException {
        if (!ventanaAbierta) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main_Socios_Club_Parana.class.getResource("/Menu Principal.fxml"));
            panLstSocios = new Pane();
            scrPaneDatosSocio = new ScrollPane();
            hBoxDatos = new HBox();
            panLstSocios.prefWidthProperty().bind(hBoxDatos.widthProperty().divide(2));
            scrPaneDatosSocio.prefWidthProperty().bind((hBoxDatos.widthProperty().divide(2)));
            Scene scene = new Scene(fxmlLoader.load(),1335, 985);
            stage.setTitle("Club Atlético Paraná");
            stage.setScene(scene);
            stage.show();
            stage.setMaximized(true);
            ventanaAbierta=true;
            Controlador_Operaciones operaciones = fxmlLoader.getController();
            operaciones.setStatusInicial();
        }
    }

    public static void main(String[] args) {
        launch();
    }


}