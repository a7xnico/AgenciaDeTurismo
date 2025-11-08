package vista.controllers;

import Agencia.Modelo.Usuarios.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanasClientesController {

    @FXML
    private TableView<Persona> tablaClientes;
    @FXML
    private TableColumn<Persona, Integer> colId;
    @FXML
    private TableColumn<Persona, String> colNombre;
    @FXML
    private TableColumn<Persona, String> colApellido;
    @FXML
    private TableColumn<Persona, String> colDni;
    @FXML
    private TableColumn<Persona, String> colEmail;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtEmail;

    private ObservableList<Persona> listaClientes = FXCollections.observableArrayList();
    private static int contador = 1;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("apellido"));
        colDni.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("dni"));
        colEmail.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));

        tablaClientes.setItems(listaClientes);
    }

    @FXML
    public void agregarCliente(ActionEvent event) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dni = txtDni.getText();
        String email = txtEmail.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        Persona nuevo = new Persona(nombre, apellido, dni, email);
        listaClientes.add(nuevo);

        txtNombre.clear();
        txtApellido.clear();
        txtDni.clear();
        txtEmail.clear();
    }

    @FXML
    public void eliminarCliente(ActionEvent event) {
        Persona seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaClientes.remove(seleccionado);
        } else {
            mostrarAlerta("Error", "Seleccioná un cliente para eliminar.");
        }
    }

    @FXML
    public void volverVentanaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/fxml/ventana_principal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}