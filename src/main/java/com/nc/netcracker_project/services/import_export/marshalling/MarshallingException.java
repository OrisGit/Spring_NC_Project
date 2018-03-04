package com.nc.netcracker_project.services.import_export.marshalling;

public class MarshallingException extends Exception{
    public MarshallingException(String message) {
        super(message);
    }

    public MarshallingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarshallingException(Throwable cause) {
        super(cause);
    }
}
