package com.nc.netcracker_project.model.entities;

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
    private String manufacturer;
    private String activeIngredient;
    private PharmachologicEffectEntity pharmachologicEffect;
    private TherapeuticEffectEntity therapeuticEffect;
    private String description;
    public DrugEntity() {
    }

    public DrugEntity(String name, String releaseForm, String manufacturer, String activeIngredient, PharmachologicEffectEntity pharmachologicEffect, TherapeuticEffectEntity therapeuticEffect, String description) {
        this.name = name;
        this.releaseForm = releaseForm;
        this.manufacturer = manufacturer;
        this.activeIngredient = activeIngredient;
        this.pharmachologicEffect = pharmachologicEffect;
        this.therapeuticEffect = therapeuticEffect;
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
    @Column(name = "DNAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "RELEASEFORM")
    public String getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(String releaseForm) {
        this.releaseForm = releaseForm;
    }

    @Basic
    @Column(name = "MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    @Basic
    @Column(name = "ACTIVEINGRIDIENT", nullable = false)
    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "P_EFFECT_ID")

    public PharmachologicEffectEntity getPharmachologicEffect() {
        return pharmachologicEffect;
    }

    public void setPharmachologicEffect(PharmachologicEffectEntity pharmachologicEffect) {
        this.pharmachologicEffect = pharmachologicEffect;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "T_EFFECT_ID")
    public TherapeuticEffectEntity getTherapeuticEffect() {
        return therapeuticEffect;
    }

    public void setTherapeuticEffect(TherapeuticEffectEntity therapeuticEffect) {
        this.therapeuticEffect = therapeuticEffect;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugEntity that = (DrugEntity) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!releaseForm.equals(that.releaseForm)) return false;
        if (!manufacturer.equals(that.manufacturer)) return false;
        if (!activeIngredient.equals(that.activeIngredient)) return false;
        if (pharmachologicEffect != null ? !pharmachologicEffect.equals(that.pharmachologicEffect) : that.pharmachologicEffect != null)
            return false;
        if (therapeuticEffect != null ? !therapeuticEffect.equals(that.therapeuticEffect) : that.therapeuticEffect != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + releaseForm.hashCode();
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + activeIngredient.hashCode();
        result = 31 * result + (pharmachologicEffect != null ? pharmachologicEffect.hashCode() : 0);
        result = 31 * result + (therapeuticEffect != null ? therapeuticEffect.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DrugEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseForm='" + releaseForm + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", pharmachologicEffect=" + pharmachologicEffect +
                ", therapeuticEffect=" + therapeuticEffect +
                ", description='" + description + '\'' +
                '}';
    }
}
