package Agencia.Modelo.Usuarios;

public class ClienteVIP extends Cliente{
    public ClienteVIP(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        setDescuentoAcumulado(15.0);
    }

    public ClienteVIP() {
        setDescuentoAcumulado(15.0);
    }

    @Override
    public String getTipoCliente() {
        return "";
    }
}
