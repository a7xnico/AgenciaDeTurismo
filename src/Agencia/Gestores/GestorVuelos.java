package Agencia.Gestores;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Vuelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorVuelos implements iGestionable<Vuelo> {
    private HashMap<String, Vuelo> vuelos;

    public GestorVuelos(){
        vuelos = new HashMap<>();
    }


    @Override
    public void alta(Vuelo objeto) {
        if (objeto == null){
            throw new IllegalArgumentException("No puede ingresar un vuelo nulo");
        }

        vuelos.put(objeto.getNumeroDeVuelo(), objeto);

        System.out.println("Vuelo agregado exitosamente");
    }

    @Override
    public void baja(String id) {
        Vuelo vuelo = consultar(id);

        if (vuelo == null){
            throw new IllegalArgumentException("el vuelo no existe");
        }

        vuelo.setActivo(false);

        System.out.println("vuelo dado de baja exitosamente");
    }

    @Override
    public void modificar(Vuelo objeto) {
        if(objeto == null) {
            throw new IllegalArgumentException("el vuelo no puede ser nulo");
        }

        Vuelo vueloExistente = consultar(objeto.getNumeroDeVuelo());

        if(vueloExistente == null) {
            throw new IllegalArgumentException("el vuelo no existe en el sistema");
        }

        vueloExistente.setCiudadOrigen(objeto.getCiudadOrigen());
        vueloExistente.setCiudadDestino(objeto.getCiudadDestino());
        vueloExistente.setCantPasajeros(objeto.getCantPasajeros());
        vueloExistente.setPrecio(objeto.getPrecio());
        vueloExistente.setFecha(objeto.getFecha());

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
