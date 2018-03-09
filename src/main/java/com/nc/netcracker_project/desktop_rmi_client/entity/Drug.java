package com.nc.netcracker_project.desktop_rmi_client.entity;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class Drug {
    private final DrugEntity drugEntity;
    private final StringProperty name;
    private final StringProperty releaseForm;
    private final StringProperty manufacturer;
    private final StringProperty activeIngredient;
    private final StringProperty pharmacologicalEffect;
    private final StringProperty therapeuticEffect;
    private final StringProperty description;

    public Drug(DrugEntity drugEntity) {
        this.drugEntity = drugEntity;
        this.name = new SimpleStringProperty(drugEntity.getName());
        this.releaseForm = new SimpleStringProperty(drugEntity.getReleaseForm());
        this.manufacturer = new SimpleStringProperty(drugEntity.getManufacturer());
        this.activeIngredient = new SimpleStringProperty(drugEntity.getActiveIngredient());
        this.pharmacologicalEffect = new SimpleStringProperty(drugEntity.getPharmachologicEffect().getName());
        this.therapeuticEffect = new SimpleStringProperty(drugEntity.getTherapeuticEffect().getName());
        this.description = new SimpleStringProperty(drugEntity.getDescription());
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

    public String getManufacturer() {
        return manufacturer.get();
    }

    public StringProperty manufacturerProperty() {
        return manufacturer;
    }

    public String getActiveIngredient() {
        return activeIngredient.get();
    }

    public StringProperty activeIngredientProperty() {
        return activeIngredient;
    }

    public String getPharmacologicalEffect() {
        return pharmacologicalEffect.get();
    }

    public StringProperty pharmacologicalEffectProperty() {
        return pharmacologicalEffect;
    }

    public String getTherapeuticEffect() {
        return therapeuticEffect.get();
    }

    public StringProperty therapeuticEffectProperty() {
        return therapeuticEffect;
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

    public UUID getId(){return drugEntity.getId();}
}
