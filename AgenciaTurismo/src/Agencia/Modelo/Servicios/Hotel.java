package Agencia.Modelo.Servicios;

public class Hotel {
    private String nombre;
    private String ciudad;
    private int estrellas;
    private double precioPorNoche;
    private int habitacionesDisponibles;

    public Hotel(String nombre, String ciudad, int estrellas, double precioPorNoche, int habitacionesDisponibles) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
        this.precioPorNoche = precioPorNoche;
        this.habitacionesDisponibles = habitacionesDisponibles;
    }


}
