package Agencia.Gestores;

import Agencia.GestionArchivos.GestorJSONClientes;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Usuarios.Cliente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Gestor de clientes que maneja el CRUD (crear, leer, actualizar, eliminar)
 * de clientes en la agencia de turismo.
 * Almacena los clientes en un Set y sincroniza con el archivo JSON.
 */

public class GestorClientes implements iGestionable<Cliente> {
    private Set<Cliente> clientes;
    private GestorJSONClientes gestorJson;

    public GestorClientes() {
        clientes = new HashSet<>();
        gestorJson = new GestorJSONClientes();
        cargarJson();
    }

    private void cargarJson(){
        List<Cliente> listaClientes = gestorJson.deserializarLista();
        if (listaClientes != null && !listaClientes.isEmpty()){
            clientes.addAll(listaClientes);
        }
    }

    private void guardarJson(){
        gestorJson.serializarLista(listadoJson());
    }

    /**
     * Agrega un nuevo cliente al sistema.
     *
     * @param cliente el cliente a agregar
     * @throws IllegalArgumentException si el cliente es nulo
     */

    @Override
    public void alta(Cliente cliente) {
        if(cliente==null)
        {
            throw new IllegalArgumentException("el cliente no puede ser nulo");

        }else
        {
            clientes.add(cliente);
            guardarJson();
            System.out.println("cliente agregado exitosamente");
        }

    }

    /**
     * Desactiva un cliente del sistema (baja lógica).
     * El cliente no se es eliminado del sistema, solo se considera inactivo
     * @param dni el DNI del cliente a dar de baja
     * @throws IllegalArgumentException si el cliente no existe
     */

    @Override
    public void baja(String dni) {
        Cliente cliente = consultar(dni);
        if (cliente ==  null){
            throw new IllegalArgumentException("El cliente no existe");
        }
        cliente.setUsuarioActivo(false);
        guardarJson();
    }

    /**
     * Modifica los datos de un cliente existente.
     * @param cliente el cliente con los datos actualizados
     * @throws IllegalArgumentException si el cliente es nulo o no existe en el sistema
     */

    @Override
    public void modificar(Cliente cliente) {
        if (cliente == null){
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        Cliente clienteExistente = consultar(cliente.getDni());
        if (clienteExistente == null){
            throw new IllegalArgumentException("El cliente a modificar no se encuentra en el sistema");
        }

        clientes.remove(clienteExistente);

        clienteExistente.modificarNombre(cliente.getNombre());
        clienteExistente.modificarApellido(cliente.getApellido());
        clienteExistente.modificarMail(cliente.getMail());
        clienteExistente.setUsuarioActivo(cliente.isUsuarioActivo());
        clientes.add(clienteExistente);

        guardarJson();
        System.out.println("Cliente modificado exitosamente");
    }

    /**
     * Retorna un listado de todos los clientes activos en el sistema.
     * @return lista con los clientes activos
     */

    @Override
    public List<Cliente> listado() {
        List<Cliente> listaActivos = new ArrayList<>();
        for(Cliente c : clientes)
            if (c.isUsuarioActivo())
                listaActivos.add(c);
        return listaActivos;
    }

    public ArrayList<Cliente> listadoJson(){
        return new ArrayList<>(clientes);
    }

    /**
     * Busca un cliente por su DNI.
     * @param dni el DNI del cliente a buscar
     * @return el cliente encontrado, o null si no existe
     */
    @Override
    public Cliente consultar(String dni) {
        Cliente cliente = null;
        for (Cliente c : clientes) if ((dni).equals(c.getDni())) cliente = c;
        return cliente;
    }
}

