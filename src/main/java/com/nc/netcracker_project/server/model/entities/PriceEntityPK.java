package com.nc.netcracker_project.server.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class PriceEntityPK implements Serializable{
    @Column(name = "DRUG_ID")
    private UUID drugID;
    @Column(name = "DRUGSTORE_ID")
    private UUID drugstoreID;

    public PriceEntityPK() {
    }

    public PriceEntityPK(UUID drugID, UUID drugstoreID) {
        this.drugID = drugID;
        this.drugstoreID = drugstoreID;
    }

    public UUID getDrugID() {
        return drugID;
    }

    public void setDrugID(UUID drug) {
        this.drugID = drug;
    }

    public UUID getDrugstoreID() {
        return drugstoreID;
    }

    public void setDrugstoreID(UUID drugstore) {
        this.drugstoreID = drugstore;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        PriceEntityPK that = (PriceEntityPK) object;

        if (!drugID.equals(that.drugID)) return false;
        return drugstoreID.equals(that.drugstoreID);
    }

    @Override
    public int hashCode() {
        int result = drugID.hashCode();
        result = 31 * result + drugstoreID.hashCode();
        return result;
    }
}
