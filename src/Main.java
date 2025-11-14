
import Agencia.Negocio.AgenciaTurismo;
import Agencia.Negocio.MenuPrincipal;

import java.util.Scanner;

public class Main{


    public static void main(String[] args) {

        AgenciaTurismo agencia = new AgenciaTurismo();
        Scanner sc = new Scanner(System.in);
        MenuPrincipal menu = new MenuPrincipal(agencia, sc);
        menu.mostrarMenuPrincipal();

        sc.close();

    }
}
