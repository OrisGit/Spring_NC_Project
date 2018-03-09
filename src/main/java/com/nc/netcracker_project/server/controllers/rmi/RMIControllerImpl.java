package com.nc.netcracker_project.server.controllers.rmi;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.DataControl;
import com.nc.netcracker_project.server.services.import_export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RMIControllerImpl implements RMIController{

    private DataControl dataControl;
    private Importer importer;
    private Exporter exporter;

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
        return dataControl.saveDrugstore(drugstore);
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
}
