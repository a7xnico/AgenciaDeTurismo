package Agencia.GestionArchivos;


import Agencia.Modelo.Usuarios.Cliente;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor para serializar y deserializar clientes desde/hacia archivos JSON.
 *  Convierte listas de clientes a formato JSON para guardarlos en archivo,
 *  y viceversa al leer el archivo.
 *  @author Nicolas
 */

public class GestorJSONClientes {
    private String nomJson = "Clientes.json";

    public GestorJSONClientes(){}

    public void serializarLista(List<Cliente> lc){
        JSONArray jsonArray = new JSONArray();
        for (Cliente c : lc){
            jsonArray.put(c.toJson());
        }
        OperacionesLectoEscritura.grabar(nomJson, jsonArray);
    }

    public  List<Cliente> deserializarLista(){
        List<Cliente> lista = new ArrayList<>();
        JSONTokener tokener = OperacionesLectoEscritura.leer(nomJson);
        if (tokener == null)
            return lista;
        JSONArray jsonArray = new JSONArray(tokener);

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jCliente = jsonArray.getJSONObject(i);
            lista.add(Cliente.fromJson(jCliente));
        }
        return lista;
    }

}
