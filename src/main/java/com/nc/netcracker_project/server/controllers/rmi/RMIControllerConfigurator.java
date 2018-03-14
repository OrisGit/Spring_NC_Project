package com.nc.netcracker_project.server.controllers.rmi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@Component
public class RMIControllerConfigurator {

    private final Logger LOG = Logger.getLogger(RMIControllerConfigurator.class);

    private @Value("${rmi.controller.name}") String name;
    private @Value("${rmi.controller.port}") int port;
    @Autowired
    private RMIController implementation;

    @PostConstruct
    public void config() {
        LOG.info("Starting registry...");
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(port);

            Remote stub = null;
            try {
                stub = UnicastRemoteObject.exportObject(implementation, 0);

                LOG.info("Binding service...");
                try {
                    registry.rebind(name,stub);
                } catch (RemoteException e) {
                    LOG.error("Failed");
                    System.exit(0);
                }
                LOG.info("OK");

            } catch (RemoteException e) {
                LOG.error("Export filed");
            }

        } catch (RemoteException e) {
            LOG.error("Failed");
        }
        LOG.info("OK");
    }
}
