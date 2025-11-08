package vista.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Agencia.Modelo.Servicios.Vuelo; // Asegúrate de que el paquete sea correcto

import java.io.IOException;

public class VentanaVuelosController {

    @FXML
    private TableView<Vuelo> tablaVuelos;

    @FXML
    private TableColumn<Vuelo, String> colNumero;

    @FXML
    private TableColumn<Vuelo, String> colOrigen;

    @FXML
    private TableColumn<Vuelo, String> colDestino;

    @FXML
    private TableColumn<Vuelo, Integer> colPasajeros;

    @FXML
    private TableColumn<Vuelo, Double> colPrecio;

    @FXML
    private TableColumn<Vuelo, String> colFecha;

    private ObservableList<Vuelo> listaVuelos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroDeVuelo"));
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("ciudadOrigen"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("ciudadDestino"));
        colPasajeros.setCellValueFactory(new PropertyValueFactory<>("cantPasajeros"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // Cargar algunos vuelos de ejemplo
        listaVuelos.addAll(
                new Vuelo("AR101", "Buenos Aires", "Madrid", 180, 950.0, "15/12/2025"),
                new Vuelo("LA202", "Santiago", "Lima", 120, 350.0, "02/01/2026"),
                new Vuelo("UX303", "Madrid", "Buenos Aires", 200, 970.0, "20/12/2025")
        );

        tablaVuelos.setItems(listaVuelos);
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/fxml/ventana_principal.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) tablaVuelos.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}