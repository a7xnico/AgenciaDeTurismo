package Agencia.Modelo.Servicios;

import Agencia.Modelo.Enums.EstadoReserva;
import Agencia.Modelo.Usuarios.Cliente;
import org.json.JSONException;
import org.json.JSONObject;

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
        this.fechaReserva = fechaReserva;
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

    public String getIdReserva() { return String.valueOf(idReserva); }
    public double getTotal() { return total; }
    public Cliente getCliente() { return cliente; }
    public Hotel getHotel() { return hotel; }
    public Vuelo getVuelo() { return vuelo; }
    public int getNoches() { return noches; }
    public void setNoches(int noches) {
        this.noches = noches;
        calcularTotal();
    }
    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fecha) { this.fechaReserva = fecha; }
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    public void setVuelo(Vuelo vuelo) {this.vuelo = vuelo;}
    public void setHotel(Hotel hotel) {this.hotel = hotel;}
    public EstadoReserva getEstado() {return estado;}
    public void setEstado(EstadoReserva estado) {this.estado = estado;}

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
