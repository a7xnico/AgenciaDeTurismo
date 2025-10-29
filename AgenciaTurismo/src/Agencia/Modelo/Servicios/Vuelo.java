package Agencia.Modelo.Servicios;

public class Vuelo {
  private String nuemeroDeVuelo;
  private String cuidadOrigen;
  private String cuidadDestino;
  private int cantPasajeros;
  
  public Vuelo(String numeroDeVuelo,String cuidadOrigen,String cuidadDestino, int cantPasajeros)
  {
    this.numeroDeVuelo=numeroDeVuelo;
    this.cuidadOrigen=cuidadOrigen;
    this.cuidadDestino=cuidadDestino;
    this.cantPasajeros=cantPasajeros;
  }
  public String getNumeroDeVuelo()
  {
    return numeroDeVuelo;
  }
  public void setNumeroDeVuelo(String numeroDeVuelo)
  {
    this.numeroDeVuelo=numeroDeVuelo;
  }
   public String getCuidadDeOrigen()
  {
    return cuidadDeOrigen;
  }
  public void setCuidadDeOrigen(String cuidadDeOrigen)
  {
    this.cuidadDeOrigen=cuidadDeOrigen;
  }
   public String getCuidadDestino()
  {
    return cuidadDestino;
  }
  public void setCuidadDestino(String cuidadDestino)
  {
    this.cuidadDestino=cuidadDestino;
  }
   public int getCantPasajeros()
  {
    return cantPasajeros;
  }
  public void setCantPasajeros(int cantPasajeros)
  {
    this.cantPasajeros=cantPasajeros;
  }
  public String toString()
  {
    return "numero de vuelo: "+numeroDeVuelo+"cuidad de origen: "+cuidaddeOrigen+"cuidad destino: "+cuidadDestino+"cantidad de pasajeros: "+cantPasajeros;
  }
  
}

