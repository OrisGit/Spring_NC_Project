package com.nc.netcracker_project.services.import_export;

public class ExportException extends Exception {

    public ExportException(Exception e) {
        super("Ошибка экспорта",e);
    }

    public ExportException(String message) {
        super(message);
    }
}
