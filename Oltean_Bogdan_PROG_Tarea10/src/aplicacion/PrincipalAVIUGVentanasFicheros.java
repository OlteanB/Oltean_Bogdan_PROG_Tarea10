package aplicacion;

import mvc.controlador.ControladorAlquilerVehiculos;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.modelo.ficheros.ModeloFicherosAlquilerVehiculo;
import mvc.modelo.IModeloAlquilerVehiculo;
import mvc.vista.IVistaAlquilerVehiculos;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;

public class PrincipalAVIUGVentanasFicheros {

    public static void main(String[] args) {
        IModeloAlquilerVehiculo modelo = new ModeloFicherosAlquilerVehiculo();
        IVistaAlquilerVehiculos vista = new IUGraficaVentanas();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(vista, modelo);

        controlador.comenzar();
    }

}
