package Agencia.Modelo.Usuarios;

public class ClienteRegular extends Cliente{
    public ClienteRegular(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        setDescuentoAcumulado(5.0);
    }

    public ClienteRegular() {
        setDescuentoAcumulado(5.0);
    }

    @Override
    public String getTipoCliente() {
        return "CLIENTE REGULAR";
    }
}
