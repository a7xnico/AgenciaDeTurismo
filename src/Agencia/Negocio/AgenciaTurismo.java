package Agencia.Negocio;

import Agencia.Gestores.*;
import Agencia.Modelo.Servicios.Hotel;
import Agencia.Modelo.Servicios.Reserva;
import Agencia.Modelo.Servicios.Vuelo;
import Agencia.Modelo.Usuarios.Cliente;

import java.util.List;

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

    // AUTENTICACIÓN

    public boolean autenticarAdmin(String usuario, String contrasenia) {
        return autenticador.validarAdmin(usuario, contrasenia);
    }

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
