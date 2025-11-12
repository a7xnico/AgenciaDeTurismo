package Agencia.Gestores;

import Agencia.GestionArchivos.GestorJSONReservas;
import Agencia.Modelo.Enums.EstadoReserva;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Reserva;
import Agencia.Modelo.Servicios.Vuelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorReservas implements iGestionable<Reserva> {
    private HashMap<String, Reserva> reservas;
    private GestorJSONReservas gestorJson;

    public GestorReservas(){
        reservas = new HashMap<>();
        gestorJson = new GestorJSONReservas();
        cargarJson();
    }

    private void cargarJson(){
        List<Reserva> listReservas = gestorJson.deserializarLista();
        if (listReservas != null && !listReservas.isEmpty())
            for (Reserva r : listReservas)
                reservas.put(r.getIdReserva(), r);
    }

    private void guardarJson(){
        List<Reserva> lista = listadoJson();
        gestorJson.serializarLista(lista);
    }

    @Override
    public void alta(Reserva reserva) {
        if (reserva == null) throw new IllegalArgumentException("La reserva no puede ser nula");

        reservas.put(reserva.getIdReserva(), reserva);
        guardarJson();
        System.out.println("reserva agregada exitosamente");
    }

    @Override
    public void baja(String id) {
        Reserva reserva = consultar(id);
        if (reserva == null) throw new IllegalArgumentException("la reserva no existe");

        reserva.setEstado(EstadoReserva.CANCELADA);
        guardarJson();
        System.out.println("reserva cancelada exitosamente");
    }

    @Override
    public void modificar(Reserva reserva) {
        if (reserva == null) throw new IllegalArgumentException("la reserva no puede ser nula");

        Reserva reservaExistente = consultar(reserva.getIdReserva());

        if(reservaExistente == null) throw new IllegalArgumentException("la reserva no existe en el sistema");

        reservaExistente.setCliente(reserva.getCliente());
        reservaExistente.setHotel(reserva.getHotel());
        reservaExistente.setVuelo(reserva.getVuelo());
        reservaExistente.setNoches(reserva.getNoches());
        reservaExistente.calcularTotal();
        reservaExistente.setEstado(reserva.getEstado());

        guardarJson();
        System.out.println("reserva modificada exitosamente");

    }


    public List<Reserva> listado() {
        List<Reserva> reservasActivas = new ArrayList<>();
        for(Reserva r : reservas.values()) {
            actualizarEstado(r);
            if(r.getEstado() != EstadoReserva.CANCELADA) {
                reservasActivas.add(r);
            }
        }
        return reservasActivas;
    }

    public List<Reserva> listadoJson(){
        return new ArrayList<>(reservas.values());
    }

    @Override
    public Reserva consultar(String id) {
        Reserva reserva = reservas.get(id);
        if (reserva != null) {
            actualizarEstado(reserva);
            guardarJson();
        }
        return reserva;
    }

    private void actualizarEstado(Reserva reserva){
        reserva.verificarEstado();
    }
}
