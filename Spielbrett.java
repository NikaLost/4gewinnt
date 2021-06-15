
/**
 * Das klassische Spielbrett eines 4-gewinnt Spiels besteht aus insgesamt 48 Feldern in 7 Spalten und 6 Zeilen.
 * 
 *      ====================== Spielbrett =======================
 * 
 *                     3. Spalte (2)
 *                          v
 *      |-------------------------------------------------------|
 *      | (5,0) | (5,1) | (5,2) | (5,3) | (5,4) | (5,5) | (5,6) |   <- OBERSTE_ZEILE (5)
 *      |-------------------------------------------------------|
 *      | (4,0) | (4,1) | (4,2) | (4,3) | (4,4) | (4,5) | (4,6) |
 *      |-------------------------------------------------------|
 *      | (3,0) | (3,1) | (3,2) | (3,3) | (3,4) | (3,5) | (3,6) |
 *      |-------------------------------------------------------|
 *      | (2,0) | (2,1) | (2,2) | (2,3) | (2,4) | (2,5) | (2,6) |
 *      |-------------------------------------------------------|
 *      | (1,0) | (1,1) | (1,2) | (1,3) | (1,4) | (1,5) | (1,6) |
 *      |-------------------------------------------------------|
 *      | (0,0) | (0,1) | (0,2) | (0,3) | (0,4) | (0,5) | (0,6) |   <- UNTERSTE_ZEILE (0)
 *      |-------------------------------------------------------|
 * 
 * 
 * 
 * @author Dominik F, Kai M, Kai S
 * @version 15.06.2021
 */
public class Spielbrett
{
    private Feld[][] felder;
    public static final int ZEILEN = 6;
    public static final int SPALTEN = 7;
    public static final int OBERSTE_ZEILE = ZEILEN - 1;
    public static final int UNTERSTE_ZEILE = 0;
    private int freieFelder;

    /**
     * Konstruktor für Objekte der Klasse Spielbrett
     */
    public Spielbrett()
    {
        initSpielbrett();
        freieFelder = ZEILEN * SPALTEN;
    }
    
    /**
     * Platziert einen Spielstein im obersten freien Feld in einer Spalte.
     * 
     * @return      false, falls die Spalte voll ist oder die übergebenen Parameter ungültig sind.
     *              true, wenn der Spielstein erfolgreich platziert wurde.
     */
    public boolean einwerfen(Spielstein stein, int spalte) {
        boolean platziert = false;
        
        //überprüfe, ob Paramter gültig sind, und ob Spalte bereits voll ist
        if (spalte < 0 || SPALTEN - spalte <= 0 || istVoll(spalte)) {
            return platziert;
        } else {
            //gehe die Spalte zeilenweise von unten nach oben durch und platziere auf nächsten freien Feld
            int zeile = UNTERSTE_ZEILE;
            while (!platziert && zeile < OBERSTE_ZEILE) {
                Feld feld = felder[zeile][spalte];
                if (feld.istFrei()) {
                    feld.platziere(stein);
                    platziert = true;
                    freieFelder -= 1;
                } else {
                    zeile += 1;
                }
            }
        }
        return platziert;
    }
    
    /**
     * Gibt an, ob alle Felder des Spielbretts besetzt sind.
     */
    public boolean istVollBesetzt() {
        return freieFelder == 0;
    }

    
    /**
     * Falls ein Spielstein in der obersten Zeile einer Spalte liegt, ist die Spalte voll.
     */
    private boolean istVoll(int spalte) {
        return !felder[OBERSTE_ZEILE][spalte].istFrei();
    }
    
    /**
     * Initialisiert ein leeres Spielbrett.
     */
    private void initSpielbrett() {
        felder = new Feld[ZEILEN][SPALTEN];
        for (int i = 0; i < ZEILEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                felder[i][j] = new Feld();
            }
        }
    }
    
}
