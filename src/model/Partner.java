package model;

import javafx.beans.property.SimpleStringProperty;

public class Partner {

    private final SimpleStringProperty id;
    private final SimpleStringProperty partnerName;

    public Partner() {
        this.id = new SimpleStringProperty("");
        this.partnerName = new SimpleStringProperty("");
    }

    public Partner(String partnerName) {
        this.id = new SimpleStringProperty("");
        this.partnerName = new SimpleStringProperty(partnerName);
    }

    public Partner(Integer id, String partnerName) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.partnerName = new SimpleStringProperty(partnerName);
    }

    public String getid() {
        return partnerName.get();
    }

    public void setId(String partnerId) {
        id.set(partnerId);
    }

    public String getPartnerName() {
        return partnerName.get();
    }

    public void setPartnerName(String name) {
        partnerName.set(name);
    }
}
