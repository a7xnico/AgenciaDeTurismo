package Agencia.Modelo.Servicios;

import org.json.JSONException;
import org.json.JSONObject;

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
  public void setNumeroDeVuelo(String numeroDeVuelo)
  {
    this.numeroDeVuelo=numeroDeVuelo;
  }
   public String getCiudadOrigen()
  {
    return ciudadOrigen;
  }
  public void setCiudadOrigen(String ciudadOrigen)
  {
    this.ciudadOrigen = ciudadOrigen;
  }
   public String getCiudadDestino()
  {
    return ciudadDestino;
  }
  public void setCiudadDestino(String ciudadDestino)
  {
    this.ciudadDestino=ciudadDestino;
  }
   public int getCantPasajeros()
  {
    return cantPasajeros;
  }
  public void setCantPasajeros(int cantPasajeros)
  {
    this.cantPasajeros=cantPasajeros;
  }
  public double getPrecio() { return precio; }
  public void setPrecio(double precio) { this.precio = precio; }
  public String getFecha() { return fecha; }
  public void setFecha(String fecha) { this.fecha = fecha; }
  public boolean isActivo() {return activo;}
  public void setActivo(boolean activo) {this.activo = activo;}

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
  
}

