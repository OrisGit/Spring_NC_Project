package com.nc.netcracker_project.server.services.event_service;

import java.rmi.RemoteException;
import java.util.UUID;

public abstract class AbstractEventListener implements EventListener {
    private UUID id;

    public AbstractEventListener(){
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() throws RemoteException {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        AbstractEventListener that = (AbstractEventListener) object;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
