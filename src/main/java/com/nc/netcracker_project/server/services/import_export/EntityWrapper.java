package com.nc.netcracker_project.server.services.import_export;


import com.nc.netcracker_project.server.model.entities.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Entities")
public class EntityWrapper{

    private List<PharmachologicEffectEntity> pharmachologicEffects;
    private List<TherapeuticEffectEntity> therapeuticEffects;
    private List<DrugstoreEntity> drugstores;
    private List<DrugEntity> drugs;
    private List<PriceEntity> price;

    public EntityWrapper(List<PharmachologicEffectEntity> pharmachologicEffects,
                         List<TherapeuticEffectEntity> therapeuticEffects, List<DrugstoreEntity> drugstores,
                         List<DrugEntity> drugs, List<PriceEntity> price) {
        this.pharmachologicEffects = pharmachologicEffects;
        this.therapeuticEffects = therapeuticEffects;
        this.drugstores = drugstores;
        this.drugs = drugs;
        this.price = price;
    }

    public EntityWrapper() {
        drugs = new ArrayList<>();
        drugstores = new ArrayList<>();
        pharmachologicEffects = new ArrayList<>();
        therapeuticEffects = new ArrayList<>();
        price = new ArrayList<>();
    }

    @XmlElementWrapper(name = "PharmachologicEffects")
    @XmlElement(name = "PharmachologicEffect", type = PharmachologicEffectEntity.class)
    public List<PharmachologicEffectEntity> getPharmachologicEffects() {
        return pharmachologicEffects;
    }

    @XmlElementWrapper(name = "TherapeuticEffects")
    @XmlElement(name = "TherapeuticEffect", type = TherapeuticEffectEntity.class)
    public List<TherapeuticEffectEntity> getTherapeuticEffects() {
        return therapeuticEffects;
    }

    @XmlElementWrapper(name = "Drugstores")
    @XmlElement(name = "Drugstore", type = DrugstoreEntity.class)
    public List<DrugstoreEntity> getDrugstores() {
        return drugstores;
    }

    @XmlElementWrapper(name = "Drugs")
    @XmlElement(name = "Drug", type = DrugEntity.class)
    public List<DrugEntity> getDrugs() {
        return drugs;
    }

    @XmlElementWrapper(name = "Price")
    @XmlElement(name = "PriceItem", type = PriceEntity.class)
    public List<PriceEntity> getPrice() {
        return price;
    }
}