package com.nc.netcracker_project.desktop_rmi_client.view_controllers;

import com.nc.netcracker_project.desktop_rmi_client.entity.Drug;
import com.nc.netcracker_project.desktop_rmi_client.entity.Drugstore;
import com.nc.netcracker_project.desktop_rmi_client.entity.Price;
import com.nc.netcracker_project.desktop_rmi_client.util.DialogFactory;
import com.nc.netcracker_project.desktop_rmi_client.util.EventListenerImpl;
import com.nc.netcracker_project.desktop_rmi_client.util.Mapper;
import com.nc.netcracker_project.server.services.event_service.EventListener;
import com.nc.netcracker_project.server.controllers.rmi.RMIController;
import com.nc.netcracker_project.server.model.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static com.nc.netcracker_project.desktop_rmi_client.util.PropertyLoader.getProperty;
import static com.nc.netcracker_project.desktop_rmi_client.util.TableInitializer.initializeDrugTable;
import static com.nc.netcracker_project.desktop_rmi_client.util.TableInitializer.initializeDrugstoreTable;
import static com.nc.netcracker_project.desktop_rmi_client.util.TableInitializer.initializePriceTable;

public class ViewFX implements Initializable{

    //region Поля
    private static final Logger LOG = Logger.getLogger(ViewFX.class);

    private Stage stage;
    private RMIController controller;
    private EventListener eventListener;

    @FXML
    private TableView tableDrugs;
    @FXML
    private TableView tableDrugstores;
    @FXML
    private TableView tablePrices;

    private DialogFactory dialogFactory;


    private ObservableList<Drug> drugs;
    private ObservableList<Drugstore> drugstores;
    private ObservableList<Price> prices;
    private ObservableList<ProducerEntity> producers;
    private ObservableList<PharmTerGroupEntity> pharmTerGroups;
    //endregion

    //region Вывод всех записей в БД
    private void displayDrugs() {
        LOG.info("Display drugs");
        try {
            display((List)controller.getAllDrug(),drugs,getProperty("error.client.display.drug"));
        } catch (RemoteException e) {
            dialogFactory.displayError(e.getMessage());
        }
    }

    private void displayDrugstores() {
        try {
            display((List)controller.getAllDrugstore(),drugstores,getProperty("error.client.display.drugstores"));
        } catch (RemoteException e) {
            dialogFactory.displayError(e.getMessage());
        }
    }

    private void displayPrices() {
        try {
            display((List)controller.getAllPrice(),prices,getProperty("error.client.display.price"));
        } catch (RemoteException e) {
            dialogFactory.displayError(e.getMessage());
        }
    }

    private void displayProducers() {
        try {
            producers.clear();
            List<ProducerEntity> list = (List<ProducerEntity>)controller.getAllProducers();
            producers.addAll(list);
        } catch (RemoteException e) {
            dialogFactory.displayError(e.getMessage());
        }
    }

    private void displayPharmTerGroups() {
        try {
            pharmTerGroups.clear();
            List<PharmTerGroupEntity> list = (List<PharmTerGroupEntity>)controller.getAllPharmTerGroups();
            pharmTerGroups.addAll(list);
        } catch (RemoteException e) {
            dialogFactory.displayError(e.getMessage());
        }
    }

    private void display(List request, ObservableList table, String errorMessage){
        try {
            LinkedList drugList = Mapper.fromAll(request);
            table.clear();
            table.addAll(drugList);
        }catch (Exception e){
            dialogFactory.displayError(errorMessage);
        }
    }

    public void updateAll(){
        displayPharmTerGroups();
        displayProducers();
        displayDrugs();
        displayDrugstores();
        displayPrices();
    }

    //endregion

