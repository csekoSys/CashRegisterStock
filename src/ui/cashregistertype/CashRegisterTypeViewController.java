package ui.cashregistertype;

import database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import model.CashRegisterType;
import util.Helper;

public class CashRegisterTypeViewController implements Initializable {

    private final Database db = new Database();
    private final ObservableList<CashRegisterType> data = FXCollections.observableArrayList();
    @FXML
    private TableView<CashRegisterType> table;
    @FXML
    private TextField inputLicenseNumber;
    @FXML
    private TextField inputTypeName;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData();
    }

    @FXML
    private void addCashRegisterType(ActionEvent event) {
        String licenseNumber = inputLicenseNumber.getText();
        String typeName = inputTypeName.getText();

        if ((licenseNumber.length() != 4) || typeName.length() < 5) {
            
            System.out.println("Hibás adatok --> addCashRegisterType");
            Helper.alert("Hibás adatok --> addCashRegisterType", anchorPane);
        } else {
            CashRegisterType newCashRegisterType = new CashRegisterType(licenseNumber, typeName);
            data.add(newCashRegisterType);
            db.addCashRegisterType(newCashRegisterType);
            inputLicenseNumber.clear();
            inputTypeName.clear();
        }

    }

    public void setTableData() {
        table.setEditable((true));

        TableColumn<CashRegisterType, String> idCol = new TableColumn<>("#");
        idCol.setMinWidth(30);
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<CashRegisterType, String> liceseNumberCol = new TableColumn<>("Engedélyszám");
        liceseNumberCol.setMinWidth(130);
        liceseNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        liceseNumberCol.setCellValueFactory(new PropertyValueFactory<>("licenseNumber"));

        liceseNumberCol.setOnEditCommit((TableColumn.CellEditEvent<CashRegisterType, String> t) -> {
            CashRegisterType actualCashRegisterType = (CashRegisterType) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualCashRegisterType.setLicenseNumber(t.getNewValue());
            db.updateCashRegisterType(actualCashRegisterType);
        });

        TableColumn<CashRegisterType, String> typeNamerCol = new TableColumn<>("Pénztárgép típusa");
        typeNamerCol.setMinWidth(300);
        typeNamerCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeNamerCol.setCellValueFactory(new PropertyValueFactory<>("typeName"));

        typeNamerCol.setOnEditCommit((TableColumn.CellEditEvent<CashRegisterType, String> t) -> {
            CashRegisterType actualCashRegisterType = (CashRegisterType) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualCashRegisterType.setTypeName(t.getNewValue());
            db.updateCashRegisterType(actualCashRegisterType);
        });

        table.getColumns().addAll(idCol, liceseNumberCol, typeNamerCol);
        data.addAll(db.getAllCashRegiseterType());
        table.setItems(data);
    }

}
