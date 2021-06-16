import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
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
            VBox root = (VBox) loader.load();
            Scene scene = new Scene(root);
            
            // Fenster festlegen
            primaryStage.setScene(scene);
            primaryStage.setTitle("4 gewinnt");
            primaryStage.centerOnScreen();
            primaryStage.setOnCloseRequest(event ->
                {
                    // System.out.print('\u000C'); // Loescht die Konsolenausgabe
                    System.exit(0);             // Beendet
                });
            primaryStage.show();
        } 
        catch(Exception e)    {
            e.printStackTrace();
        }
    }

}