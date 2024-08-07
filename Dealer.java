/*Programa hecho por Jonathan Tubac carnet: 24484, seccion: 10
 * este programa es una simulacion del famoso juego Blackjack, utilizando POO.
 */
import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Integer> mano;
    //declaracion del public
    public Dealer() {
        this.mano = new ArrayList<>();
    }

    //metodo para agregar las cartas a la mano del dealer
    public void agregarCarta(int carta) {
        mano.add(carta);
    }

    //metodo para obtener el valor de la suma de la mano del dealer
    public int valorManoD() {
        int valor = 0;
        for (int carta : mano) {
            valor += carta;
        }
        return valor;
    }

    //metodo para verificar que el valor de la mano está entre 21
    public boolean estaDentroDelLimite() {
        return valorManoD() <= 21;
    }

    public List<Integer> getManoD() {
        return mano;
    }
}
