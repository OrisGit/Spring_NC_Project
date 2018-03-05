package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DrugStoreRepository extends CrudRepository<DrugstoreEntity, UUID> {
}
