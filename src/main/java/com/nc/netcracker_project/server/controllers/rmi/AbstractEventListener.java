package com.nc.netcracker_project.server.controllers.rmi;

import com.nc.netcracker_project.desktop_rmi_client.util.Action;

import java.io.Serializable;
import java.rmi.Remote;
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

}
