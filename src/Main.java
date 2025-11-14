
import Agencia.Negocio.AgenciaTurismo;

public class Main{


    public static void main(String[] args) {

        AgenciaTurismo agencia =new AgenciaTurismo();
        agencia.mostrarResumenGeneral();
        agencia.mostrarListadoClientes();
        agencia.mostrarListadoHoteles();
        agencia.mostrarListadoVuelos();
        agencia.mostrarListadoReservas();

    }
}
