/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;

/**
 *
 * @author bogdan
 */
public class ControladorListadoVehiculos {
     @FXML
    private ListView<String> lvVehiculos;

    @FXML
    private Label lbTitulo;

    @FXML
    private void initialize() {
        actualizaVehiculos();
    }

    public void actualizaVehiculos() {
        ObservableList<String> vehiculos = FXCollections.observableArrayList();
        for (Vehiculo vehiculo : IUGraficaVentanas.controladorMVC.obtenerVehiculos()) {
            String vehiculoStr = String.format("Vehiculo: %s", vehiculo.toString());
            vehiculos.add(vehiculoStr);
        }
        lvVehiculos.setItems(vehiculos);
    }
}