    //region Добавление записи
    private void addProducer() {
        Dialog<ProducerEntity> dialog = dialogFactory.getAddProducerDialog();
        Optional<ProducerEntity> result = dialog.showAndWait();
        result.ifPresent(producerEntity -> {
            try {
                if(!controller.addProducer(producerEntity)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы производителей");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        });
    }

    private void addPharmTerGroup() {
        Dialog<PharmTerGroupEntity> dialog = dialogFactory.getAddPharmTerGroupDialog();
        Optional<PharmTerGroupEntity> result = dialog.showAndWait();
        result.ifPresent(pharmTerGroup -> {
            try {
                if(!controller.addPharmTerGroup(pharmTerGroup)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы фармакотерапевтичесих групп");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        });
    }

    @FXML
    public void addDrug() {

        Dialog<DrugEntity> dialog = dialogFactory.getAddDrugDialog();

        Optional<DrugEntity> result = dialog.showAndWait();

        result.ifPresent(drugEntity -> {
            try {
                if(!controller.addDrug(drugEntity)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы лекарств");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        });
    }

    @FXML
    public void addDrugstore() {

        Dialog<DrugstoreEntity> dialog = dialogFactory.getAddDrugstoreDialog();

        Optional<DrugstoreEntity> result = dialog.showAndWait();

        result.ifPresent(drugstoreEntity -> {
            try {
                if(!controller.addDrugstore(drugstoreEntity)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы аптек");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        });
    }

    @FXML
    public void addPrice() {
        Dialog<PriceEntity> dialog = dialogFactory.getAddPriceDialog();

        Optional<PriceEntity> result = dialog.showAndWait();

        result.ifPresent(priceEntity -> {
            try {
                if(!controller.addPrice(priceEntity)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы цен");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        });
    }
    //endregion

    //region Обновление записи
    @FXML
    public void updateDrug() {
        int indexSelectElement = tableDrugs.getSelectionModel().getSelectedIndex();

        Dialog<DrugEntity> dialog = dialogFactory.getUpdDrugDialog(indexSelectElement);
        if(dialog!=null){

            Optional<DrugEntity> result = dialog.showAndWait();

            result.ifPresent(drugEntity -> {
                DrugEntity drug = result.get();
                drug.setId(drugs.get(indexSelectElement).getId());
                try {
                    if(!controller.updateDrug(drug)){
                        dialogFactory.displayError("Произошла ошибка при обновлении таблицы лекарств");
                    }
                } catch (RemoteException e) {
                    dialogFactory.displayError(e.getMessage());
                }
            });
        } else {
            dialogFactory.displayError(getProperty("error.client.update.notSelectIndex"));
        }
    }

    @FXML
    public void updateDrugstore() {
        int indexSelectElement = tableDrugstores.getSelectionModel().getSelectedIndex();

        Dialog<DrugstoreEntity> drugstoreDialog = dialogFactory.getUpdDrugstoreDialog(indexSelectElement);

        if(drugstoreDialog!=null){

            Optional<DrugstoreEntity> result = drugstoreDialog.showAndWait();

            result.ifPresent(drugstoreEntity -> {
                DrugstoreEntity drugstore = result.get();
                drugstore.setId(drugstores.get(indexSelectElement).getId());
                try {
                    if(!controller.updateDrugstore(drugstore)){
                        dialogFactory.displayError("Произошла ошибка при обновлении таблицы аптек");
                    }
                } catch (RemoteException e) {
                    dialogFactory.displayError(e.getMessage());
                }
            });
        } else {
            dialogFactory.displayError(getProperty("error.client.update.notSelectIndex"));
        }
    }

    @FXML
    public void updatePrice() {
        int indexSelectElement = tablePrices.getSelectionModel().getSelectedIndex();

        Dialog<PriceEntity> priceDialog = dialogFactory.getUpdPriceDialog(indexSelectElement);

        if(priceDialog!=null){

            Optional<PriceEntity> result = priceDialog.showAndWait();

            result.ifPresent(priceEntity -> {
                PriceEntity price = result.get();
                try {
                    if(!controller.updatePrice(price)){
                        dialogFactory.displayError("Произошла ошибка при обновлении таблицы цен");
                    }
                } catch (RemoteException e) {
                    dialogFactory.displayError(e.getMessage());
                }
            });
        } else {
            dialogFactory.displayError(getProperty("error.client.update.notSelectIndex"));
        }
    }
    //endregion

    //region Удаление записи
    @FXML
    public void deleteDrug() {
        int index = tableDrugs.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            DrugEntity drugEntity = drugs.get(index).getDrugEntity();
            try {
                if(!controller.deleteDrug(drugEntity)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы лекарств");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        }
    }

    @FXML
    public void deleteDrugstore() {
        int index = tableDrugstores.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            DrugstoreEntity drugstoreEntity = drugstores.get(index).getDrugstoreEntity();
            try {
                if(!controller.deleteDrugstore(drugstoreEntity)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы аптек");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        }
    }

    @FXML
    public void deletePrice() {
        int index = tablePrices.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            PriceEntity priceEntity = prices.get(index).getPriceEntity();
            try {
                if(!controller.deletePrice(priceEntity)){
                    dialogFactory.displayError("Произошла ошибка при обновлении таблицы цен");
                }
            } catch (RemoteException e) {
                dialogFactory.displayError(e.getMessage());
            }
        }
    }
    //endregion

    //region Экспорт/импорт
    @FXML
    public void showExportWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/desktop_view/export_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage exportWindow = new Stage();
            ExportWindowController.setController(controller);
            ExportWindowController.setDialogFactory(dialogFactory);
            ExportWindowController.setStage(exportWindow);
            exportWindow.setScene(new Scene(root));
            exportWindow.initModality(Modality.WINDOW_MODAL);
            exportWindow.initOwner(stage);
            exportWindow.setTitle("Экспорт");
            exportWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showImportWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/desktop_view/import_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage importWindow = new Stage();
            ImportWindowController.setStage(importWindow);
            ImportWindowController.setDialogFactory(dialogFactory);
            ImportWindowController.setController(controller);
            importWindow.setScene(new Scene(root));
            importWindow.initModality(Modality.WINDOW_MODAL);
            importWindow.initOwner(stage);
            importWindow.setTitle("Импорт");
            importWindow.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region Setters
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setController(RMIController controller) {
        this.controller = controller;
    }

    public void disableListener() {
        try {
            controller.removeEventListener(eventListener);
        } catch (RemoteException e) {
            LOG.error(e);
        }
    }

    public void enableListener(){
        try {
            controller.addEventListener((EventListener)UnicastRemoteObject.exportObject(eventListener, 0));
        } catch (RemoteException e) {
            LOG.info(e);
        }
    }
    //endregion

    //region Initialize
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeDrugstoreTable(tableDrugstores);
        initializeDrugTable(tableDrugs);
        initializePriceTable(tablePrices);
        drugs = tableDrugs.getItems();
        drugstores = tableDrugstores.getItems();
        prices = tablePrices.getItems();
        producers = FXCollections.observableList(new LinkedList<>());
        pharmTerGroups = FXCollections.observableList(new LinkedList<>());
        eventListener = new EventListenerImpl( this::displayDrugs,this::displayDrugstores,
                this::displayPrices, this::displayPharmTerGroups,this::displayProducers);
        dialogFactory = new DialogFactory(pharmTerGroups, producers,drugs,drugstores,prices,
                this::addPharmTerGroup,this::addProducer);
    }
    //endregion
}
