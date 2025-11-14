package Agencia.Negocio;

import Agencia.Gestores.*;
import Agencia.Modelo.Servicios.Hotel;
import Agencia.Modelo.Servicios.Reserva;
import Agencia.Modelo.Servicios.Vuelo;
import Agencia.Modelo.Usuarios.Cliente;

import java.util.List;

/**
 * Clase principal que actúa como fachada del sistema de agencia de turismo.
 * Centraliza el acceso a todos los gestores (clientes, vuelos, hoteles, reservas)
 * y proporciona métodos para autenticación de usuarios y administradores.
 * Todos los gestores se inicializan al crear una instancia de AgenciaTurismo
 * y cargan automáticamente los datos desde los archivos JSON.
 */

public class AgenciaTurismo {
    private GestorClientes gestorClientes;
    private GestorVuelos gestorVuelos;
    private GestorHoteles gestorHoteles;
    private GestorReservas gestorReservas;
    private Autenticador autenticador;

    public AgenciaTurismo() {
        gestorClientes = new GestorClientes();
        gestorVuelos = new GestorVuelos();
        gestorHoteles = new GestorHoteles();
        gestorReservas = new GestorReservas();
        autenticador = new Autenticador();
    }

    //  Clientes

    public void altaCliente(Cliente cliente) {
        gestorClientes.alta(cliente);
    }

    public void bajaCliente(String dni) {
        gestorClientes.baja(dni);
    }

    public Cliente consultarCliente(String dni) {
        return gestorClientes.consultar(dni);
    }

    public List<Cliente> listadoClientes() {
        return gestorClientes.listado();
    }

    public void mostrarListadoClientes() {
        System.out.println("\n========== LISTADO DE CLIENTES ==========");
        List<Cliente> clientes = listadoClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }

        System.out.println("Total de clientes: " + clientes.size());
        System.out.println("-----------------------------------------");

        for (Cliente c : clientes) {
            System.out.println(c.toString());
            System.out.println("-----------------------------------------");
        }
    }


    //  Hoteles

    public void altaHotel(Hotel hotel) {
        gestorHoteles.alta(hotel);
    }

    public void bajaHotel(String idHotel) {
        gestorHoteles.baja(idHotel);
    }

    public Hotel consultarHotel(String idHotel) {
        return gestorHoteles.consultar(idHotel);
    }

    public List<Hotel> listadoHoteles() {
        return gestorHoteles.listado();
    }

    public void mostrarListadoHoteles() {
        System.out.println("\n========== LISTADO DE HOTELES ==========");
        List<Hotel> hoteles = listadoHoteles();

        if (hoteles.isEmpty()) {
            System.out.println("No hay hoteles registrados");
            return;
        }

        System.out.println("Total de hoteles: " + hoteles.size());
        System.out.println("-----------------------------------------");

        for (Hotel h : hoteles) {
            System.out.println(h.toString());
            System.out.println("-----------------------------------------");
        }
    }


    // VUELOS

    public void altaVuelo(Vuelo vuelo) {
        gestorVuelos.alta(vuelo);
    }

    public void bajaVuelo(String codigoVuelo) {
        gestorVuelos.baja(codigoVuelo);
    }

    public Vuelo consultarVuelo(String codigoVuelo) {
        return gestorVuelos.consultar(codigoVuelo);
    }

    public List<Vuelo> listadoVuelos() {
        return gestorVuelos.listado();
    }

    public void mostrarListadoVuelos() {
        System.out.println("\n========== LISTADO DE VUELOS ==========");
        List<Vuelo> vuelos = listadoVuelos();

        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados");
            return;
        }

        System.out.println("Total de vuelos: " + vuelos.size());
        System.out.println("-----------------------------------------");

        for (Vuelo v : vuelos) {
            System.out.println(v.toString());
            System.out.println("-----------------------------------------");
        }
    }


    // RESERVAS

    public void altaReserva(Reserva reserva) {
        gestorReservas.alta(reserva);
    }

    public void bajaReserva(String idReserva) {
        gestorReservas.baja(idReserva);
    }

    public Reserva consultarReserva(String idReserva) {
        return gestorReservas.consultar(idReserva);
    }

    public List<Reserva> listadoReservas() {
        return gestorReservas.listado();
    }

    public void mostrarListadoReservas() {
        System.out.println("\n========== LISTADO DE RESERVAS ==========");
        List<Reserva> reservas = listadoReservas();

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        System.out.println("Total de reservas: " + reservas.size());
        System.out.println("-----------------------------------------");

        for (Reserva r : reservas) {
            System.out.println(r.toString());
            System.out.println("-----------------------------------------");
        }
    }

    public void mostrarResumenGeneral() {
        System.out.println("\n========== RESUMEN GENERAL ==========");
        System.out.println("Total de clientes: " + listadoClientes().size());
        System.out.println("Total de hoteles: " + listadoHoteles().size());
        System.out.println("Total de vuelos: " + listadoVuelos().size());
        System.out.println("Total de reservas: " + listadoReservas().size());
        System.out.println("=====================================\n");
    }

    // AUTENTICACIÓN

    public boolean autenticarAdmin(String usuario, String contrasenia) {
        return autenticador.validarAdmin(usuario, contrasenia);
    }

    public void cambiarCredencialesAdmin(String nuevoUsername, String nuevoPassword) {
        autenticador.cambiarCredenciales(nuevoUsername, nuevoPassword);
    }

    /**
     * Autentica un cliente verificando nombre, DNI y que esté activo.
     * Validaciones que realiza:
     * 1. Verifica que nombre y DNI no sean nulos ni estén vacíos
     * 2. Busca el cliente por DNI en el sistema
     * 3. Compara el nombre ingresado con el del cliente (sin importar mayúsculas)
     * 4. Verifica que el cliente esté activo en el sistema
     * @param nombre el nombre del cliente
     * @param dni el DNI del cliente
     * @return true si la autenticación es exitosa, false en caso contrario */
    public boolean autenticarCliente(String nombre, String dni) {
        if (nombre == null || nombre.isEmpty() || dni == null || dni.isEmpty()) return false;

        Cliente cliente = gestorClientes.consultar(dni);

        return cliente != null && cliente.getNombre().equalsIgnoreCase(nombre) && cliente.isUsuarioActivo();
    }


    // GETTERS

    public GestorClientes getGestorClientes() {
        return gestorClientes;
    }

    public GestorHoteles getGestorHoteles() {
        return gestorHoteles;
    }

    public GestorVuelos getGestorVuelos() {
        return gestorVuelos;
    }

    public GestorReservas getGestorReservas() {
        return gestorReservas;
    }

    public Autenticador getAutenticador() {
        return autenticador;
    }

}
