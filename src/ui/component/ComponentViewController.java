package ui.component;

import database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import model.CashRegisterType;
import model.Component;
import util.Helper;

public class ComponentViewController implements Initializable {

    private final Database db = new Database();
    private final ObservableList<Component> data = FXCollections.observableArrayList();

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Component> table;
    @FXML
    private TextField inputItemNumber;
    @FXML
    private TextField inputBarCode;
    @FXML
    private TextField inputComponentName;
    @FXML
    private TextField inputComment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData();
    }

    @FXML
    private void addComponent(ActionEvent event) {
        String itemNumber = inputItemNumber.getText();
        String barCode = inputBarCode.getText();
        String componentName = inputComponentName.getText();
        String comment = inputComment.getText();

        if (componentName.length() < 4) {

            System.out.println("Hibás adatok --> addCashRegisterType");
            Helper.alert("Hibás adatok --> addCashRegisterType", anchorPane);
        } else {
            Component newComponent = new Component(itemNumber, barCode, componentName, comment);
            data.add(newComponent);
            db.addComponent(newComponent);
            inputBarCode.clear();
            inputComment.clear();
            inputComponentName.clear();
            inputItemNumber.clear();
        }

    }

    public void setTableData() {
        table.setEditable((true));

        TableColumn<Component, String> idCol = new TableColumn<>("#");
        idCol.setMinWidth(30);
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Component, String> itemNumberCol = new TableColumn<>("Cikkszám");
        itemNumberCol.setMinWidth(130);
        itemNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        itemNumberCol.setCellValueFactory(new PropertyValueFactory<>("itemNumber"));
/*
        itemNumberCol.setOnEditCommit((TableColumn.CellEditEvent<Component, String> t) -> {
            Component actualComponent = (Component) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualComponent.setItemNumber(t.getNewValue());
            db.updateComponent(actualComponent);
        });
*/


        table.getColumns().addAll(idCol, itemNumberCol);
        data.addAll(db.getAllComponent());
        table.setItems(data);
    }

}
