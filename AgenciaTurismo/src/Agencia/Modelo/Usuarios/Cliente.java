package Agencia.Modelo.Usuarios;

public abstract class Cliente extends Persona {
    private double puntosAcumulados;

    public Cliente(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        puntosAcumulados = 0;
    }

    public Cliente() {
        super();
        puntosAcumulados = 0;
    }

    public double getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(double puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    @Override
    public String toString() {
        return super.toString() + "Puntos acumulados= " + puntosAcumulados;
    }
}
