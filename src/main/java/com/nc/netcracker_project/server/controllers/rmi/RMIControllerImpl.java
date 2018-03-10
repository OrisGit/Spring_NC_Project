package com.nc.netcracker_project.server.controllers.rmi;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.DataControl;
import com.nc.netcracker_project.server.services.import_export.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

@Component
public class RMIControllerImpl implements RMIController{

    private static final Logger LOG = Logger.getLogger(RMIControllerImpl.class);

    private DataControl dataControl;
    private Importer importer;
    private Exporter exporter;
    private Set<EventListener> listeners = new HashSet<>();

    @Autowired
    public RMIControllerImpl(DataControl dataControl, Importer importer, Exporter exporter) {
        this.dataControl = dataControl;
        this.importer = importer;
        this.exporter = exporter;
    }

    @Override
    public Iterable<DrugEntity> getAllDrug() {
        return dataControl.getAllDrug();
    }

    @Override
    public boolean addDrug(DrugEntity drug) {
        return dataControl.saveDrug(drug);
    }

    @Override
    public boolean deleteDrug(DrugEntity drug) {
        return dataControl.deleteDrug(drug);
    }

    @Override
    public boolean updateDrug(DrugEntity drug) {
        return dataControl.updateDrug(drug);
    }

    @Override
    public Iterable<DrugstoreEntity> getAllDrugstore() {
        return dataControl.getAllDrugstore();
    }

    @Override
    public boolean addDrugstore(DrugstoreEntity drugstore) {
        boolean result = dataControl.saveDrugstore(drugstore);
        for (EventListener listener:listeners) {
            try {
                listener.updateDrustores();
            } catch (RemoteException e) {
                LOG.error("Remote update drugstore listener exception: "+e);
            }
        }
        return result;
    }

    @Override
    public boolean deleteDrugstore(DrugstoreEntity drugstore) {
        return dataControl.deleteDrugstore(drugstore);
    }

    @Override
    public boolean updateDrugstore(DrugstoreEntity drugstore) {
        return dataControl.updateDrugstore(drugstore);
    }

    @Override
    public Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect() {
        return dataControl.getAllPharmachologicEffect();
    }

    @Override
    public boolean addPharmachologicEffect(PharmachologicEffectEntity pEffect) {
        return dataControl.savePharmachologicEffect(pEffect);
    }

    @Override
    public boolean deletePharmachologicEffect(PharmachologicEffectEntity pEffect) {
        return dataControl.savePharmachologicEffect(pEffect);
    }

    @Override
    public boolean updatePharmachologicEffect(PharmachologicEffectEntity pEffect) {
        return dataControl.updatePharmachologicEffect(pEffect);
    }

    @Override
    public Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect() {
        return dataControl.getAllTherapeuticEffect();
    }

    @Override
    public boolean addTherapeuticEffect(TherapeuticEffectEntity tEffect) {
        return dataControl.saveTherapeuticEffect(tEffect);
    }

    @Override
    public boolean deleteTherapeuticEffect(TherapeuticEffectEntity tEffect) {
        return dataControl.deleteTherapeuticEffect(tEffect);
    }

    @Override
    public boolean updateTherapeuticEffect(TherapeuticEffectEntity tEffect) {
        return dataControl.updateTherapeuticEffect(tEffect);
    }

    @Override
    public Iterable<PriceEntity> getAllPrice() {
        return dataControl.getAllPrice();
    }

    @Override
    public boolean addPrice(PriceEntity price) {
        return dataControl.savePrice(price);
    }

    @Override
    public boolean deletePrice(PriceEntity price) {
        return dataControl.deletePrice(price);
    }

    @Override
    public boolean updatePrice(PriceEntity price) {
        return dataControl.updatePrice(price);
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
        try {
            LOG.info("Added event listener with UUID: "+listener.getId().toString());
        } catch (RemoteException e) {
            LOG.error("Remote error: "+e);
        }
        listeners.add(listener);
    }

    @Override
    public void deleteEventListener(EventListener listener) {
        throw new NotImplementedException();
    }
}
