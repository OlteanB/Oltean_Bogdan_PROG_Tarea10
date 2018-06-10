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
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;
import mvc.vista.utilidades.Dialogos;

/**
 *
 * @author bogdan
 */
public class ControladorAnadirCliente {
       private ControladorDatosCliente datosCliente;

    public void setDatosCliente(ControladorDatosCliente datosCliente) {
        this.datosCliente = datosCliente;
    }

    @FXML
    private Button btAnadir, btCancelar;

    @FXML
    private void anadirCliente() {
        Cliente cliente = null;
        try {
            cliente = datosCliente.getCliente();
            System.out.println("prueba despues cliente");
            if (IUGraficaVentanas.controladorMVC.buscarCliente(cliente.getDNI()) == null) {
                IUGraficaVentanas.controladorMVC.anadirCliente(cliente);
                Stage propietario = ((Stage) btAnadir.getScene().getWindow());
                Dialogos.mostrarDialogoInformacion("Añadir Cliente", "Cliente añadido satisfactoriamente", propietario);
            } else {
                Dialogos.mostrarDialogoError("Añadir cliente", "Ya existe un cliente con ese DNI");
            }
        } catch (ExcepcionAlquilerVehiculos e) {
            Dialogos.mostrarDialogoError("Añadir cliente", e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        ((Stage) btCancelar.getScene().getWindow()).close();
    }
}
