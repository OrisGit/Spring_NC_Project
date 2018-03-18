package com.nc.netcracker_project.server.services.import_export;


import com.nc.netcracker_project.server.model.entities.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Entities")
public class EntityWrapper{

    private List<ProducerEntity> producers;
    private List<PharmTerGroupEntity> pharmTerGroups;
    private List<DrugstoreEntity> drugstores;
    private List<DrugEntity> drugs;
    private List<PriceEntity> price;

    public EntityWrapper(List<ProducerEntity> producers,
                         List<PharmTerGroupEntity> pharmTerGroups, List<DrugstoreEntity> drugstores,
                         List<DrugEntity> drugs, List<PriceEntity> price) {
        this.producers = producers;
        this.pharmTerGroups = pharmTerGroups;
        this.drugstores = drugstores;
        this.drugs = drugs;
        this.price = price;
    }

    public EntityWrapper() {
        drugs = new ArrayList<>();
        drugstores = new ArrayList<>();
        producers = new ArrayList<>();
        pharmTerGroups = new ArrayList<>();
        price = new ArrayList<>();
    }

    @XmlElementWrapper(name = "Producers")
    @XmlElement(name = "Producer", type = ProducerEntity.class)
    public List<ProducerEntity> getProducers() {
        return producers;
    }

    @XmlElementWrapper(name = "PharmTerGroups")
    @XmlElement(name = "PharmTerGroup", type = PharmTerGroupEntity.class)
    public List<PharmTerGroupEntity> getPharmTerGroups() {
        return pharmTerGroups;
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