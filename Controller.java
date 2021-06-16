import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.Node;


public class Controller {
    private Spiel spiel = new Spiel();
    
    @FXML
    GridPane spielfeld;
        
    
    
    /**
     * Methode die nach Drücken des Buttons über einer Spalte aufgerufen wird um einen Stein im
     * Spielfeld zu platzieren.
     */
    @FXML
    public void addStein(ActionEvent event) {
        Button pressed = (Button) event.getSource();
        int column = spielfeld.getColumnIndex(pressed);
        SpielSteinView stein = new SpielSteinView(Farbe.GELB);
        // Vorläufige Platzierung von Steinen
        spielfeld.add(new SpielSteinView(Farbe.GELB), column, 6);

    }

    
    
    
}