import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import java.util.Optional;


public class ControllerStart {
    private Spiel spiel;
    
    @FXML
    private GridPane spielfeld;
    
    @FXML
    private ListView<String> spielerListe;
    private ObservableList<String> spielerNamen;
    
    @FXML
    private Text maxNumPlayers;
    @FXML
    private Text curNumPlayers;
    
    public ControllerStart() {
        spiel = new Spiel();
        spielerNamen = FXCollections.observableArrayList();
    }
    
    @FXML
    public void spielerHinzufugen() {
        TextInputDialog dialog = new TextInputDialog("Spielername");
        dialog.setHeaderText("Gib den Namen des Spielers ein");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            spiel.addSpieler(result.get());
            spielerNamen.add(result.get());
            spielerListe.setItems(spielerNamen);
            curNumPlayers.setText(Integer.toString(spielerNamen.size()));
        }
    }
    
    //@FXML
    //public void initialize() {
    //    maxNumPlayers.setText(Integer.toString(spiel.ANZ_SPIELER));
    //    curNumPlayers.setText(Integer.toString(spielerNamen.size()));
    //}
    
    
}
