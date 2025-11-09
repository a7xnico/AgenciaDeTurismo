package Agencia.Gestores;
import Agencia.GestionArchivos.GestorJSONVuelos;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Vuelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    @Override
    public void alta(Vuelo vuelo) {
        if (vuelo == null){
            throw new IllegalArgumentException("No puede ingresar un vuelo nulo");
        }

        vuelos.put(vuelo.getNumeroDeVuelo(), vuelo);
        guardarJson();

        System.out.println("Vuelo agregado exitosamente");
    }

    @Override
    public void baja(String id) {
        Vuelo vuelo = consultar(id);

        if (vuelo == null){
            throw new IllegalArgumentException("el vuelo no existe");
        }

        vuelo.setActivo(false);
        guardarJson();

        System.out.println("vuelo dado de baja exitosamente");
    }

    @Override
    public void modificar(Vuelo vuelo) {
        if(vuelo == null) {
            throw new IllegalArgumentException("el vuelo no puede ser nulo");
        }

        Vuelo vueloExistente = consultar(vuelo.getNumeroDeVuelo());

        if(vueloExistente == null) {
            throw new IllegalArgumentException("el vuelo no existe en el sistema");
        }

        vueloExistente.setCiudadOrigen(vuelo.getCiudadOrigen());
        vueloExistente.setCiudadDestino(vuelo.getCiudadDestino());
        vueloExistente.setCantPasajeros(vuelo.getCantPasajeros());
        vueloExistente.setPrecio(vuelo.getPrecio());
        vueloExistente.setFecha(vuelo.getFecha());

        guardarJson();
        System.out.println("vuelo modificado exitosamente");
    }

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

    @Override
    public Vuelo consultar(String id) {
        return vuelos.get(id);
    }
}
