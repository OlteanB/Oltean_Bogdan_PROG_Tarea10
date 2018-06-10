/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugraficaventanas.controladoresvistas;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.iugraficaventanas.IUGraficaVentanas;
import mvc.vista.utilidades.Dialogos;

/**
 *
 * @author bogdan
 */
public class ControladorVentanaPrincipal {

    private ControladorDatosCliente cDatosClienteAC, cDatosClienteMC;
    private ControladorListadoClientes cListadoClientes;
    private ControladorAnadirCliente cAnadirCliente;
    private ControladorMostrarCliente cMostrarCliente;
    private ControladorListadoVehiculos cListadoVehiculos;
    private ControladorAnadirVehiculo cAnadirVehiculo;
    private ControladorMostrarVehiculo cMostrarVehiculo;
    private ControladorListadoAlquileres cListadoAlquileres;
    private ControladorAnadirAlquiler cAnadirAlquiler;
    private ControladorMostrarAlquiler cMostrarAlquiler;
    private Stage listadoClientes, anadirCliente, mostrarCliente, listadoVehiculos,
            anadirVehiculo, mostrarVehiculo, listadoAlquileres, anadirAlquiler, mostrarAlquiler;

    @FXML
    private Button btListarClientes, btAnadirCliente, btBuscarCliente, btListarVehiculos, btAnadirVehiculo,
            btBuscarVehiculo, btListarAlquileres, btAnadirAlquiler, btBuscarAlquiler;

    @FXML
    private void listarClientes() {
        cListadoClientes.actualizaClientes();
        listadoClientes.showAndWait();
    }

    @FXML
    private void anadirCliente() {
        cDatosClienteAC.setCliente(null);
        anadirCliente.showAndWait();
    }

    @FXML
    private void buscarCliente() {
        String dniCliente = Dialogos.mostrarDialogoTexto("Buscar cliente", "Introduce el DNI del cliente a buscar");
        if (dniCliente != null) {
            Cliente cliente = IUGraficaVentanas.controladorMVC.buscarCliente(dniCliente);
            if (cliente != null) {
                cDatosClienteMC.setCliente(cliente);
                cDatosClienteMC.inhabilitaEdicionCampos();
                cMostrarCliente.setCliente(cliente);
                mostrarCliente.showAndWait();
            } else {
                Dialogos.mostrarDialogoError("Cliente no encontrado", "No existe ningún cliente con ese DNI");
            }
        }
    }

    @FXML
    private void listarVehiculos() {
        cListadoVehiculos.actualizaVehiculos();
        listadoVehiculos.showAndWait();
    }

    @FXML
    private void anadirVehiculo() {
        cAnadirVehiculo.setVehiculo(null);
        cAnadirVehiculo.anadirVehiculo();
        anadirVehiculo.showAndWait();
    }

    @FXML
    private void buscarVehiculo() {
        String matricula = Dialogos.mostrarDialogoTexto("Buscar vehículo", "Introduce la matrícula del vehículo a buscar");
        if (matricula != null) {
            Vehiculo vehiculo = IUGraficaVentanas.controladorMVC.buscarVehiculo(matricula);
            if (vehiculo != null) {
                cMostrarVehiculo.rellenarVehiculo(vehiculo);
                cMostrarVehiculo.setVehiculo(vehiculo);
                mostrarVehiculo.showAndWait();
            } else {
                Dialogos.mostrarDialogoError("Vehículo no encontrado", "No existe ningún vehículo con esa matrícula");
            }
        }
    }

    @FXML
    private void listarAlquileres() {
        cListadoAlquileres.actualizaAlquileres();
        listadoAlquileres.showAndWait();
    }

    @FXML
    private void anadirAlquiler() {
        String dni = Dialogos.mostrarDialogoTexto("Añadir alquiler", "Introduce el DNI del cliente del alquiler a añadir");
        String matricula = Dialogos.mostrarDialogoTexto("Añadir alquiler", "Introduce la matrícula del vehículo del alquiler a añadir");
        Cliente cliente = IUGraficaVentanas.controladorMVC.buscarCliente(dni);
        Vehiculo vehiculo = IUGraficaVentanas.controladorMVC.buscarVehiculo(matricula);
        if (cliente != null && vehiculo != null) {
            cAnadirAlquiler.setAlquiler(cliente, vehiculo);
            anadirAlquiler.showAndWait();
        } else {
            Dialogos.mostrarDialogoError("Cliente o vehículo no encontrado", "No existe ningún cliente con ese DNI o un vehículo con esa matrícula");
        }
    }

