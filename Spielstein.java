
/**
 * Ein Spielstein eines 4-gewinnt Spiels hat eine eindeutige Farbe, die einem eindeutigen Spieler zugeordnet
 * werden kann.
 * 
 * @author Dominik F, Kai M, Kai S
 * @version 15.06.2021
 */
public class Spielstein
{
    private Farbe farbe;

    /**
     * Konstruktor für Objekte der Klasse Spielstein
     */
    public Spielstein(Farbe farbe)
    {
        this.farbe = farbe;
    }
    
    public Farbe gibFarbe() {
        return this.farbe;
    }
}
