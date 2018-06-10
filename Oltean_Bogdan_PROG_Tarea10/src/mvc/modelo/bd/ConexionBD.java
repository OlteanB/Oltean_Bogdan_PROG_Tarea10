/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.bd;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bogdan
 */
public class ConexionBD {

    private final static String ESQUEMA = "alquilerVehiculos";
    private final static String USUARIO = "alquilerVehiculos";
    private final static String CONTRASENA = "alquilerVehiculos";

    public static Connection estableceConexion() {
        Connection conexion = null;
        try {
            //Cargamos el driver para MySQL
            Class.forName("com.mysql.jdbc.Driver");

            //Establecemos la conexion
            String urlConexion = "jdbc:mysql://localhost/" + ESQUEMA + "?user=" + USUARIO + "&password=" + CONTRASENA + "&useSSL=false";
            conexion = (Connection) DriverManager.getConnection(urlConexion);

        } catch (ClassNotFoundException cE) {
            System.out.println("Excepción: " + cE.toString());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        }
        return conexion;
    }

    public static void cierraConexion(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        }
    }
}
