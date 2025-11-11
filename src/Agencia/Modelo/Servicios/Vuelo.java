package Agencia.Modelo.Servicios;

import Agencia.Gestores.Validador;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Vuelo {
  private String numeroDeVuelo;
  private String ciudadOrigen;
  private String ciudadDestino;
  private int cantPasajeros;
  private double precio;
  private String fecha;
  private boolean activo;
  
  public Vuelo(String numeroDeVuelo,String ciudadOrigen,String ciudadDestino, int cantPasajeros, double precio, String fecha)
  {
    validarDatos(numeroDeVuelo, ciudadOrigen, ciudadDestino, cantPasajeros, precio, fecha);
    this.numeroDeVuelo =numeroDeVuelo;
    this.ciudadOrigen=ciudadOrigen;
    this.ciudadDestino=ciudadDestino;
    this.cantPasajeros=cantPasajeros;
    this.precio = precio;
    this.fecha = fecha;
    this.activo = true;
  }

  public Vuelo() {
  }

  public Vuelo(JSONObject jsonVuelo){
    this.numeroDeVuelo = jsonVuelo.getString("numeroDeVuelo");
    this.ciudadOrigen = jsonVuelo.getString("ciudadOrigen");
    this.ciudadDestino = jsonVuelo.getString("ciudadDestino");
    this.cantPasajeros = jsonVuelo.getInt("cantPasajeros");
    this.precio = jsonVuelo.getDouble("precio");
    this.fecha = jsonVuelo.getString("fecha");
    this.activo = jsonVuelo.getBoolean("activo");
  }

  public String getNumeroDeVuelo()
  {
    return numeroDeVuelo;
  }
   public String getCiudadOrigen()
  {
    return ciudadOrigen;
  }
  public void setCiudadOrigen(String ciudadOrigen) {
    Validador.noVacio(numeroDeVuelo, "número de vuelo");
    this.ciudadOrigen = ciudadOrigen;}
   public String getCiudadDestino()
  {
    return ciudadDestino;
  }
  public void setCiudadDestino(String ciudadDestino) {
    Validador.soloLetras(ciudadDestino, "ciudad de destino");
    this.ciudadDestino=ciudadDestino;}
   public int getCantPasajeros()
  {
    return cantPasajeros;
  }
  public void setCantPasajeros(int cantPasajeros) {
    if (cantPasajeros <= 0) throw new IllegalArgumentException("La cantidad de pasajeros debera ser mayor a 0");
    this.cantPasajeros=cantPasajeros;}
  public double getPrecio() { return precio; }
  public void setPrecio(double precio) {
    if (precio <= 0) throw new IllegalArgumentException("El precio debe ser mayor a 0");
    this.precio = precio; }
  public String getFecha() { return fecha; }
  public boolean isActivo() {return activo;}
  public void setActivo(boolean activo) {this.activo = activo;}

  public void validarDatos(String numeroDeVuelo, String ciudadOrigen, String ciudadDestino, int cantPasajeros, double precio, String fecha){
    Validador.noVacio(numeroDeVuelo, "número de vuelo");
    Validador.soloLetras(ciudadOrigen, "ciudad de origen");
    Validador.soloLetras(ciudadDestino, "ciudad de destino");
    Validador.fecha(fecha, "fecha");
    if (cantPasajeros <= 0) throw new IllegalArgumentException("La cantidad de pasajeros debera ser mayor a 0");
    if (precio <= 0) throw new IllegalArgumentException("El precio debe ser mayor a 0");
  }

  public String toString()
  {
    return "Vuelo "+ numeroDeVuelo +":"+ ciudadOrigen +
            " -> "+ ciudadDestino + " (" + fecha + ") | $" + precio +
            "| Pasajeros disponibles: " + cantPasajeros;
  }

  public JSONObject toJson(){
    JSONObject jsonVuelo = null;
    try{
      jsonVuelo = new JSONObject();
      jsonVuelo.put("numeroDeVuelo", this.numeroDeVuelo);
      jsonVuelo.put("ciudadOrigen", this.ciudadOrigen);
      jsonVuelo.put("ciudadDestino", this.ciudadDestino);
      jsonVuelo.put("cantPasajeros", this.cantPasajeros);
      jsonVuelo.put("precio", this.precio);
      jsonVuelo.put("fecha", this.fecha);
      jsonVuelo.put("activo", this.activo);
    }catch (JSONException e){
      e.printStackTrace();
    }
    return jsonVuelo;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Vuelo vuelo)) return false;
    return Objects.equals(numeroDeVuelo, vuelo.numeroDeVuelo);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(numeroDeVuelo);
  }
}

