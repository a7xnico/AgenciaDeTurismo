package Agencia.Gestores;

import Agencia.Modelo.Usuarios.Admin;

public class Autenticador {
    private Admin admin;

    public Autenticador(){
        this.admin = new Admin("admin", "admin123");
    }

    public boolean validarAdmin(String username, String password){
        return admin.autenticar(username, password);
    }

    public Admin getAdmin(){
        return admin;
    }





}
