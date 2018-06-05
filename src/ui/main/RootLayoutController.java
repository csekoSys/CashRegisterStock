package ui.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootLayoutController implements Initializable {

    @FXML
    private TabPane contentTabPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Alkatrészek kiadása
     *
     * @param event
     */
    @FXML
    private void componentRelease(ActionEvent event) {
        Tab t = new Tab("Alkatrész kiadás");
        contentTabPane.getTabs().add(t);
    }

    /**
     * Gyártott pénztárgépek rögzítése
     *
     * @param event
     */
    @FXML
    private void readyCashREgisters(ActionEvent event) {
    }

    /**
     * Pénztárgép típusok
     *
     * @param event
     */
    @FXML
    private void cashRegistersType(ActionEvent event) {
        Tab t = new Tab("Pénztárgép típusok");
        contentTabPane.getTabs().add(t);
    }

    /**
     * Alkatrészek listája
     *
     * @param event
     */
    @FXML
    private void components(ActionEvent event) {
    }

    
}
