/*Programa hecho por Jonathan Tubac carnet: 24484, seccion: 10
 * este programa es una simulacion del famoso juego Blackjack, utilizando POO.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Juego juego = null;
        boolean salir = false;


        //ciclo de repeticion para el menu
        while (!salir) {
            System.out.println("Bienvenido al Blackjack");
            System.out.println("1. Empezar a jugar");
            System.out.println("2. Reglas del Blackjack");
            System.out.println("3. Ver estadísticas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 


            switch (opcion) {
                case 1:
                //se coloca el nombre del jugador y se crea un objeto para empezar el juego
                    System.out.print("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    juego = new Juego(nombre);
                    jugarBlackjack(juego, scanner);
                    break;
                case 2:
                //imprime las reglas de Blackjack
                    mostrarReglas();
                    break;
                case 3:
                //mostrara las estidisticas, si no se ha jugado ni una partida mostrara un mensaje indicandolo
                    if (juego != null) {
                        juego.mostrarEstadisticas();
                    } else {
                        System.out.println("No se ha jugado ninguna partida todavía.");
                    }
                    break;
                case 4:
                //sale del programa, despidiendose del usuario
                    salir = true;
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    break;
                default:
                //si se ingresa cualquier otra opcion que no se mencione en el menu
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }

    private static void mostrarReglas() {
        //reglas de blackjack
        System.out.println("\nReglas del Blackjack:");
        System.out.println("1. El objetivo es alcanzar una mano cuyo valor total esté lo más cerca posible de 21 sin excederlo.");
        System.out.println("2. Se reparten 2 cartas al jugador y 2 cartas al dealer.");
        System.out.println("3. Durante su turno, el jugador puede pedir una carta adicional o detenerse.");
        System.out.println("4. Si el valor total de las cartas del jugador supera los 21, el jugador pierde automáticamente.");
        System.out.println("5. Si el jugador decide detenerse, el dealer tomará cartas si el valor total de sus cartas es menor a 16.");
        System.out.println("6. Gana la mano que esté más cerca de 21 sin excederlo. Si ambos tienen el mismo valor, es un empate.\n");
    }

    private static void jugarBlackjack(Juego juego, Scanner scanner) {
        boolean jugarDeNuevo = true;

        //se jugaran las partidas hasta que el usuario desee regresar al menu
        while (jugarDeNuevo) {
            //se inicia el juego
            juego.repartirCartasIniciales();
            System.out.println("Mano inicial de " + juego.getJugador().getNombre() + ": " + juego.getJugador().getMano());
            System.out.println("Mano inicial del dealer: " + juego.getDealer().getManoD());

            //menu para que el usuario decida pedri carta o quedarse, si se excede del limite, perdera
            boolean continuar = true;
            while (continuar && juego.getJugador().estaDentroDelLimite()) {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Pedir carta");
                System.out.println("2. Detenerse");
                System.out.print("Opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (opcion) {
                    case 1:
                        juego.pedirCartaJugador();
                        System.out.println("Nueva carta para " + juego.getJugador().getNombre() + ": " + juego.getJugador().getMano());
                        break;
                    case 2:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            }

            //si el jugador se queda y está dentro del limite, el dealer juega su turno
            if (juego.getJugador().estaDentroDelLimite()) {
                juego.jugarTurnoDealer();
                System.out.println("Mano final del dealer: " + juego.getDealer().getManoD());
            }

            //menu por si el usuario desea jugar de nuevo o retirase al menu principal
            String resultado = juego.detenerse();
            System.out.println("\n" + resultado);

            System.out.println("\n¿Desea jugar otra vez?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Opción: ");

            int opcionFinal = scanner.nextInt();
            scanner.nextLine(); 

            if (opcionFinal != 1) {
                jugarDeNuevo = false;
            }
        }
    }
}
