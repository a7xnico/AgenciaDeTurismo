package Agencia.Gestores;

import Agencia.Modelo.Enums.EstadoReserva;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Reserva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorReservas implements iGestionable<Reserva> {
    private HashMap<String, Reserva> reservas;

    public GestorReservas(){
        reservas = new HashMap<>();
    }

    @Override
    public void alta(Reserva reserva) {
        if (reserva == null){
            throw new IllegalArgumentException("La reserva no puede ser nula");
        }

        reservas.put(reserva.getIdReserva(), reserva);
        System.out.println("reserva agregada exitosamente");
    }

    @Override
    public void baja(String id) {
        Reserva reserva = consultar(id);
        if (reserva == null){
            throw new IllegalArgumentException("la reserva no existe");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        System.out.println("reserva cancelada exitosamente");
    }

    @Override
    public void modificar(Reserva reserva) {
        if (reserva == null){
            throw new IllegalArgumentException("la reserva no puede ser nula");
        }

        Reserva reservaExistente = consultar(reserva.getIdReserva());

        if(reservaExistente == null) {
            throw new IllegalArgumentException("la reserva no existe en el sistema");
        }

        reservaExistente.setCliente(reserva.getCliente());
        reservaExistente.setHotel(reserva.getHotel());
        reservaExistente.setVuelo(reserva.getVuelo());
        reservaExistente.setNoches(reserva.getNoches());
        reservaExistente.calcularTotal();
        reservaExistente.setFechaReserva(reserva.getFechaReserva());
        reservaExistente.setEstado(reserva.getEstado());

        System.out.println("reserva modificada exitosamente");

    }


    public List<Reserva> listado() {
        List<Reserva> reservasActivas = new ArrayList<>();
        for(Reserva r : reservas.values()) {
            if(r.getEstado() != EstadoReserva.CANCELADA) {
                reservasActivas.add(r);
            }
        }
        return reservasActivas;
    }

    @Override
    public Reserva consultar(String id) {
        return reservas.get(id);
    }
}
