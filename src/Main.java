

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/fxml/ventana_principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        primaryStage.setTitle("Agencia de Turismo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

        AgenciaTurismo agenciaTurismo=new AgenciaTurismo();

        Cliente clienteRegular=new ClienteRegular("Baltasar","rodriguez","1","baltasaro@gmsil.com");
        Cliente clienteRegular1=new ClienteRegular("Franco","Alvarez","45","franalva@gmail.com");
        Cliente clienteRegular2=new ClienteRegular("Antonio","Fernandez","24","antofer@gmail.com");
        Cliente clienteRegular3=new ClienteRegular("Juan","Martinez","6","juanmar@gmail.com");
        Cliente clienteRegular4=new ClienteRegular("Lionel","Messi","9","liomessi@gmail.com");
        Cliente clienteRegular5=new ClienteRegular("Rodrigo","De paul","63","rodridepaul@gmail.com");
        Cliente clienteRegular6=new ClienteRegular("Emiliano","Martinez","48","dibumartinez@gmail.com");



        Cliente clienteVIP=new ClienteVIP("Franco","Mastantuono","31","franmastantuono@gmail.com");
        Cliente clienteVIP1=new ClienteVIP("Julian","Alvarez","52","julialvarez@gmail.com");
        Cliente clienteVIP2=new ClienteVIP("Lautaro","Martinez","17","toromartinez@gmail.com");
        Cliente clienteVIP3=new ClienteVIP("Alexis","Mac Allister","13","alemac@gmail.com");
        Cliente clienteVIP4=new ClienteVIP("Enzo","Fernandez","66","enzofer@gmail.com");
        Cliente clienteVIP5=new ClienteVIP("Nicolas","Gonzalez","43","nicogonzalez@gmail.com");
        Cliente clienteVIP6=new ClienteVIP("Nicolas","Paz","72","nicopaz@gmail.com");
        Cliente clienteVIP7=new ClienteVIP("Franco","Armani","8","franarmani@gmail.com");


        Hotel hotel=new Hotel("The Ritz London","Londres",5,800,136);
        Hotel hotel1=new Hotel("Hotel Santa Fe","Ciudad de México",3,120,90);
        Hotel hotel2=new Hotel("Shinjuku Granbell Hotel","Tokio",4,150,505);
        Hotel hotel3=new Hotel("Hotel Iguaçu Falls","Foz do Iguaçu",4,200,100);
        Hotel hotel4=new Hotel("Marriott Dubai Marina"," Dubái",5,300,340);
        Hotel hotel5=new Hotel("Hotel Casa Blanca","Madrid",3,90,50);
        Hotel hotel6=new Hotel("Pullman Paris Montparnasse","París",4,180,813);
        Hotel hotel7=new Hotel("The Vari Hotel","Estambul",4,100,68);
        Hotel hotel8=new Hotel("Grand Hyatt Tokyo","Tokio",5,500,377);
        Hotel hotel9=new Hotel("Hotel Royal Orchid","Bangalore",4,80,200);
        Hotel hotel10=new Hotel("Hotel Bungalows Las Garzas","Santiago De Chile",3,70,30);
        Hotel hotel11=new Hotel("The Westin Dublin","Dublín",4,220,172);
        Hotel hotel12=new Hotel("Hotel Presidente","San José",3,85,69);
        Hotel hotel13=new Hotel("Sofitel Sydney","Sídney",5,350,235);
        Hotel hotel14=new Hotel("Hotel Bolivar","Buenos Aires",4,120,159);

        Vuelo vuelo=new Vuelo("AR102","Buenos Aires","Madrid",4,890,"12-03-2026");
        Vuelo vuelo1=new Vuelo("LA233","Santiago de Chile","Dublin",2,220,"04-05-2026");
        Vuelo vuelo2=new Vuelo("MX540","Ciudad de México","Tokio",3,350,"18-02-2026");
        Vuelo vuelo3=new Vuelo("IB781","Madrid","París",2,140,"02-05-2026");
        Vuelo vuelo4=new Vuelo("AV914","Bogota","Londres",3,480,"21-03-2026");
        Vuelo vuelo5=new Vuelo("UX320","Barcelona","San Jose",4,130,"11-06-2026");
        Vuelo vuelo6=new Vuelo("AM665","Cancun","Sidney",2,410,"29-04-2026");
        Vuelo vuelo7=new Vuelo("AA443","Miami","Buenos Aires",5,760,"17-05-2026");
        Vuelo vuelo8=new Vuelo("FR902","Dublin","Estambul",3,95,"07-03-2026");
        Vuelo vuelo9=new Vuelo("KL221","Amsterdam","Cuidad de Mexico",2,150,"28-02-2026");
        Vuelo vuelo10=new Vuelo("TP508","Lisboa","Santiago De Chile",6,630,"03-06-2026");
        Vuelo vuelo11=new Vuelo("QF730","Sídney","Tokio",4,280,"14-07-2026");
        Vuelo vuelo12=new Vuelo("AF307","Paris","Foz Do Iguacu",3,1150,"22-04-2026");
        Vuelo vuelo13=new Vuelo("EK911","Dubai","Bangalore",2,390,"05-08-2026");
        Vuelo vuelo14=new Vuelo("QR602","Doha","Dubai",4,310,"30-03-2026");

        Reserva reserva=new Reserva(clienteRegular,hotel5,vuelo,5,"01-11-2025");
        Reserva reserva1=new Reserva(clienteRegular1, hotel11, vuelo1,3,"02-11-2025");
        Reserva reserva2=new Reserva(clienteRegular2,hotel2,vuelo2,8,"03-11-2025");
        Reserva reserva3=new Reserva(clienteRegular3,hotel6,vuelo3,4,"04-11-2025");
        Reserva reserva4=new Reserva(clienteRegular4,hotel,vuelo4,6,"05-11-2025");
        Reserva reserva5=new Reserva(clienteRegular5,hotel12,vuelo5,2,"06-11-2025");
        Reserva reserva6=new Reserva(clienteRegular6,hotel13,vuelo6,9,"07-11-2025");
        Reserva reserva7=new Reserva(clienteVIP,hotel14,vuelo7,5,"08-11-2025");
        Reserva reserva8=new Reserva(clienteVIP1,hotel7,vuelo8,7,"09-11-2025");
        Reserva reserva9=new Reserva(clienteVIP2,hotel1,vuelo9,6,"10-11-2025");
        Reserva reserva10=new Reserva(clienteVIP3,hotel10,vuelo10,10,"11-11-2025");
        Reserva reserva11=new Reserva(clienteVIP4,hotel8,vuelo11,5,"12-11-2025");
        Reserva reserva12=new Reserva(clienteVIP5,hotel3,vuelo12,7,"13-11-2025");
        Reserva reserva13=new Reserva(clienteVIP6,hotel9,vuelo13,4,"14-11-2025");
        Reserva reserva14=new Reserva(clienteVIP7,hotel4,vuelo14,12,"15-11-2025");

        agenciaTurismo.altaCliente(clienteRegular);
        agenciaTurismo.altaCliente(clienteRegular1);
        agenciaTurismo.altaCliente(clienteRegular2);
        agenciaTurismo.altaCliente(clienteRegular3);
        agenciaTurismo.altaCliente(clienteRegular4);
        agenciaTurismo.altaCliente(clienteRegular5);
        agenciaTurismo.altaCliente(clienteRegular6);
        agenciaTurismo.altaCliente(clienteVIP);
        agenciaTurismo.altaCliente(clienteVIP1);
        agenciaTurismo.altaCliente(clienteVIP2);
        agenciaTurismo.altaCliente(clienteVIP3);
        agenciaTurismo.altaCliente(clienteVIP4);
        agenciaTurismo.altaCliente(clienteVIP5);
        agenciaTurismo.altaCliente(clienteVIP6);
        agenciaTurismo.altaCliente(clienteVIP7);
        
        
        agenciaTurismo.altaHotel(hotel);
        agenciaTurismo.altaHotel(hotel1);
        agenciaTurismo.altaHotel(hotel2);
        agenciaTurismo.altaHotel(hotel3);
        agenciaTurismo.altaHotel(hotel4);
        agenciaTurismo.altaHotel(hotel5);
        agenciaTurismo.altaHotel(hotel6);
        agenciaTurismo.altaHotel(hotel7);
        agenciaTurismo.altaHotel(hotel8);
        agenciaTurismo.altaHotel(hotel9);
        agenciaTurismo.altaHotel(hotel10);
        agenciaTurismo.altaHotel(hotel11);
        agenciaTurismo.altaHotel(hotel12);
        agenciaTurismo.altaHotel(hotel13);
        agenciaTurismo.altaHotel(hotel14);
        
        
        
        
        agenciaTurismo.altaVuelo(vuelo);
        agenciaTurismo.altaVuelo(vuelo1;
        agenciaTurismo.altaVuelo(vuelo2);
        agenciaTurismo.altaVuelo(vuelo3);
        agenciaTurismo.altaVuelo(vuelo4);
        agenciaTurismo.altaVuelo(vuelo5);
        agenciaTurismo.altaVuelo(vuelo6);
        agenciaTurismo.altaVuelo(vuelo7);
        agenciaTurismo.altaVuelo(vuelo8);
        agenciaTurismo.altaVuelo(vuelo9);
        agenciaTurismo.altaVuelo(vuelo10);
        agenciaTurismo.altaVuelo(vuelo11);
        agenciaTurismo.altaVuelo(vuelo12);
        agenciaTurismo.altaVuelo(vuelo13);
        agenciaTurismo.altaVuelo(vuelo14);
        
        
        
        agenciaTurismo.altaReserva(reserva);
        agenciaTurismo.altaReserva(reserva1);
        agenciaTurismo.altaReserva(reserva2);
        agenciaTurismo.altaReserva(reserva3);
        agenciaTurismo.altaReserva(reserva4);
        agenciaTurismo.altaReserva(reserva5);
        agenciaTurismo.altaReserva(reserva6);
        agenciaTurismo.altaReserva(reserva7);
        agenciaTurismo.altaReserva(reserva8);
        agenciaTurismo.altaReserva(reserva9);
        agenciaTurismo.altaReserva(reserva10);
        agenciaTurismo.altaReserva(reserva11);
        agenciaTurismo.altaReserva(reserva12);
        agenciaTurismo.altaReserva(reserva13);
        agenciaTurismo.altaReserva(reserva14);
        
        
        agenciaTurismo.listadoClientes();
        agenciaTurismo.listadoHoteles();
        agenciaTurismo.listadoHoteles();
        agenciaTurismo.listadoReservas();
        
        
        agenciaTurismo.bajaCliente("6");
        agenciaTurismo.bajaCliente("43");
        
        
        agenciaTurismo.bajaHotel("3");
        agenciaTurismo.bajaHotel("8");
        
        
        agenciaTurismo.bajaReserva("5");
        agenciaTurismo.bajaReserva("11");
        
        
        agenciaTurismo.bajaVuelo("IB781");
        agenciaTurismo.bajaVuelo("EK911");
        
        
        agenciaTurismo.autenticarCliente("Lionel","9");
        agenciaTurismo.consultarCliente("66");
        
        agenciaTurismo.consultarHotel("7");
        
        agenciaTurismo.consultarVuelo("KL221");
        
        agenciaTurismo.consultarReserva("2");
        
    }
}
