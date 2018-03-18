package com.nc.netcracker_project.server.controllers.rmi;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.*;
import com.nc.netcracker_project.server.services.event_service.EventListener;
import com.nc.netcracker_project.server.services.event_service.EventService;
import com.nc.netcracker_project.server.services.import_export.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RMIControllerImpl implements RMIController{

    private static final Logger LOG = Logger.getLogger(RMIControllerImpl.class);

    private DrugDataControl drugDataControl;
    private DrugstoreDataControl drugstoreDataControl;
    private PriceDataControl priceDataControl;
    private ProducerDataControl producerDataControl;
    private PharmTerGroupDataControl pharmTerGroupDataControl;
    private Importer importer;
    private Exporter exporter;
    private EventService eventService;

    @Autowired
    public RMIControllerImpl(DrugDataControl drugDataControl, DrugstoreDataControl drugstoreDataControl,
                             PriceDataControl priceDataControl, ProducerDataControl producerDataControl,
                             PharmTerGroupDataControl pharmTerGroupDataControl, Importer importer, Exporter exporter,
                             EventService eventService) {
        this.drugDataControl = drugDataControl;
        this.drugstoreDataControl = drugstoreDataControl;
        this.priceDataControl = priceDataControl;
        this.producerDataControl = producerDataControl;
        this.pharmTerGroupDataControl = pharmTerGroupDataControl;
        this.importer = importer;
        this.exporter = exporter;
        this.eventService = eventService;
    }

    @Override
    public Iterable<DrugEntity> getAllDrug() {
        return drugDataControl.getAll();
    }

    @Override
    public boolean addDrug(DrugEntity drug) {
        try {
            drugDataControl.saveOrUpdate(drug);
            eventService.updateDrugs();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDrug(DrugEntity drug) {
        try {
            drugDataControl.delete(drug);
            eventService.updateDrugs();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDrug(DrugEntity drug) {
        try {
            drugDataControl.saveOrUpdate(drug);
            eventService.updateDrugs();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Iterable<DrugstoreEntity> getAllDrugstore() {
        return drugstoreDataControl.getAll();
    }

    @Override
    public boolean addDrugstore(DrugstoreEntity drugstore) {
        try {
            drugstoreDataControl.saveOrUpdate(drugstore);
            eventService.updateDrugstores();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDrugstore(DrugstoreEntity drugstore) {
        try {
            drugstoreDataControl.delete(drugstore);
            eventService.updateDrugstores();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDrugstore(DrugstoreEntity drugstore) {
        try {
            drugstoreDataControl.saveOrUpdate(drugstore);
            eventService.updateDrugstores();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Iterable<ProducerEntity> getAllProducers() {
        return producerDataControl.getAll();
    }

    @Override
    public boolean addProducer(ProducerEntity producer) {
        try {
            producerDataControl.saveOrUpdate(producer);
            eventService.updateProducers();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProducer(ProducerEntity producer) {
        try {
            producerDataControl.delete(producer);
            eventService.updateProducers();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProducer(ProducerEntity producer) {
        try {
            producerDataControl.saveOrUpdate(producer);
            eventService.updateProducers();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Iterable<PharmTerGroupEntity> getAllPharmTerGroups() {
        return pharmTerGroupDataControl.getAll();
    }

    @Override
    public boolean addPharmTerGroup(PharmTerGroupEntity pharmTerGroup) {
        try {
            pharmTerGroupDataControl.saveOrUpdate(pharmTerGroup);
            eventService.updatePharmTerGroups();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePharmTerGroup(PharmTerGroupEntity pharmTerGroup) {
        try {
            pharmTerGroupDataControl.delete(pharmTerGroup);
            eventService.updatePharmTerGroups();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePharmTerGroup(PharmTerGroupEntity pharmTerGroup) {
        try {
            pharmTerGroupDataControl.saveOrUpdate(pharmTerGroup);
            eventService.updatePharmTerGroups();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Iterable<PriceEntity> getAllPrice() {
        return priceDataControl.getAll();
    }

    @Override
    public boolean addPrice(PriceEntity price) {
        try {
            priceDataControl.saveOrUpdate(price);
            eventService.updatePrices();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePrice(PriceEntity price) {
        try {
            priceDataControl.delete(price);
            eventService.updatePrices();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePrice(PriceEntity price) {
        try {
            priceDataControl.saveOrUpdate(price);
            eventService.updatePrices();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String exportFromDB(FormatType format) throws ExportException {
        return exporter.export(format,true);
    }

    @Override
    public void importInDB(String data, FormatType format) throws ImportException {
        importer._import(data,format);
    }

    @Override
    public void addEventListener(EventListener listener) {
        eventService.addRmiListener(listener);
    }

    @Override
    public void removeEventListener(EventListener listener) {
        eventService.removeRmiListener(listener);
    }
}
