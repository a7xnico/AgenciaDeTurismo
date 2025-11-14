package Agencia.Gestores;

import Agencia.GestionArchivos.GestorJSONAdmin;
import Agencia.Modelo.Usuarios.Admin;

public class Autenticador {
    private Admin admin;
    private GestorJSONAdmin gestorJson;

    public Autenticador(){
        gestorJson = new GestorJSONAdmin();
        cargarAdmin();
    }

    private void cargarAdmin(){
        admin = gestorJson.deserializarAdmin();
        if (admin == null) {
            admin = new Admin("admin", "admin123");
            guardarAdmin();
        }
    }

    private void guardarAdmin(){
        gestorJson.serializarAdmin(admin);
    }

    public boolean validarAdmin(String username, String password){
        return admin.autenticar(username, password);
    }

    public void cambiarCredenciales(String nuevoUsername, String nuevoPassword){
        admin = new Admin(nuevoUsername, nuevoPassword);
        guardarAdmin();
        System.out.println("Credenciales del admin actualizadas exitosamente");
    }

    public Admin getAdmin(){
        return admin;
    }





}
