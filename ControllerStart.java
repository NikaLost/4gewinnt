import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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
    
    @FXML
    private Button losButton;
    
    @FXML
    private ChoiceBox modusWahl;
    private ObservableList<Modus> modusNamen;
    
    public ControllerStart() {
        spiel = new Spiel(Modus.KLASSISCH);
        spielerNamen = FXCollections.observableArrayList();
        modusNamen = FXCollections.observableArrayList();
    }
    
    @FXML
    public void spielerHinzufugen() {
        //max. Anz Spieler
        if (spielerNamen.size() >= spiel.ANZ_SPIELER)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText("Es können nicht noch mehr Spieler teilnehmen.");
            alert.showAndWait();
        }
        else
        {
            //Spieler hinzufügen
            TextInputDialog dialog = new TextInputDialog("Spielername");
            dialog.setTitle("Spielername");
            dialog.setHeaderText("Gib den Namen des Spielers ein");
    
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String spielerName = result.get();
                //falls Spielername schon vergeben ist
                if (spielerNamen.contains(spielerName)) {
                    dialog.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fehler");
                    alert.setHeaderText("Dieser Name ist bereits vergeben. Wähle einen anderen Namen.");
                    alert.showAndWait();
                    //füge Spieler hinzu
                } else {
                    spiel.addSpieler(spielerName);
                    spielerNamen.add(spielerName);
                    spielerListe.setItems(spielerNamen);
                    maxNumPlayers.setText(Integer.toString(spiel.ANZ_SPIELER));
                    curNumPlayers.setText(Integer.toString(spielerNamen.size()));
                }
            }
        }
    }
    
    @FXML void spielerLoeschen() {
        String item = spielerListe.getSelectionModel().getSelectedItem();
        if (item != null) {
            spielerNamen.remove(item);
            curNumPlayers.setText(Integer.toString(spielerNamen.size()));
        }
    }
    
    @FXML
    public void spielStarten(){
        if (spielerNamen.size() != spiel.ANZ_SPIELER) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText("Es sind noch nicht genügend Spieler. Füge weitere Spieler hinzu.");
            alert.showAndWait();
        } else {
            spiel = new Spiel((Modus) modusWahl.getSelectionModel().getSelectedItem());
            for (String spielerName : spielerNamen) {
                spiel.addSpieler(spielerName);
            }
            erstelleSpielfenster();
        }
    }
    
    private void erstelleSpielfenster() {
        try {
            /*#
             * Hier wird die FXML-Datei, die ihr mit dem SceneBuilder erstellt habt, geladen.
             * Der Dateiname muss ggf. von euch angepasst werden:
             */
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/spielfeld.fxml"));
            
            /*#
             * Verwendet ihr einen anderen Container als "Haupt-Container" (z.B. eine Pane), so muessen
             * die naechsten zwei Zeilen angepasst werden:
             */
            Pane root = (Pane) loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            // Fenster festlegen
            stage.setScene(scene);
            stage.setTitle("4 gewinnt");
            stage.centerOnScreen();
            stage.setOnCloseRequest(event ->
                {
                    // System.out.print('\u000C'); // Loescht die Konsolenausgabe
                    stage.close();             // Beendet
                });
            
            //hier wird dem Controller des Spielfensters das initialisierte Spiel mit übergeben
            ControllerSpiel controller = loader.getController();
            controller.initDaten(spiel);
            
            //Fenster anzeigen
            stage.show();
        } 
        catch(Exception e)    {
            e.printStackTrace();
        }
    }
    
    public void initialize() {
        //füge alle verfügbaren Modi der Auswahl hinzu
        for (Modus modus : Modus.values()) {
            modusNamen.add(modus);
        }
        modusWahl.getItems().addAll(modusNamen);
        //voreingestellter modus
        modusWahl.getSelectionModel().select(Modus.KLASSISCH);
    }
    
}
