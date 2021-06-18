
/**
 * Diese Aufzählung enthält die unterschiedlichen verfügbaren Modi für das Spiel 4-gewinnt.
 * 
 * @author  Dominik F, Kai M, Kai S
 * @version 18.06.2021
 */
public enum Modus
{
    KLASSISCH("klassisch"), ZWEI_STEINE("Nimm 2!");
    
    String modus;
    
    Modus (String modus) {
        this.modus = modus;
    }
    
    @Override
    public String toString() {
        return modus;
    }
}
