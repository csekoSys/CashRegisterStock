package model;

import javafx.beans.property.SimpleStringProperty;

public class Component {

    private SimpleStringProperty id;
    private SimpleStringProperty itemNumber;
    private SimpleStringProperty barCode;
    private SimpleStringProperty componentName;
    private SimpleStringProperty quantity;
    private SimpleStringProperty comment;

    public Component() {
        this.id = new SimpleStringProperty("");
        this.itemNumber = new SimpleStringProperty("");
        this.barCode = new SimpleStringProperty("");
        this.componentName = new SimpleStringProperty("");
        this.comment = new SimpleStringProperty("");
    }

    public Component(String itemNumber, String barCode, String componentName, String comment) {
        this.id = new SimpleStringProperty("");
        this.itemNumber = new SimpleStringProperty(itemNumber);
        this.barCode = new SimpleStringProperty(barCode);
        this.componentName = new SimpleStringProperty(componentName);
        this.comment = new SimpleStringProperty(comment);
    }

    public Component(Integer id, String itemNumber, String barCode, String componentName, String comment) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.itemNumber = new SimpleStringProperty(itemNumber);
        this.barCode = new SimpleStringProperty(barCode);
        this.componentName = new SimpleStringProperty(componentName);
        this.comment = new SimpleStringProperty(comment);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String idx) {
        id.set(idx);
    }

    public String getItemNumber() {
        return itemNumber.get();
    }

    public void setItemNumber(String itemNumberx) {
        itemNumber.set(itemNumberx);
    }

    public String getBarCode() {
        return barCode.get();
    }

    public void setBarCode(String barCodex) {
        barCode.set(barCodex);
    }

    public String getComponentName() {
        return componentName.get();
    }

    public void setComponentName(String componentNamex) {
        componentName.set(componentNamex);
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String commentx) {
        comment.set(commentx);
    }

}
