package Agencia.GestionArchivos;

import Agencia.Modelo.Servicios.Hotel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor para serializar y deserializar hoteles desde/hacia archivos JSON.
 * Convierte listas de hoteles a formato JSON para guardarlos en archivo,
 * y viceversa al leer el archivo.
 * @author Nicolas
 */

public class GestorJSONHoteles {
    private String nomJson = "Hoteles.json";

    public GestorJSONHoteles(){}

    public void serializarLista(List<Hotel> lh){
        JSONArray jsonArray = new JSONArray();
        for (Hotel h : lh){
            jsonArray.put(h.toJson());
        }
        OperacionesLectoEscritura.grabar(nomJson, jsonArray);
    }

    public List<Hotel> deserializarLista(){
        List<Hotel> lista = new ArrayList<>();

        JSONTokener tokener = OperacionesLectoEscritura.leer(nomJson);
        if (tokener == null) return lista;

        JSONArray array = new JSONArray(tokener);

        for (int i = 0; i < array.length(); i++){
            JSONObject jHotel = array.getJSONObject(i);
            lista.add(new Hotel(jHotel));
        }
        return lista;
    }


}
