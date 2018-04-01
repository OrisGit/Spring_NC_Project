package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity,PriceEntityPK> {

    List<PriceEntity> findByDrug(DrugEntity drugEntity);

    Page<PriceEntity> findByDrug(DrugEntity drugEntity, Pageable pageable);

    List<PriceEntity> findByDrugstore(DrugstoreEntity drugstoreEntity);

    Page<PriceEntity> findByDrugstore(DrugstoreEntity drugstoreEntity, Pageable pageable);
}
