package vista.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;

public class VentanaPrincipalController {

    @FXML
    private StackPane panelCentral;

    @FXML
    private void abrirClientes() {
        cargarVista("/vista/fxml/ventana_clientes.fxml");
    }

    @FXML
    private void abrirVuelos() {
        cargarVista("/vista/fxml/ventana_vuelos.fxml");
    }

    @FXML
    private void abrirHoteles() {
        cargarVista("/vista/fxml/vetana_hoteles.fxml");
    }

    private void cargarVista(String rutaFXML) {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource(rutaFXML));
            panelCentral.getChildren().setAll(vista); // reemplaza el contenido central
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}