/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculo;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class ControladorDatosVehiculo {
    @FXML
    private TextField tfMatricula, tfMarca, tfModelo, tfCilindrada, tfNumeroPlazas, tfPma;

    @FXML
    private ComboBox<TipoVehiculo> cbTipo;

    public Vehiculo getVehiculo() {
        Vehiculo vehiculo = (cbTipo.getValue()).getInstancia(
                tfMatricula.getText(), tfMarca.getText(), tfModelo.getText(),
                new DatosTecnicosVehiculo(
                        Integer.parseInt(tfCilindrada.getText()),
                        Integer.parseInt(tfNumeroPlazas.getText()),
                        Integer.parseInt(tfPma.getText())));
        return vehiculo;
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

    public void inhabilitaEdicionCampos() {
        tfMatricula.setEditable(false);
        tfMarca.setEditable(false);
        tfModelo.setEditable(false);
        tfCilindrada.setEditable(false);
        tfNumeroPlazas.setEditable(false);
        tfPma.setEditable(false);
    }

    @FXML
    private void initialize() {
        ObservableList<TipoVehiculo> tiposVehiculo = FXCollections.observableArrayList(TipoVehiculo.values());
        cbTipo.setItems(tiposVehiculo);
    }
}
