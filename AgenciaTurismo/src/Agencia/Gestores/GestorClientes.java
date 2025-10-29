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
        if(cliente==null)
        {
            throw new IllegalArgumentException("el cliente no puede ser nulo");
            
        }else
        {
            clientes.add(objeto);
            System.out.println("cliente agregado exitosamente");
        }

    }

    @Override
    public void baja(String id) {
     Cliente cliente=clientes.get(id);
        if(cliente==null)
        {
             throw new IllegalArgumentException("ese ID no se encuentra asociado a ningun cliente");
            
        }else
        {
            clientes.remove(id);
                 System.out.println("cliente eliminado exitosamente");
        }
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

