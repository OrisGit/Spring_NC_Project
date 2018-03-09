package com.nc.netcracker_project.desktop_rmi_client.view_controllers;

import com.nc.netcracker_project.desktop_rmi_client.util.DialogFactory;
import com.nc.netcracker_project.server.controllers.rmi.RMIController;
import com.nc.netcracker_project.server.services.import_export.ExportException;
import com.nc.netcracker_project.server.services.import_export.FormatType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ExportWindowController implements Initializable{

    private File path;
    private static Stage stage;
    private static RMIController controller;
    private static DialogFactory dialogFactory;

    @FXML
    public TextField text_path;
    @FXML
    public RadioButton rbtn_json;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text_path.setText("Пожалуйста выберите путь");
    }

    public static void setStage(Stage stage) {
        ExportWindowController.stage = stage;
    }

    public static void setController(RMIController controller) {
        ExportWindowController.controller = controller;
    }

    public static void setDialogFactory(DialogFactory dialogFactory) {
        ExportWindowController.dialogFactory = dialogFactory;
    }

    @FXML
   public void clickPathSelectBtn(){
       DirectoryChooser dc = new DirectoryChooser();
       path = dc.showDialog(stage);
       if(path==null){
           text_path.setText("Пожалуйста выберите путь");
       }else{
           text_path.setText(path.getAbsolutePath());
       }
   }

   @FXML
   public void clickExportBtn(){

       if(text_path.getText().equals("Пожалуйста выберите путь")){
           clickPathSelectBtn();
           return;
       }

       String fileExt;
       FormatType type;
       if(rbtn_json.isSelected()){
           type = FormatType.JSON;
           fileExt = ".json";
       }else{
           type = FormatType.XML;
           fileExt = ".xml";
       }

       String path = String.format("%s\\%s%s",this.path.getAbsolutePath(),"export_data",fileExt);

       try {
           export(controller.exportFromDB(type),path);
       } catch (ExportException e) {
           dialogFactory.displayError(e.getMessage());
       } catch (RemoteException e) {
           dialogFactory.displayError(e.getMessage());
       }

       stage.close();
   }

    private void export(String data, String path) {
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            dialogFactory.displayError("Ошибка записи экспортированных данных в файл по пути: "+path);
        }
    }
}
