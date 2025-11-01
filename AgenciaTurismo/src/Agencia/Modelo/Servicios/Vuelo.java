package Agencia.Modelo.Servicios;

public class Vuelo {
  private String numeroDeVuelo;
  private String ciudadOrigen;
  private String ciudadDestino;
  private int cantPasajeros;
  private double precio;
  private String fecha;
  
  public Vuelo(String numeroDeVuelo,String ciudadOrigen,String ciudadDestino, int cantPasajeros, double precio, String fecha)
  {
    this.numeroDeVuelo =numeroDeVuelo;
    this.ciudadOrigen=ciudadOrigen;
    this.ciudadDestino=ciudadDestino;
    this.cantPasajeros=cantPasajeros;
    this.precio = precio;
    this.fecha = fecha;
  }

  public Vuelo() {
  }

  public String getNumeroDeVuelo()
  {
    return numeroDeVuelo;
  }
  public void setNumeroDeVuelo(String numeroDeVuelo)
  {
    this.numeroDeVuelo=numeroDeVuelo;
  }
   public String getCuidadOrigen()
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
  public String toString()
  {
    return "Vuelo "+ numeroDeVuelo +":"+ ciudadOrigen +
            " -> "+ ciudadDestino + " (" + fecha + ") | $" + precio +
            "| Pasajeros disponibles: " + cantPasajeros;
  }
  
}

