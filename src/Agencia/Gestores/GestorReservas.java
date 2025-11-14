package Agencia.Gestores;

import Agencia.GestionArchivos.GestorJSONReservas;
import Agencia.Modelo.Enums.EstadoReserva;
import Agencia.Modelo.Exceptions.DatosInvalidosException;
import Agencia.Modelo.Exceptions.EntidadNoEncontradaException;
import Agencia.Modelo.Interfaces.iGestionable;
import Agencia.Modelo.Servicios.Hotel;
import Agencia.Modelo.Servicios.Reserva;

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
        if (listReservas != null && !listReservas.isEmpty()){
            int maxId = 0;
            for (Reserva r : listReservas){
                reservas.put(r.getIdReserva(), r);
                int id = Integer.parseInt(r.getIdReserva());
                if (id > maxId) maxId = id;
            }
            Reserva.setContadorReservas(maxId);
        }
    }

    private void guardarJson(){
        List<Reserva> lista = listadoJson();
        gestorJson.serializarLista(lista);
    }

    private void validarHabitacionesDisponibles(Hotel hotel) {
        if (hotel.getHabitacionesDisponibles() <= 0)
            throw new DatosInvalidosException("No hay habitaciones disponibles en el hotel " + hotel.getNombre());
    }

    /**
     * Agrega una nueva reserva al sistema.
     * @param reserva la reserva a agregar
     * @throws DatosInvalidosException si la reserva es nula */

    @Override
    public void alta(Reserva reserva) {
        if (reserva == null) throw new DatosInvalidosException("La reserva no puede ser nula");
        Hotel h = reserva.getHotel();
        validarHabitacionesDisponibles(h);
        reservas.put(reserva.getIdReserva(), reserva);
        h.setHabitacionesDisponibles(h.getHabitacionesDisponibles() - 1);
        guardarJson();
        System.out.println("reserva agregada exitosamente");
    }

    /**
     * Cancela una reserva existente.
     * La reserva cambia su estado a CANCELADA.
     * @param id el ID de la reserva a cancelar
     * @throws EntidadNoEncontradaException si la reserva no existe */

    @Override
    public void baja(String id) {
        Reserva reserva = reservas.get(id);
        if (reserva == null) throw new EntidadNoEncontradaException("la reserva no existe");

        Hotel h = reserva.getHotel();
        reserva.setEstado(EstadoReserva.CANCELADA);
        h.setHabitacionesDisponibles(h.getHabitacionesDisponibles() + 1);

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
        if (reserva == null) throw new DatosInvalidosException("la reserva no puede ser nula");

        Reserva reservaExistente = consultar(reserva.getIdReserva());

        if(reservaExistente == null) throw new EntidadNoEncontradaException("la reserva no existe en el sistema");
        // Verifica que vaya a cambiarse o no el hotel
        // Si lo cambia, devuelve al hotel anterior la habitacion disponible para no tener perdida de datos
        if (!reservaExistente.getHotel().getIdHotel().equals(reserva.getHotel().getIdHotel())) {
            validarHabitacionesDisponibles(reserva.getHotel());
            reservaExistente.getHotel().setHabitacionesDisponibles(reservaExistente.getHotel().getHabitacionesDisponibles() + 1);
            reserva.getHotel().setHabitacionesDisponibles(reserva.getHotel().getHabitacionesDisponibles() - 1);
        }

        reservaExistente.setCliente(reserva.getCliente());
        reservaExistente.setHotel(reserva.getHotel());
        reservaExistente.setVuelo(reserva.getVuelo());
        reservaExistente.setNoches(reserva.getNoches());
        reservaExistente.calcularTotal();

        EstadoReserva estadoAnterior = reservaExistente.getEstado();
        reservaExistente.setEstado(reserva.getEstado());

        if (reserva.getEstado() == EstadoReserva.COMPLETADA && estadoAnterior != EstadoReserva.COMPLETADA) {
            Hotel hotel = reservaExistente.getHotel();
            hotel.setHabitacionesDisponibles(hotel.getHabitacionesDisponibles() + 1);
        }

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
            EstadoReserva estadoAnterior = r.getEstado();
            r.verificarEstado();
            if (r.getEstado() == EstadoReserva.COMPLETADA && estadoAnterior != EstadoReserva.COMPLETADA) {
                Agencia.Modelo.Servicios.Hotel hotel = r.getHotel();
                hotel.setHabitacionesDisponibles(hotel.getHabitacionesDisponibles() + 1);
            }
            if(r.getEstado() != EstadoReserva.CANCELADA) reservasActivas.add(r);
        }
        guardarJson();
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
            boolean cambio = reserva.verificarEstado();
            if (cambio && reserva.getEstado() == EstadoReserva.COMPLETADA) {
                Agencia.Modelo.Servicios.Hotel hotel = reserva.getHotel();
                hotel.setHabitacionesDisponibles(hotel.getHabitacionesDisponibles() + 1);
                guardarJson();
            }
        }
        return reserva;
    }
}
