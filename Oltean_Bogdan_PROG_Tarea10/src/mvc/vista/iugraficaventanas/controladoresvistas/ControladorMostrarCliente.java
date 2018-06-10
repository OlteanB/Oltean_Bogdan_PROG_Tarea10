/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mvc.modelo.dominio.Cliente;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;
import mvc.vista.utilidades.Dialogos;

/**
 *
 * @author bogdan
 */
public class ControladorMostrarCliente {
        private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private Button btBorrar, btCancelar;

    @FXML
    private void borrarCliente() {
        IUGraficaVentanas.controladorMVC.borrarCliente(cliente.getDNI());
        Stage propietario = (Stage) btBorrar.getScene().getWindow();
        Dialogos.mostrarDialogoInformacion("Borrar Cliente", "Cliente borrado satisfactoriamente", propietario);
    }

    @FXML
    private void cancelar() {
        ((Stage) btCancelar.getScene().getWindow()).close();
    }

}
