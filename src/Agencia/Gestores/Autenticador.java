package Agencia.Gestores;

import Agencia.Modelo.Usuarios.Admin;

public class Autenticador {
    private Admin admin;

    public Autenticador(){
        this.admin = new Admin("admin", "admin123");
    }

    public boolean autenticarAdmin(String username, String password){
        return admin.autenticar(username, password);
    }

    public void cambiarCredenciales(String nuevoUsername, String nuevoPassword){
        this.admin = new Admin(nuevoUsername, nuevoPassword);
    }

    public Admin getAdmin(){
        return admin;
    }



}
