/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.util.LinkedList;
import mvc.modelo.IModeloAlquilerVehiculo;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.vista.IVistaAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public class ControladorAlquilerVehiculos implements IControladorAlquilerVehiculos {

    private IModeloAlquilerVehiculo modelo;
    private IVistaAlquilerVehiculos vista;

    public ControladorAlquilerVehiculos(IVistaAlquilerVehiculos vista, IModeloAlquilerVehiculo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setControlador(this);
    }

    @Override
    public void comenzar() {
        //modelo.anadirDatosPrueba();
        modelo.leerAlquileres();
        modelo.leerClientes();
        modelo.leerVehiculos();
        vista.comenzar();
    }

    @Override
    public void anadirCliente(Cliente cliente) {
        modelo.anadirCliente(cliente);
    }

    @Override
    public void borrarCliente(String dni) {
        modelo.borrarCliente(dni);
    }

    @Override
    public Cliente buscarCliente(String dni) {
        return modelo.buscarCliente(dni);
    }

    @Override
    public LinkedList<Cliente> obtenerClientes() {
        return modelo.obtenerClientes();
    }

    @Override
    public void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
        modelo.anadirVehiculo(vehiculo, tipoVehiculo);
    }

    @Override
    public void borrarVehiculo(String matricula) {
        modelo.borrarVehiculo(matricula);
    }

    @Override
    public Vehiculo buscarVehiculo(String matricula) {
        return modelo.buscarVehiculo(matricula);
    }

    @Override
    public LinkedList<Vehiculo> obtenerVehiculos() {
        return modelo.obtenerVehiculo();
    }

    @Override
    public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
        modelo.abrirAlquiler(cliente, vehiculo);
    }

    @Override
    public void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo) {
        modelo.cerrarAlquiler(cliente, vehiculo);
    }
    
    @Override
    public LinkedList<Alquiler> obtenerAlquiler() {
        return modelo.obtenerAlquiler();
    }

    @Override
    public LinkedList<Alquiler> obtenerAlquileresAbiertos() {
        return modelo.obtenerAlquileresAbiertos();
    }

    @Override
    public LinkedList<Alquiler> obtenerAlquileresCliente(String dni) {
        return modelo.obtenerAlquileresCliente(dni);
    }

    @Override
    public LinkedList<Alquiler> obtenerAlquileresVehiculo(String matricula) {
        return modelo.obtenerAlquileresVehiculo(matricula);
    }
    /*@Override
    public void anadirDatosPrueba() {
        modelo.anadirDatosPrueba();
    }*/
    
    @Override
    public void salir(){
        modelo.escribirAlquileres();
        modelo.escribirClientes();
        modelo.escribirVehiculos();
    }

}
