package Agencia.Modelo.Usuarios;

import org.json.JSONObject;
/**
 * Representa un cliente de tipo VIP.
 * Tiene un descuento base del 15% en todas sus reservas.
 */
public class ClienteVIP extends Cliente{
    /**
     * Crea un nuevo cliente VIP con los datos especificados.
     * El descuento inicial es del 15%.
     * @param nombre el nombre del cliente
     * @param apellido el apellido del cliente
     * @param dni el DNI del cliente
     * @param mail el email del cliente */
    public ClienteVIP(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        setDescuentoAcumulado(15.0);
    }

    public ClienteVIP() {
        setDescuentoAcumulado(15.0);
    }
    /**
     * Crea un cliente VIP a partir de un objeto JSON.
     * @param jsonCliente objeto JSON con los datos del cliente */
    public ClienteVIP(JSONObject jsonCliente) {
        super(jsonCliente);
    }

    @Override
    public String getTipoCliente() {
        return "VIP";
    }
}
