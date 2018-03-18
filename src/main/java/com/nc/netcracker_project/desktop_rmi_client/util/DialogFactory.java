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

import static com.nc.netcracker_project.desktop_rmi_client.util.TableInitializer.initializeDrugTable;
import static com.nc.netcracker_project.desktop_rmi_client.util.TableInitializer.initializeDrugstoreTable;

public class DialogFactory {

    private Dialog<PharmTerGroupEntity> pharmTerGroupDialog;
    private Dialog<ProducerEntity> producerDialog;
    private Dialog<DrugEntity> drugDialog;
    private Dialog<DrugstoreEntity> drugstoreDialog;
    private Dialog<PriceEntity> priceDialog;
    private Alert alertDialog;

    private ObservableList<PharmTerGroupEntity> pharmTerGroups;
    private ObservableList<ProducerEntity> producers;
    private ObservableList<Drug> drugs;
    private ObservableList<Drugstore> drugstores;
    private ObservableList<Price> prices;
    private Action addProducer;
    private Action addPharmTerGroup;

    public DialogFactory(ObservableList<PharmTerGroupEntity> pharmTerGroups, ObservableList<ProducerEntity> producers, ObservableList<Drug> drugs, ObservableList<Drugstore> drugstores,
                         ObservableList<Price> prices, Action addPharmTerGroup, Action addProducer){
        this.addProducer = addProducer;
        this.addPharmTerGroup = addPharmTerGroup;
        this.producers = producers;
        this.pharmTerGroups = pharmTerGroups;
        this.drugs = drugs;
        this.drugstores = drugstores;
        this.prices = prices;
        createAlertDialog();
        createDrugDialog();
        createDrugstoreDialog();
        createProducerDialog();
        createPriceDialog();
        createPharmTerGroupDialog();
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

    private void createPharmTerGroupDialog(){
        pharmTerGroupDialog = new Dialog<>();
        pharmTerGroupDialog.setTitle("Новая фармакотерапевтическая группа");
        pharmTerGroupDialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextArea description = new TextArea();

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Описание:"), 0, 1);
        grid.add(description, 1, 1);

        pharmTerGroupDialog.getDialogPane().setContent(grid);
        pharmTerGroupDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        pharmTerGroupDialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!name.getText().isEmpty()) {
                    return new PharmTerGroupEntity(name.getText(), description.getText());
                }
            }
            return null;
        });
    }

    private void createProducerDialog(){
        producerDialog = new Dialog<>();
        producerDialog.setTitle("Новый производитель");
        producerDialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextArea description = new TextArea();

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Описание:"), 0, 1);
        grid.add(description, 1, 1);

        producerDialog.getDialogPane().setContent(grid);
        producerDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        producerDialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!name.getText().isEmpty()) {
                    return new ProducerEntity(name.getText(), description.getText());
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
        TextField indicationsForUse = new TextField();
        TextField activeIngredient = new TextField();
        TextArea description = new TextArea();
        description.setPrefWidth(350);

        ListView<ProducerEntity> producerSelector = new ListView<>();
        producerSelector.setPrefSize(300, 100);

        producerSelector.setCellFactory(
                new Callback<ListView<ProducerEntity>, ListCell<ProducerEntity>>() {
                    @Override
                    public ListCell<ProducerEntity> call(ListView<ProducerEntity> param) {

                        return new ListCell<ProducerEntity>() {
                            @Override
                            public void updateItem(ProducerEntity item, boolean empty) {
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

        producerSelector.setItems(producers);

        Button addPEffectBtn = new Button("Добавить...");
        addPEffectBtn.setOnAction(event -> {
            addProducer.action();
        });

        ListView<PharmTerGroupEntity> pharmTerGroupSelector = new ListView<>();
        pharmTerGroupSelector.setPrefSize(300, 100);

        pharmTerGroupSelector.setCellFactory(
                new Callback<ListView<PharmTerGroupEntity>, ListCell<PharmTerGroupEntity>>() {
                    @Override
                    public ListCell<PharmTerGroupEntity> call(ListView<PharmTerGroupEntity> param) {

                        return new ListCell<PharmTerGroupEntity>() {
                            @Override
                            public void updateItem(PharmTerGroupEntity item, boolean empty) {
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

        pharmTerGroupSelector.setItems(pharmTerGroups);

        Button addTEffectBtn = new Button("Добавить...");
        addTEffectBtn.setOnAction(event -> {
            addPharmTerGroup.action();
        });

        name.setId("name");
        releaseForm.setId("releaseForm");
        indicationsForUse.setId("indicationsForUse");
        activeIngredient.setId("activeIngredient");
        description.setId("description");
        producerSelector.setId("producerSelector");
        pharmTerGroupSelector.setId("pharmTerGroupSelector");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Форма выпуска:"), 0, 1);
        grid.add(releaseForm, 1, 1);
        grid.add(new Label("Показания к применению:"), 0, 2);
        grid.add(indicationsForUse, 1, 2);
        grid.add(new Label("Действующее вещество:"), 0, 3);
        grid.add(activeIngredient, 1, 3);
        grid.add(new Label("Производитель:"), 0, 4);
        grid.add(new HBox(5, producerSelector, addPEffectBtn), 1, 4);
        grid.add(new Label("фармакотерапевтическая группа:"), 0, 5);
        grid.add(new HBox(5, pharmTerGroupSelector, addTEffectBtn), 1, 5);
        grid.add(new Label("Описание:"), 0, 6);
        grid.add(description, 1, 6);

        drugDialog.getDialogPane().setContent(grid);
        drugDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        drugDialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                if (!name.getText().isEmpty() && !releaseForm.getText().isEmpty() && !indicationsForUse.getText().isEmpty()
                        && !activeIngredient.getText().isEmpty() && !producerSelector.getSelectionModel().isEmpty()
                        && !pharmTerGroupSelector.getSelectionModel().isEmpty()) {
                    return new DrugEntity(name.getText(), releaseForm.getText(), indicationsForUse.getText(),
                            activeIngredient.getText(), producerSelector.getSelectionModel().getSelectedItem(),
                            pharmTerGroupSelector.getSelectionModel().getSelectedItem(), description.getText());
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
                            phone.getText(), hours.getText(),(short) (isRoundTheClock.isSelected() ? 1 : 0));
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

        drugsTable.setPrefSize(350, 200);
        drugstoresTable.setPrefSize(350, 200);

        initializeDrugstoreTable(drugstoresTable);
        initializeDrugTable(drugsTable);

        drugsTable.setItems(drugs);
        drugstoresTable.setItems(drugstores);

        cost.setId("cost");
        drugsTable.setId("drug");
        drugstoresTable.setId("drugstore");

        grid.add(drugsTable, 0, 0);
        grid.add(drugstoresTable, 1, 0);
        grid.add(new HBox(5, new Label("Цена:"), cost), 0, 1);

        priceDialog.getDialogPane().setContent(grid);
        priceDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        priceDialog.setResultConverter(dialogButton -> {
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

    public Dialog<PharmTerGroupEntity> getAddPharmTerGroupDialog() {
        createPharmTerGroupDialog();
        return pharmTerGroupDialog;
    }

    public Dialog<ProducerEntity> getAddProducerDialog() {
        createProducerDialog();
        return producerDialog;
    }

    public Dialog<DrugEntity> getAddDrugDialog() {
        createDrugDialog();
        return drugDialog;
    }

    public Dialog<DrugstoreEntity> getAddDrugstoreDialog() {
        createDrugstoreDialog();
        return drugstoreDialog;
    }

    public Dialog<PriceEntity> getAddPriceDialog() {
        createPriceDialog();
        return priceDialog;
    }


    public Dialog<PharmTerGroupEntity> getUpdPharmTerGroupDialog(int index) {
        return pharmTerGroupDialog;
    }

    public Dialog<ProducerEntity> getUpdProducerDialog() {
        return producerDialog;
    }

    public Dialog<DrugEntity> getUpdDrugDialog(int index) {
        createDrugDialog();
        if (index != -1) {
            Node content = drugDialog.getDialogPane().getContent();
            DrugEntity drugEntity = drugs.get(index).getDrugEntity();

            setTextInTextControl(content, "#name", drugEntity.getName());
            setTextInTextControl(content, "#releaseForm", drugEntity.getReleaseForm());
            setTextInTextControl(content, "#indicationsForUse", drugEntity.getIndicationsForUse());
            setTextInTextControl(content, "#activeIngredient", drugEntity.getActiveIngredient());
            setTextInTextControl(content, "#description", drugEntity.getDescription());
            setSelectInSelectorControl(content, "#producerSelector",
                    drugEntity.getProducer(), producers);
            setSelectInSelectorControl(content, "#pharmTerGroupSelector",
                    drugEntity.getPharmTerGroup(), pharmTerGroups);
            return drugDialog;
        }
        return null;
    }

    public Dialog<DrugstoreEntity> getUpdDrugstoreDialog(int index) {
        createDrugstoreDialog();
        if (index != -1) {
            Node content = drugstoreDialog.getDialogPane().getContent();
            DrugstoreEntity drugstore = drugstores.get(index).getDrugstoreEntity();

            setTextInTextControl(content, "#name", drugstore.getName());
            setTextInTextControl(content, "#district", drugstore.getDistrict());
            setTextInTextControl(content, "#street", drugstore.getStreet());
            setTextInTextControl(content, "#building", drugstore.getBuilding());
            setTextInTextControl(content, "#phone", drugstore.getPhone());
            setTextInTextControl(content, "#hours", drugstore.getWorkingHours());
            setBooleanInCheckbox(content, "#isRoundTheClock", drugstore.getIsRoundTheClock() != 0);
            return drugstoreDialog;
        }
        return null;
    }

    public Dialog<PriceEntity> getUpdPriceDialog(int index) {
        createPriceDialog();
        if (index != -1) {
            Node content = priceDialog.getDialogPane().getContent();

            PriceEntity price = prices.get(index).getPriceEntity();

            setTextInTextControl(content, "#cost", String.valueOf(price.getCost()));
            selectDrugInTable(content, "#drug", price.getDrug(), drugs);
            selectDrugstoreInTable(content, "#drugstore", price.getDrugstore(), drugstores);
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

    private void selectDrugInTable(Node node, String id, DrugEntity entity, ObservableList<Drug> listEntity) {
        Node element = node.getScene().lookup(id);
        if (element instanceof TableView) {
            for(int i=0; i<listEntity.size();i++){
                if(listEntity.get(i).getId().equals(entity.getId()))
                    ((TableView) element).getSelectionModel().select(i);
            }
        }
    }

    private void selectDrugstoreInTable(Node node, String id, DrugstoreEntity entity, ObservableList<Drugstore> listEntity) {
        Node element = node.getScene().lookup(id);
        if (element instanceof TableView) {
            for(int i=0; i<listEntity.size();i++){
                if(listEntity.get(i).getId().equals(entity.getId()))
                    ((TableView) element).getSelectionModel().select(i);
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
