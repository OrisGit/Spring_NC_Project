package com.nc.netcracker_project.server.services.event_service;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

@Service
@Scope("singleton")
public class EventServiceImpl implements EventService {

    private static final Logger LOG = Logger.getLogger(EventServiceImpl.class);

    private Set<EventListener> rmiListeners = new HashSet<>();
    private Set<EventListener> webListeners = new HashSet<>();

    @Override
    public void addRmiListener(EventListener listener){
        rmiListeners.add(listener);
    }

    @Override
    public void removeRmiListener(EventListener listener){
        rmiListeners.remove(listener);
    }

    @Override
    public void addWebListener(EventListener listener){
        webListeners.add(listener);
    }

    @Override
    public void removeWebListener(EventListener listener){
        webListeners.remove(listener);
    }

    @Override
    public void updateDrugs(){
        update(EventListener::updateDrugs);
    }

    @Override
    public void updateDrugstores(){
        update(EventListener::updateDrugstores);
    }

    @Override
    public void updatePrices(){
        update(EventListener::updatePrices);
    }

    @Override
    public void updateTherapheuticEffects(){
        update(EventListener::updateTherapheuticEffects);
    }

    @Override
    public void updatePharmachologicEffects(){
        update(EventListener::updatePharmachologicEffects);
    }

    private void update(Consumer consumer){
        for(EventListener listener : rmiListeners){
            try {
                consumer.accept(listener);
            }catch (RemoteException e){
                LOG.error(e);
            }
        }
    }


    private interface Consumer{
        void accept(EventListener listener) throws RemoteException;
    }

}
