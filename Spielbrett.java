
/**
 * Das klassische Spielbrett eines 4-gewinnt Spiels besteht aus insgesamt 48 Feldern in 7 Spalten und 6 Zeilen.
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

    /**
     * Konstruktor für Objekte der Klasse Spielbrett
     */
    public Spielbrett()
    {
        initSpielbrett();
    }
    
    /**
     * Platziert einen Spielstein im obersten freien Feld in einer Spalte.
     * 
     * @return      false, falls die Spalte voll ist oder die übergebenen Parameter ungültig sind.
     *              true, wenn der Spielstein erfolgreich platziert wurde.
     */
    public boolean einwerfen(Spielstein stein, int spalte) {
        boolean platziert = false;
        if (spalte < 0 || SPALTEN - spalte <= 0 || istVoll(spalte)) {
            return platziert;
        } else {
            int zeile = UNTERSTE_ZEILE;
            while (!platziert && zeile >= UNTERSTE_ZEILE) {
                Feld feld = felder[zeile][spalte];
                if (feld.istFrei()) {
                    feld.platziere(stein);
                    platziert = true;
                } else {
                    zeile += 1;
                }
            }
        }
        return platziert;
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
