package ui.supply;

import com.jfoenix.controls.JFXButton;
import database.Database;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import model.Component;

public class SupplyViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField partner;
    @FXML
    private TextField certificateNumber;
    @FXML
    private DatePicker supplyDate;
    @FXML
    private TextField piece;
    @FXML
    private TableView<String> table;
    @FXML
    private ComboBox<Component> component;
    private final Database db = new Database();
    private final ObservableList<Component> componentData = FXCollections.observableArrayList();
    private final ObservableList<String> itemsData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxData();
        setTableData();
    }

    public void comboBoxData() {
        componentData.addAll(db.getAllComponent());
        component.getItems().addAll(componentData);
    }

    @FXML
    private void addItem(ActionEvent event) {
        Component c = component.getValue();
        System.out.println("Választott alkatrész: " + c.getComponentName());
        itemsData.add(c.getComponentName());

    }
    
   public void setTableData() {
        table.setEditable((true));

        //Alkatrész megjelenítése
        TableColumn<String, String> componentNameCol = new TableColumn<>("Alkatrész neve");
        componentNameCol.setMinWidth(250);
//        componentNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        componentNameCol.setCellValueFactory(new PropertyValueFactory<>("componentName"));
/*
        //Darabszám megjelenítése + szerkesztése
        TableColumn<String, String> pieceCol = new TableColumn<>("Mennyiség");
        pieceCol.setMinWidth(130);
        pieceCol.setCellFactory(TextFieldTableCell.forTableColumn());
       pieceCol.setCellValueFactory(new PropertyValueFactory<>("itemNumber"));

        piceCol.setOnEditCommit((TableColumn.CellEditEvent<Component, String> t) -> {
            Component actualComponent = (Component) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualComponent.setItemNumber(t.getNewValue());
            db.updateComponent(actualComponent);
        });

        //Sor törlés funkció
        TableColumn<Component, String> deleteCol = new TableColumn<>("Törlés");
        deleteCol.setMinWidth(130);
        deleteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("deleteItem"));

   
*/        
        
        table.getColumns().add(componentNameCol);
//        data.addAll(db.getAllComponent());
//        table.setItems(data);
    }    

    @FXML
    private void saveSupply(ActionEvent event) {
    }

}
