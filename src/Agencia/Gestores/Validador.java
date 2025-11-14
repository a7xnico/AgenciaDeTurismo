package Agencia.Gestores;

import Agencia.Modelo.Exceptions.DatosInvalidosException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Clase utility con metodos para la validacion de datos
 * comprueba que los datos subidos cumplan criterios logicos.
 * @author Nicolas
 * */

public class Validador {

    /**
     * Valida que no se envie un string nulo o con solo espacios
     * Lanza una excepcion si ese es el caso
     * se utilizara principalmente como campo inicial para las otras validaciones
     * @param valor string a validar
     * @param campo nombre del campo, se utiliza en el mensaje de error
     * @throws DatosInvalidosException cuando el valor este vacio o sea nulo
     */
    public static void noVacio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DatosInvalidosException("El " + campo + " no puede estar vacío");
        }
    }

    /**
     * Valida que el string pasado solo contenga letras
     * con el regex usado permite mayusculas, minusculas, acentos y la letra ñ
     * @param valor sera utilizado para nombres de clientes y de ciudades
     * @param campo utilizado para mostrar en el mensaje de error
     * @throws DatosInvalidosException si el valor contiene números u otros caracteres especiales
     */

    public static void soloLetras(String valor, String campo) {
        noVacio(valor, campo);
        if (!valor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new DatosInvalidosException("El " + campo + " solo puede contener letras");
        }
    }

    /**
     * Busca que el string solo contenga numeros
     * este regex verifica que los valores solo sean decimales positivos
     * @param valor dni del cliente
     * @param campo "dni" para el mensaje de error
     * @throws DatosInvalidosException si el valor tiene cualquier otro caracter
     */

    public static void soloNumeros(String valor, String campo) {
        noVacio(valor, campo);
        if (!valor.matches("\\d+")) {
            throw new DatosInvalidosException("El " + campo + " solo puede contener números");
        }
    }

    public static void email(String valor) {
        noVacio(valor, "email");
        if (!valor.contains("@")) {
            throw new DatosInvalidosException("Formato incorrecto de email");
        }
    }

    /**
     * Valida que la fecha pasada sea real y en formato dd/MM/yyyy
     * 25/12/2025 es valido, pero 31/02/2025 no ya que toma en cuenta que febrero no tiene 31 dias
     *
     * @param valor sera la fecha, esta debe de estar ya en el formato dd/MM/yyyy
     * @param campo "fecha" como el mensaje de error
     * @throws DatosInvalidosException si el formato es incorrecto o si la fecha no existe
     */

    public static void fecha(String valor, String campo) {
        noVacio(valor, campo);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                    .withResolverStyle(ResolverStyle.STRICT);
        try{
            LocalDate.parse(valor, formato);
        } catch (Exception e) {
            throw new DatosInvalidosException("El " + campo + " debe tener formato dd/MM/yyyy y ser una fecha real");
        }
    }


}
