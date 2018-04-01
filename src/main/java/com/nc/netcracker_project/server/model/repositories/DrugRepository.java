package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import com.nc.netcracker_project.server.model.entities.ProducerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DrugRepository extends JpaRepository<DrugEntity, UUID> {

	List<DrugEntity> findByProducer(ProducerEntity producer);

	Page<DrugEntity> findByProducer(ProducerEntity producer, Pageable pageable);

	List<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup);

	Page<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup, Pageable pageable);

	//Page<DrugEntity> findDrugsWithParameters(Parameters parameters, Pageable pageable);
}
