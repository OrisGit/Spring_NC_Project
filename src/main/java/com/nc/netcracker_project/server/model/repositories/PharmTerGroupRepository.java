package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PharmTerGroupRepository extends CrudRepository<PharmTerGroupEntity,UUID>{
}
