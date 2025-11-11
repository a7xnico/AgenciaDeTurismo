package Agencia.Negocio;

import Agencia.Gestores.*;

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
}
