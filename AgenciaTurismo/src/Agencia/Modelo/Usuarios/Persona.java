package Agencia.Modelo.Usuarios;

import java.util.Objects;

public class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private String mail;
    private boolean usuarioActivo;

    public Persona(String nombre, String apellido, String dni, String mail) {
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

    protected void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    protected void setApellido(String apellido) {this.apellido = apellido;}

    public String getDni() {return dni;}

    public String getMail() {return mail;}

    public void setMail(String mail) {this.mail = mail;}

    public boolean isUsuarioActivo() {return usuarioActivo;}

    public void setUsuarioActivo(boolean usuarioActivo) {this.usuarioActivo = usuarioActivo;}

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
