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

    }

    @Override
    public void baja(String id) {

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
