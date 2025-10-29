package Agencia.Gestores;

import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Usuarios.Cliente;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestorClientes implements iGestionable<Cliente> {
    private Set<Cliente> clientes;

    public GestorClientes() {
        clientes = new HashSet<>();
    }

    @Override
    public void alta(Cliente objeto) {

    }

    @Override
    public void baja(String id) {

    }

    @Override
    public void modificar(Cliente objeto) {

    }

    @Override
    public List<Cliente> listado() {
        return List.of();
    }

    @Override
    public Cliente consultar(String id) {
        return null;
    }
}
