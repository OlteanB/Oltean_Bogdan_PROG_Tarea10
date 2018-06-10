package aplicacion;

import mvc.controlador.ControladorAlquilerVehiculos;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.modelo.AlquilerVehiculo;
import mvc.modelo.IModeloAlquilerVehiculo;
import mvc.vista.IVistaAlquilerVehiculos;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;

public class PrincipalAVIUGVentanasFicheros {

    public static void main(String[] args) {
        IModeloAlquilerVehiculo modelo = new AlquilerVehiculo();
        IVistaAlquilerVehiculos vista = new IUGraficaVentanas();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(vista, modelo);

        controlador.comenzar();
    }

}
