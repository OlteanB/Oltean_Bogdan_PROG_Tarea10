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
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.SQLException;

import java.util.LinkedList;
import mvc.modelo.bd.ConexionBD;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public class Clientes {

    public LinkedList<Cliente> getClientes() {
        LinkedList<Cliente> clientes = new LinkedList<Cliente>();
        Connection conexion = ConexionBD.estableceConexion();
        try {
            String sentenciaStr = "select nombre, dni, calle, localidad, codigoPostal from clientes";
            Statement sentencia = (Statement) conexion.createStatement();
            ResultSet filas = (ResultSet) sentencia.executeQuery(sentenciaStr);
            while (filas.next()) {
                String nombre = filas.getString(1);
                String dni = filas.getString(2);
                String calle = filas.getString("calle");
                String localidad = filas.getString("localidad");
                String codigoPostal = filas.getString("codigoPostal");
                Cliente cliente = new Cliente(nombre, dni, new DireccionPostal(codigoPostal, calle, localidad));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        ConexionBD.cierraConexion(conexion);
        return clientes;
    }

    public void anadir(Cliente cliente) {
        Connection conexion = ConexionBD.estableceConexion();
        try {
            String sentenciaStr = "insert into clientes values (null, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getDNI());
            DireccionPostal direccion = cliente.getDireccionPostal();
            sentencia.setString(3, direccion.getCalle());
            sentencia.setString(4, direccion.getLocalidad());
            sentencia.setString(5, direccion.getCodigoPostal());
            sentencia.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con ese DNI");
        } catch (SQLException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        ConexionBD.cierraConexion(conexion);
    }

    public void borrar(String dni) {
        Connection conexion = ConexionBD.estableceConexion();
        try {
            String sentenciaStr = "delete from clientes where dni = ?";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setString(1, dni);
            if (sentencia.executeUpdate() == 0) {
                ConexionBD.cierraConexion(conexion);
                throw new ExcepcionAlquilerVehiculos("No existe ningún cliente con ese DNI");
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("No se puede borrar un cliente que ya tiene alquileres");
        } catch (SQLException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        ConexionBD.cierraConexion(conexion);
    }

    public Cliente buscar(String dni) {
        Cliente cliente = null;
        Connection conexion = ConexionBD.estableceConexion();
        try {
            String sentenciaStr = "select nombre, calle, localidad, codigoPostal from clientes where dni = ?";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setString(1, dni);
            ResultSet filas = (ResultSet) sentencia.executeQuery();
            if (filas.next()) {
                String nombre = filas.getString(1);
                String calle = filas.getString("calle");
                String localidad = filas.getString("localidad");
                String codigoPostal = filas.getString("codigoPostal");
                cliente = new Cliente(nombre, dni, new DireccionPostal(calle, localidad, codigoPostal));
            }
        } catch (SQLException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        ConexionBD.cierraConexion(conexion);
        return cliente;
    }

    public static int getIdentificador(String dni) {
        int identificador = -1;
        Connection conexion = ConexionBD.estableceConexion();
        try {
            String sentenciaStr = "select identificador from clientes where dni = ?";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setString(1, dni);
            ResultSet filas = (ResultSet) sentencia.executeQuery();
            if (filas.next()) {
                identificador = filas.getInt(1);
            }
        } catch (SQLException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        ConexionBD.cierraConexion(conexion);
        return identificador;
    }

    public static Cliente buscar(int identificador) {
        Cliente cliente = null;
        Connection conexion = ConexionBD.estableceConexion();
        try {
            String sentenciaStr = "select nombre, dni, calle, localidad, codigoPostal from clientes where identificador = ?";
            PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sentenciaStr);
            sentencia.setInt(1, identificador);
            ResultSet filas = (ResultSet) sentencia.executeQuery();
            if (filas.next()) {
                String nombre = filas.getString(1);
                String dni = filas.getString(2);
                String calle = filas.getString("calle");
                String localidad = filas.getString("localidad");
                String codigoPostal = filas.getString("codigoPostal");
                cliente = new Cliente(nombre, dni, new DireccionPostal(calle, localidad, codigoPostal));
            }
        } catch (SQLException e) {
            ConexionBD.cierraConexion(conexion);
            throw new ExcepcionAlquilerVehiculos("SQL Exception: " + e.toString());
        }
        ConexionBD.cierraConexion(conexion);
        return cliente;
    }
}
