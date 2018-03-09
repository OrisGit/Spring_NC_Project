package com.nc.netcracker_project.desktop_rmi_client;

import com.nc.netcracker_project.desktop_rmi_client.view_controllers.ViewFX;
import com.nc.netcracker_project.server.controllers.RMIController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static com.nc.netcracker_project.desktop_rmi_client.util.PropertyLoader.getProperty;

public class ClientStart extends Application {

    private final Logger LOG = Logger.getLogger(ClientStart.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/desktop_view/main_window.fxml"));
        try {
            root = loader.load();
            primaryStage.setScene(new Scene(root));
            ViewFX.setStage(primaryStage);
            primaryStage.setOnCloseRequest(event -> closeApplication());
            primaryStage.show();
            ViewFX.setController(getRMIController());
        } catch (NotBoundException e){
            LOG.error("Сервер не вернул запрашиваемый объект.");
            showAllert("Сервер не вернул запрашиваемый объект. Приложение будет закрыто...");
            closeApplication();
        } catch (RemoteException e){
            LOG.error("Не установлено соединение с сервером.");
            showAllert("Не установлено соединение с сервером. Приложение будет закрыто...");
            closeApplication();
        } catch (IOException e) {
            LOG.error("Не найден ресурс: /desktop_view/main_window.fxml : " + e);
            showAllert("Не найден файл ресурса. Приложение будет закрыто.");
            closeApplication();
        }
    }

    private void showAllert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeApplication(){
        Platform.exit();
        System.exit(0);
    }

    private RMIController getRMIController() throws RemoteException, NotBoundException {
        String host = getProperty("server.rmi.host");
        int port = Integer.valueOf(getProperty("server.rmi.port"));
        String path = getProperty("server.rmi.path");

        Registry registry = LocateRegistry.getRegistry(host, port);
        LOG.info(String.format("Connect to host: %s, port: %s Ok",host,port));

        RMIController controller = (RMIController)registry.lookup(path);
        LOG.info("Lookup "+path+" Ok");

        return controller;
    }

}
