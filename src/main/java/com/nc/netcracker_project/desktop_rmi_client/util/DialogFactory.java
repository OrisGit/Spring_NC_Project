package com.nc.netcracker_project.desktop_rmi_client.util;

import com.nc.netcracker_project.desktop_rmi_client.entity.Drug;
import com.nc.netcracker_project.desktop_rmi_client.entity.Drugstore;
import com.nc.netcracker_project.desktop_rmi_client.entity.Price;
import com.nc.netcracker_project.server.model.entities.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;

public class DialogFactory {

    private Dialog<TherapeuticEffectEntity> tEffectDialog;
    private Dialog<PharmachologicEffectEntity> pEffectDialog;
    private Dialog<DrugEntity> drugDialog;
    private Dialog<DrugstoreEntity> drugstoreDialog;
    private Dialog<PriceEntity> priceDialog;
    private Alert alertDialog;

    private List<TherapeuticEffectEntity> therapeuticEffects;
    private List<PharmachologicEffectEntity> pharmachologicEffects;
    private ObservableList<Drug> drugs;
    private ObservableList<Drugstore> drugstores;
    private ObservableList<Price> prices;
    private Action addPharmacologicEffect;
    private Action addTherapeuticEffect;
    private TableView drugsTable;
    private TableView drugstoresTable;

    public DialogFactory(List tEffects, List pEffects, ObservableList<Drug> drugs, ObservableList<Drugstore> drugstores,
                         ObservableList<Price> prices, Action addTherapeuticEffect, Action addPharmacologicEffect,
                         TableView drugsTable, TableView drugstoresTable){
        this.addPharmacologicEffect = addPharmacologicEffect;
        this.addTherapeuticEffect = addTherapeuticEffect;
        this.pharmachologicEffects = pEffects;
        this.therapeuticEffects = tEffects;
        this.drugs = drugs;
        this.drugstores = drugstores;
        this.drugsTable = drugsTable;
        this.drugstoresTable = drugstoresTable;
        this.prices = prices;
        createAlertDialog();
        createDrugDialog();
        createDrugstoreDialog();
        createPharmacologicEffectDialog();
        createPriceDialog();
        createTherapeuticEffectDialog();
    }

    public void displayError(String message) {
        alertDialog.setContentText(message);
        alertDialog.show();
    }

    private void createAlertDialog(){
        alertDialog = new Alert(Alert.AlertType.ERROR);
        alertDialog.setTitle("Ошибка");
        alertDialog.setHeaderText(null);
    }

