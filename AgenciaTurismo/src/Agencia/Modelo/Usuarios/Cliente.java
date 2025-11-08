package Agencia.Modelo.Usuarios;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static Cliente fromJson(JSONObject jsonCliente){
        String tipo = jsonCliente.getString("tipoCliente");

        if(tipo.equalsIgnoreCase("VIP")){
            return new ClienteVIP(jsonCliente);
        } else {
            return new ClienteRegular(jsonCliente);
        }
    }


}
