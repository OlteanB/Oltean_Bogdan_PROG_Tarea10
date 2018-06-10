/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;
import mvc.vista.utilidades.Dialogos;

/**
 *
 * @author bogdan
 */
public class ControladorAnadirAlquiler {
    private Vehiculo vehiculo;
    private Cliente cliente;

    public void setAlquiler(Cliente cliente, Vehiculo vehiculo) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        tfDni.setText(cliente.getDNI());
        tfMatricula.setText(vehiculo.getMatricula());
    }

    @FXML
    private TextField tfDni, tfMatricula;

    @FXML
    private Button btAnadir, btCancelar;

    @FXML
    private void cancelar() {
        ((Stage) btCancelar.getScene().getWindow()).close();
    }

    @FXML
    private void crearAlquiler() {
        Stage propietario = ((Stage) btAnadir.getScene().getWindow());
        try {
            IUGraficaVentanas.controladorMVC.abrirAlquiler(cliente, vehiculo);
            Dialogos.mostrarDialogoInformacion("Crear alquiler", "Alquiler creado satisfactoriamente", propietario);
        } catch (ExcepcionAlquilerVehiculos e) {
            Dialogos.mostrarDialogoError("Crear alquiler", e.getMessage(), propietario);
        }
    }
}
