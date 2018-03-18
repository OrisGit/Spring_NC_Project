package com.nc.netcracker_project.server.controllers.rmi;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.event_service.EventListener;
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

    Iterable<ProducerEntity> getAllProducers() throws RemoteException;
    boolean addProducer(ProducerEntity pEffect) throws RemoteException;
    boolean deleteProducer(ProducerEntity pEffect) throws RemoteException;
    boolean updateProducer(ProducerEntity pEffect) throws RemoteException;

    Iterable<PharmTerGroupEntity> getAllPharmTerGroups()throws RemoteException;
    boolean addPharmTerGroup(PharmTerGroupEntity tEffect)throws RemoteException;
    boolean deletePharmTerGroup(PharmTerGroupEntity tEffect)throws RemoteException;
    boolean updatePharmTerGroup(PharmTerGroupEntity tEffect)throws RemoteException;

    Iterable<PriceEntity> getAllPrice()throws RemoteException;
    boolean addPrice(PriceEntity price)throws RemoteException;
    boolean deletePrice(PriceEntity price)throws RemoteException;
    boolean updatePrice(PriceEntity price)throws RemoteException;

    String exportFromDB(FormatType format) throws ExportException, RemoteException;
    void importInDB(String data, FormatType format) throws ImportException, RemoteException;

    void addEventListener(EventListener listener) throws RemoteException;
    void removeEventListener(EventListener listener) throws RemoteException;
}