    @FXML
    private void buscarAlquiler() {
        //String dni = Dialogos.mostrarDialogoTexto("Buscar alquiler", "Introduce el DNI del cliente del alquiler a buscar");
        String matricula = Dialogos.mostrarDialogoTexto("Buscar alquiler", "Introduce la matrícula del vehículo del alquiler a buscar");
        if (/*dni != null ||*/ matricula != null) {
            LinkedList<Alquiler> alquileresCliente = IUGraficaVentanas.controladorMVC.obtenerAlquileresVehiculo(matricula);
            Vehiculo vehiculo = IUGraficaVentanas.controladorMVC.buscarVehiculo(matricula);
            //Cliente cliente = IUGraficaVentanas.controladorMVC.buscarCliente(dni);
            if (alquileresCliente != null /*|| cliente != null*/) {
                //Alquiler alquiler = IUGraficaVentanas.controladorMVC.
                cMostrarAlquiler.setAlquileresCliente(alquileresCliente);
                mostrarAlquiler.showAndWait();
            }
        } else {
            Dialogos.mostrarDialogoError("Cliente no encontrado", "No existe ningún Cliente con ese DNI");
        }
    }

    @FXML
    private void initialize() {
        try {
            crearListadoClientes();
            crearAnadirCliente();
            crearMostrarCliente();
            crearListadoVehiculos();
            crearAnadirVehiculo();
            crearMostrarVehiculo();
            crearListadoAlquileres();
            crearAnadirAlquiler();
            crearMostrarAlquiler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearMostrarCliente() throws IOException {
        mostrarCliente = new Stage();
        FXMLLoader cargadorMostrarCliente = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/MostrarCliente.fxml"));
        VBox raizMostrarClientes = (VBox) cargadorMostrarCliente.load();
        cMostrarCliente = cargadorMostrarCliente.getController();
        FXMLLoader cargadorDatosCliente = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/DatosCliente.fxml"));
        GridPane gpDatosCliente = (GridPane) cargadorDatosCliente.load();
        cDatosClienteMC = cargadorDatosCliente.getController();
        raizMostrarClientes.getChildren().add(0, gpDatosCliente);
        Scene escenaMostrarCliente = new Scene(raizMostrarClientes);
        mostrarCliente.setTitle("Mostrar cliente");
        mostrarCliente.initModality(Modality.APPLICATION_MODAL);
        mostrarCliente.setScene(escenaMostrarCliente);
    }

    private void crearAnadirCliente() throws IOException {
        anadirCliente = new Stage();
        FXMLLoader cargadorAnadirCliente = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/AnadirCliente.fxml"));
        VBox raizAnadirCliente = (VBox) cargadorAnadirCliente.load();
        cAnadirCliente = cargadorAnadirCliente.getController();
        FXMLLoader cargadorDatosCliente = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/DatosCliente.fxml"));
        GridPane gpDatosCliente = (GridPane) cargadorDatosCliente.load();
        cDatosClienteAC = cargadorDatosCliente.getController();
        cAnadirCliente.setDatosCliente(cDatosClienteAC);
        raizAnadirCliente.getChildren().add(0, gpDatosCliente);
        Scene escenaAnadirCliente = new Scene(raizAnadirCliente);
        anadirCliente.setTitle("Añadir cliente");
        anadirCliente.initModality(Modality.APPLICATION_MODAL);
        anadirCliente.setScene(escenaAnadirCliente);
    }

    private void crearListadoClientes() throws IOException {
        listadoClientes = new Stage();
        FXMLLoader cargadorListadoClientes = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/ListadoClientes.fxml"));
        VBox raizListadoClientes = (VBox) cargadorListadoClientes.load();
        cListadoClientes = cargadorListadoClientes.getController();
        Scene escenaListadoClientes = new Scene(raizListadoClientes);
        listadoClientes.setTitle("Listar clientes");
        listadoClientes.initModality(Modality.APPLICATION_MODAL);
        listadoClientes.setScene(escenaListadoClientes);
    }

    private void crearListadoVehiculos() throws IOException {
        listadoVehiculos = new Stage();
        FXMLLoader cargadorListadoVehiculos = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/ListadoVehiculos.fxml"));
        VBox raizListadoVehiculos = (VBox) cargadorListadoVehiculos.load();
        cListadoVehiculos = cargadorListadoVehiculos.getController();
        Scene escenaListadoVehiculos = new Scene(raizListadoVehiculos);
        listadoVehiculos.setTitle("Listar vehículos");
        listadoVehiculos.initModality(Modality.APPLICATION_MODAL);
        listadoVehiculos.setScene(escenaListadoVehiculos);
    }

    private void crearAnadirVehiculo() throws IOException {
        anadirVehiculo = new Stage();
        FXMLLoader cargadorAnadirVehiculo = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/AnadirVehiculo.fxml"));
        VBox raizAnadirVehiculo = (VBox) cargadorAnadirVehiculo.load();
        cAnadirVehiculo = cargadorAnadirVehiculo.getController();
        Scene escenaAnadirVehiculo = new Scene(raizAnadirVehiculo);
        anadirVehiculo.setTitle("Añadir vehículo");
        anadirVehiculo.initModality(Modality.APPLICATION_MODAL);
        anadirVehiculo.setScene(escenaAnadirVehiculo);
    }

    private void crearMostrarVehiculo() throws IOException {
        mostrarVehiculo = new Stage();
        FXMLLoader cargadorMostrarVehiculo = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/MostrarVehiculo.fxml"));
        VBox raizMostrarVehiculo = (VBox) cargadorMostrarVehiculo.load();
        cMostrarVehiculo = cargadorMostrarVehiculo.getController();
        Scene escenaMostrarVehiculo = new Scene(raizMostrarVehiculo);
        mostrarVehiculo.setTitle("Mostrar vehículo");
        mostrarVehiculo.initModality(Modality.APPLICATION_MODAL);
        mostrarVehiculo.setScene(escenaMostrarVehiculo);
    }

    private void crearListadoAlquileres() throws IOException {
        listadoAlquileres = new Stage();
        FXMLLoader cargadorListadoAlquileres = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/ListadoAlquileres.fxml"));
        VBox raizListadoAlquileres = (VBox) cargadorListadoAlquileres.load();
        cListadoAlquileres = cargadorListadoAlquileres.getController();
        Scene escenaListadoTrabajos = new Scene(raizListadoAlquileres);
        listadoAlquileres.setTitle("Listar alquileres");
        listadoAlquileres.initModality(Modality.APPLICATION_MODAL);
        listadoAlquileres.setScene(escenaListadoTrabajos);
    }

    private void crearAnadirAlquiler() throws IOException {
        anadirAlquiler = new Stage();
        FXMLLoader cargadorAnadirAlquiler = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/AnadirAlquiler.fxml"));
        VBox raizAnadirAlquiler = (VBox) cargadorAnadirAlquiler.load();
        cAnadirAlquiler = cargadorAnadirAlquiler.getController();
        Scene escenaAnadirAlquilerVehiculo = new Scene(raizAnadirAlquiler);
        anadirAlquiler.setTitle("Añadir alquiler");
        anadirAlquiler.initModality(Modality.APPLICATION_MODAL);
        anadirAlquiler.setScene(escenaAnadirAlquilerVehiculo);
    }

    private void crearMostrarAlquiler() throws IOException {
        mostrarAlquiler = new Stage();
        FXMLLoader cargadorMostrarAlquiler = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugraficaventanas/vistas/MostrarAlquiler.fxml"));
        VBox raizMostrarAlquiler = (VBox) cargadorMostrarAlquiler.load();
        cMostrarAlquiler = cargadorMostrarAlquiler.getController();
        Scene escenaMostrarVehiculo = new Scene(raizMostrarAlquiler);
        mostrarAlquiler.setTitle("Mostrar alquiler");
        mostrarAlquiler.initModality(Modality.APPLICATION_MODAL);
        mostrarAlquiler.setScene(escenaMostrarVehiculo);
    }
}
