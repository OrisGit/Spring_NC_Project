package com.nc.netcracker_project.server.model.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "DRUGS_STORES")
@XmlRootElement
public class PriceEntity implements Serializable{

    private PriceEntityPK id;
    private DrugEntity drug;
    private DrugstoreEntity drugstore;
    private Long cost;

    public PriceEntity() {
    }

    public PriceEntity(DrugEntity drug, DrugstoreEntity drugstore, Long cost) {
        this.drug = drug;
        this.drugstore = drugstore;
        this.cost = cost;
        this.id = new PriceEntityPK(drug.getId(),drugstore.getId());
    }

    @EmbeddedId
    public PriceEntityPK getId() {
        return id;
    }

    public void setId(PriceEntityPK id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("drugID")
    @JoinColumn(name = "DRUG_ID")
    public DrugEntity getDrug() {
        return drug;
    }

    public void setDrug(DrugEntity drug) {
        this.drug = drug;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("drugstoreID")
    @JoinColumn(name = "DRUGSTORE_ID")
    public DrugstoreEntity getDrugstore() {
        return drugstore;
    }

    public void setDrugstore(DrugstoreEntity drugstore) {
        this.drugstore = drugstore;
    }

    @Column(name = "COST")
    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        PriceEntity that = (PriceEntity) object;

        if (!id.equals(that.id)) return false;
        if (!drug.equals(that.drug)) return false;
        if (!drugstore.equals(that.drugstore)) return false;
        return cost.equals(that.cost);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + drug.hashCode();
        result = 31 * result + drugstore.hashCode();
        result = 31 * result + cost.hashCode();
        return result;
    }
}
