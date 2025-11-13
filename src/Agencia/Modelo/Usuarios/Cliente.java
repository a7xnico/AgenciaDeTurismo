package Agencia.Modelo.Usuarios;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase abstracta que representa un cliente en el sistema.
 * Extiende de Persona y agrega la funcionalidad de descuentos.
 * Los clientes pueden ser de dos tipos: REGULAR (5% descuento) o VIP (15% descuento).
 * El descuento se aplica automáticamente al calcular el total de una reserva. */

public abstract class Cliente extends Persona {
    private double descuentoAcumulado;
    /**
     * Crea un nuevo cliente con los datos especificados.
     * @param nombre el nombre (solo letras)
     * @param apellido el apellido (solo letras)
     * @param dni el DNI (solo números)
     * @param mail el correo electrónico */
    public Cliente(String nombre, String apellido, String dni, String mail) {
        super(nombre, apellido, dni, mail);
        this.descuentoAcumulado = 0.0;
    }

    public Cliente() {
        super();
        this.descuentoAcumulado = 0.0;
    }
    /**
     * Crea un cliente a partir de un objeto JSON.
     * Se utiliza al deserializar clientes desde el archivo JSON.
     * El metodo fromJson() decide si crear un ClienteRegular o ClienteVIP.
     * @param jsonCliente objeto JSON con los datos del cliente
     */
    public Cliente(JSONObject jsonCliente){
        this.modificarNombre(jsonCliente.getString("nombre"));
        this.modificarApellido(jsonCliente.getString("apellido"));
        this.modificarMail(jsonCliente.getString("mail"));
        this.modificarDni(jsonCliente.getString("dni"));
        this.setDescuentoAcumulado(jsonCliente.getDouble("descuentoAcumulado"));
        this.setUsuarioActivo(jsonCliente.getBoolean("usuarioActivo"));
    }



    public double getDescuentoAcumulado() {
        return descuentoAcumulado;
    }

    public void setDescuentoAcumulado(double descuentoAcumulado) {
        this.descuentoAcumulado = descuentoAcumulado;
    }
    /**
     * Calcula el monto de descuento sobre una cantidad.
     * Fórmula: monto × (descuentoAcumulado / 100)
     * Por ejemplo: calcularDescuento(1000) con 15% retorna 150
     * @param monto el monto sobre el cual calcular el descuento
     * @return el valor del descuento a aplicar
     */
    public double calcularDescuento(double monto){
        return monto * (getDescuentoAcumulado() / 100);
    }
    /**
     * Retorna el tipo de cliente.
     * Metodo abstracto que debe ser implementado por las subclases.
     * @return "REGULAR" o "VIP"
     */
    public abstract String getTipoCliente();

    public void modificarNombre(String nombre) { setNombre(nombre); }
    public void modificarApellido(String apellido) { setApellido(apellido); }
    public void modificarMail(String mail) { setMail(mail); }
    public void modificarDni(String dni){setDni(dni);}

    @Override
    public String toString() {
        String estado = isUsuarioActivo() ? "Activo" : "Inactivo";
        return " [" + getTipoCliente() + "] " + getNombreCompleto() +
                "\nDNI: " + getDni() + "\n| Email: " + getMail() +
                "\n Descuento: " + getDescuentoAcumulado() + "%  |Estado: " + estado;
    }

    public JSONObject toJson(){
        JSONObject jsonCliente = null;
        try{
            jsonCliente = new JSONObject();
            jsonCliente.put("tipoCliente", getTipoCliente());
            jsonCliente.put("nombre", this.getNombre());
            jsonCliente.put("apellido", this.getApellido());
            jsonCliente.put("mail", this.getMail());
            jsonCliente.put("dni", this.getDni());
            jsonCliente.put("descuentoAcumulado", this.getDescuentoAcumulado());
            jsonCliente.put("usuarioActivo", this.isUsuarioActivo());
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonCliente;
    }
    /**
     * Metodo factory que deserializa un cliente desde JSON.
     * Automáticamente crea un ClienteRegular o ClienteVIP según el tipo almacenado.
     * @param jsonCliente objeto JSON con los datos del cliente
     * @return una instancia de ClienteRegular o ClienteVIP según corresponda
     */
    public static Cliente fromJson(JSONObject jsonCliente){
        String tipo = jsonCliente.getString("tipoCliente");

        if(tipo.equalsIgnoreCase("VIP")){
            return new ClienteVIP(jsonCliente);
        } else {
            return new ClienteRegular(jsonCliente);
        }
    }


}
