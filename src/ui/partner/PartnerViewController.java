/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.partner;

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
import model.Component;
import model.Partner;
import util.Helper;

/**
 * FXML Controller class
 *
 * @author szerviz2
 */
public class PartnerViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Partner> table;
    @FXML
    private TextField inputPartnerName;
    private final Database db = new Database();
    private final ObservableList<Partner> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData();
    }

    @FXML
    private void addPartner(ActionEvent event) {
        String partnerName = inputPartnerName.getText();
        ;

        if (partnerName.length() < 2) {
            System.out.println("Hibás adatok --> addPartner");
            Helper.alert("Hibás adatok --> addPartner", anchorPane);
        } else {
            Partner newPartner = new Partner(partnerName);
            data.add(newPartner);
            db.addPartner(newPartner);
            inputPartnerName.clear();
        }
    }

    public void setTableData() {
        table.setEditable((true));

        //ID megjelenítése
        TableColumn<Partner, String> idCol = new TableColumn<>("#");
        idCol.setMinWidth(50);
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        //partnerName megjelenítése + szerkesztése
        TableColumn<Partner, String> partnerNameCol = new TableColumn<>("Partner neve");
        partnerNameCol.setMinWidth(400);
        partnerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        partnerNameCol.setCellValueFactory(new PropertyValueFactory<>("partnerName"));

        partnerNameCol.setOnEditCommit((TableColumn.CellEditEvent<Partner, String> t) -> {
            Partner actualPartner = (Partner) t.getTableView().getItems().get(t.getTablePosition().getRow());
            actualPartner.setPartnerName(t.getNewValue());
            db.updatePartner(actualPartner);
        });

        table.getColumns().addAll(idCol, partnerNameCol);
        data.addAll(db.getAllPartner());
        table.setItems(data);
    }

}
