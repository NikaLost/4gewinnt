
/**
 * Die Aufzählung Farbe enthält mögliche Farben die u.a. für die Spielsteine des 4-gewinnt Spiels genutzt werden
 * können.
 * 
 * @author  Dominik F, Kai M, Kai S
 * @version 15.06.2021
 */
public enum Farbe
{
    GELB("yellow"), ROT("red");
    
    String farbe;
    
    Farbe (String farbe) {
        this.farbe =farbe;
    }
    
    @Override
    public String toString() {
        return farbe;
    }
}
