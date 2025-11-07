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
    public void alta(Hotel objeto) {
     if(objeto==null)
     {
         throw new IllegalArgumentException("el hotel no puede ser nulo");
     }else
     {
         hoteles.add(objeto);
              System.out.println("hotel agregado exitosamente");
     }

    }

    @Override
    public void baja(String id) {
        Hotel aux=hoteles.get(Integer.parseInt(id));
        if(hoteles==null)
        {
            throw new IllegalArgumentException("ese id no esta asociado a ningun hotel");
        }else
        {
            hoteles.remove(id);
                 System.out.println("hotel eliminado exitosamente exitosamente");
        }

    }

    @Override
    public void modificar(Hotel objeto) {

    }

    @Override
    public List<Hotel> listado() {
        return List.of();
    }

    @Override
    public Hotel consultar(String id) {
        return null;
    }
}

