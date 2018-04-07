package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.services.parameters.Parameters;
import com.nc.netcracker_project.server.model.repositories.DrugstoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DrugstoreDataControlImpl extends AbstractDataControl<DrugstoreEntity, UUID> implements DrugstoreDataControl {

    @Autowired
    protected DrugstoreDataControlImpl(DrugstoreRepository repository) {
        super(repository);
    }

    @Override
    public Page<DrugstoreEntity> findByParameters(Parameters parameters, Pageable pageable) {
        DrugstoreRepository drugstoreRepository = (DrugstoreRepository) repository;
        return drugstoreRepository.findByParameters(parameters.getName(), parameters.getDistrict(),
                parameters.getStreet(), parameters.getIsRoundTheClock(), pageable);
    }
}
