/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;

/**
 *
 * @author bogdan
 */
public class ControladorDatosCliente {
    
    @FXML
    private TextField tfNombre, tfDni, tfCalle, tfLocalidad, tfCodigoPostal;

    public Cliente getCliente() {
        DireccionPostal direccion = new DireccionPostal(tfCodigoPostal.getText(), tfCalle.getText(), tfLocalidad.getText());
        return new Cliente(tfNombre.getText(), tfDni.getText(), direccion);
    }

    public void setCliente(Cliente cliente) {
        if (cliente != null) {
            tfNombre.setText(cliente.getNombre());
            tfDni.setText(cliente.getDNI());
            tfCalle.setText(cliente.getDireccionPostal().getCalle());
            tfLocalidad.setText(cliente.getDireccionPostal().getLocalidad());
            tfCodigoPostal.setText(cliente.getDireccionPostal().getCodigoPostal());
        } else {
            tfNombre.setText("");
            tfDni.setText("");
            tfCalle.setText("");
            tfLocalidad.setText("");
            tfCodigoPostal.setText("");
        }
    }

    public void inhabilitaEdicionCampos() {
        tfNombre.setDisable(true);
        tfDni.setDisable(true);
        tfCalle.setDisable(true);
        tfLocalidad.setDisable(true);
        tfCodigoPostal.setDisable(true);
    }

}
