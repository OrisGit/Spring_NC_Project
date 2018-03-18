package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.ProducerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProducerRepository extends CrudRepository<ProducerEntity, UUID> {
}
