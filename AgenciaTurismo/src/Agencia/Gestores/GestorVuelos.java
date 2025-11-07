package Agencia.Gestores;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Vuelo;

import java.util.List;

public class GestorVuelos implements iGestionable<Vuelo> {


    @Override
    public void alta(Vuelo objeto) {

    }

    @Override
    public void baja(String id) {

    }

    @Override
    public void modificar(Vuelo objeto) {

    }

    @Override
    public List<Vuelo> listado() {
        return List.of();
    }

    @Override
    public Vuelo consultar(String id) {
        return null;
    }
}
