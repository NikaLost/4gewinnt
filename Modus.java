
/**
 * Diese Aufzählung enthält die unterschiedlichen verfügbaren Modi für das Spiel 4-gewinnt.
 * 
 * @author  Dominik F, Kai M, Kai S
 * @version 18.06.2021
 */
public enum Modus
{
    KLASSISCH("klassisch", "Das ist die klassiche Variante von 4 gewinnt."),
    ZWEI_STEINE("Nimm 2!", "Bei dieser Variante von 4 gewinnt darf jeder Spieler immer 2 Spielsteine hintereinander setzen."),
    DREI_SPIELER("Trio", "Bei dieser Variante von 4 gewinnt spielen 3 Spieler anstatt 2 Spieler auf dem gleichen Spielfeld gegeneinander");
    
    String modus;
    String info;
    
    Modus (String modus, String info) {
        this.modus = modus;
        this.info = info;
    }
    
    @Override
    public String toString() {
        return modus;
    }
    
    public String getInfoText() {
        return this.info;
    }
}
