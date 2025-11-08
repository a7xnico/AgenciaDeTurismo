package Agencia.Modelo.Interfaces;

import Agencia.Modelo.Servicios.Reserva;

import java.util.List;

public interface iGestionable<T> {
    void alta(T objeto);;
    void baja(String id);
    void modificar(T objeto);
    List<T> listado();
    T consultar(String id);
}
