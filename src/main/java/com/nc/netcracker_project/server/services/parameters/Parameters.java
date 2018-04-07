package com.nc.netcracker_project.server.services.parameters;

import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import com.nc.netcracker_project.server.model.entities.ProducerEntity;

public class Parameters {
    private String name;
    private String releaseForm;
    private String activeIngredient;
    private String indicationsForUse;
    private ProducerEntity producer;
    private PharmTerGroupEntity pharmTerGroup;
    private String district;
    private String street;
    private Short isRoundTheClock;

    public Parameters() {
        this.name = null;
        this.releaseForm = null;
        this.activeIngredient = null;
        this.indicationsForUse = null;
        this.producer = null;
        this.pharmTerGroup = null;
        this.district = null;
        this.street = null;
        this.isRoundTheClock = null;
    }

    public Parameters(String name, String releaseForm, String activeIngredient, String indicationsForUse,
                      ProducerEntity producer, PharmTerGroupEntity pharmTerGroup,
                      String district, String street, Short isRoundTheClock) {
        this.name = name;
        this.releaseForm = releaseForm;
        this.activeIngredient = activeIngredient;
        this.indicationsForUse = indicationsForUse;
        this.producer = producer;
        this.pharmTerGroup = pharmTerGroup;
        this.district = district;
        this.street = street;
        this.isRoundTheClock = isRoundTheClock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(String releaseForm) {
        this.releaseForm = releaseForm;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public String getIndicationsForUse() {
        return indicationsForUse;
    }

    public void setIndicationsForUse(String indicationsForUse) {
        this.indicationsForUse = indicationsForUse;
    }

    public ProducerEntity getProducer() {
        return producer;
    }

    public void setProducer(ProducerEntity producer) {
        this.producer = producer;
    }

    public PharmTerGroupEntity getPharmTerGroup() {
        return pharmTerGroup;
    }

    public void setPharmTerGroup(PharmTerGroupEntity pharmTerGroup) {
        this.pharmTerGroup = pharmTerGroup;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Short getIsRoundTheClock() {
        return isRoundTheClock;
    }

    public void setIsRoundTheClock(Short isRoundTheClock) {
        this.isRoundTheClock = isRoundTheClock;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "name='" + name + '\'' +
                ", releaseForm='" + releaseForm + '\'' +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", indicationsForUse='" + indicationsForUse + '\'' +
                ", producer=" + producer +
                ", pharmTerGroup=" + pharmTerGroup +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", isRoundTheClock=" + isRoundTheClock +
                '}';
    }
}
