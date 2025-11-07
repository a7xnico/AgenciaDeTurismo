package Agencia.GestionArchivos;

import Agencia.Modelo.Servicios.Vuelo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class GestorJSONVuelos {
    private String nomJSON = "Vuelos.json";

    public GestorJSONVuelos(){}

    public void serializarLista(List<Vuelo> lv){
        JSONArray jsonArray = new JSONArray();
        for (Vuelo v : lv){
            jsonArray.put(v.toJson());
        }
        OperacionesLectoEscritura.grabar(nomJSON, jsonArray);
    }

    public List<Vuelo> deserializarLista(){
        List<Vuelo> lista = new ArrayList<>();
        JSONTokener tokener = OperacionesLectoEscritura.leer(nomJSON);
        if (tokener == null) return lista;
        JSONArray jArr = new JSONArray(tokener);
        for (int i = 0; i < jArr.length(); i++){
            JSONObject jVuelo = jArr.getJSONObject(i);
            lista.add(new Vuelo(jVuelo));
        }
        return lista;
    }

}
