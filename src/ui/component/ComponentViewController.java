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

        if (componentName.length() < 2) {
            System.out.println("Hibás adatok --> addComponent");
            Helper.alert("Hibás adatok --> addComponent", anchorPane);
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

        //ID megjelenítése
        TableColumn<Component, String> idCol = new TableColumn<>("#");
        idCol.setMinWidth(50);
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        //itemNumber megjelenítése + szerkesztése
        TableColumn<Component, String> itemNumberCol = new TableColumn<>("Cikkszám");
        itemNumberCol.setMinWidth(130);
        itemNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        itemNumberCol.setCellValueFactory(new PropertyValueFactory<>("itemNumber"));

        itemNumberCol.setOnEditCommit((TableColumn.CellEditEvent<Component, String> t) -> {
            Component actualComponent = (Component) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualComponent.setItemNumber(t.getNewValue());
            db.updateComponent(actualComponent);
        });

        //barCode megjelenítése + szerkesztése
        TableColumn<Component, String> barCodeCol = new TableColumn<>("Vonalkód");
        barCodeCol.setMinWidth(130);
        barCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        barCodeCol.setCellValueFactory(new PropertyValueFactory<>("barCode"));

        barCodeCol.setOnEditCommit((TableColumn.CellEditEvent<Component, String> t) -> {
            Component actualComponent = (Component) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualComponent.setBarCode(t.getNewValue());
            db.updateComponent(actualComponent);
        });
        
        //componentName megjelenítése + szerkesztése
        TableColumn<Component, String> componentNameCol = new TableColumn<>("Alkatrész");
        componentNameCol.setMinWidth(130);
        componentNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        componentNameCol.setCellValueFactory(new PropertyValueFactory<>("componentName"));

        componentNameCol.setOnEditCommit((TableColumn.CellEditEvent<Component, String> t) -> {
            Component actualComponent = (Component) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualComponent.setComponentName(t.getNewValue());
            db.updateComponent(actualComponent);
        });

        //comment megjelenítése + szerkesztése
        TableColumn<Component, String> commentCol = new TableColumn<>("Megjegyzés");
        commentCol.setMinWidth(130);
        commentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));

        commentCol.setOnEditCommit((TableColumn.CellEditEvent<Component, String> t) -> {
            Component actualComponent = (Component) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualComponent.setComment(t.getNewValue());
            db.updateComponent(actualComponent);
        });        
        
        
        table.getColumns().addAll(idCol, itemNumberCol, barCodeCol, componentNameCol, commentCol);
        data.addAll(db.getAllComponent());
        table.setItems(data);
    }

}
