package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DrugRepository extends CrudRepository<DrugEntity, UUID> {
}
