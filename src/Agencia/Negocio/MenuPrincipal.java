package Agencia.Negocio;

import Agencia.Modelo.Exceptions.DatosInvalidosException;
import Agencia.Modelo.Exceptions.EntidadNoEncontradaException;
import Agencia.Modelo.Servicios.Hotel;
import Agencia.Modelo.Servicios.Reserva;
import Agencia.Modelo.Servicios.Vuelo;
import Agencia.Modelo.Usuarios.Cliente;
import Agencia.Modelo.Usuarios.ClienteRegular;
import Agencia.Modelo.Usuarios.ClienteVIP;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private AgenciaTurismo agencia;
    private Scanner sc;

    public MenuPrincipal(AgenciaTurismo agencia, Scanner sc) {
        this.agencia = agencia;
        this.sc = sc;
    }

    public void mostrarMenuPrincipal() {
        int opcion = -1;

        while (opcion != 4) {
            System.out.println("\n===== AGENCIA DE TURISMO =====");
            System.out.println("1. Ingresar como Cliente");
            System.out.println("2. Ingresar como Administrador");
            System.out.println("3. Crear Cuenta");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> loginCliente();
                    case 2 -> loginAdmin();
                    case 3 -> crearCuenta();
                    case 4 -> System.out.println("Saliendo...");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresar un numero valido");
                opcion = -1;
            }
        }

    }

    private void loginCliente() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        if (!agencia.autenticarCliente(nombre, dni)) {
            System.out.println("❌ Credenciales incorrectas.");
            return;
        }

        Cliente cliente = agencia.consultarCliente(dni);
        menuCliente(cliente);
    }

    private void loginAdmin() {
        System.out.print("Usuario Admin: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        if (!agencia.autenticarAdmin(user, pass)) {
            System.out.println("❌ Credenciales incorrectas.");
            return;
        }

        menuAdministrador();
    }

    private void crearCuenta() {
        System.out.println("\n===== CREAR NUEVA CUENTA =====");

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
        System.out.print("Seleccione: ");

        try {
            int tc = Integer.parseInt(sc.nextLine());

            Cliente nuevo = (tc == 2)
                    ? new ClienteVIP(n, a, d, m)
                    : new ClienteRegular(n, a, d, m);

            agencia.altaCliente(nuevo);
            System.out.println("✔ Cliente creado exitosamente.");
        } catch (DatosInvalidosException | EntidadNoEncontradaException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ Ingrese un número válido.");
        }
    }

    private void menuCliente(Cliente cliente) {
        int op = -1;
        while (op != 4) {
            System.out.println("\n===== MENU CLIENTE =====");
            System.out.println("Hola " + cliente.getNombre());
            System.out.println("1. Crear Reserva");
            System.out.println("2. Ver Mis Reservas");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Volver");
            System.out.print("Elija una opción: ");

            try {
                op = Integer.parseInt(sc.nextLine());

                switch (op) {
                    case 1 -> crearReserva(cliente);
                    case 2 -> verMisReservas(cliente);
                    case 3 -> cancelarReserva(cliente);
                    case 4 -> System.out.println("Volviendo...");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese un número válido.");
            }
        }
    }

    private void menuAdministrador() {
        int op = -1;
        while (op != 6) {
            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("1. Agregar Hotel");
            System.out.println("2. Dar de baja Hotel");
            System.out.println("3. Agregar Vuelo");
            System.out.println("4. Dar de baja Vuelo");
            System.out.println("5. Dar de baja Cliente");
            System.out.println("6. Volver");
            System.out.print("Elija una opción: ");

            try {
                op = Integer.parseInt(sc.nextLine());

                switch (op) {
                    case 1 -> agregarHotel();
                    case 2 -> bajaHotel();
                    case 3 -> agregarVuelo();
                    case 4 -> bajaVuelo();
                    case 5 -> bajaCliente();
                    case 6 -> System.out.println("Volviendo...");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese un número válido.");
            } catch (DatosInvalidosException | EntidadNoEncontradaException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private void verMisReservas(Cliente cliente) {
        System.out.println("\n=== TUS RESERVAS ===");
        List<Reserva> misReservas = agencia.listadoReservas().stream()
                .filter(r -> r.getCliente().getDni().equals(cliente.getDni()))
                .toList();

        if (misReservas.isEmpty()) {
            System.out.println("No tienes reservas.");
        } else {
            for (Reserva r : misReservas) {
                System.out.println(r);
                System.out.println("--------------------------------");
            }
        }
    }

    private void crearReserva(Cliente cliente) {

        try {
            // ==============================
            // HOTELES
            // ==============================

            List<Hotel> hoteles = agencia.listadoHoteles();
            if (hoteles.isEmpty()) {
                System.out.println("❌ No hay hoteles disponibles.");
                return;
            }

            System.out.println("\n=== HOTELES DISPONIBLES ===");
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

            List<Vuelo> vuelos = agencia.listadoVuelos();
            if (vuelos.isEmpty()) {
                System.out.println("❌ No hay vuelos disponibles.");
                return;
            }

            System.out.println("\n=== VUELOS DISPONIBLES ===");
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
            agencia.altaReserva(r);

            System.out.println("✔ Reserva creada exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Ingrese un número válido.");
        } catch (DatosInvalidosException | EntidadNoEncontradaException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void cancelarReserva(Cliente cliente){
        List<Reserva> misReservas = agencia.listadoReservas().stream()
                .filter(r -> r.getCliente().getDni().equals(cliente.getDni()))
                .toList();

        if (misReservas.isEmpty()) {
            System.out.println("❌ No tienes reservas para cancelar.");
            return;
        }

        System.out.println("\n=== TUS RESERVAS ===");
        for (int i = 0; i < misReservas.size(); i++) {
            System.out.println((i + 1) + ") Reserva #" + misReservas.get(i).getIdReserva() +
                    " - " + misReservas.get(i).getHotel().getNombre() +
                    " (" + misReservas.get(i).getEstado() + ")");
        }

        try {
            int idxReserva;
            while (true) {
                System.out.print("Seleccione reserva a cancelar (número): ");
                idxReserva = Integer.parseInt(sc.nextLine());

                if (idxReserva >= 1 && idxReserva <= misReservas.size()) break;

                System.out.println("❌ Opción inválida. Ingrese un número entre 1 y " + misReservas.size());
            }

            String idReserva = misReservas.get(idxReserva - 1).getIdReserva();
            agencia.bajaReserva(idReserva);
            System.out.println("✔ Reserva cancelada exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Ingrese un número válido.");
        } catch (EntidadNoEncontradaException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }


    private void agregarHotel() {
        try {
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
            agencia.altaHotel(h);

            System.out.println("✔ Hotel agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Ingrese datos válidos.");
        } catch (DatosInvalidosException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }


    private void bajaHotel() {

        List<Hotel> hoteles = agencia.listadoHoteles();

        if (hoteles.isEmpty()) {
            System.out.println("❌ No hay hoteles para dar de baja.");
            return;
        }

        System.out.println("\n=== HOTELES ===");
        for (int i = 0; i < hoteles.size(); i++) {
            System.out.println((i + 1) + ") " + hoteles.get(i).getNombre());
        }

        try {
            int idxHotel;
            while (true) {
                System.out.print("Seleccione hotel (número): ");
                idxHotel = Integer.parseInt(sc.nextLine());

                if (idxHotel >= 1 && idxHotel <= hoteles.size()) break;

                System.out.println("❌ Opción inválida.");
            }

            agencia.bajaHotel(hoteles.get(idxHotel - 1).getIdHotel());
            System.out.println("✔ Hotel dado de baja.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Ingrese un número válido.");
        } catch (EntidadNoEncontradaException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    private void agregarVuelo() {

        try {
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

            System.out.print("Fecha (dd/MM/yyyy): ");
            String fecha = sc.nextLine();

            Vuelo v = new Vuelo(numero, origen, destino, cant, precio, fecha);
            agencia.altaVuelo(v);

            System.out.println("✔ Vuelo agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Ingrese datos válidos.");
        } catch (DatosInvalidosException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void bajaVuelo() {

        List<Vuelo> vuelos = agencia.listadoVuelos();

        if (vuelos.isEmpty()) {
            System.out.println("❌ No hay vuelos para dar de baja.");
            return;
        }

        System.out.println("\n=== VUELOS ===");
        for (int i = 0; i < vuelos.size(); i++) {
            System.out.println((i + 1) + ") " + vuelos.get(i).getNumeroDeVuelo());
        }

        try {
            int idxVuelo;
            while (true) {
                System.out.print("Seleccione el vuelo (número): ");
                idxVuelo = Integer.parseInt(sc.nextLine());

                if (idxVuelo >= 1 && idxVuelo <= vuelos.size()) break;

                System.out.println("❌ Opción inválida.");
            }

            agencia.bajaVuelo(vuelos.get(idxVuelo - 1).getNumeroDeVuelo());
            System.out.println("✔ Vuelo dado de baja.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Ingrese un número válido.");
        } catch (EntidadNoEncontradaException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void bajaCliente() {
        System.out.print("DNI del cliente a dar de baja: ");
        String dni = sc.nextLine();

        try {
            agencia.bajaCliente(dni);
            System.out.println("✔ Cliente dado de baja.");
        } catch (EntidadNoEncontradaException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

}
