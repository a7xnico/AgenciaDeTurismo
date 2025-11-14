package Agencia.Gestores;
import Agencia.GestionArchivos.GestorJSONVuelos;
import Agencia.Modelo.Exceptions.DatosInvalidosException;
import Agencia.Modelo.Exceptions.EntidadNoEncontradaException;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Vuelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Gestor de vuelos que maneja el CRUD de vuelos en el sistema.
 * Almacena los vuelos en un HashMap y sincroniza con el archivo JSON.
 */

public class GestorVuelos implements iGestionable<Vuelo> {
    private HashMap<String, Vuelo> vuelos;
    private GestorJSONVuelos gestorJson;

    public GestorVuelos(){
        vuelos = new HashMap<>();
        gestorJson = new GestorJSONVuelos();
        cargarJson();
    }

    private void cargarJson(){
        List<Vuelo> listaVuelos = gestorJson.deserializarLista();
        if (listaVuelos != null && !listaVuelos.isEmpty())
            for (Vuelo v : listaVuelos)
                vuelos.put(v.getNumeroDeVuelo(), v);
    }

    private void guardarJson(){
        List<Vuelo> lista = listadoJson();
        gestorJson.serializarLista(lista);
    }

    /**
     * Agrega un nuevo vuelo al sistema.
     * @param vuelo el vuelo a agregar
     * @throws DatosInvalidosException si el vuelo es nulo */
    @Override
    public void alta(Vuelo vuelo) {
        if (vuelo == null){
            throw new DatosInvalidosException("No puede ingresar un vuelo nulo");
        }

        vuelos.put(vuelo.getNumeroDeVuelo(), vuelo);
        guardarJson();

        System.out.println("Vuelo agregado exitosamente");
    }
    /**
     * Desactiva un vuelo del sistema (baja lógica).
     * El vuelo no se elimina, solo se marca como inactivo, guardando los datos del mismo
     * @param id el número de vuelo a dar de baja
     * @throws DatosInvalidosException si el vuelo no existe */
    @Override
    public void baja(String id) {
        Vuelo vuelo = consultar(id);

        if (vuelo == null){
            throw new EntidadNoEncontradaException("el vuelo no existe");
        }

        vuelo.setActivo(false);
        guardarJson();

        System.out.println("vuelo dado de baja exitosamente");
    }

    /**
     * Modifica los datos de un vuelo existente.
     * @param vuelo el vuelo con los datos actualizados
     * @throws IllegalArgumentException si el vuelo es nulo o no existe en el sistema */
    @Override
    public void modificar(Vuelo vuelo) {
        if(vuelo == null) {
            throw new DatosInvalidosException("el vuelo no puede ser nulo");
        }

        Vuelo vueloExistente = consultar(vuelo.getNumeroDeVuelo());

        if(vueloExistente == null) {
            throw new EntidadNoEncontradaException("el vuelo no existe en el sistema");
        }

        vueloExistente.setCiudadOrigen(vuelo.getCiudadOrigen());
        vueloExistente.setCiudadDestino(vuelo.getCiudadDestino());
        vueloExistente.setCantPasajeros(vuelo.getCantPasajeros());
        vueloExistente.setPrecio(vuelo.getPrecio());
        vueloExistente.setFecha(vuelo.getFecha());

        guardarJson();
        System.out.println("vuelo modificado exitosamente");
    }
    /**
     * Retorna un listado de todos los vuelos activos.
     * @return lista con los vuelos activos */
    @Override
    public ArrayList<Vuelo> listado() {
        ArrayList<Vuelo> vuelosActivos = new ArrayList<>();

        for(Vuelo v : vuelos.values()) {
            if(v.isActivo()) {
                vuelosActivos.add(v);
            }
        }

        return vuelosActivos;
    }

    public ArrayList<Vuelo> listadoJson(){
        return new ArrayList<>(vuelos.values());
    }
    /**
     * Busca un vuelo por su número de vuelo.
     * @param id el número del vuelo a buscar
     * @return el vuelo encontrado, o null si no existe */
    @Override
    public Vuelo consultar(String id) {
        return vuelos.get(id);
    }
}
