package com.nc.netcracker_project.server.services.event_service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface EventListener extends Remote {
    UUID getId() throws RemoteException;

    void updateDrugs() throws RemoteException;

    void updateDrugstores() throws RemoteException;

    void updatePrices() throws RemoteException;

    void updateTherapheuticEffects() throws RemoteException;

    void updatePharmachologicEffects() throws RemoteException;
}
