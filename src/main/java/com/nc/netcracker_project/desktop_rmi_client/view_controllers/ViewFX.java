package com.nc.netcracker_project.desktop_rmi_client.view_controllers;

import com.nc.netcracker_project.desktop_rmi_client.entity.Drug;
import com.nc.netcracker_project.desktop_rmi_client.entity.Drugstore;
import com.nc.netcracker_project.desktop_rmi_client.entity.Price;
import com.nc.netcracker_project.desktop_rmi_client.util.DialogFactory;
import com.nc.netcracker_project.desktop_rmi_client.util.Mapper;
import com.nc.netcracker_project.server.controllers.RMIController;
import com.nc.netcracker_project.server.model.entities.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.nc.netcracker_project.desktop_rmi_client.util.PropertyLoader.getProperty;

public class ViewFX implements Initializable{

    //region Поля
    private static Stage STAGE;
    private static RMIController controller;

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
    private List<PharmachologicEffectEntity> pharmachologicEffects;
    private List<TherapeuticEffectEntity> therapeuticEffects;
    //endregion

    //region Вывод всех записей в БД
    public void displayDrugs() {
        display((List)controller.getAllDrug(),drugs,getProperty("error.client.display.drug"));
    }

    public void displayDrugstores() {
        display((List)controller.getAllDrugstore(),drugstores,getProperty("error.client.display.drugstores"));
    }

    public void displayPrices() {
        display((List)controller.getAllPrice(),prices,getProperty("error.client.display.price"));
    }

    public void displayPharmacologicEffects() {
        pharmachologicEffects = (List<PharmachologicEffectEntity>)controller.getAllPharmachologicEffect();
    }

    public void displayTherapeuticEffects() {
        therapeuticEffects = (List<TherapeuticEffectEntity>)controller.getAllTherapeuticEffect();
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

    //endregion

    //region Добавление записи
    public void addPharmacologicEffect() {
        Dialog<PharmachologicEffectEntity> dialog = dialogFactory.getAddPEffectDialog();
        Optional<PharmachologicEffectEntity> result = dialog.showAndWait();
        result.ifPresent(pharmachologicEffectEntity -> controller.addPharmachologicEffect(pharmachologicEffectEntity));
    }

    public void addTherapeuticEffect() {
        Dialog<TherapeuticEffectEntity> dialog = dialogFactory.getAddTEffectDialog();
        Optional<TherapeuticEffectEntity> result = dialog.showAndWait();
        result.ifPresent(therapeuticEffectEntity -> controller.addTherapeuticEffect(therapeuticEffectEntity));
    }

    @FXML
    public void addDrug() {

        Dialog<DrugEntity> dialog = dialogFactory.getAddDrugDialog();

        Optional<DrugEntity> result = dialog.showAndWait();

        result.ifPresent(drugEntity -> controller.addDrug(drugEntity));
    }

    @FXML
    public void addDrugstore() {

        Dialog<DrugstoreEntity> dialog = dialogFactory.getAddDrugstoreDialog();

        Optional<DrugstoreEntity> result = dialog.showAndWait();

        result.ifPresent(drugstoreEntity -> controller.addDrugstore(drugstoreEntity));
    }

    @FXML
    public void addPrice() {
        Dialog<PriceEntity> dialog = dialogFactory.getAddPriceDialog();

        Optional<PriceEntity> result = dialog.showAndWait();

        result.ifPresent(priceEntity -> controller.addPrice(priceEntity));
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
                controller.updateDrug(drug);
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
                controller.addDrugstore(drugstore);
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
                controller.updatePrice(price);
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
            controller.deleteDrug(drugEntity);
        }
    }

