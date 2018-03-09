package com.nc.netcracker_project.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@Configuration
public class RMIControllerConfigurator {

    private @Value("${rmi.controller.name}") String name;
    private @Value("${rmi.controller.port}") int port;

    @Bean
    RmiServiceExporter exporter(RMIController implementation) {
        Class<RMIController> serviceInterface = RMIController.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(name);
        exporter.setRegistryPort(port);
        return exporter;
    }
}
