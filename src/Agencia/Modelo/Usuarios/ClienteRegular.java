package Agencia.Modelo.Usuarios;

import org.json.JSONObject;

/**
 * Representa un cliente de tipo REGULAR.
 * Tiene un descuento base del 5% en todas sus reservas. */
public class ClienteRegular extends Cliente{

    /**
     * Crea un nuevo cliente regular con los datos especificados.
     * El descuento inicial es del 5%.
     * @param nombre el nombre del cliente
     * @param apellido el apellido del cliente
     * @param dni el DNI del cliente
     * @param mail el email del cliente */
    public ClienteRegular(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        setDescuentoAcumulado(5.0);
    }

    public ClienteRegular() {
        setDescuentoAcumulado(5.0);
    }
    /**
     * Crea un cliente regular a partir de un objeto JSON.
     * @param jsonCliente objeto JSON con los datos del cliente */
    public ClienteRegular(JSONObject jsonCliente) {
        super(jsonCliente);
    }

    @Override
    public String getTipoCliente() {
        return "REGULAR";
    }
}
