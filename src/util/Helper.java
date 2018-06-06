
package util;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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

}
