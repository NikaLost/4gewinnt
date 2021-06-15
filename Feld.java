
/**
 * Ein Feld für ein 4-gewinnt Spielbrett ist ein einfacher Platzhalter für einen Spielstein.
 * Ein Feld ist entweder frei oder durch einen Spielstein besetzt.
 * 
 * @author Dominik F, Kai M, Kai S
 * @version 15.06.2021
 */
public class Feld
{
    private Spielstein stein;

    /**
     * Konstruktor für Objekte der Klasse Feld
     */
    public Feld()
    {
    }
    
    public void platziere(Spielstein stein) {
        this.stein = stein;
    }

    /**
     * Gibt an, ob dieses feld bereits durch einen Spielstein besetzt ist.
     * 
     * @return        true, falls in diesem Feld kein Spielstein liegt, andernfalls false
     */
    public boolean istFrei()
    {
        return stein == null;
    }
}
