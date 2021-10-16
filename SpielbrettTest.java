

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse SpielbrettTest.
 *
 * Enthält Tests für die Facklasse "Spielbrett"
 */
public class SpielbrettTest
{
    Spielbrett brett = new Spielbrett();
    Spielstein steinR = new Spielstein(Farbe.ROT);
    Spielstein steinG = new Spielstein(Farbe.GELB);
    
    /**
     * Konstruktor fuer die Test-Klasse SpielbrettTest
     */
    public SpielbrettTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
        brett = new Spielbrett();
        steinR = new Spielstein(Farbe.ROT);
        steinG = new Spielstein(Farbe.GELB);
    }
    
    /**
     * Testet Gewinnreihe horizontal links.
     */
    @Test
    public void testeHorizontaleGewinn() {
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 1);
        brett.platzieren(steinR, 2);
        brett.platzieren(steinR, 3);
        assertTrue(brett.gewinnReihe());
    }
    
    /**
     * Testet Gewinnreihe Horizintal mittig.
     */
    @Test
    public void testeHorizontaleGewinn2() {
        brett.platzieren(steinR, 2);
        brett.platzieren(steinR, 3);
        brett.platzieren(steinR, 4);
        brett.platzieren(steinR, 5);
        assertTrue(brett.gewinnReihe());
    }
    
    /**
     * Testet Gwinnreihe horizontal rechts.
     */
    @Test
    public void testeHorizontaleGewinn3() {
        brett.platzieren(steinR, 2);
        brett.platzieren(steinR, 2);
        brett.platzieren(steinG, 3);
        brett.platzieren(steinR, 3);
        brett.platzieren(steinG, 4);
        brett.platzieren(steinR, 4);
        brett.platzieren(steinG, 5);
        assertFalse(brett.gewinnReihe());
        brett.platzieren(steinR, 5);
        assertTrue(brett.gewinnReihe());
    }
    
    /**
     * Teste Gewinnreihe vertikal links.
     */
    @Test
    public void testeVertikalGewinn1() {
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        assertTrue(brett.gewinnReihe());
    }
    
    /**
     * Teste Gewinnreihe vertikal links mit Unterbrechung.
     */
    @Test
    public void testeVertikalGewinn2() {
        brett.platzieren(steinR, 0);
        brett.platzieren(steinG, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        assertTrue(brett.gewinnReihe());
    }
    
    /**
     * Teste Gewinnreihe diagonal
     */
    @Test
    public void testeDiagonalGewinn1() {
        brett.platzieren(steinR, 0);
        brett.platzieren(steinG, 1);
        brett.platzieren(steinR, 1);
        brett.platzieren(steinG, 2);
        brett.platzieren(steinR, 2);
        brett.platzieren(steinR, 2);
        brett.platzieren(steinR, 3);
        brett.platzieren(steinR, 3);
        brett.platzieren(steinR, 3);
        assertFalse(brett.gewinnReihe());
        brett.platzieren(steinR, 3);
        assertTrue(brett.gewinnReihe());
    }
    
    /**
     * Testet Gewinnreihe diagonal mit Lücke.
     */
    @Test
    public void testDiagonaleGewinnLücke() {
        brett.platzieren(steinG, 0);
        brett.platzieren(steinG, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinR, 0);
        brett.platzieren(steinG, 0);
        brett.platzieren(steinR, 1);
        brett.platzieren(steinR, 1);
        brett.platzieren(steinG, 1);
        brett.platzieren(steinR, 1);
        brett.platzieren(steinG, 1);
        brett.platzieren(steinG, 1);
        brett.platzieren(steinR, 2);
        brett.platzieren(steinR, 2);
        brett.platzieren(steinG, 2);
        brett.platzieren(steinR, 2);
        brett.platzieren(steinG, 2);
        brett.platzieren(steinG, 3);
        brett.platzieren(steinG, 3);
        brett.platzieren(steinR, 3);
        brett.platzieren(steinR, 4);
        brett.platzieren(steinR, 4);
        brett.platzieren(steinG, 4);
        brett.platzieren(steinG, 5);
        assertFalse(brett.gewinnReihe());
        brett.platzieren(steinG, 3);
        assertTrue(brett.gewinnReihe());
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }
}
