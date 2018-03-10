package com.nc.netcracker_project.server.controllers.rmi;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.import_export.ExportException;
import com.nc.netcracker_project.server.services.import_export.FormatType;
import com.nc.netcracker_project.server.services.import_export.ImportException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIController extends Remote{
    Iterable<DrugEntity> getAllDrug() throws RemoteException;
    boolean addDrug(DrugEntity drug) throws RemoteException;
    boolean deleteDrug(DrugEntity drug) throws RemoteException;
    boolean updateDrug(DrugEntity drug) throws RemoteException;

    Iterable<DrugstoreEntity> getAllDrugstore() throws RemoteException;
    boolean addDrugstore(DrugstoreEntity drugstore) throws RemoteException;
    boolean deleteDrugstore(DrugstoreEntity drugstore) throws RemoteException;
    boolean updateDrugstore(DrugstoreEntity drugstore) throws RemoteException;

    Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect() throws RemoteException;
    boolean addPharmachologicEffect(PharmachologicEffectEntity pEffect) throws RemoteException;
    boolean deletePharmachologicEffect(PharmachologicEffectEntity pEffect) throws RemoteException;
    boolean updatePharmachologicEffect(PharmachologicEffectEntity pEffect) throws RemoteException;

    Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect()throws RemoteException;
    boolean addTherapeuticEffect(TherapeuticEffectEntity tEffect)throws RemoteException;
    boolean deleteTherapeuticEffect(TherapeuticEffectEntity tEffect)throws RemoteException;
    boolean updateTherapeuticEffect(TherapeuticEffectEntity tEffect)throws RemoteException;

    Iterable<PriceEntity> getAllPrice()throws RemoteException;
    boolean addPrice(PriceEntity price)throws RemoteException;
    boolean deletePrice(PriceEntity price)throws RemoteException;
    boolean updatePrice(PriceEntity price)throws RemoteException;

    String exportFromDB(FormatType format) throws ExportException, RemoteException;
    void importInDB(String data, FormatType format) throws ImportException, RemoteException;

    void addEventListener(EventListener listener) throws RemoteException;
    void deleteEventListener(EventListener listener) throws RemoteException;
}
