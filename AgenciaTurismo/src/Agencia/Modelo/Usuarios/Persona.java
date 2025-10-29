package Agencia.Modelo.Usuarios;

public abstract class Persona {
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

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public String getMail() {return mail;}

    public void setMail(String mail) {this.mail = mail;}

    public boolean isUsuarioActivo() {return usuarioActivo;}

    public void setUsuarioActivo(boolean usuarioActivo) {this.usuarioActivo = usuarioActivo;}

    @Override
    public String toString() {
        return "mail='" + mail + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'';
    }
}
