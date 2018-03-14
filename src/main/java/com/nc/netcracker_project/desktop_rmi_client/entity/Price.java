package com.nc.netcracker_project.desktop_rmi_client.entity;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntityPK;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class Price {
    private final PriceEntity priceEntity;
    private final StringProperty drug;
    private final StringProperty drugstore;
    private final LongProperty cost;

    public Price(PriceEntity priceEntity) {
        this.priceEntity = priceEntity;
        this.drug = new SimpleStringProperty(
                String.format("%s, %s, %s", priceEntity.getDrug().getName(), priceEntity.getDrug().getReleaseForm(),
                        priceEntity.getDrug().getManufacturer()));
        this.drugstore = new SimpleStringProperty(
                String.format("%s, %s, %s %s", priceEntity.getDrugstore().getName(), priceEntity.getDrugstore().getDistrict(),
                        priceEntity.getDrugstore().getStreet(), priceEntity.getDrugstore().getBuilding()));
        this.cost = new SimpleLongProperty(priceEntity.getCost());
    }

    public String getDrug() {
        return drug.get();
    }

    public StringProperty drugProperty() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug.set(drug);
    }

    public String getDrugstore() {
        return drugstore.get();
    }

    public StringProperty drugstoreProperty() {
        return drugstore;
    }

    public void setDrugstore(String drugstore) {
        this.drugstore.set(drugstore);
    }

    public long getCost() {
        return cost.get();
    }

    public LongProperty costProperty() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost.set(cost);
    }

    public PriceEntity getPriceEntity() {
        return priceEntity;
    }

    public PriceEntityPK getId(){
        return priceEntity.getId();
    }
}
