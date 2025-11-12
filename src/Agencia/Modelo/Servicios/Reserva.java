package Agencia.Modelo.Servicios;

import Agencia.Modelo.Enums.EstadoReserva;
import Agencia.Modelo.Usuarios.Cliente;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva{
    private static int contadorReservas = 0;
    private int idReserva;
    private Cliente cliente;
    private Hotel hotel;
    private Vuelo vuelo;
    private int noches;
    private double total;
    private String fechaReserva;
    private EstadoReserva estado;

    public Reserva(Cliente cliente, Hotel hotel, Vuelo vuelo, int noches, String fechaReserva) {
        contadorReservas++;
        this.idReserva = contadorReservas;
        this.cliente = cliente;
        this.hotel = hotel;
        this.vuelo = vuelo;
        this.noches = noches;
        this.fechaReserva = generarFechaActual();
        calcularTotal();
        this.estado = EstadoReserva.PENDIENTE;
    }

    public Reserva(JSONObject jsonReserva){
        try{
            this.idReserva = jsonReserva.getInt("idReserva");
            this.cliente = Cliente.fromJson(jsonReserva.getJSONObject("cliente"));
            this.hotel = new Hotel(jsonReserva.getJSONObject("hotel"));
            this.vuelo = new Vuelo(jsonReserva.getJSONObject("vuelo"));
            this.noches = jsonReserva.getInt("noches");
            this.total = jsonReserva.getDouble("total");
            this.fechaReserva = jsonReserva.getString("fechaReserva");
            this.estado = jsonReserva.getEnum(EstadoReserva.class ,"estado");
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public void calcularTotal(){
        double costoHotel = hotel.getPrecioPorNoche() * noches;
        double costoVuelo = vuelo.getPrecio();
        double subtotal = costoHotel + costoVuelo;
        double descuento = cliente.calcularDescuento(subtotal);
        this.total = subtotal - descuento;
    }

    private void validarDatos(Cliente cliente, Hotel hotel, Vuelo vuelo, int noches){
        if (cliente == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (hotel == null) throw new IllegalArgumentException("El hotel no puede ser nulo");
        if (vuelo == null) throw new IllegalArgumentException("El vuelo no puede ser nulo");
        if (noches <= 0) throw new IllegalArgumentException("La cantidad de noches debe ser mayor a 0");
    }

    private String generarFechaActual() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.now().format(formato);
    }

    public String getIdReserva() { return String.valueOf(idReserva); }
    public double getTotal() { return total; }
    public Cliente getCliente() { return cliente; }
    public Hotel getHotel() { return hotel; }
    public Vuelo getVuelo() { return vuelo; }
    public int getNoches() { return noches; }
    public void setNoches(int noches) {
        if (noches <= 0) throw new IllegalArgumentException("La cantidad de noches debe ser mayor a 0");
        this.noches = noches;
        calcularTotal();}
    public String getFechaReserva() { return fechaReserva; }
    public void setCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        this.cliente = cliente;}
    public void setVuelo(Vuelo vuelo) {
        if (vuelo == null) throw new IllegalArgumentException("El vuelo no puede ser nulo");
        this.vuelo = vuelo;}
    public void setHotel(Hotel hotel) {
        if (hotel == null) throw new IllegalArgumentException("El hotel no puede ser nulo");
        this.hotel = hotel;}
    public EstadoReserva getEstado() {return estado;}
    public void setEstado(EstadoReserva estado) {this.estado = estado;}

    public boolean verificarEstado(){
        try{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaVuelo = LocalDate.parse(vuelo.getFecha(), formato);
            LocalDate fechaFinInstancia = fechaVuelo.plusDays(noches);
            LocalDate hoy = LocalDate.now();

            if (this.estado == EstadoReserva.PENDIENTE && !hoy.isBefore(fechaVuelo)){
                this.estado = EstadoReserva.CONFIRMADA;
                System.out.println("Reserva #" + idReserva + " confirmada automáticamente (día del vuelo)");
                return true;
            }
            if (this.estado == EstadoReserva.CONFIRMADA && hoy.isAfter(fechaFinInstancia)){
                this.estado = EstadoReserva.COMPLETADA;
                System.out.println("Reserva #" + idReserva + " completada automáticamente (fin de estancia)");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al verificar fechas: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public String getFinEstancia() {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaVuelo = LocalDate.parse(vuelo.getFecha(), formato);
            LocalDate fechaFin = fechaVuelo.plusDays(noches);
            return fechaFin.format(formato);
        } catch (Exception e) {
            System.out.println("Error al calcular fecha de fin: " + e.getMessage());
            return null;
        }
    }





    public String toString(){
        return "Reserva #" + idReserva + " |Cliente: " + cliente.getNombreCompleto() +
                "(DNI: " + cliente.getDni() + ")\n" +
                "Hotel: " + hotel.getNombre() + " - " + noches + "noches | Vuelo: " +
                vuelo.getNumeroDeVuelo() + "\n Fecha: " + fechaReserva + " | Total: $" + total +
                "(Descuento Aplicado: " + cliente.getDescuentoAcumulado() + "%)";
    }

    public JSONObject toJson(){
        JSONObject jsonReserva = null;
        try{
            jsonReserva = new JSONObject();
            jsonReserva.put("idReserva", this.idReserva);
            jsonReserva.put("cliente", this.cliente.toJson());
            jsonReserva.put("hotel", this.hotel.toJson());
            jsonReserva.put("vuelo", this.vuelo.toJson());
            jsonReserva.put("noches", this.noches);
            jsonReserva.put("total", this.total);
            jsonReserva.put("fechaReserva", this.fechaReserva);
            jsonReserva.put("estado", this.estado.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonReserva;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Reserva reserva)) return false;
        return idReserva == reserva.idReserva;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idReserva);
    }
}
