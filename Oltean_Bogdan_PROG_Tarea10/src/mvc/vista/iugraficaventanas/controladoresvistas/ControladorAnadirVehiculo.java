/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculo;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;
import mvc.vista.utilidades.Dialogos;

/**
 *
 * @author bogdan
 */
public class ControladorAnadirVehiculo {
    private Vehiculo vehiculo;

    @FXML
    private ComboBox<TipoVehiculo> cbTipo;

    @FXML
    private TextField tfMatricula, tfMarca, tfModelo, tfCilindrada, tfNumeroPlazas, tfPma;

    @FXML
    private Button btAnadir, btCancelar;

    @FXML
    private void cancelar() {
        ((Stage) btCancelar.getScene().getWindow()).close();
    }

    @FXML
    private void initialize() {
        ObservableList<TipoVehiculo> tiposVehiculo = FXCollections.observableArrayList(TipoVehiculo.values());
        cbTipo.setItems(tiposVehiculo);
    }

    public void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            cbTipo.setValue(vehiculo.getTipoVehiculo());
            tfMatricula.setText(vehiculo.getMatricula());
            tfMarca.setText(vehiculo.getMarca());
            tfModelo.setText(vehiculo.getModelo());
            tfCilindrada.setText(Integer.toString(vehiculo.getDatosTecnicos().getCilindrada()));
            tfNumeroPlazas.setText(Integer.toString(vehiculo.getDatosTecnicos().getNumeroPlazas()));
            tfPma.setText(Integer.toString(vehiculo.getDatosTecnicos().getPma()));
        } else {
            cbTipo.setValue(TipoVehiculo.TURISMO);
            tfMatricula.setText("");
            tfMarca.setText("");
            tfModelo.setText("");
            tfCilindrada.setText("");
            tfNumeroPlazas.setText("");
            tfPma.setText("");
        }
    }

    @FXML
    public void anadirVehiculo() {
        try {
            Vehiculo vehiculo = (cbTipo.getValue()).getInstancia(
                    tfMatricula.getText(), tfMarca.getText(),
                    tfModelo.getText(), new DatosTecnicosVehiculo(tfCilindrada.getText().equals("") ? 0 : Integer.parseInt(tfCilindrada.getText()),
                    tfNumeroPlazas.getText().equals("") ? 0 : Integer.parseInt(tfNumeroPlazas.getText()),
                    tfPma.getText().equals("") ? 0 : Integer.parseInt(tfPma.getText())));
            if (IUGraficaVentanas.controladorMVC.buscarVehiculo(vehiculo.getMatricula()) == null) {
                IUGraficaVentanas.controladorMVC.anadirVehiculo(vehiculo, cbTipo.getValue());
                Stage propietario = ((Stage) btAnadir.getScene().getWindow());
                Dialogos.mostrarDialogoInformacion("Añadir Vehículo", "Vehículo añadido satisfactoriamente", propietario);
            } else {
                Dialogos.mostrarDialogoError("Añadir Vehículo", "Ya existe un vehículo con esa matrícula");
            }
        } catch (ExcepcionAlquilerVehiculos e) {
            Dialogos.mostrarDialogoError("Añadir Vehículo", e.getMessage());
        }
    }

    public void inhabilitaEdicionCampos() {
        tfMatricula.setEditable(false);
        tfMarca.setEditable(false);
        tfModelo.setEditable(false);
        tfCilindrada.setEditable(false);
        tfNumeroPlazas.setEditable(false);
        tfPma.setEditable(false);
    }
}
