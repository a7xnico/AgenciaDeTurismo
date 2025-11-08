package Agencia.Modelo.Usuarios;

import org.json.JSONObject;

public class ClienteVIP extends Cliente{
    public ClienteVIP(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        setDescuentoAcumulado(15.0);
    }

    public ClienteVIP() {
        setDescuentoAcumulado(15.0);
    }

    public ClienteVIP(JSONObject jsonCliente) {
        super(jsonCliente);
    }

    @Override
    public String getTipoCliente() {
        return "VIP";
    }
}
