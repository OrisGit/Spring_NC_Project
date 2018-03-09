package com.nc.netcracker_project.server;

import com.nc.netcracker_project.server.controllers.RMIController;
import com.nc.netcracker_project.server.controllers.RMIControllerConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SpringBootApplication
public class NetcrackerProjectApplication {



	public static void main(String[] args){
		SpringApplication.run(NetcrackerProjectApplication.class, args);
	}
}
