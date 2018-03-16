package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface DrugstoreRepository extends PagingAndSortingRepository<DrugstoreEntity, UUID> {
}
