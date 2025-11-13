package Agencia.Gestores;

import Agencia.GestionArchivos.GestorJSONReservas;
import Agencia.Modelo.Enums.EstadoReserva;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Reserva;
import Agencia.Modelo.Servicios.Vuelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Gestor de reservas
 * Maneja el CRUD de reservas y verifica automáticamente el estado de cada reserva
 * comparando fechas con la fecha actual.
 * Los estados posibles son: PENDIENTE, CONFIRMADA, FINALIZADA, CANCELADA.
 * El estado se actualiza automáticamente al consultar una reserva. */

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

    /**
     * Agrega una nueva reserva al sistema.
     * @param reserva la reserva a agregar
     * @throws IllegalArgumentException si la reserva es nula */

    @Override
    public void alta(Reserva reserva) {
        if (reserva == null) throw new IllegalArgumentException("La reserva no puede ser nula");

        reservas.put(reserva.getIdReserva(), reserva);
        guardarJson();
        System.out.println("reserva agregada exitosamente");
    }

    /**
     * Cancela una reserva existente.
     * La reserva cambia su estado a CANCELADA.
     * @param id el ID de la reserva a cancelar
     * @throws IllegalArgumentException si la reserva no existe */

    @Override
    public void baja(String id) {
        Reserva reserva = consultar(id);
        if (reserva == null) throw new IllegalArgumentException("la reserva no existe");

        reserva.setEstado(EstadoReserva.CANCELADA);
        guardarJson();
        System.out.println("reserva cancelada exitosamente");
    }

    /**
     * Modifica los datos de una reserva existente.
     * Recalcula el total y actualiza el estado automáticamente.
     * @param reserva la reserva con los datos actualizados
     * @throws IllegalArgumentException si la reserva es nula o no existe en el sistema */

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

    /**
     * Retorna un listado de reservas activas (no canceladas).
     * El estado de cada reserva se actualiza automáticamente antes de retornar.
     * @return lista con las reservas activas */
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

    /**
     * Busca una reserva por su ID.
     * Actualiza automáticamente su estado antes de retornarla.
     * @param id el ID de la reserva a buscar
     * @return la reserva encontrada, o null si no existe */

    @Override
    public Reserva consultar(String id) {
        Reserva reserva = reservas.get(id);
        if (reserva != null) {
            actualizarEstado(reserva);
            guardarJson();
        }
        return reserva;
    }


    /**
     * Actualiza el estado de una reserva verificando las fechas.
     * Se llama automáticamente al consultar o listar reservas.
     * @param reserva la reserva cuyo estado se debe actualizar */
    private void actualizarEstado(Reserva reserva){
        reserva.verificarEstado();
    }
}
