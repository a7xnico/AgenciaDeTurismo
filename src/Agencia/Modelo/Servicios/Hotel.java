package Agencia.Modelo.Servicios;

import Agencia.Gestores.Validador;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Hotel {
    private static int cantHoteles=0;
    private int idHotel;
    private String nombre;
    private String ciudad;
    private int estrellas;
    private double precioPorNoche;
    private int habitacionesDisponibles;
    private boolean activo;

    public Hotel(String nombre, String ciudad, int estrellas, double precioPorNoche, int habitaciones) {
        validarDatos(nombre, ciudad, estrellas, precioPorNoche, habitaciones);
        cantHoteles++;
        this.idHotel = cantHoteles;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
        this.precioPorNoche = precioPorNoche;
        this.habitacionesDisponibles = habitaciones;
        this.activo = habitaciones > 0;
    }

    public Hotel(JSONObject jsonHotel){
        try{
            this.idHotel = jsonHotel.getInt("idHotel");
            this.nombre = jsonHotel.getString("nombre");
            this.ciudad = jsonHotel.getString("ciudad");
            this.estrellas = jsonHotel.getInt("estrellas");
            this.precioPorNoche = jsonHotel.getDouble("precioPorNoche");
            this.habitacionesDisponibles = jsonHotel.getInt("habitacionesDisponibles");
            this.activo = jsonHotel.getBoolean("activo");
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public Hotel() {
        cantHoteles++;
        this.idHotel = cantHoteles;
    }

    private void validarDatos(String nombre, String ciudad, int estrellas, double precio, int habitaciones) {
        Validador.noVacio(nombre, "nombre del hotel");
        Validador.noVacio(ciudad, "ciudad");
        Validador.soloLetras(ciudad, "ciudad");
        if (estrellas < 1 || estrellas > 5) throw new IllegalArgumentException("Las estrellas deben estar entre 1 y 5");
        if (precio <= 0) throw new IllegalArgumentException("El precio por noche debe ser mayor a 0");
        if (habitaciones < 0) throw new IllegalArgumentException("Las habitaciones disponibles no pueden ser negativas");
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {
        Validador.noVacio(nombre, "nombre del hotel");
        this.nombre = nombre;}
    public String getCiudad() {return ciudad;}
    public void setCiudad(String ciudad) {
        Validador.soloLetras(ciudad, "ciudad");
        this.ciudad = ciudad;}
    public int getEstrellas() {return estrellas;}
    public double getPrecioPorNoche() {return precioPorNoche;}
    public void setPrecioPorNoche(double precioPorNoche) {
        if (precioPorNoche <= 0) throw new IllegalArgumentException("El precio por noche debe ser mayor a 0");
        this.precioPorNoche = precioPorNoche;}
    public int getHabitacionesDisponibles() {return habitacionesDisponibles;}
    public void setHabitacionesDisponibles(int habitaciones) {
        if (habitaciones < 0) throw new IllegalArgumentException("Las habitaciones no pueden ser negativas");
        this.habitacionesDisponibles = habitaciones;
        this.activo = habitaciones > 0;}
    public static int getCantHoteles() {return cantHoteles;}
    public String getIdHotel() {   return String.valueOf(idHotel);}
    public boolean isActivo() {return activo;}
    public void setActivo(boolean activo) {this.activo = activo;}

    public void setEstrellas(int estrellas) {
        if (estrellas < 1 || estrellas > 5)
            throw new IllegalArgumentException("Las estrellas deben estar entre 1 y 5");
        this.estrellas = estrellas;
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

    public JSONObject toJson(){
        JSONObject jsonHotel = null;
        try{
            jsonHotel = new JSONObject();
            jsonHotel.put("idHotel", this.idHotel);
            jsonHotel.put("nombre", this.nombre);
            jsonHotel.put("ciudad", this.ciudad);
            jsonHotel.put("estrellas", this.estrellas);
            jsonHotel.put("precioPorNoche", this.precioPorNoche);
            jsonHotel.put("habitacionesDisponibles", this.habitacionesDisponibles);
            jsonHotel.put("activo", this.activo);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonHotel;
    }
}




