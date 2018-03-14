package com.nc.netcracker_project.server.services.event_service;

import java.rmi.RemoteException;

public interface EventService {
    void addRmiListener(EventListener listener);

    void removeRmiListener(EventListener listener);

    void addWebListener(EventListener listener);

    void removeWebListener(EventListener listener);

    void updateDrugs();

    void updateDrugstores();

    void updatePrices();

    void updateTherapheuticEffects();

    void updatePharmachologicEffects();
}
