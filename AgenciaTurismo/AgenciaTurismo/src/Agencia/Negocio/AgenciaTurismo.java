package Agencia.Negocio;

import Agencia.Gestores.GestorClientes;
import Agencia.Gestores.GestorHoteles;
import Agencia.Gestores.GestorVuelos;

public class AgenciaTurismo {
    private GestorClientes gestorClientes;
    private GestorVuelos gestorVuelos;
    private GestorHoteles gestorHoteles;

    public AgenciaTurismo() {
        gestorClientes = new GestorClientes();
        gestorVuelos = new GestorVuelos();
        gestorHoteles = new GestorHoteles();
    }
}
