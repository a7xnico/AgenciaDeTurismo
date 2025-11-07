package Agencia.Modelo.Servicios;

import java.util.Objects;

public class Hotel {
    private static int cantHoteles=0;
    private int idHotel;
    private String nombre;
    private String ciudad;
    private int estrellas;
    private double precioPorNoche;
    private int habitacionesDisponibles;

    public Hotel(String nombre, String ciudad, int estrellas, double precioPorNoche, int habitaciones) {
        cantHoteles++;
        this.idHotel = cantHoteles;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
        this.precioPorNoche = precioPorNoche;
        this.habitacionesDisponibles = habitaciones;
    }

    public Hotel() {
        cantHoteles++;
        this.idHotel = cantHoteles;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getCiudad() {return ciudad;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}
    public int getEstrellas() {return estrellas;}
    public double getPrecioPorNoche() {return precioPorNoche;}
    public void setPrecioPorNoche(double precioPorNoche) {this.precioPorNoche = precioPorNoche;}
    public int getHabitacionesDisponibles() {return habitacionesDisponibles;}
    public void setHabitacionesDisponibles(int habitaciones) {this.habitacionesDisponibles = habitaciones;}
    public static int getCantHoteles() {return cantHoteles;}
    public int getIdHotel() {   return idHotel;}

    public void setEstrellas(int estrellas) {
        if (estrellas >= 1 && estrellas <= 5) {
            this.estrellas = estrellas;
        }
    }

    public String estrellasVisible(){
        return "⭐".repeat(estrellas);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hotel hotel)) return false;
        return idHotel == hotel.idHotel;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idHotel);
    }

    @Override
    public String toString() {
        return "ID: " + idHotel + " | " + nombre + " " + estrellasVisible()
                + " - " + ciudad + "\n $" + precioPorNoche +
                "/Noche | Habitaciones: " + habitacionesDisponibles;
    }
}




