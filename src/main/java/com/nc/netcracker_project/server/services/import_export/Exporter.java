package com.nc.netcracker_project.server.services.import_export;

public interface Exporter {
    String export(FormatType type, boolean format) throws ExportException;
}
