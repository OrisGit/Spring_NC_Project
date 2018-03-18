package com.nc.netcracker_project.server.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "DRUGS")
@XmlRootElement
public class DrugEntity implements Serializable{

    private UUID id;
    private String name;
    private String releaseForm;
    private PharmTerGroupEntity pharmTerGroup;
    private String indicationsForUse;
    private ProducerEntity producer;
    private String activeIngredient;
    private String description;
    public DrugEntity() {
    }

    public DrugEntity(String name, String releaseForm, String indicationsForUse, String activeIngredient, ProducerEntity producer, PharmTerGroupEntity pharmTerGroup, String description) {
        this.name = name;
        this.releaseForm = releaseForm;
        this.indicationsForUse = indicationsForUse;
        this.activeIngredient = activeIngredient;
        this.producer = producer;
        this.pharmTerGroup = pharmTerGroup;
        this.description = description;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "DRUG_ID", nullable = false, unique = true)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "RELEASE_FORM", nullable = false)
    public String getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(String releaseForm) {
        this.releaseForm = releaseForm;
    }

    @ManyToOne()
    @JoinColumn(name = "FARM_TER_GROUP", nullable = false)
    public PharmTerGroupEntity getPharmTerGroup() {
        return pharmTerGroup;
    }

    public void setPharmTerGroup(PharmTerGroupEntity pharmTerGroup) {
        this.pharmTerGroup = pharmTerGroup;
    }

    @Basic
    @Column(name = "IND_FOR_USE", nullable = false)
    public String getIndicationsForUse() {
        return indicationsForUse;
    }

    public void setIndicationsForUse(String indicationsForUse) {
        this.indicationsForUse = indicationsForUse;
    }

    @ManyToOne()
    @JoinColumn(name = "PRODUCER", nullable = false)
    public ProducerEntity getProducer() {
        return producer;
    }

    public void setProducer(ProducerEntity producer) {
        this.producer = producer;
    }

    @Basic
    @Column(name = "ACTIVE_INGREDIENT", nullable = false)
    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }


    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        DrugEntity that = (DrugEntity) object;

        if (!getId().equals(that.getId())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getReleaseForm().equals(that.getReleaseForm())) return false;
        if (!getPharmTerGroup().equals(that.getPharmTerGroup())) return false;
        if (!getIndicationsForUse().equals(that.getIndicationsForUse())) return false;
        if (!getProducer().equals(that.getProducer())) return false;
        if (!getActiveIngredient().equals(that.getActiveIngredient())) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getReleaseForm().hashCode();
        result = 31 * result + getPharmTerGroup().hashCode();
        result = 31 * result + getIndicationsForUse().hashCode();
        result = 31 * result + getProducer().hashCode();
        result = 31 * result + getActiveIngredient().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DrugEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseForm='" + releaseForm + '\'' +
                ", pharmTerGroup=" + pharmTerGroup +
                ", indicationsForUse='" + indicationsForUse + '\'' +
                ", producer=" + producer +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
