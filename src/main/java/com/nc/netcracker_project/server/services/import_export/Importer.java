package com.nc.netcracker_project.server.services.import_export;

import org.springframework.transaction.annotation.Transactional;

public interface Importer {
    @Transactional
    void _import(String str, FormatType type) throws ImportException;
}
