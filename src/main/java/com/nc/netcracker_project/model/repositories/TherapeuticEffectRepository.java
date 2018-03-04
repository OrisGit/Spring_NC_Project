package com.nc.netcracker_project.model.repositories;

import com.nc.netcracker_project.model.entities.TherapeuticEffectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TherapeuticEffectRepository extends CrudRepository<TherapeuticEffectEntity,UUID>{
}
