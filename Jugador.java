/*Programa hecho por Jonathan Tubac carnet: 24484, seccion: 10
 * este programa es una simulacion del famoso juego Blackjack, utilizando POO.
 */

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Integer> mano;
    //se declara el public de la clase Jugador
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }
    
    //metodo para agregar las cartas a la mano del Jugador
    public void agregarCarta(int carta) {
        mano.add(carta);
    }
    
    //metodo para obtener la suma de las cartas de la mano del jugador
    public int valorMano() {
        int valor = 0;
        for (int carta : mano) {
            valor += carta;
        }
        return valor;
    }

    //metodo para verificar que el jugador esta en el limite de 21
    public boolean estaDentroDelLimite() {
        return valorMano() <= 21;
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    public List<Integer> getMano() {
        return mano;
    }
}
