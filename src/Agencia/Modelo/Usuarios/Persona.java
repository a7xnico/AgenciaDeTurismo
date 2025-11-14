package Agencia.Modelo.Usuarios;

import Agencia.Gestores.Validador;

import java.util.Objects;

/**
 * Clase padre que representa una persona en el sistema.
 * Almacena información personal básica: nombre, apellido, DNI y email.
 * Valida automáticamente todos los datos ingresados. */

public class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private String mail;
    private boolean usuarioActivo;

    /**
     * Crea una nueva persona con los datos especificados.
     * Valida que todos los datos sean correctos antes de crear la persona.
     * @param nombre el nombre (solo letras)
     * @param apellido el apellido (solo letras)
     * @param dni el DNI (solo números)
     * @param mail el correo electrónico (debe contener @)
     * @throws  si algún dato no cumple con los requisitos */
    public Persona(String nombre, String apellido, String dni, String mail) {
        validarDatos(nombre, apellido, dni, mail);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        this.usuarioActivo = true;
    }

    public Persona() {
        this.usuarioActivo = true;
    }

    public String getNombre() {return nombre;}

    protected void setNombre(String nombre) {
        Validador.soloLetras(nombre, "nombre");
        this.nombre = nombre;}

    public String getApellido() {return apellido;}

    protected void setApellido(String apellido) {
        Validador.soloLetras(apellido, "apellido");
        this.apellido = apellido;}


    public String getDni() {return dni;}

    protected void setDni(String dni){
        Validador.soloNumeros(dni, "DNI");
        this.dni = dni;}


    public String getMail() {return mail;}

    public void setMail(String mail) {
        Validador.email(mail);
        this.mail = mail;}

    public boolean isUsuarioActivo() {return usuarioActivo;}

    public void setUsuarioActivo(boolean usuarioActivo) {this.usuarioActivo = usuarioActivo;}

    public void validarDatos(String nombre, String apellido, String dni, String mail){
        Validador.soloNumeros(dni, "DNI");
        Validador.soloLetras(nombre, "nombre");
        Validador.soloLetras(apellido, "apellido");
        Validador.email(mail);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public String getNombreCompleto(){
        return nombre + " " + apellido;}
}
