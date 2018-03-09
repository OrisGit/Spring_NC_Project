package com.nc.netcracker_project.server.controllers;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.import_export.ExportException;
import com.nc.netcracker_project.server.services.import_export.FormatType;
import com.nc.netcracker_project.server.services.import_export.ImportException;

import java.rmi.Remote;

public interface RMIController{
    Iterable<DrugEntity> getAllDrug();
    boolean addDrug(DrugEntity drug);
    boolean deleteDrug(DrugEntity drug);
    boolean updateDrug(DrugEntity drug);

    Iterable<DrugstoreEntity> getAllDrugstore();
    boolean addDrugstore(DrugstoreEntity drugstore);
    boolean deleteDrugstore(DrugstoreEntity drugstore);
    boolean updateDrugstore(DrugstoreEntity drugstore);

    Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect();
    boolean addPharmachologicEffect(PharmachologicEffectEntity pEffect);
    boolean deletePharmachologicEffect(PharmachologicEffectEntity pEffect);
    boolean updatePharmachologicEffect(PharmachologicEffectEntity pEffect);

    Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect();
    boolean addTherapeuticEffect(TherapeuticEffectEntity tEffect);
    boolean deleteTherapeuticEffect(TherapeuticEffectEntity tEffect);
    boolean updateTherapeuticEffect(TherapeuticEffectEntity tEffect);

    Iterable<PriceEntity> getAllPrice();
    boolean addPrice(PriceEntity price);
    boolean deletePrice(PriceEntity price);
    boolean updatePrice(PriceEntity price);

    String exportFromDB(FormatType format) throws ExportException;
    void importInDB(String data, FormatType format) throws ImportException;
}
