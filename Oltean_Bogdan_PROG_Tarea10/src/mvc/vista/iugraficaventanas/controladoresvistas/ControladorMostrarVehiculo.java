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
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;
import mvc.vista.utilidades.Dialogos;

/**
 *
 * @author bogdan
 */
public class ControladorMostrarVehiculo {
    private Vehiculo vehiculo;

    public void rellenarVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            tfTipo.setText(vehiculo.getTipoVehiculo().name());
            tfMatricula.setText(vehiculo.getMatricula());
            tfMarca.setText(vehiculo.getMarca());
            tfModelo.setText(vehiculo.getModelo());
            tfCilindrada.setText(Integer.toString(vehiculo.getDatosTecnicos().getCilindrada()));
            tfNumeroPlazas.setText(Integer.toString(vehiculo.getDatosTecnicos().getNumeroPlazas()));
            tfPma.setText(Integer.toString(vehiculo.getDatosTecnicos().getPma()));
        } else {
            tfMatricula.setText("");
            tfMarca.setText("");
            tfModelo.setText("");
            tfCilindrada.setText("");
            tfNumeroPlazas.setText("");
            tfPma.setText("");
        }
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    @FXML
    private TextField tfTipo, tfMatricula, tfMarca, tfModelo, tfCilindrada, tfNumeroPlazas, tfPma;

    @FXML
    private Button btBorrar, btCancelar;

    @FXML
    private void borrarVehiculo() {
        IUGraficaVentanas.controladorMVC.borrarVehiculo(vehiculo.getMatricula());
        Stage propietario = (Stage) btBorrar.getScene().getWindow();
        Dialogos.mostrarDialogoInformacion("Borrar Vehículo", "Vehículo borrado satisfactoriamente", propietario);
    }

    public void inhabilitaEdicionCampos() {
        tfTipo.setEditable(false);
        tfMatricula.setEditable(false);
        tfMarca.setEditable(false);
        tfModelo.setEditable(false);
        tfCilindrada.setEditable(false);
        tfNumeroPlazas.setEditable(false);
        tfPma.setEditable(false);
    }

    @FXML
    private void cancelar() {
        ((Stage) btCancelar.getScene().getWindow()).close();
    }
}
