package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.model.repositories.DrugstoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DrugstoreDataControlImpl extends AbstractDataControl<DrugstoreEntity, UUID> implements DrugstoreDataControl {

    @Autowired
    protected DrugstoreDataControlImpl(DrugstoreRepository repository) {
        super(repository);
    }

}
