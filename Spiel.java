import java.util.LinkedList;
/**
 * In dieser Klasse wird der Zustand des 4-gewinnt Spiels verwaltet.
 * Zu einem 4-gewint Spiel gehört ein Spielbrett mit Spielsteinen und 2 Spieler.
 * 
 * @author Dominik F, Kai M, Kai S
 * @version 16.06.2021
 */
public class Spiel
{
    public static final int ANZ_SPIELER = 2;
    public static final int STANDARD_ANZ_SPIELSTEINE = 21;
    public static final int STANDARD_ANZ_GEWINN = 4;
    private Spieler[] spieler;
    private Spielbrett spielbrett;
    private int amZug;
    private LinkedList<Farbe> farbenAuswahl;
    private Spieler gewinner;
    
    
    Spiel() {
        this.spieler = new Spieler[ANZ_SPIELER];
        this.spielbrett = new Spielbrett();
        this.farbenAuswahl = new LinkedList();
        amZug = 0;
        for (Farbe farbe : Farbe.values()) {
            farbenAuswahl.add(farbe);
        }
    }
    
    public void addSpieler(String name) {
        for (int i = 0; i < spieler.length; i++) {
            if (spieler[i] == null) {
                spieler[i] = new Spieler(name, farbenAuswahl.remove(), STANDARD_ANZ_SPIELSTEINE);
                break;
            }
        }
    }
    
    public boolean einwerfen (int spalte) {
        if (spieler[amZug].einwerfenIn(spielbrett, spalte)) {
            if (spielbrett.gewinnReihe()) {
                gewinner = spieler[amZug];
            } else {
                naechsterSpieler();
            }
            return true;
        }
        return false;
    }
    
    public Spieler amZug() {
        return spieler[amZug];
    }
    
    public void naechsterSpieler() {
        amZug = (amZug + 1) % ANZ_SPIELER;
    }
    
    public Spielbrett gibSpielbrett() {
        return this.spielbrett;
    }
    
    public Spieler gibGewinner() {
        return this.gewinner;
    }
}
