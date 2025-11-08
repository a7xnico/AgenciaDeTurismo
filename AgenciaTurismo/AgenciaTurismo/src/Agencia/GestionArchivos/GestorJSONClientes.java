package Agencia.GestionArchivos;


import Agencia.Modelo.Usuarios.Cliente;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

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
