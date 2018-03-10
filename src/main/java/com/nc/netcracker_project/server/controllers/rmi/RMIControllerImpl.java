package com.nc.netcracker_project.server.controllers.rmi;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.DataControl;
import com.nc.netcracker_project.server.services.event_service.EventListener;
import com.nc.netcracker_project.server.services.event_service.EventService;
import com.nc.netcracker_project.server.services.import_export.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class RMIControllerImpl implements RMIController{

    private static final Logger LOG = Logger.getLogger(RMIControllerImpl.class);

    private DataControl dataControl;
    private Importer importer;
    private Exporter exporter;
    private EventService eventService;
    private Set<EventListener> listeners = new HashSet<>();

    @Autowired
    public RMIControllerImpl(DataControl dataControl, Importer importer, Exporter exporter, EventService eventService) {
        this.dataControl = dataControl;
        this.importer = importer;
        this.exporter = exporter;
        this.eventService = eventService;
    }

    @Override
    public Iterable<DrugEntity> getAllDrug() {
        return dataControl.getAllDrug();
    }

    @Override
    public boolean addDrug(DrugEntity drug) {
        boolean result = dataControl.saveDrug(drug);
        if(result)
            eventService.updateDrugs();
        return result;
    }

    @Override
    public boolean deleteDrug(DrugEntity drug) {
        boolean result = dataControl.deleteDrug(drug);
        if(result)
            eventService.updateDrugs();
        return result;
    }

    @Override
    public boolean updateDrug(DrugEntity drug) {
        boolean result = dataControl.updateDrug(drug);
        if(result)
            eventService.updateDrugs();
        return result;
    }

    @Override
    public Iterable<DrugstoreEntity> getAllDrugstore() {
        return dataControl.getAllDrugstore();
    }

    @Override
    public boolean addDrugstore(DrugstoreEntity drugstore) {
        boolean result = dataControl.saveDrugstore(drugstore);
        if(result)
            eventService.updateDrugstores();
        return result;
    }

    @Override
    public boolean deleteDrugstore(DrugstoreEntity drugstore) {
        boolean result = dataControl.deleteDrugstore(drugstore);
        if(result)
            eventService.updateDrugstores();
        return result;
    }

    @Override
    public boolean updateDrugstore(DrugstoreEntity drugstore) {
        boolean result = dataControl.updateDrugstore(drugstore);
        if(result)
            eventService.updateDrugstores();
        return result;
    }

    @Override
    public Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect() {
        return dataControl.getAllPharmachologicEffect();
    }

    @Override
    public boolean addPharmachologicEffect(PharmachologicEffectEntity pEffect) {
        boolean result = dataControl.savePharmachologicEffect(pEffect);
        if(result)
            eventService.updatePharmachologicEffects();
        return result;
    }

    @Override
    public boolean deletePharmachologicEffect(PharmachologicEffectEntity pEffect) {
        boolean result = dataControl.deletePharmachologicEffect(pEffect);
        if(result)
            eventService.updatePharmachologicEffects();
        return result;
    }

    @Override
    public boolean updatePharmachologicEffect(PharmachologicEffectEntity pEffect) {
        boolean result = dataControl.updatePharmachologicEffect(pEffect);
        if(result)
            eventService.updatePharmachologicEffects();
        return result;
    }

    @Override
    public Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect() {
        return dataControl.getAllTherapeuticEffect();
    }

    @Override
    public boolean addTherapeuticEffect(TherapeuticEffectEntity tEffect) {
        boolean result = dataControl.saveTherapeuticEffect(tEffect);
        if(result)
            eventService.updateTherapheuticEffects();
        return result;
    }

    @Override
    public boolean deleteTherapeuticEffect(TherapeuticEffectEntity tEffect) {
        boolean result = dataControl.deleteTherapeuticEffect(tEffect);
        if(result)
            eventService.updateTherapheuticEffects();
        return result;
    }

    @Override
    public boolean updateTherapeuticEffect(TherapeuticEffectEntity tEffect) {
        boolean result = dataControl.updateTherapeuticEffect(tEffect);
        if(result)
            eventService.updateTherapheuticEffects();
        return result;
    }

    @Override
    public Iterable<PriceEntity> getAllPrice() {
        return dataControl.getAllPrice();
    }

    @Override
    public boolean addPrice(PriceEntity price) {
        boolean result = dataControl.savePrice(price);
        if(result)
            eventService.updatePrices();
        return result;
    }

    @Override
    public boolean deletePrice(PriceEntity price) {
        boolean result = dataControl.deletePrice(price);
        if(result)
            eventService.updatePrices();
        return result;
    }

    @Override
    public boolean updatePrice(PriceEntity price) {
        boolean result = dataControl.updatePrice(price);
        if(result)
            eventService.updatePrices();
        return result;
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
