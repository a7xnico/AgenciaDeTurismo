package Agencia.Modelo.Usuarios;

import org.json.JSONObject;

public class ClienteRegular extends Cliente{
    public ClienteRegular(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        setDescuentoAcumulado(5.0);
    }

    public ClienteRegular() {
        setDescuentoAcumulado(5.0);
    }

    public ClienteRegular(JSONObject jsonCliente) {
        super(jsonCliente);
    }

    @Override
    public String getTipoCliente() {
        return "REGULAR";
    }
}
