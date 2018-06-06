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
import model.CashRegisterType;

public class CashRegisterTypeViewController implements Initializable {

    private Database db = new Database();
    private final ObservableList<CashRegisterType> data = FXCollections.observableArrayList();
    @FXML
    private TableView<CashRegisterType> table;
    @FXML
    private TextField inputLicenseNumber;
    @FXML
    private TextField inputTypeName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addCashRegisterType(ActionEvent event) {
        String licenseNumber = inputLicenseNumber.getText();
        String typeNumber = inputTypeName.getText();

        CashRegisterType newCashRegisterType = new CashRegisterType(licenseNumber, typeNumber);
        data.add(newCashRegisterType);
        db.addCashRegisterType(newCashRegisterType);
        inputLicenseNumber.clear();
        inputTypeName.clear();
    }

    public void setTableData() {
        TableColumn liceseNumberCol = new TableColumn("Engedélyszám");
        liceseNumberCol.setMinWidth(130);
        liceseNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        liceseNumberCol.setCellValueFactory(new PropertyValueFactory<CashRegisterType, String>("licenseNumber"));

        liceseNumberCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashRegisterType, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CashRegisterType, String> t) {
                CashRegisterType actualCashRegisterType = (CashRegisterType) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualCashRegisterType.setLicenseNumber(t.getNewValue());
                db.updateCashRegisterType(actualCashRegisterType);
            }
        }
        );
        
        table.getColumns().addAll(liceseNumberCol);
        data.addAll(db.getAllCashRegiseterType());
        table.setItems(data);
    }

}
