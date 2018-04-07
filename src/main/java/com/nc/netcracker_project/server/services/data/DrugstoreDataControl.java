package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.services.parameters.Parameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DrugstoreDataControl extends DataControl<DrugstoreEntity, UUID> {

    Page<DrugstoreEntity> findByParameters(Parameters parameters, Pageable pageable);
}
