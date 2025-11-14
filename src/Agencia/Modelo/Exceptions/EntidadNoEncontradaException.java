package Agencia.Modelo.Exceptions;

/**
 * Excepcion usada para busqueda de entidades que son nulas
 * ejemplo buscar un hotel por un id que no existe en el sistema
 */

public class EntidadNoEncontradaException extends IllegalArgumentException {
    public EntidadNoEncontradaException(String message) {
        super(message);
    }

}
