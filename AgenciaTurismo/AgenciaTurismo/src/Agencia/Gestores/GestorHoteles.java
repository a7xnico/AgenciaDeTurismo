package Agencia.Gestores;

import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Hotel;

import java.util.ArrayList;
import java.util.List;

public class GestorHoteles implements iGestionable<Hotel> {
    private List<Hotel> hoteles;

    public GestorHoteles() {
        hoteles = new ArrayList<>();
    }

    @Override
    public void alta(Hotel hotel) {
     if(hotel==null)
     {
         throw new IllegalArgumentException("el hotel no puede ser nulo");
     }
     hoteles.add(hotel);
     System.out.println("hotel agregado exitosamente");


    }

    @Override
    public void baja(String id) {
        Hotel hotel = consultar(id);
        if(hotel==null)
        {
            throw new IllegalArgumentException("El hotel no existe.");
        }

        hotel.setActivo(false);
        System.out.println("Hotel dado de baja");

    }

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

        System.out.println("hotel modificado exitosamente");

    }

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

    @Override
    public Hotel consultar(String id) {

        for (Hotel h : hoteles)
            if (h.getIdHotel().equals(id))
                return h;
        return null;
    }
}

