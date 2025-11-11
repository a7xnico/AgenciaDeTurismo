package Agencia.Gestores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Validador {
    public static void noVacio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El " + campo + " no puede estar vacío");
        }
    }

    public static void soloLetras(String valor, String campo) {
        noVacio(valor, campo);
        if (!valor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new IllegalArgumentException("El " + campo + " solo puede contener letras");
        }
    }

    public static void soloNumeros(String valor, String campo) {
        noVacio(valor, campo);
        if (!valor.matches("\\d+")) {
            throw new IllegalArgumentException("El " + campo + " solo puede contener números");
        }
    }

    public static void email(String valor) {
        noVacio(valor, "email");
        if (!valor.contains("@")) {
            throw new IllegalArgumentException("Formato incorrecto de email");
        }
    }

    public static void fecha(String valor, String campo) {
        noVacio(valor, campo);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try{
            LocalDate.parse(valor, formato);
        } catch (Exception e) {
            throw new IllegalArgumentException("El " + campo + " debe tener formato dd/MM/yyyy y ser una fecha real");
        }
    }
}
