/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.bd.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class Alquileres {

    public LinkedList<Alquiler> getAlquiler() {
        LinkedList<Alquiler> alquileres = new LinkedList<Alquiler>();
        Connection conexion = accesoBD.estableceConexion();
        try {
            String sentenciaStr = "select idCliente, idVehiculo, fecha, dias from alquileres";
            Statement sentencia = (Statement) conexion.createStatement();
            ResultSet filas = (ResultSet) sentencia.executeQuery(sentenciaStr);
            while (filas.next()) {
                int idCliente = filas.getInt(1);
                int idVehiculo = filas.getInt(2);
                Timestamp fecha = filas.getTimestamp(3);
                int dias = filas.getInt(4);
                Cliente cliente = Clientes.buscar(idCliente);
                Vehiculo vehiculo = Vehiculos.buscar(idVehiculo);
                Alquiler alquiler = new Alquiler(cliente, vehiculo, fecha, dias);
                alquileres.add(alquiler);
            }
        } catch (SQLException e) {
            accesoBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        accesoBD.cierraConexion(conexion);
        return alquileres;
    }

    public void abrir(Cliente cliente, Vehiculo vehiculo) {
        if (!vehiculo.getDisponible()) {
            throw new ExcepcionAlquilerVehiculos("El vehículo que quiere alquilar no está disponible");
        }
        int idCliente = Clientes.getIdentificador(cliente.getDNI());
        int idVehiculo = Vehiculos.getIdentificador(vehiculo.getMatricula());
        Vehiculos.setDiponible(idVehiculo, false);
        Connection conexion = accesoBD.estableceConexion();
        try {
            String sentenciaStr = "insert into alquileres values (?, ?, now(), 0)";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setInt(1, idCliente);
            sentencia.setInt(2, idVehiculo);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            accesoBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        accesoBD.cierraConexion(conexion);
    }

    public void cerrar(Cliente cliente, Vehiculo vehiculo) {
        int idCliente = Clientes.getIdentificador(cliente.getDni());
        int idVehiculo = Vehiculos.getIdentificador(vehiculo.getMatricula());
        Alquiler alquiler = buscarAbierto(cliente, vehiculo);
        if (alquiler == null) {
            throw new ExcepcionAlquilerVehiculos("No hay ningún alquiler abierto para ese cliente y ese vehículo");
        }
        alquiler.close();
        Vehiculos.setDiponible(idVehiculo, true);
        Connection conexion = accesoBD.estableceConexion();
        try {
            String sentenciaStr = "update alquileres set dias = ? where idCliente = ? and idVehiculo = ? and fecha = ?";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setInt(1, alquiler.getDias());
            sentencia.setInt(2, idCliente);
            sentencia.setInt(3, idVehiculo);
            sentencia.setTimestamp(4, new Timestamp(alquiler.getFecha().getTime()));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            accesoBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        accesoBD.cierraConexion(conexion);
    }

    private Alquiler buscarAbierto(Cliente cliente, Vehiculo vehiculo) {
        Alquiler alquiler = null;
        int idCliente = Clientes.getIdentificador(cliente.getDni());
        int idVehiculo = Vehiculos.getIdentificador(vehiculo.getMatricula());
        Connection conexion = accesoBD.estableceConexion();
        try {
            String sentenciaStr = "select fecha, dias from alquileres where idCliente = ? and idVehiculo = ? and dias = 0";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setInt(1, idCliente);
            sentencia.setInt(2, idVehiculo);;
            ResultSet filas = (ResultSet) sentencia.executeQuery();
            if (filas.next()) {
                Timestamp fecha = filas.getTimestamp(1);
                int dias = filas.getInt(2);
                alquiler = new Alquiler(cliente, vehiculo, fecha, dias);
            }
        } catch (SQLException e) {
            accesoBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        accesoBD.cierraConexion(conexion);
        return alquiler;
    }
}
