package Agencia.GestionArchivos;

import Agencia.Modelo.Servicios.Reserva;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class GestorJSONReservas {
    private String nomJson = "Reservas.json";

    public GestorJSONReservas(){}

    public void serializarLista(List<Reserva> lr) {
        JSONArray jsonArray = new JSONArray();
        for (Reserva r : lr){
            jsonArray.put(r.toJson());
        }
        OperacionesLectoEscritura.grabar(nomJson, jsonArray);
    }

    public List<Reserva> deserializarLista() {
        List<Reserva> lista = new ArrayList<>();

        JSONTokener tokener = OperacionesLectoEscritura.leer(nomJson);
        if (tokener == null) return lista;

        JSONArray arr = new JSONArray(tokener);

        for (int i = 0; i < arr.length(); i++){
            JSONObject jsonReserva = arr.getJSONObject(i);
            lista.add(new Reserva(jsonReserva));
        }

        return lista;
    }
}



