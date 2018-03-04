package com.nc.netcracker_project.services.import_export.marshalling;


import com.nc.netcracker_project.services.import_export.EntityWrapper;

import java.util.logging.Logger;

public abstract class AbstractUnmarshaller {

    protected final static Logger logger = Logger.getLogger("AbstractUnmarshaller");

    public abstract EntityWrapper importFromFile(String path) throws UnmarshallingException;

    public abstract EntityWrapper importFromString(String str) throws UnmarshallingException;
}
