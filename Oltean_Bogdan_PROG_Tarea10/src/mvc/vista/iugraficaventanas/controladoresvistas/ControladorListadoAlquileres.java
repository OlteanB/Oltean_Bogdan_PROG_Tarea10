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
import mvc.modelo.dominio.Alquiler;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;

/**
 *
 * @author bogdan
 */
public class ControladorListadoAlquileres {
    @FXML
    private ListView<String> lvAlquileres;

    @FXML
    private Label lbTitulo;

    @FXML
    private void initialize() {
        actualizaAlquileres();
    }

    public void actualizaAlquileres() {
        ObservableList<String> alquileres = FXCollections.observableArrayList();
        for (Alquiler alquiler : IUGraficaVentanas.controladorMVC.obtenerAlquiler()) {
            String AlquilerStr = String.format("Fecha entrada: %s, Días: %d, Precio: %.2f, Precio Fijo: %.2f%n\tCliente: %s%n\tVehiculo: %s",
                    alquiler.getFecha(), alquiler.getDias(), alquiler.getPrecio(), alquiler.getPrecioFijo(), alquiler.getCliente(),
                    alquiler.getVehiculo());
            alquileres.add(AlquilerStr);
        }
        lvAlquileres.setItems(alquileres);
    }
}