    private void createTherapeuticEffectDialog(){
        tEffectDialog = new Dialog<>();
        tEffectDialog.setTitle("Новый терапевтический эффект");
        tEffectDialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextArea description = new TextArea();

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Описание:"), 0, 1);
        grid.add(description, 1, 1);

        tEffectDialog.getDialogPane().setContent(grid);
        tEffectDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        tEffectDialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!name.getText().isEmpty()) {
                    return new TherapeuticEffectEntity(name.getText(), description.getText());
                }
            }
            return null;
        });
    }

    private void createPharmacologicEffectDialog(){
        pEffectDialog = new Dialog<>();
        pEffectDialog.setTitle("Новый фармакологический эффект");
        pEffectDialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextArea description = new TextArea();

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Описание:"), 0, 1);
        grid.add(description, 1, 1);

        pEffectDialog.getDialogPane().setContent(grid);
        pEffectDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        pEffectDialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!name.getText().isEmpty()) {
                    return new PharmachologicEffectEntity(name.getText(), description.getText());
                }
            }
            return null;
        });
    }

    private void createDrugDialog() {
        drugDialog = new Dialog<>();
        drugDialog.setTitle("Новый препарат");
        drugDialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextField releaseForm = new TextField();
        TextField manufacturer = new TextField();
        TextField activeIngredient = new TextField();
        TextArea description = new TextArea();
        description.setPrefWidth(350);

        ListView<PharmachologicEffectEntity> pEffectSelector = new ListView<>();
        pEffectSelector.setPrefSize(300, 100);

        pEffectSelector.setCellFactory(
                new Callback<ListView<PharmachologicEffectEntity>, ListCell<PharmachologicEffectEntity>>() {
                    @Override
                    public ListCell<PharmachologicEffectEntity> call(ListView<PharmachologicEffectEntity> param) {

                        return new ListCell<PharmachologicEffectEntity>() {
                            @Override
                            public void updateItem(PharmachologicEffectEntity item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item.getName());
                                } else {
                                    setText(null);
                                }
                            }

                        };
                    }
                });

        pEffectSelector.getItems().addAll(pharmachologicEffects);

        Button addPEffectBtn = new Button("Добавить...");
        addPEffectBtn.setOnAction(event -> {
            addPharmacologicEffect.action();
            pEffectSelector.getItems().clear();
            pEffectSelector.getItems().addAll(pharmachologicEffects);
        });

        ListView<TherapeuticEffectEntity> tEffectSelector = new ListView<>();
        tEffectSelector.setPrefSize(300, 100);

        tEffectSelector.setCellFactory(
                new Callback<ListView<TherapeuticEffectEntity>, ListCell<TherapeuticEffectEntity>>() {
                    @Override
                    public ListCell<TherapeuticEffectEntity> call(ListView<TherapeuticEffectEntity> param) {

                        return new ListCell<TherapeuticEffectEntity>() {
                            @Override
                            public void updateItem(TherapeuticEffectEntity item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(String.format("%s: %s", item.getName(), item.getDescription()));
                                } else {
                                    setText(null);
                                }
                            }

                        };
                    }
                });

        tEffectSelector.getItems().addAll(therapeuticEffects);

        Button addTEffectBtn = new Button("Добавить...");
        addTEffectBtn.setOnAction(event -> {
            addTherapeuticEffect.action();
            tEffectSelector.getItems().clear();
            tEffectSelector.getItems().addAll(therapeuticEffects);
        });

        name.setId("name");
        releaseForm.setId("releaseForm");
        manufacturer.setId("manufacturer");
        activeIngredient.setId("activeIngredient");
        description.setId("description");
        pEffectSelector.setId("pEffectSelector");
        tEffectSelector.setId("tEffectSelector");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Форма выпуска:"), 0, 1);
        grid.add(releaseForm, 1, 1);
        grid.add(new Label("Производитель:"), 0, 2);
        grid.add(manufacturer, 1, 2);
        grid.add(new Label("Активный ингредиент:"), 0, 3);
        grid.add(activeIngredient, 1, 3);
        grid.add(new Label("Фармакологический эффект:"), 0, 4);
        grid.add(new HBox(5, pEffectSelector, addPEffectBtn), 1, 4);
        grid.add(new Label("Терапевтический эффект:"), 0, 5);
        grid.add(new HBox(5, tEffectSelector, addTEffectBtn), 1, 5);
        grid.add(new Label("Описание:"), 0, 6);
        grid.add(description, 1, 6);

        drugDialog.getDialogPane().setContent(grid);
        drugDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        drugDialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                if (!name.getText().isEmpty() && !releaseForm.getText().isEmpty() && !manufacturer.getText().isEmpty()
                        && !activeIngredient.getText().isEmpty() && !pEffectSelector.getSelectionModel().isEmpty()
                        && !tEffectSelector.getSelectionModel().isEmpty()) {
                    return new DrugEntity(name.getText(), releaseForm.getText(), manufacturer.getText(),
                            activeIngredient.getText(), pEffectSelector.getSelectionModel().getSelectedItem(),
                            tEffectSelector.getSelectionModel().getSelectedItem(), description.getText());
                }
            }
            return null;
        });
    }

    private void createDrugstoreDialog() {
        drugstoreDialog = new Dialog<>();
        drugstoreDialog.setTitle("Новая аптека");
        drugstoreDialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextField district = new TextField();
        TextField street = new TextField();
        TextField building = new TextField();
        TextField phone = new TextField();
        TextField hours = new TextField();
        CheckBox isRoundTheClock = new CheckBox();

        name.setId("name");
        district.setId("district");
        street.setId("street");
        building.setId("building");
        phone.setId("phone");
        hours.setId("hours");
        isRoundTheClock.setId("isRoundTheClock");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Район:"), 0, 1);
        grid.add(district, 1, 1);
        grid.add(new Label("Улица:"), 0, 2);
        grid.add(street, 1, 2);
        grid.add(new Label("Дом:"), 0, 3);
        grid.add(building, 1, 3);
        grid.add(new Label("Телефон:"), 0, 4);
        grid.add(phone, 1, 4);
        grid.add(new Label("Часы работы:"), 0, 5);
        grid.add(hours, 1, 5);
        grid.add(new Label("Крулосуточная:"), 0, 6);
        grid.add(isRoundTheClock, 1, 6);

        drugstoreDialog.getDialogPane().setContent(grid);
        drugstoreDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        drugstoreDialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                if (!name.getText().isEmpty() && !district.getText().isEmpty() && !street.getText().isEmpty()
                        && !building.getText().isEmpty() && !phone.getText().isEmpty() && !hours.getText().isEmpty()) {
                    return new DrugstoreEntity(name.getText(), district.getText(), street.getText(), building.getText(),
                            Long.valueOf(phone.getText()), hours.getText(),
                            (short) (isRoundTheClock.isSelected() ? 1 : 0));
                }
            }
            return null;
        });
    }

    private void createPriceDialog() {
        priceDialog = new Dialog<>();
        priceDialog.setTitle("");
        priceDialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField cost = new TextField();
        TableView drugsTable = new TableView();
        TableView drugstoresTable = new TableView();

        double drugsTableWidth = drugsTable.getPrefHeight();
        double drugsTableHeight = drugsTable.getPrefWidth();

        double drugstoresTableWidth = drugstoresTable.getPrefHeight();
        double drugstoresTableHeight = drugstoresTable.getPrefWidth();

        drugsTable.setPrefSize(350, 200);
        drugstoresTable.setPrefSize(350, 200);

        cost.setId("cost");
        drugsTable.setId("drug");
        drugstoresTable.setId("drugstore");

        grid.add(drugsTable, 0, 0);
        grid.add(drugstoresTable, 1, 0);
        grid.add(new HBox(5, new Label("Цена:"), cost), 0, 1);

        priceDialog.getDialogPane().setContent(grid);
        priceDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        priceDialog.setResultConverter(dialogButton -> {
            drugsTable.setPrefSize(drugsTableWidth,drugsTableHeight);
            drugstoresTable.setPrefSize(drugstoresTableWidth,drugstoresTableHeight);
            if (dialogButton == ButtonType.OK) {
                if (!cost.getText().isEmpty() && !drugsTable.getSelectionModel().isEmpty()
                        && !drugstoresTable.getSelectionModel().isEmpty()) {
                    return new PriceEntity(
                            drugs.get(drugsTable.getSelectionModel().getSelectedIndex()).getDrugEntity(),
                            drugstores.get(drugstoresTable.getSelectionModel().getSelectedIndex()).getDrugstoreEntity(),
                            Long.valueOf(cost.getText()));
                }
            }
            return null;
        });
    }

    public Dialog<TherapeuticEffectEntity> getAddTEffectDialog() {
        return tEffectDialog;
    }

    public Dialog<PharmachologicEffectEntity> getAddPEffectDialog() {
        return pEffectDialog;
    }

    public Dialog<DrugEntity> getAddDrugDialog() {
        return drugDialog;
    }

    public Dialog<DrugstoreEntity> getAddDrugstoreDialog() {
        return drugstoreDialog;
    }

    public Dialog<PriceEntity> getAddPriceDialog() {
        return priceDialog;
    }


    public Dialog<TherapeuticEffectEntity> getUpdTEffectDialog(int index) {
        return tEffectDialog;
    }

    public Dialog<PharmachologicEffectEntity> getUpdPEffectDialog() {
        return pEffectDialog;
    }

    public Dialog<DrugEntity> getUpdDrugDialog(int index) {
        if (index != -1) {
            Node content = drugDialog.getDialogPane().getContent();
            DrugEntity drugEntity = drugs.get(index).getDrugEntity();

            setTextInTextControl(content, "#name", drugEntity.getName());
            setTextInTextControl(content, "#releaseForm", drugEntity.getReleaseForm());
            setTextInTextControl(content, "#manufacturer", drugEntity.getManufacturer());
            setTextInTextControl(content, "#activeIngredient", drugEntity.getActiveIngredient());
            setTextInTextControl(content, "#description", drugEntity.getDescription());
            setSelectInSelectorControl(content, "#pEffectSelector",
                    drugEntity.getPharmachologicEffect(), pharmachologicEffects);
            setSelectInSelectorControl(content, "#tEffectSelector",
                    drugEntity.getTherapeuticEffect(), therapeuticEffects);
            return drugDialog;
        }
        return null;
    }

    public Dialog<DrugstoreEntity> getUpdDrugstoreDialog(int index) {
        if (index != -1) {
            Node content = drugstoreDialog.getDialogPane().getContent();
            DrugstoreEntity drugstore = drugstores.get(index).getDrugstoreEntity();

            setTextInTextControl(content, "#name", drugstore.getName());
            setTextInTextControl(content, "#district", drugstore.getDistrict());
            setTextInTextControl(content, "#street", drugstore.getStreet());
            setTextInTextControl(content, "#building", drugstore.getBuilding());
            setTextInTextControl(content, "#phone", String.valueOf(drugstore.getPhone()));
            setTextInTextControl(content, "#hours", drugstore.getWorkingHours());
            setBooleanInCheckbox(content, "#isRoundTheClock", drugstore.getIsRoundTheClock() != 0);
            return drugstoreDialog;
        }
        return null;
    }

    public Dialog<PriceEntity> getUpdPriceDialog(int index) {
        if (index != -1) {
            Node content = priceDialog.getDialogPane().getContent();

            PriceEntity price = prices.get(index).getPriceEntity();

            setTextInTextControl(content, "#cost", String.valueOf(price.getCost()));
            setSelectInTable(content, "#drug", price.getDrug(), drugs);
            setSelectInTable(content, "#drugstore", price.getDrugstore(), drugstores);
            disableTable(content, "#drugstore");
            disableTable(content, "#drug");
            return priceDialog;
        }
        return null;
    }

    private void setTextInTextControl(Node node, String id, String text) {
        Node element = node.getScene().lookup(id);
        if (element instanceof TextInputControl) {
            ((TextInputControl) element).setText(text);
        }
    }

    private void setSelectInSelectorControl(Node node, String id, Object entity, List listEntity) {
        Node element = node.getScene().lookup(id);
        if (element instanceof ListView) {
            //TODO: подумать над этим
            for (int i = 0; i < listEntity.size(); i++) {
                if (listEntity.get(i).equals(entity)) {
                    ((ListView) element).getSelectionModel().select(i);
                }
            }
        }
    }

    private void setBooleanInCheckbox(Node node, String id, Boolean value) {
        Node element = node.getScene().lookup(id);
        if (element instanceof CheckBox) {
            ((CheckBox) element).setSelected(value);
        }
    }

    private void setSelectInTable(Node node, String id, Object entity, List listEntity) {
        Node element = node.getScene().lookup(id);
        if (element instanceof TableView) {
            //TODO: подумать над этим
            for (int i = 0; i < listEntity.size(); i++) {
                if (listEntity.get(i).equals(entity)) {
                    ((TableView) element).getSelectionModel().select(i);
                }
            }
        }
    }

    private void disableTable(Node node, String id) {
        Node element = node.getScene().lookup(id);
        if (element instanceof TableView) {
            ((TableView) element).setDisable(true);
        }
    }
}
