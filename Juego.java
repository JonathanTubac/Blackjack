/*Programa hecho por Jonathan Tubac carnet: 24484, seccion: 10
 * este programa es una simulacion del famoso juego Blackjack, utilizando POO.
 */
import java.util.Random;

public class Juego {
    private Jugador jugador;
    private Dealer dealer;
    private Random random;
    private int juegosGanados;
    private int juegosPerdidos;
    private int juegosEmpatados;

    //declaracion del public
    public Juego(String nombreJugador) {
        this.jugador = new Jugador(nombreJugador);
        this.dealer = new Dealer();
        this.random = new Random();
        this.juegosGanados = 0;
        this.juegosPerdidos = 0;
        this.juegosEmpatados = 0;
    }

    public void repartirCartasIniciales() {
        //limpiamos los valores de las manos para cada vez que inicia la partida
        jugador.getMano().clear();
        dealer.getManoD().clear();

        //en este ciclo de repeticion se repartiran 2 cartas a cada uno
        for (int i = 0; i < 2; i++) {
            jugador.agregarCarta(obtenerCartaAleatoria());
            dealer.agregarCarta(obtenerCartaAleatoria());
        }
    }


    public void pedirCartaJugador() {
        //agregar la carta obtenida a la mano del jugador
        jugador.agregarCarta(obtenerCartaAleatoria());
    }

    public void jugarTurnoDealer() {

        //segun las reglas de Blackjack, el dealer toma cartas si su mano es menor a 16
        while (dealer.valorManoD() < 16) {
            dealer.agregarCarta(obtenerCartaAleatoria());
        }
    }

    public String detenerse() {

        //se obtienen los datos del valor total de cada mano, para realizar las condiciones
        int valorJugador = jugador.valorMano();
        int valorDealer = dealer.valorManoD();

        /* condiciones para la victorioa, perdida o empate entre el dealer y el jugador, ademas se van agregando
         * los conteos para poder generar las estadisticas en el menu
        */
        if (valorJugador > 21) {
            juegosPerdidos++;
            return jugador.getNombre() + " pierde por excederse de 21. Dealer gana.";
        } else if (valorDealer > 21) {
            juegosGanados++;
            return jugador.getNombre() + " gana. Dealer pierde por excederse de 21.";
        } else if (valorJugador > valorDealer) {
            juegosGanados++;
            return jugador.getNombre() + " gana. Su mano fue mayor a la del dealer";
        } else if (valorJugador < valorDealer) {
            juegosPerdidos++;
            return "Dealer gana. Su mano fue mayor a la de " + jugador.getNombre();
        } else {
            juegosEmpatados++;
            return "Empate. Mismas manos en ambos";
        }
    }

    public int obtenerCartaAleatoria() {
        //Se obtiene un valor aleatorio para la carta que va del 1 al 9
        return random.nextInt(9) + 1;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void mostrarEstadisticas() {

        //mostrar las estadisticas del jugador
        System.out.println("\nEstadísticas del jugador:");
        System.out.println("Juegos ganados: " + juegosGanados);
        System.out.println("Juegos perdidos: " + juegosPerdidos);
        System.out.println("Juegos empatados: " + juegosEmpatados + "\n");
    }
}
