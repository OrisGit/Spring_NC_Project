package com.nc.netcracker_project.model.repositories;

import com.nc.netcracker_project.model.entities.DrugEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DrugRepository extends CrudRepository<DrugEntity, UUID> {
}
