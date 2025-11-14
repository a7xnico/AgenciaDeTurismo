package Agencia.Modelo.Exceptions;

/**
 * Excepcion que controla datos que no son validos segun la logica del programa
 * ejemplo noches reservadas <= 0 o cliente de una reserva nulo
 */
public class DatosInvalidosException extends IllegalArgumentException {
    public DatosInvalidosException(String message) {
        super(message);
    }
}
