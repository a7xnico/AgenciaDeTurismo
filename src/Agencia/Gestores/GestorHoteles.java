package Agencia.Gestores;

import Agencia.GestionArchivos.GestorJSONHoteles;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de hoteles que maneja el CRUD de hoteles en el sistema.
 * Almacena los hoteles en una lista y sincroniza con el archivo JSON.*/

public class GestorHoteles implements iGestionable<Hotel> {
    private List<Hotel> hoteles;
    private GestorJSONHoteles gestorJson;

    public GestorHoteles() {
        hoteles = new ArrayList<>();
        gestorJson = new GestorJSONHoteles();
        cargarJson();
    }

    private void cargarJson(){
        hoteles = gestorJson.deserializarLista();
    }

    private void guardarJson(){
        gestorJson.serializarLista( hoteles);
    }

    /**
     * Agrega un nuevo hotel al sistema.
     * @param hotel el hotel a agregar
     * @throws IllegalArgumentException si el hotel es nulo*/

    @Override
    public void alta(Hotel hotel) {
     if(hotel==null)
     {
         throw new IllegalArgumentException("el hotel no puede ser nulo");
     }
     hoteles.add(hotel);
     guardarJson();
     System.out.println("hotel agregado exitosamente");


    }

    /**
     * Desactiva un hotel del sistema (baja lógica).
     * El hotel no se elimina, solo se marca como inactivo.
     * @param id el ID del hotel a dar de baja
     * @throws IllegalArgumentException si el hotel no existe*/

    @Override
    public void baja(String id) {
        Hotel hotel = consultar(id);
        if(hotel==null)
        {
            throw new IllegalArgumentException("El hotel no existe.");
        }

        hotel.setActivo(false);
        guardarJson();
        System.out.println("Hotel dado de baja");
    }

    /**
     * Modifica los datos de un hotel existente.
     * @param hotel el hotel con los datos actualizados
     * @throws IllegalArgumentException si el hotel es nulo o no existe en el sistema*/

    @Override
    public void modificar(Hotel hotel) {
        if (hotel == null){
            throw new IllegalArgumentException("El hotel no puede ser nulo");
        }

        Hotel hotelExistente = consultar(hotel.getIdHotel());
        if (hotelExistente == null){
            throw new IllegalArgumentException("El hotel no se encuentra en el sistema");
        }

        hotelExistente.setNombre(hotel.getNombre());
        hotelExistente.setCiudad(hotel.getCiudad());
        hotelExistente.setEstrellas(hotel.getEstrellas());
        hotelExistente.setPrecioPorNoche(hotel.getPrecioPorNoche());
        hotelExistente.setHabitacionesDisponibles(hotel.getHabitacionesDisponibles());

        guardarJson();
        System.out.println("hotel modificado exitosamente");

    }

    /**
     * Retorna un listado de todos los hoteles activos.
     * @return lista con los hoteles activos*/
    @Override
    public ArrayList<Hotel> listado() {
        ArrayList<Hotel> hotelesActivos = new ArrayList<>();
        for (Hotel h : hoteles){
            if (h.isActivo())
                hotelesActivos.add(h);
            }
        return hotelesActivos;
    }

    public List<Hotel> listadoJson(){
        return hoteles;
    }

    /**
     * Busca un hotel por su ID.
     * @param id el ID del hotel a buscar
     * @return el hotel encontrado, o null si no existe*/

    @Override
    public Hotel consultar(String id) {

        for (Hotel h : hoteles)
            if (h.getIdHotel().equals(id))
                return h;
        return null;
    }
}

