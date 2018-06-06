package util;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Helper {

    public static void loadTab(URL fxmlUrl, String tabTitle, TabPane tabPane) {
        try {
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent parent = loader.load();
            Tab t = new Tab();
            t.setText(tabTitle);
            t.setContent(parent);
            tabPane.getTabs().add(t);
        } catch (IOException ex) {
        }

    }

    public static void alert(String text, Pane mainPane) {
        mainPane.setDisable(true);
        mainPane.setOpacity(0.4);

        Label label = new Label(text);
        Button alertButton = new Button("OK");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, alertButton);
        vbox.setAlignment(Pos.CENTER);

        alertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mainPane.setDisable(false);
                mainPane.setOpacity(1);
                vbox.setVisible(false);
            }
        });

        Pane alrtPane = new AnchorPane();
        alrtPane.getChildren().add(label);
 //       alrtPane.setTopAnchor(vbox, 300);
 //       alrtPane.setLeftAnchor(vbox, 300);
    }

}
