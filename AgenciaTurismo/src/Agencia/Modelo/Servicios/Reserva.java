package Agencia.Modelo.Servicios;

import Agencia.Modelo.Usuarios.Cliente;

public class Reserva{
    private static int contadorReservas = 0;
    private int idReserva;
    private Cliente cliente;
    private Hotel hotel;
    private Vuelo vuelo;
    private int noches;
    private double total;
    private String fechaReserva;

    public Reserva(Cliente cliente, Hotel hotel, Vuelo vuelo, int noches, String fechaReserva) {
        contadorReservas++;
        this.idReserva = contadorReservas;
        this.cliente = cliente;
        this.hotel = hotel;
        this.vuelo = vuelo;
        this.noches = noches;
        this.fechaReserva = fechaReserva;
        calcularTotal();
    }

    public void calcularTotal(){
        double costoHotel = hotel.getPrecioPorNoche() * noches;
        double costoVuelo = vuelo.getPrecio();
        double subtotal = costoHotel + costoVuelo;
        double descuento = cliente.calcularDescuento(subtotal);
        this.total = subtotal - descuento;
    }

    public int getIdReserva() { return idReserva; }
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

    public String toString(){
        return "Reserva #" + idReserva + " |Cliente: " + cliente.getNombreCompleto() +
                "(DNI: " + cliente.getDni() + ")\n" +
                "Hotel: " + hotel.getNombre() + " - " + noches + "noches | Vuelo: " +
                vuelo.getNumeroDeVuelo() + "\n Fecha: " + fechaReserva + " | Total: $" + total +
                "(Descuento Aplicado: " + cliente.getDescuentoAcumulado() + "%)";
    }


}
