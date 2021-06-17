import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.*;
import javafx.scene.control.*;
import java.util.Optional;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import java.util.LinkedList;


public class ControllerSpiel {
    public static final String AM_ZUG_TEXT= " ist am Zug!";
    public static final String GEWINNER_TEXT= " hat gewonnen!";
    public static final String NEUSTART_TEXT= "Neustart";
    public static final String NOCHMAL_TEXT= "Nochmal !";
    private Spiel spiel;
    
    @FXML
    private GridPane spielfeld;
    
    @FXML
    private Text amZugText;
    
    @FXML
    private VBox amZugBox;
    
    @FXML
    private Button resetButton;
    
    
    /**
     * Methode die nach Drücken des Buttons über einer Spalte aufgerufen wird um einen Stein im
     * Spielfeld zu platzieren.
     */
    @FXML
    public void addStein(ActionEvent event) {
        if (spiel.istBeendet()) {
            return;
        }
        Button gedrückt = (Button) event.getSource();
        int spalte = spielfeld.getColumnIndex(gedrückt);
    
        
        if (spiel.amZug() != null) {
            Farbe farbe = spiel.amZug().gibFarbe();
            if (!spiel.einwerfen(spalte)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fehler");
                alert.setHeaderText(spiel.amZug().gibName() 
                + " leider ist diese Spalte schon voll");
                alert.showAndWait();
            } else {
            // Vorläufige Platzierung von Steinen
                spielfeld.add(new SpielSteinView(farbe), spalte,
                    spielfeld.getRowCount() - spiel.gibSpielbrett().gibErstesFreiesFeld(spalte));
                    
                //checke ob gewonnen wurde
                if (spiel.gibGewinner() != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Herzlichen Glückwunsch");
                    alert.setHeaderText("GLÜCKWUNSCH!!! \n" + spiel.gibGewinner().gibName() 
                    + " hat das Spiel gewonnen.");
                    amZugText.setText(spiel.amZug().gibName() + GEWINNER_TEXT);
                    alert.showAndWait();
                    resetButton.setText(NOCHMAL_TEXT);
                } else {
                    amZugText.setText(spiel.amZug().gibName() + AM_ZUG_TEXT);
                }
            }
        }
    }
    
    public void neustart() {
        spiel.reset();
        entferneAlleSpielsteine();
        amZugText.setText(spiel.amZug().gibName() + AM_ZUG_TEXT);
        resetButton.setText(NEUSTART_TEXT);
    }
    
    public void initDaten(Spiel spiel) {
        this.spiel = spiel;
        amZugText.setText(spiel.amZug().gibName() + AM_ZUG_TEXT);
    }

    public void initialize(){
        amZugBox.setAlignment(Pos.CENTER);
        resetButton.setText(NEUSTART_TEXT);
    }
    
    
    private void entferneAlleSpielsteine() {
        LinkedList<SpielSteinView> steine = new LinkedList();
        //suche alle Steine in der GUI
        for (Node children : spielfeld.getChildren()) {
            if (children.getClass() == SpielSteinView.class) {
                steine.add((SpielSteinView) children);
            }
        }
        spielfeld.getChildren().removeAll(steine);
    }
}