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
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;

/**
 *
 * @author bogdan
 */
public class ControladorListadoClientes {
    
    @FXML
    private ListView<String> lvClientes;

    @FXML
    private Label lbTitulo;

    @FXML
    private void initialize() {
        actualizaClientes();
    }

    public void actualizaClientes() {
        ObservableList<String> clientes = FXCollections.observableArrayList();
        for (Cliente cliente : IUGraficaVentanas.controladorMVC.obtenerClientes()) {
            DireccionPostal direccion = cliente.getDireccionPostal();
            String clienteStr = String.format(
                    "Nombre: %s\tDNI: %s\tCalle: %s\tLocalidad: %s\tCódigo Postal:%s",
                    cliente.getNombre(), cliente.getDNI(),
                    direccion.getCalle(), direccion.getLocalidad(), direccion.getCodigoPostal());
            clientes.add(clienteStr);
        }
        lvClientes.setItems(clientes);
    }

}
