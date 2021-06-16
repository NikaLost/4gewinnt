import javafx.scene.shape.*;
import javafx.scene.paint.*;
/**
 * Diese Klasse soll repräsentiert einen Spielstein in der graphischen Benutzeroberfläche.
 * Dafür erbt die Klasse von der JavaFX Klasse Circle und 
 * 
 * @author Dominik F, Kai M, Kai S
 * @version 16.06.2021
 */
public class SpielSteinView extends Circle
{
    // Konstante, die die größe der Spielsteine festlegt.
    private static final double RADIUS = 15.;

    /**
     * Konstruktor für Objekte der Klasse SpielSteinView
     */
    public SpielSteinView(Farbe farbe)
    {
       super(RADIUS, Paint.valueOf(farbe.toString()));
    }

}
