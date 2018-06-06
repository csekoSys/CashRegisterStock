package ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import util.Helper;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cashregistertype/CashRegisterTypeView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Tab t = new Tab();
            t.setText("Pénztárgép típusok");
            t.setContent(pane);
            contentTabPane.getTabs().add(t);
        } catch (IOException ex) {
        }
    }

    /**
     * Alkatrészek listája
     *
     * @param event
     */
    @FXML
    private void components(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/component/ComponentView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Tab t = new Tab();
            t.setText("Alkatrészek");
            t.setContent(pane);
            contentTabPane.getTabs().add(t);
        } catch (IOException ex) {
        }
    }

}
