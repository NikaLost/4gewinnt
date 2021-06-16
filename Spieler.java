import java.util.Stack;

/**
 * Ein Spieler des 4-gewinnt Spiels hat einen Namen und eine Menge an gleichfarbigen Spielsteinen.
 * 
 * @author Dominik F, Kai M, Kai S
 * @version 15.06.2021
 */
public class Spieler
{
    private String name;
    private Stack<Spielstein> inventar;
    private Farbe farbe;

    /**
     * Konstruktor für Objekte der Klasse Spieler
     */
    public Spieler(String name, Farbe farbe, int anzahlSpielsteine)
    {
        this.name = name;
        this.farbe = farbe;
        this.inventar = new Stack();
        initSpielsteine(anzahlSpielsteine);
    }
    
    /**
     * Wirft einen Spielstein in das entsprechende Spielbrett an der gegebenen Spalte ein. Der Spielstein wird
     * dann aus dem Inventar entfernt. Falls das Inventar vorher leer war, kann kein Spielstein eingeworfen werden.
     */
    public boolean einwerfenIn(Spielbrett brett, int spalte) {
        if (inventar.size() > 0) {
            return brett.platzieren(inventar.pop(), spalte);
        }
        return false;
    }
    
    public String gibName() {
        return this.name;
    }
    
    public Farbe gibFarbe() {
        return this.farbe;
    }
    
    public int gibAnzSpielsteine() {
        return this.inventar.size();
    }

    
    private void initSpielsteine(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            inventar.push(new Spielstein(farbe));
        }
    }
}
