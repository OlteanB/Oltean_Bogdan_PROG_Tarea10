/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import mvc.controlador.ControladorAlquilerVehiculos;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.modelo.IModeloAlquilerVehiculo;
import mvc.modelo.bd.ModeloBDAlquilerVehiculos;
import mvc.vista.IVistaAlquilerVehiculos;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;

/**
 *
 * @author bogdan
 */
public class PrincipalAVIUGVentanasBD {
        public static void main(String[] args) {
        IModeloAlquilerVehiculo modelo = new ModeloBDAlquilerVehiculos();
        IVistaAlquilerVehiculos vista = new IUGraficaVentanas();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(vista, modelo);

        controlador.comenzar();
    }
}
