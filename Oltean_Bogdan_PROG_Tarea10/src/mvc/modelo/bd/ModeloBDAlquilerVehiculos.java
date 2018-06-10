/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.bd;

import java.util.LinkedList;
import mvc.modelo.IModeloAlquilerVehiculo;
import mvc.modelo.bd.dao.Alquileres;
import mvc.modelo.bd.dao.Clientes;
import mvc.modelo.bd.dao.Vehiculos;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class ModeloBDAlquilerVehiculos implements IModeloAlquilerVehiculo {
    private Clientes clientes;
    private Alquileres alquileres;
    private Vehiculos vehiculos;

    public ModeloBDAlquilerVehiculos() {
        clientes = new Clientes();
        alquileres = new Alquileres();
        vehiculos = new Vehiculos();
    }
    
    @Override
    public LinkedList<Alquiler> obtenerAlquiler() {
        return alquileres.getAlquiler();
    }

    @Override
    public LinkedList<Vehiculo> obtenerVehiculo() {
        return vehiculos.getVehiculo();
    }

    @Override
    public LinkedList<Cliente> obtenerClientes() {
        return clientes.getClientes();
    }

    @Override
    public Cliente buscarCliente(String dni) {
        return clientes.buscar(dni);
    }

    @Override
    public void anadirCliente(Cliente cliente) {
        clientes.anadir(cliente);
    }

    @Override
    public void borrarCliente(String dni) {
        clientes.borrar(dni);
    }

    @Override
    public void leerClientes() {
        //clientes.leerClientes();
    }

    @Override
    public void escribirClientes() {
        //clientes.escribirClientes();
    }

    @Override
    public void anadirVehiculo(Vehiculo vehiculo , TipoVehiculo tipoVehiculo) {
        vehiculos.anadir(vehiculo, tipoVehiculo);
    }

    @Override
    public void borrarVehiculo(String matricula) {
        vehiculos.borrar(matricula);
    }

    @Override
    public Vehiculo buscarVehiculo(String matricula) {
        return vehiculos.buscar(matricula);
    }

    @Override
    public void leerVehiculos() {
        //vehiculos.leerVehiculos();
    }

    @Override
    public void escribirVehiculos() {
        //vehiculos.escribirVehiculos();
    }

    @Override
    public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
        alquileres.abrir(cliente, vehiculo);
    }

    @Override
    public void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo) {
        alquileres.cerrar(cliente, vehiculo);
    }

    @Override
    public LinkedList<Alquiler> obtenerAlquileresAbiertos() {
        //return alquileres.obtenerAlquileresAbiertos();
    	return null;
    }

    @Override
    public LinkedList<Alquiler> obtenerAlquileresCliente(String dni) {
        //return alquileres.obtenerAlquileresCliente(dni);
    	return null;
    }

    @Override
    public LinkedList<Alquiler> obtenerAlquileresVehiculo(String matricula) {
        //return alquileres.obtenerAlquileresVehiculo(matricula);
    	return null;
    }

    @Override
    public void leerAlquileres() {
        //alquileres.leerAlquileres();
    }

    @Override
    public void escribirAlquileres() {
        //alquileres.escribirAlquileres();
    }
}