    @FXML
    public void deleteDrugstore() {
        int index = tableDrugstores.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            DrugstoreEntity drugstoreEntity = drugstores.get(index).getDrugstoreEntity();
            controller.deleteDrugstore(drugstoreEntity);
        }
    }

    @FXML
    public void deletePrice() {
        int index = tablePrices.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            PriceEntity priceEntity = prices.get(index).getPriceEntity();
            controller.deletePrice(priceEntity);
        }
    }
    //endregion

    //region Экспорт/импорт
    @FXML
    public void showExportWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("desktop_view/export_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage exportWindow = new Stage();
            ExportWindowController.setController(controller);
            ExportWindowController.setDialogFactory(dialogFactory);
            ExportWindowController.setStage(exportWindow);
            exportWindow.setScene(new Scene(root));
            exportWindow.initModality(Modality.WINDOW_MODAL);
            exportWindow.initOwner(STAGE);
            exportWindow.setTitle("Экспорт");
            exportWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showImportWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("desktop_view/import_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage importWindow = new Stage();
            ImportWindowController.setStage(importWindow);
            ImportWindowController.setDialogFactory(dialogFactory);
            ImportWindowController.setController(controller);
            importWindow.setScene(new Scene(root));
            importWindow.initModality(Modality.WINDOW_MODAL);
            importWindow.initOwner(STAGE);
            importWindow.setTitle("Импорт");
            importWindow.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region Setters
    public static void setStage(Stage stage) {
        ViewFX.STAGE = stage;
    }

    public static void setController(RMIController controller) {
        ViewFX.controller = controller;
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
        pharmachologicEffects = new ArrayList<>();
        therapeuticEffects = new ArrayList<>();
        dialogFactory = new DialogFactory(therapeuticEffects,pharmachologicEffects,drugs,drugstores,prices,
                this::addTherapeuticEffect,this::addPharmacologicEffect,tableDrugs,tableDrugstores);
    }

    //region Инициализация таблиц
    private void initializeDrugTable(TableView tableDrugs) {
        tableDrugs.getColumns().clear();
        TableColumn<Drug, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());

        TableColumn<Drug, String> formCol = new TableColumn<>("Форма выпуска");
        formCol.setCellValueFactory(
                cellData -> cellData.getValue().releaseFormProperty());

        TableColumn<Drug, String> manufacturerCol = new TableColumn<>("Производитель");
        manufacturerCol.setCellValueFactory(
                cellData -> cellData.getValue().manufacturerProperty());

        TableColumn<Drug, String> ingredientCol = new TableColumn<>("Активный ингредиент");
        ingredientCol.setCellValueFactory(
                cellData -> cellData.getValue().activeIngredientProperty());

        TableColumn<Drug, String> pEffectCol = new TableColumn<>("Фармакологический эффект");
        pEffectCol.setCellValueFactory(
                cellData -> cellData.getValue().pharmacologicalEffectProperty());

        TableColumn<Drug, String> tEffectCol = new TableColumn<>("Терапевтический эффект");
        tEffectCol.setCellValueFactory(
                cellData -> cellData.getValue().therapeuticEffectProperty());

        TableColumn<Drug, String> descCol = new TableColumn<>("Описание");
        descCol.setCellValueFactory(
                cellData -> cellData.getValue().descriptionProperty());

        tableDrugs.getColumns().addAll(nameCol, formCol, manufacturerCol, ingredientCol, pEffectCol, tEffectCol, descCol);

    }

    private void initializeDrugstoreTable(TableView tableDrugstores) {
        tableDrugstores.getColumns().clear();

        TableColumn<Drugstore, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());

        TableColumn<Drugstore, String> districtCol = new TableColumn<>("Район");
        districtCol.setCellValueFactory(
                cellData -> cellData.getValue().districtProperty());

        TableColumn<Drugstore, String> addressCol = new TableColumn<>("Адрес");
        addressCol.setCellValueFactory(
                cellData -> cellData.getValue().addressProperty());

        TableColumn<Drugstore, String> phoneCol = new TableColumn<>("Телефон");
        phoneCol.setCellValueFactory(
                cellData -> cellData.getValue().phoneProperty());

        TableColumn<Drugstore, String> hoursCol = new TableColumn<>("Часы работы");
        hoursCol.setCellValueFactory(
                cellData -> cellData.getValue().workingHoursProperty());

        TableColumn<Drugstore, Boolean> boolCol = new TableColumn<>("Круглосуточная");
        boolCol.setCellValueFactory(
                cellData -> cellData.getValue().isRoundTheClockProperty());
        boolCol.setCellFactory(
                column -> {
                    CheckBoxTableCell<Drugstore, Boolean> c = new CheckBoxTableCell<>();
                    c.setDisable(true);
                    return c;
                });

        tableDrugstores.getColumns().addAll(nameCol, districtCol, addressCol, phoneCol, hoursCol, boolCol);
    }

    private void initializePriceTable(TableView tablePrices) {
        tablePrices.getColumns().clear();

        TableColumn<Price, String> drugCol = new TableColumn<>("Препарат");
        drugCol.setCellValueFactory(
                cellData -> cellData.getValue().drugProperty());

        TableColumn<Price, String> drugstoreCol = new TableColumn<>("Аптека");
        drugstoreCol.setCellValueFactory(
                cellData -> cellData.getValue().drugstoreProperty());

        TableColumn<Price, String> costCol = new TableColumn<>("Цена");
        costCol.setCellValueFactory(
                cellData -> cellData.getValue().costProperty().asString());

        tablePrices.getColumns().addAll(drugCol, drugstoreCol, costCol);
    }

    //endregion

    //endregion
}
