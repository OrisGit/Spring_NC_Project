package com.nc.netcracker_project.desktop_rmi_client.view_controllers;

import com.nc.netcracker_project.desktop_rmi_client.util.DialogFactory;
import com.nc.netcracker_project.server.controllers.RMIController;
import com.nc.netcracker_project.server.services.import_export.FormatType;
import com.nc.netcracker_project.server.services.import_export.ImportException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import static com.nc.netcracker_project.desktop_rmi_client.util.PropertyLoader.getProperty;

public class ImportWindowController implements Initializable {

    @FXML
    public TextField text_path;
    private File file;
    private static Stage STAGE;
    private static RMIController controller;
    private static DialogFactory dialogFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void setController(RMIController controller) {
        ImportWindowController.controller = controller;
    }

    public static void setDialogFactory(DialogFactory dialogFactory) {
        ImportWindowController.dialogFactory = dialogFactory;
    }

    public static void setStage(Stage STAGE) {
        ImportWindowController.STAGE = STAGE;
    }

    @FXML
    public void clickFileChooseBtn(){
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(STAGE);
        if(file == null){
            text_path.setText(getProperty("import_dialog.default_text_in_path"));
        }else{
            text_path.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void clickImportBtn(){
        FormatType type;
        if(getFileExtension(file).toLowerCase().equals("json")){
            type = FormatType.JSON;
        }else if(getFileExtension(file).toLowerCase().equals("xml")){
            type = FormatType.XML;
        }else{
            dialogFactory.displayError(getProperty("error.import_dialog.invalid_file_extension"));
            return;
        }

        byte[] bytes = null;
        String data = null;
        try {
            bytes = Files.readAllBytes(file.toPath());
            data = new String(bytes,"UTF-8");
        } catch (IOException e) {
            dialogFactory.displayError(getProperty("error.import_dialog.io_exception"));
            return;
        }

        try {
            controller.importInDB(data,type);
        } catch (ImportException e) {
            dialogFactory.displayError(e.getMessage());
        }

        STAGE.close();
    }


    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
