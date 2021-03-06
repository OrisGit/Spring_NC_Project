package com.nc.netcracker_project.desktop_rmi_client.util;

import com.nc.netcracker_project.desktop_rmi_client.entity.Drug;
import com.nc.netcracker_project.desktop_rmi_client.entity.Drugstore;
import com.nc.netcracker_project.desktop_rmi_client.entity.Price;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;

public class TableInitializer {
    public static void initializeDrugTable(TableView tableDrugs) {
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

    public static void initializeDrugstoreTable(TableView tableDrugstores) {
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

    public static void initializePriceTable(TableView tablePrices) {
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
}
