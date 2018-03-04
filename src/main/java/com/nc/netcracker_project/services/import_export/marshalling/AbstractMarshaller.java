package com.nc.netcracker_project.services.import_export.marshalling;

import com.nc.netcracker_project.services.import_export.EntityWrapper;

import java.util.logging.Logger;

public abstract class AbstractMarshaller {

    protected boolean format;

    protected static  final Logger logger = Logger.getLogger("AbstractMarshaller");

    public AbstractMarshaller(boolean format) {
        this.format = format;
    }

    public abstract void exportToFile(String path, EntityWrapper entityWrapper) throws MarshallingException;
    public abstract String exportToString(EntityWrapper entityWrapper) throws MarshallingException;
}
