package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import com.nc.netcracker_project.server.model.entities.ProducerEntity;
import com.nc.netcracker_project.server.services.parameters.Parameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DrugDataControl extends DataControl<DrugEntity, UUID> {

	List<DrugEntity> findByProducer(ProducerEntity producer);

	Page<DrugEntity> findByProducer(ProducerEntity producer, Pageable pageable);

	List<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup);

	Page<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup, Pageable pageable);

    Page<DrugEntity> findByParameters(Parameters parameters, Pageable pageable);
}
