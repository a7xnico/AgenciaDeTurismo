package Agencia.GestionArchivos;

import Agencia.Modelo.Usuarios.Admin;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorJSONAdmin {
    private String nomJson = "Admin.json";

    public GestorJSONAdmin(){}

    /**
     * Guarda el admin en archivo JSON.
     * @param admin el admin a guardar
     */
    public void serializarAdmin(Admin admin){
        JSONObject jsonAdmin = new JSONObject();
        try {
            jsonAdmin.put("username", admin.getUsername());
            jsonAdmin.put("password", admin.getPassword());
            OperacionesLectoEscritura.grabar(nomJson, jsonAdmin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el admin desde el archivo JSON.
     * Si el archivo no existe, retorna null.
     *
     * @return el admin cargado, o null si no existe
     */
    public Admin deserializarAdmin(){
        JSONTokener tokener = OperacionesLectoEscritura.leer(nomJson);
        if (tokener == null)
            return null;
        try {
            JSONObject jsonAdmin = new JSONObject(tokener);
            String username = jsonAdmin.getString("username");
            String password = jsonAdmin.getString("password");
            return new Admin(username, password);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
