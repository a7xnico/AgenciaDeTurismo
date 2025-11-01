package Agencia.Modelo.Usuarios;

public abstract class Cliente extends Persona {
    private double descuentoAcumulado;

    public Cliente(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        this.descuentoAcumulado = 0.0;
    }

    public Cliente() {
        super();
        this.descuentoAcumulado = 0.0;
    }

    public double getDescuentoAcumulado() {
        return descuentoAcumulado;
    }

    public void setDescuentoAcumulado(double descuentoAcumulado) {
        this.descuentoAcumulado = descuentoAcumulado;
    }

    public double calcularDescuento(double monto){
        return monto * (getDescuentoAcumulado() / 100);
    }

    public abstract String getTipoCliente();

    public void acumularDescuento(double porcentaje){
        this.descuentoAcumulado += porcentaje;
        if(this.descuentoAcumulado > 30){
            this.descuentoAcumulado = 30;
        }
    }

    public void modificarNombre(String nombre) { setNombre(nombre); }
    public void modificarApellido(String apellido) { setApellido(apellido); }
    public void modificarMail(String mail) { setMail(mail); }

    @Override
    public String toString() {
        String estado = isUsuarioActivo() ? "Activo" : "Inactivo";
        return " [" + getTipoCliente() + "] " + getNombreCompleto() +
                "\nDNI: " + getDni() + "\n| Email: " + getMail() +
                "\n Descuento: " + getDescuentoAcumulado() + "%  |Estado: " + estado;
    }
}
