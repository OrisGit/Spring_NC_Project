package com.nc.netcracker_project.model.repositories;

import com.nc.netcracker_project.model.entities.PharmachologicEffectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PharmachologicEffectRepository extends CrudRepository<PharmachologicEffectEntity, UUID> {
}
