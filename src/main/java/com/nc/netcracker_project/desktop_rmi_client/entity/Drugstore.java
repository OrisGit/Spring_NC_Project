package com.nc.netcracker_project.desktop_rmi_client.entity;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class Drugstore {
    private final DrugstoreEntity drugstoreEntity;
    private final StringProperty name;
    private final StringProperty district;
    private final StringProperty address;
    private final StringProperty phone;
    private final StringProperty workingHours;
    private final BooleanProperty isRoundTheClock;

    public Drugstore(DrugstoreEntity drugstoreEntity) {
        this.drugstoreEntity = drugstoreEntity;
        this.name = new SimpleStringProperty(drugstoreEntity.getName());
        this.district = new SimpleStringProperty(drugstoreEntity.getDistrict());
        this.address = new SimpleStringProperty(drugstoreEntity.getStreet() + ", " + drugstoreEntity.getBuilding());
        this.phone = new SimpleStringProperty(drugstoreEntity.getPhone().toString());
        this.workingHours = new SimpleStringProperty(drugstoreEntity.getWorkingHours());
        this.isRoundTheClock = new SimpleBooleanProperty(drugstoreEntity.getIsRoundTheClock()!=0);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDistrict() {
        return district.get();
    }

    public StringProperty districtProperty() {
        return district;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getWorkingHours() {
        return workingHours.get();
    }

    public StringProperty workingHoursProperty() {
        return workingHours;
    }

    public boolean getIsRoundTheClock() {
        return isRoundTheClock.get();
    }

    public BooleanProperty isRoundTheClockProperty() {
        return isRoundTheClock;
    }

    public DrugstoreEntity getDrugstoreEntity() {
        return drugstoreEntity;
    }

    public UUID getId(){
        return drugstoreEntity.getId();
    }
}
