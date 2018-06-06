package model;

import javafx.beans.property.SimpleStringProperty;

public class CashRegisterType {
    
    private SimpleStringProperty id;
    private SimpleStringProperty licenseNumber;
    private SimpleStringProperty typeName;
    
    public CashRegisterType() {
        this.id = new SimpleStringProperty("");
        this.licenseNumber = new SimpleStringProperty("");
        this.typeName = new SimpleStringProperty("");
    }
    
    public CashRegisterType(String licenseNumber, String typeName) {
        this.id = new SimpleStringProperty("");
        this.licenseNumber = new SimpleStringProperty(licenseNumber);
        this.typeName = new SimpleStringProperty(typeName);
    }
    
    public CashRegisterType(Integer id, String licenseNumber, String typeName) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.licenseNumber = new SimpleStringProperty(licenseNumber);
        this.typeName = new SimpleStringProperty(typeName);
    }
       
    public String getId() {
        return id.get();
    }
    
    public void setId(String idx) {
        id.set(idx);
    }
    
    public String getLicenseNumber() {
        return licenseNumber.get();
    }
    
    public void setLicenseNumber(String licenseNumberx) {
        licenseNumber.set(licenseNumberx);
    }
    
    public String getTypeName() {
        return typeName.get();
    }
    
    public void setTypeName(String typeNamex) {
        typeName.set(typeNamex);
    }
    
}
