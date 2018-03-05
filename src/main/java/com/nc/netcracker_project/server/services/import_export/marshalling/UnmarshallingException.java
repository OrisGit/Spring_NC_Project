package com.nc.netcracker_project.server.services.import_export.marshalling;

public class UnmarshallingException extends Exception{
    public UnmarshallingException(String message) {
        super(message);
    }

    public UnmarshallingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnmarshallingException(Throwable cause) {
        super(cause);
    }
}
