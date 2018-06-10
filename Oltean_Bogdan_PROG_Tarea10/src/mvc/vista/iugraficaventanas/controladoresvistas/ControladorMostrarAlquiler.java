/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;
import mvc.vista.utilidades.Dialogos;

/**
 *
 * @author bogdan
 */
public class ControladorMostrarAlquiler {
    private Cliente cliente;
    private Alquiler alquiler;
    private LinkedList<Alquiler> alquileresCliente;

    @FXML
    private TextField tfDniCliente, tfMatricula, tfFecha, tfDias, tfPrecio, tfPrecioFijo;

    @FXML
    private Button btCerrar;

    @FXML
    private void initialize() {

    }

    @FXML
    private void cerrarAlquiler() {
        IUGraficaVentanas.controladorMVC.cerrarAlquiler(alquiler.getCliente(), alquiler.getVehiculo());
        Stage propietario = (Stage) btCerrar.getScene().getWindow();
        Dialogos.mostrarDialogoInformacion("Cerrar Alquiler", "Alquiler cerrado satisfactoriamente", propietario);
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
        actualizaAlquiler();
    }

    public void setAlquileresCliente(LinkedList<Alquiler> alquileresCliente) {
        this.alquileresCliente = alquileresCliente;
        actualizaAlquiler();
    }

    private void actualizaAlquiler() {
        if (alquiler != null) {
            tfDniCliente.setText(alquiler.getCliente().getDNI());
            tfMatricula.setText(alquiler.getVehiculo().getMatricula());
            tfFecha.setText(alquiler.getFecha().toString());
            tfDias.setText("" + alquiler.getDias());
            tfPrecioFijo.setText("" + alquiler.getPrecioFijo());
            tfPrecio.setText("" + alquiler.getPrecio());
        } else {
            tfDniCliente.setText("");
            tfMatricula.setText("");
            tfFecha.setText("");
            tfDias.setText("");
            tfPrecioFijo.setText("");
            tfPrecio.setText("");
        }
    }

    public void inhabilitaEdicionCampos() {
        tfDniCliente.setDisable(true);
        tfMatricula.setDisable(true);
        tfFecha.setDisable(true);
        tfDias.setDisable(true);
        tfPrecio.setDisable(true);
        tfPrecioFijo.setDisable(true);
    }
}
