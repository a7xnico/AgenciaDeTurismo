package Agencia.GestionArchivos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase con métodos estáticos para leer y escribir archivos JSON.
 * Se utiliza como intermediaria entre los gestores JSON y el sistema de archivos.
 * Maneja automáticamente las excepciones de lectura/escritura.
 * @author Nicolas
 */

public class OperacionesLectoEscritura {

    /**
     * Guarda un JSONObject en un archivo con formato indentado.
     *
     * @param nombreArchivo nombre del archivo donde guardar (ej: "archivo.json")
     * @param jsonObject el objeto JSON a guardar
     */

    public static void grabar(String nombreArchivo, JSONObject jsonObject){
        try(FileWriter fw = new FileWriter(nombreArchivo)){
            fw.write(jsonObject.toString(4));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Guarda un JSONArray en un archivo con formato indentado.
     * @param nombreArchivo nombre del archivo donde guardar (ej: "archivo.json")
     * @param jsonArray el array JSON a guardar
     */

    public static void grabar(String nombreArchivo, JSONArray jsonArray){
        try(FileWriter fw = new FileWriter(nombreArchivo)){
            fw.write(jsonArray.toString(4));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Lee un archivo JSON y retorna un JSONTokener para procesarlo.
     * Si el archivo no existe, retorna null.
     *
     * @param nombreArchivo nombre del archivo a leer (ej: "archivo.json")
     * @return un JSONTokener para procesar el contenido, o null si no existe el archivo
     */

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
