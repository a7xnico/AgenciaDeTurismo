package Agencia.GestionArchivos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperacionesLectoEscritura {

    public static void grabar(String nombreArchivo, JSONObject jsonObject){
        try(FileWriter fw = new FileWriter(nombreArchivo)){
            fw.write(jsonObject.toString(4));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void grabar(String nombreArchivo, JSONArray jsonArray){
        try(FileWriter fw = new FileWriter(nombreArchivo)){
            fw.write(jsonArray.toString(4));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static JSONTokener leer(String nombreArchivo){
        JSONTokener tokener = null;
        try{
            tokener = new JSONTokener(new FileReader(nombreArchivo));
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return tokener;
    }




}
