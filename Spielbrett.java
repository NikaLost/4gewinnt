
/**
 * Das klassische Spielbrett eines 4-gewinnt Spiels besteht aus insgesamt 42 Feldern in 7 Spalten und 6 Zeilen.
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
    private int erstesFreiesFeld[];
    private int freieFelder;
    private boolean gewinnReihe;

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
    public boolean platzieren(Spielstein stein, int spalte) {
        boolean platziert = false;
        
        //überprüfe, ob Paramter gültig sind, und ob Spalte bereits voll ist
        if (spalte < 0 || SPALTEN - spalte < 0 || istVoll(spalte)) {
            this.ausgabe();
            return platziert;
        } else {
            //gehe die Spalte zeilenweise von unten nach oben durch und platziere auf nächsten freien Feld
            int zeile = UNTERSTE_ZEILE;
            while (!platziert && zeile <= OBERSTE_ZEILE) {
                Feld feld = felder[zeile][spalte];
                if (feld.istFrei()) {
                    feld.platziere(stein);
                    gewinnReihe = checkGewinnfolge(zeile, spalte, stein.gibFarbe(), Spiel.STANDARD_ANZ_GEWINN);
                    platziert = true;
                    freieFelder -= 1;
                    erstesFreiesFeld[spalte] = erstesFreiesFeld[spalte] + 1; 
                } else {
                    zeile += 1;
                }
            }
        }
        this.ausgabe();
        return platziert;
    }
    
    /**
     * Gibt an, ob alle Felder des Spielbretts besetzt sind.
     */
    public boolean istVollBesetzt() {
        return freieFelder == 0;
    }
    
    public boolean gewinnReihe() {
        return gewinnReihe;
    }
    
    private boolean checkGewinnfolge(int z, int s, Farbe farbe, int anzahlGewinn) {
        return checkHorizontal(z, s, farbe, anzahlGewinn) 
                || checkVertikal(z, s, farbe, anzahlGewinn) 
                || checkDiagonal(z, s, farbe, anzahlGewinn);
    }
    
    private boolean checkHorizontal(int z, int s, Farbe farbe, int anzahlGewinn) {
        int gesamt = 1;
        //horizontal rechts
        int i = 1;
        while (i <= anzahlGewinn && (s + i) < SPALTEN && !felder[z][s + i].istFrei() 
            && felder[z][s + i].gibStein().gibFarbe().equals(farbe)) {
            gesamt += 1;
            i++;
        }
        //horizontal links
        i = 1;
        while (i <= anzahlGewinn && (s - i) >= 0  && !felder[z][s - i].istFrei() 
            && felder[z][s - i].gibStein().gibFarbe().equals(farbe)) {
            gesamt += 1;
            i++;
        }
        if (gesamt >= anzahlGewinn) {
            return true;
        }
        
        return false;
    }
    
    private boolean checkVertikal(int z, int s, Farbe farbe, int anzahlGewinn) {
        int gesamt = 1;
        //vertikal oben... braucht man eig nicht
        int i = 1;
        while (i <= anzahlGewinn && (z + i) < ZEILEN && !felder[z + i][s].istFrei()
            && felder[z + i][s].gibStein().gibFarbe() == farbe) {
            gesamt += 1;
            i++;
        }
        //vertikal unten
        i = 1;
        while (i <= anzahlGewinn && (z - i) >= 0 && !felder[z - i][s].istFrei()
            && felder[z - i][s].gibStein().gibFarbe() == farbe) {
            gesamt += 1;
            i++;
        }
        if (gesamt == anzahlGewinn) {
            return true;
        }
        
        return false;
    }
    
    private boolean checkDiagonal(int z, int s, Farbe farbe, int anzahlGewinn) {
        int gesamt = 1;
        //diagonal nach rechtes oben
        int i = 1;
        while (i <= anzahlGewinn && (z + i) < ZEILEN && (s + i) < SPALTEN && !felder[z + i][s + i].istFrei()
            && felder[z + i][s + i].gibStein().gibFarbe() == farbe) {
            gesamt += 1;
            i++;
        }
        //diagonal nach links unten
        i = 1;
        while (i <= anzahlGewinn && (z - i) >= 0 && (s - i) >= 0 && !felder[z - i][s - i].istFrei()
            && felder[z - i][s - i].gibStein().gibFarbe() == farbe) {
            gesamt += 1;
            i++;
        }
        if (gesamt == anzahlGewinn) {
            return true;
        }
        
        
        gesamt = 1;
        //diagonal nach links oben
        i = 1;
        while (i <= anzahlGewinn && (z + i) < ZEILEN && (s - i) >= 0 && !felder[z + i][s - i].istFrei()
            && felder[z + i][s - i].gibStein().gibFarbe() == farbe) {
            gesamt += 1;
            i++;
        }
        //diagonal nach rechts unten
        i = 1;
        while (i <= anzahlGewinn && (z - i) >= 0 && (s + i) < SPALTEN && !felder[z - i][s + i].istFrei()
            && felder[z - i][s + i].gibStein().gibFarbe() == farbe) {
            gesamt += 1;
            i++;
        }
        if (gesamt == anzahlGewinn) {
            return true;
        }
        
        return false;
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
        erstesFreiesFeld = new int[SPALTEN];
        for (int i = 0; i < ZEILEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                felder[i][j] = new Feld();
            }
        }
    }
    
    public int gibErstesFreiesFeld(int spalte) {
        return erstesFreiesFeld[spalte];    
    }
    
    
    public void ausgabe( ){
	for (int i = felder.length - 1; i >= 0; i--){
		for (int j = 0; j < felder[i].length; j++){
			if ( felder[i][j].istFrei()){
				System.out.print("-|");
			} else System.out.print(" *|");
		}
		System.out.println();
			
			
	}
	System.out.println("----------------------------------------------------");	
    }
}
