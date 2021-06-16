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


public class Controller {
    private Spiel spiel = new Spiel();
    
    @FXML
    private GridPane spielfeld;
        
    
    
    /**
     * Methode die nach Drücken des Buttons über einer Spalte aufgerufen wird um einen Stein im
     * Spielfeld zu platzieren.
     */
    @FXML
    public void addStein(ActionEvent event) {
        Button gedrückt = (Button) event.getSource();
        int spalte = spielfeld.getColumnIndex(gedrückt);
        
        if (spiel.amZug() != null) {
            if (!spiel.einwerfen(spalte)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fehler");
                alert.setHeaderText(spiel.amZug().gibName() 
                + " leider ist diese Spalte schon voll");
                alert.showAndWait();
            }
            
        
            // Vorläufige Platzierung von Steinen
            spielfeld.add(new SpielSteinView(spiel.amZug().gibFarbe()), spalte, 6);
        }

    }
    
    @FXML
    public void spielerHinzufugen() {
        TextInputDialog dialog = new TextInputDialog("Spielername");
        dialog.setHeaderText("Gib den Namen des Spielers ein");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            spiel.addSpieler(result.get());
        }
    }

    
    
    
}