import Agencia.Negocio.AgenciaTurismo;
import Agencia.Modelo.Usuarios.*;
import Agencia.Modelo.Servicios.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        AgenciaTurismo agencia = new AgenciaTurismo();
        Scanner sc = new Scanner(System.in);

        int opcion = -1;

        while (opcion != 4) {
            System.out.println("===== AGENCIA DE TURISMO =====");
            System.out.println("1. Ingresar como Cliente");
            System.out.println("2. Ingresar como Administrador");
            System.out.println("3. Crear Cuenta");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    loginCliente(agencia, sc);
                    break;

                case 2:
                    loginAdmin(agencia, sc);
                    break;

                case 3:
                    crearCuenta(agencia, sc);
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void loginCliente(AgenciaTurismo agencia, Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        if (!agencia.autenticarCliente(nombre, dni)) {
            System.out.println("❌ Credenciales incorrectas.");
            return;
        }

        Cliente cliente = agencia.getGestorClientes().consultar(dni);
        menuCliente(agencia, cliente, sc);
    }

    private static void loginAdmin(AgenciaTurismo agencia, Scanner sc) {
        System.out.print("Usuario Admin: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        if (!agencia.autenticarAdmin(user, pass)) {
            System.out.println("❌ Credenciales incorrectas.");
            return;
        }

        menuAdministrador(agencia, sc);
    }

    private static void crearCuenta(AgenciaTurismo agencia, Scanner sc) {
        System.out.println("===== CREAR NUEVA CUENTA =====");

        System.out.print("Nombre: ");
        String n = sc.nextLine();
        System.out.print("Apellido: ");
        String a = sc.nextLine();
        System.out.print("DNI: ");
        String d = sc.nextLine();
        System.out.print("Mail: ");
        String m = sc.nextLine();

        System.out.println("Tipo de cliente:");
        System.out.println("1. Regular");
        System.out.println("2. VIP");
        int tc = Integer.parseInt(sc.nextLine());

        Cliente nuevo = (tc == 2)
                ? new ClienteVIP(n, a, d, m)
                : new ClienteRegular(n, a, d, m);

        agencia.getGestorClientes().alta(nuevo);
        System.out.println("✔ Cliente creado exitosamente.");
    }

    private static void menuCliente(AgenciaTurismo agencia, Cliente cliente, Scanner sc) {
        int op = -1;
        while (op != 3) {
            System.out.println("===== MENU CLIENTE =====");
            System.out.println("Hola " + cliente.getNombre());
            System.out.println("1. Crear Reserva");
            System.out.println("2. Ver Mis Reservas");
            System.out.println("3. Volver");
            System.out.print("Elija una opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    crearReserva(agencia, cliente, sc);
                    break;

                case 2:
                    System.out.println("=== TUS RESERVAS ===");
                    for (Reserva r : agencia.listadoReservas()) {
                        if (r.getCliente().getDni().equals(cliente.getDni())) {
                            System.out.println(r);
                            System.out.println("--------------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuAdministrador(AgenciaTurismo agencia, Scanner sc) {
        int op = -1;
        while (op != 6) {
            System.out.println("===== MENU ADMINISTRADOR =====");
            System.out.println("1. Agregar Hotel");
            System.out.println("2. Dar de baja Hotel");
            System.out.println("3. Agregar Vuelo");
            System.out.println("4. Dar de baja Vuelo");
            System.out.println("5. Dar de baja Cliente");
            System.out.println("6. Volver");
            System.out.print("Elija una opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1:
                    agregarHotel(agencia, sc);
                    break;

                case 2:
                    bajaHotel(agencia, sc);
                    break;

                case 3:
                    agregarVuelo(agencia, sc);
                    break;

                case 4:
                    bajaVuelo(agencia, sc);
                    break;

                case 5:
                    System.out.print("DNI del cliente a dar de baja: ");
                    String dni = sc.nextLine();
                    agencia.getGestorClientes().baja(dni);
                    System.out.println("✔ Cliente dado de baja.");
                    break;

                case 6:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void crearReserva(AgenciaTurismo agencia, Cliente cliente, Scanner sc) {

        // ==============================
        // HOTELES
        // ==============================

        List<Hotel> hoteles = agencia.getGestorHoteles().listado();
        if (hoteles.isEmpty()) {
            System.out.println("No hay hoteles disponibles.");
            return;
        }

        System.out.println("=== HOTELES DISPONIBLES ===");
        for (int i = 0; i < hoteles.size(); i++) {
            System.out.println((i + 1) + ") " + hoteles.get(i));
            System.out.println("--------------------------------");
        }

        int idxHotel;
        while (true) {
            System.out.print("Seleccione hotel (número): ");
            idxHotel = Integer.parseInt(sc.nextLine());

            if (idxHotel >= 1 && idxHotel <= hoteles.size()) break;

            System.out.println("❌ Opción inválida. Ingrese un número entre 1 y " + hoteles.size());
        }

        Hotel hotel = hoteles.get(idxHotel - 1);

        // ==============================
        // VUELOS
        // ==============================

        List<Vuelo> vuelos = agencia.getGestorVuelos().listado();
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos disponibles.");
            return;
        }

        System.out.println("=== VUELOS DISPONIBLES ===");
        for (int i = 0; i < vuelos.size(); i++) {
            System.out.println((i + 1) + ") " + vuelos.get(i));
            System.out.println("--------------------------------");
        }

        int idxVuelo;
        while (true) {
            System.out.print("Seleccione vuelo (número): ");
            idxVuelo = Integer.parseInt(sc.nextLine());

            if (idxVuelo >= 1 && idxVuelo <= vuelos.size()) break;

            System.out.println("❌ Opción inválida. Ingrese un número entre 1 y " + vuelos.size());
        }

        Vuelo vuelo = vuelos.get(idxVuelo - 1);

        // ==============================
        // NOCHES
        // ==============================

        System.out.print("Cantidad de noches: ");
        int noches = Integer.parseInt(sc.nextLine());

        Reserva r = new Reserva(cliente, hotel, vuelo, noches);
        agencia.getGestorReservas().alta(r);

        System.out.println("✔ Reserva creada exitosamente.");
    }

    private static void agregarHotel(AgenciaTurismo agencia, Scanner sc) {
        System.out.print("Nombre del hotel: ");
        String nombre = sc.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine();

        System.out.print("Estrellas: ");
        int est = Integer.parseInt(sc.nextLine());

        System.out.print("Precio por noche: ");
        double precio = Double.parseDouble(sc.nextLine());

        System.out.print("Habitaciones disponibles: ");
        int hab = Integer.parseInt(sc.nextLine());

        Hotel h = new Hotel(nombre, ciudad, est, precio, hab);
        agencia.getGestorHoteles().alta(h);

        System.out.println("✔ Hotel agregado correctamente.");
    }

    private static void bajaHotel(AgenciaTurismo agencia, Scanner sc) {

        List<Hotel> hoteles = agencia.getGestorHoteles().listado();

        if (hoteles.isEmpty()) {
            System.out.println("No hay hoteles para dar de baja.");
            return;
        }

        System.out.println("\n=== HOTELES ===");
        for (int i = 0; i < hoteles.size(); i++) {
            System.out.println((i + 1) + ") " + hoteles.get(i).getNombre());
        }

        int idxHotel;
        while (true) {
            System.out.print("Seleccione hotel (número): ");
            idxHotel = Integer.parseInt(sc.nextLine());

            if (idxHotel >= 1 && idxHotel <= hoteles.size()) break;

            System.out.println("❌ Opción inválida.");
        }

        agencia.getGestorHoteles().baja(hoteles.get(idxHotel - 1).getIdHotel());
        System.out.println("✔ Hotel dado de baja.");
    }

    private static void agregarVuelo(AgenciaTurismo agencia, Scanner sc) {

        System.out.print("Número de vuelo: ");
        String numero = sc.nextLine();

        System.out.print("Ciudad origen: ");
        String origen = sc.nextLine();

        System.out.print("Ciudad destino: ");
        String destino = sc.nextLine();

        System.out.print("Cantidad de pasajeros: ");
        int cant = Integer.parseInt(sc.nextLine());

        System.out.print("Precio: ");
        double precio = Double.parseDouble(sc.nextLine());

        System.out.print("Fecha: ");
        String fecha = sc.nextLine();

        Vuelo v = new Vuelo(numero, origen, destino, cant, precio, fecha);
        agencia.getGestorVuelos().alta(v);

        System.out.println("✔ Vuelo agregado correctamente.");
    }

    private static void bajaVuelo(AgenciaTurismo agencia, Scanner sc) {

        List<Vuelo> vuelos = agencia.getGestorVuelos().listado();

        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos para dar de baja.");
            return;
        }

        System.out.println("\n=== VUELOS ===");
        for (int i = 0; i < vuelos.size(); i++) {
            System.out.println((i + 1) + ") " + vuelos.get(i).getNumeroDeVuelo());
        }

        int idxVuelo;
        while (true) {
            System.out.print("Seleccione el vuelo (número): ");
            idxVuelo = Integer.parseInt(sc.nextLine());

            if (idxVuelo >= 1 && idxVuelo <= vuelos.size()) break;

            System.out.println("❌ Opción inválida.");
        }

        agencia.getGestorVuelos().baja(vuelos.get(idxVuelo - 1).getNumeroDeVuelo());
        System.out.println("✔ Vuelo dado de baja.");
    }
}
