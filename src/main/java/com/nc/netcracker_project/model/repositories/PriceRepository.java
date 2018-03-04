package com.nc.netcracker_project.model.repositories;

import com.nc.netcracker_project.model.entities.PriceEntity;
import com.nc.netcracker_project.model.entities.PriceEntityPK;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<PriceEntity,PriceEntityPK> {
}
