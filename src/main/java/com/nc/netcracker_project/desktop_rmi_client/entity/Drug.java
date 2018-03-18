package com.nc.netcracker_project.desktop_rmi_client.entity;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class Drug {
    private final DrugEntity drugEntity;
    private final StringProperty name;
    private final StringProperty releaseForm;
    private final StringProperty pharmTerGroup;
    private final StringProperty indicationsForUse;
    private final StringProperty producer;
    private final StringProperty activeIngredient;
    private final StringProperty description;

    public Drug(DrugEntity drugEntity) {
        this.drugEntity = drugEntity;
        this.name = new SimpleStringProperty(drugEntity.getName());
        this.releaseForm = new SimpleStringProperty(drugEntity.getReleaseForm());
        this.activeIngredient = new SimpleStringProperty(drugEntity.getActiveIngredient());
        this.producer = new SimpleStringProperty(drugEntity.getProducer().getName());
        this.pharmTerGroup = new SimpleStringProperty(drugEntity.getPharmTerGroup().getName());
        this.description = new SimpleStringProperty(drugEntity.getDescription());
        this.indicationsForUse = new SimpleStringProperty(drugEntity.getIndicationsForUse());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getReleaseForm() {
        return releaseForm.get();
    }

    public StringProperty releaseFormProperty() {
        return releaseForm;
    }

    public String getActiveIngredient() {
        return activeIngredient.get();
    }

    public StringProperty activeIngredientProperty() {
        return activeIngredient;
    }

    public String getProducer() {
        return producer.get();
    }

    public StringProperty producerProperty() {
        return producer;
    }

    public String getPharmTerGroup() {
        return pharmTerGroup.get();
    }

    public StringProperty pharmTerGroupProperty() {
        return pharmTerGroup;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public DrugEntity getDrugEntity() {
        return drugEntity;
    }

    public String getIndicationsForUse() {
        return indicationsForUse.get();
    }

    public StringProperty indicationsForUseProperty() {
        return indicationsForUse;
    }

    public UUID getId(){return drugEntity.getId();}
}
